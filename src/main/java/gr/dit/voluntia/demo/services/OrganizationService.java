package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.linkers.ParticipationObj;
import gr.dit.voluntia.demo.models.*;
import gr.dit.voluntia.demo.repositories.EventRepository;
import gr.dit.voluntia.demo.repositories.OrganizationRepository;
import gr.dit.voluntia.demo.repositories.ParticipationRepository;
import gr.dit.voluntia.demo.repositories.VolunteerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private  ParticipationRepository participationRepository;
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private EventService eventService;

    /**
     * Description:
     * Retrieves an Organization object based on his id.
     * @param id of searching organization
     * @return an Organization object or null of Organization with this id does not exist
     * * */
    public Organization getOrganization(Long id) {
        Optional<Organization> org = organizationRepository.findById(id);
        return org.isPresent() ? org.get() : null;
    }

    /**
     * Description:
     * Retrieves all expired events for a specific organization.
     * @param orgId the organization ID
     * @return List of expired events
     * * */
    @Transactional
    public List<Event> getAllExpiredEvents(Long orgId) {
        return eventService.calculateExpiredEvents(orgId);
    }


    /**
     * Description:
     * Retrieves all rejected events for a specific organization.
     * @param orgId the organization ID
     * @return List of rejected events
     * * */
    @Transactional
    public List<Event> getAllRejectedEvents(Long orgId) {
        return eventRepository.findByOrganizationIdAndStatus(orgId, "rejected");
    }

    /**
     * Description:
     * Checks if an organization is rejected.
     * @param orgId the organization ID
     * @return true if the organization is rejected
     * * */
    @Transactional
    public boolean isOrganizationRejected(Long orgId) {
        Organization org = organizationRepository.findById(orgId).get();
        return org.getAccountStatus().equals("rejected") ? true : false;
    }

    /**
     * Description:
     * Checks if an organization is pending.
     * @param orgId the organization ID
     * @return true if the organization is rejected
     * * */
    @Transactional
    public boolean isOrganizationPending(Long orgId) {
        Organization org = organizationRepository.findById(orgId).get();
        return org.getAccountStatus().equals("pending") ? true : false;
    }


    /**
     * Description:
     * Saves a new event for an organization and saves it to the repository.
     * @param ev the event from the frontend
     * @param orgId the ID of the host organization
     * * */
    @Transactional
    public void saveEvent(Event ev, Long orgId) {

        // Insert only valid values to the Event object, if not fill those
        // attributes as an empty String
        ev.setName((ev.getName() == null || ev.getName().isBlank())
                    ? ""
                    : ev.getName());
        ev.setDescription((ev.getDescription() == null || ev.getDescription().isBlank())
                    ? ""
                    : ev.getDescription());
        ev.setDate((ev.getDate() == null || ev.getDate().isBlank())
                    ? ""
                    : ev.getDate());
        ev.setLocation((ev.getLocation() == null || ev.getLocation().isBlank())
                ? ""
                : ev.getLocation());
        ev.setTopic((ev.getTopic() == null || ev.getTopic().isBlank())
                ? ""
                : ev.getTopic());

        // Initialize participation List, Number of Volunteers and status
        ev.setParticipationList(new ArrayList<>());
        ev.setNumberOfVolunteers(0);
        ev.setStatus("pending");

        // Set organization ID
        ev.setOrganizationId(orgId);

        // Save to database
        eventRepository.save(ev);
    }

    /**
     * Description:
     * Returns all the active events of that organization
     * @return List<Event> the current active events
     * * */
    @Transactional
    public List<Event> getActiveEvents(Long orgId) {
        return organizationRepository.findById(orgId)
                .map(Organization::getListOfCurrentEvents)
                .orElse(new ArrayList<>());
    }

    /**
     * Description:
     * Retrieves all participations for a specific organization based on the status code.
     * @param orgId the ID of the organization
     * @param status the status that we will search
     * @return a list of {@link ParticipationObj} containing pending participations
     * * */
    @Transactional
    public List<ParticipationObj> getAllParticipationsOfAnOrgWithStatus(Long orgId, String status) {
        List<Participation> participations = participationRepository.findParticipationsForOrganizationByStatus(orgId, status);
        List<ParticipationObj> result = new ArrayList<>();

        for (Participation part : participations) {
            Event event = eventRepository.findById(part.getEventId()).orElse(null);
            Volunteer volunteer = volunteerRepository.findById(part.getVolunteerId()).orElse(null);

            if (event != null && volunteer != null) {
                ParticipationObj obj = ParticipationObj.builder()
                        .partId(part.getId())
                        .event(event)
                        .vol(volunteer)
                        .status(status)
                        .build();
                result.add(obj);
            }
        }

        return result;
    }

   /**
    * Description:
    * Approves a participation for an organization.
    * @param partId the ID of the participation
    * * */
   @Transactional
   public void approveParticipationById(Long partId) {
       Participation part = participationRepository.findById(partId)
               .orElseThrow(() -> new IllegalArgumentException("Participation not found"));
       part.setStatus("approved");
       participationRepository.save(part);

       Event ev = eventRepository.findById(part.getEventId()).orElse(null);
       if (ev != null) {
           ev.setNumberOfVolunteers(ev.getNumberOfVolunteers() + 1);
       }
       System.out.println("Participation ID: " + partId + " updated to approved.");
   }

    /**
     * Description:
     * Rejects a participation for an organization.
     * @param partId the ID of the participation
     * * */
    @Transactional
    public void rejectParticipationById(Long partId) {
        Participation part = participationRepository.findById(partId)
                .orElseThrow(() -> new IllegalArgumentException("Participation not found"));
        part.setStatus("rejected");
        participationRepository.save(part);

        System.out.println("Participation ID: " + partId + " updated to rejected.");
    }



}
