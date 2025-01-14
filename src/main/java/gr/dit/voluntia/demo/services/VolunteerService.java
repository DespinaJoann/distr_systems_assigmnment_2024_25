package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.links.ParticipationObj;
import gr.dit.voluntia.demo.models.*;
import gr.dit.voluntia.demo.repositories.EventRepository;
import gr.dit.voluntia.demo.repositories.OrganizationRepository;
import gr.dit.voluntia.demo.repositories.ParticipationRepository;
import gr.dit.voluntia.demo.repositories.VolunteerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    /**
     * Description:
     * Checks if a volunteer is rejected.
     * @param volId the volunteer ID
     * @return true if the volunteer is rejected
     * * */
    public boolean isVolunteerRejected(Long volId) {
        Volunteer vol = volunteerRepository.findById(volId).get();
        return vol.getAccountStatus().equals("rejected") ? true : false;
    }


    /**
     * Description:
     * Retrieves all confirmed events.
     * @return a list of confirmed events or an empty list if no events
     * are found
     * * */
    @Transactional
    public List<Event> getAllConfirmedEvents() {
        List<Event> confEvs = eventRepository.findAllConfirmedEvents();
        return confEvs == null ? new ArrayList<>() : confEvs;
    }


    /**
     * Description:
     *  Creates a new participation with status "pending" for a given event.
     * @param ev the event for which the volunteer wants to apply
     * @param volId the ID of the volunteer applying
     * * */
    public void applyToEvent(Event ev, Long volId) {
        // Get today's date and parse it to string with patter YYYY-MM-DD
        LocalDate currDate = LocalDate.now();
        String applyDate = currDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Create a new participation object
        Participation part = new Participation();
        part.setEventId(ev.getId());
        part.setVolunteerId(volId);
        part.setDate(applyDate);
        part.setStatus("pending");

        // Save the new participation to the repository
        participationRepository.save(part);
    }


    /**
     * Description:
     * Retrieves all participations with a specific status for a given volunteer.
     * @param volId  the ID of the volunteer
     * @param status the status of the participation (e.g., "accepted", "rejected", "pending")
     * @return a list of participations matching the status or an empty list if none are found
     * * */
    public List<ParticipationObj> getAllParticipationWithStatus(Long volId, String status) {
        Volunteer vol = volunteerRepository.findById(volId).orElse(null);
        if (vol == null) {
            return new ArrayList<>();
        }

        // Filter participations locally based on the status
        List<Participation> filteredParticipations = new ArrayList<>();
        for (Participation participation : vol.getListOfParticipation()) {
            if (status.equals(participation.getStatus())) {
                filteredParticipations.add(participation);
            }
        }
        return parseToParticipationObjListWithStatus(filteredParticipations, status);
    }

    /**
     * Description:
     * Deletes all rejected participations of a given volunteer.
     * @param volId the ID of the volunteer
     * * */
    public void deleteAllRejectedParticipation(Long volId) {
        // Retrieve volunteer object by their ID
        Volunteer vol = volunteerRepository.findById(volId).orElse(null);
        if (vol == null) {
            throw new IllegalArgumentException("Volunteer not found with ID: " + volId);
        }

        // Filter rejected participations locally
        List<Participation> rejectedParts = new ArrayList<>();
        Iterator<Participation> iterator = vol.getListOfParticipation().iterator();

        while (iterator.hasNext()) {
            Participation participation = iterator.next();
            if ("rejected".equals(participation.getStatus())) {
                rejectedParts.add(participation);
                iterator.remove(); // Remove from local list
            }
        }

        // Delete rejected participations from the database
        participationRepository.deleteAll(rejectedParts);

        // Save the updated volunteer to reflect the changes in the local list
        volunteerRepository.save(vol);
    }

    /**
     * Description:
     * Converts a List of Participation to a list of ParticipationObj
     * @param partList the list of participation
     * @param status the providing status
     * * */
    private List<ParticipationObj> parseToParticipationObjListWithStatus (List<Participation> partList, String status) {
        List<ParticipationObj> result = new ArrayList<>();

        for (Participation part : partList) {
            Event ev = eventRepository.findById(part.getEventId()).orElse(null);
            Volunteer vol = volunteerRepository.findById(part.getVolunteerId()).orElse(null);
            Organization org = organizationRepository.findById(part.getOrganizationId()).orElse(null);

            if (ev != null && vol != null && org != null) {
                ParticipationObj obj = ParticipationObj.builder()
                        .partId(part.getId())
                        .event(ev)
                        .vol(vol)
                        .org(org)
                        .status(status)
                        .build();
                result.add(obj);
            }
        }

        return result;
    }

}



