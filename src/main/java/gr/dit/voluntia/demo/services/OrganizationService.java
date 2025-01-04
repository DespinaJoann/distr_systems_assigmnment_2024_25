package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.auths.DeleteDto;
import gr.dit.voluntia.demo.dtos.auths.LogOutDto;
import gr.dit.voluntia.demo.dtos.auths.SignInDto;
import gr.dit.voluntia.demo.dtos.auths.SignUpDto;
import gr.dit.voluntia.demo.dtos.org.CreateNewEventDto;
import gr.dit.voluntia.demo.dtos.org.DisplayParticipationListsDto;
import gr.dit.voluntia.demo.dtos.glob.DisplayProfileDto;
import gr.dit.voluntia.demo.dtos.glob.EditProfileInfoDto;
import gr.dit.voluntia.demo.models.*;
import gr.dit.voluntia.demo.repositories.EventRepository;
import gr.dit.voluntia.demo.repositories.OrganizationRepository;
import gr.dit.voluntia.demo.repositories.ParticipationRepository;
import gr.dit.voluntia.demo.services.blueprints.AuthenticationService;
import gr.dit.voluntia.demo.services.blueprints.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.RequestToViewNameTranslator;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService implements UserService, AuthenticationService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private RequestToViewNameTranslator requestToViewNameTranslator;

    // Methods for Organization Activities
    // -> (for every single organization  -- things that can do)
    //////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Description:
     * Creates a new event for the organization.
     *
     * @param createDto to create new event
     * */
    public Event createEvent(
            CreateNewEventDto createDto
    ) {
        // Create a new Event instance and populate it with data from the request
        Event event = new Event();
        event.setOrganizationId(createDto.getOrganizationId());
        event.setName(createDto.getName());
        event.setDescription(createDto.getDescription());
        event.setLocation(createDto.getLocation());
        event.setDate(createDto.getDate());
        event.setMaxNumbOfVolunteers(createDto.getMaxNumberOfPeople());

        event.setParticipationList(null);    // Initialize the list as empty
        event.setStatus("Pending");          // Event is pending and sends a request to the
                                             // Admin and waits until Admin make it confirmed

        // Save the created Event to the event table
        event = eventRepository.save(event);

        // Return the created event
        return event;
    }

    /**
     * Description:
     * Reviews pending volunteer participations for a given organization and updates their status to "Accepted" or "Rejected".
     *
     * This method retrieves all "Pending" participations for the organization, evaluates them based on event criteria,
     * and updates their status. It counts the number of accepted and rejected participations and updates the provided
     * {@link DisplayParticipationListsDto} with the results.
     *
     * @param dispPartDto A {@link DisplayParticipationListsDto} containing the organization ID and participation details.
     *                    It will be updated with the review results and task completion status.
     * @return The updated {@link DisplayParticipationListsDto} with the review results and task completion flag.
     * */

    public DisplayParticipationListsDto reviewVolunteerParticipation(DisplayParticipationListsDto dispPartDto) {

        // Counters for info message visualization
        int countAccepted = 0;
        int countRejected = 0;

        // Get All participation that have status "Pending" and the event belongs to this organization
        List<Participation> pendingParticipations = participationRepository.findPendingParticipationsForOrganization(
                dispPartDto.getOrganizationId()
        );

        // If the list is empty, nothing to review
        if (pendingParticipations.isEmpty()) {
            dispPartDto.setInfoMessage("Nothing to review! Everything is up to date!");
            dispPartDto.setTaskCompleted(true);
            return dispPartDto; // This should return when there are no pending participations
        }

        // If the list is not empty -> loop over all the list and review
        for (Participation part : pendingParticipations) {
            // Find the event that connects to the participation
            Optional<Event> optEv = eventRepository.findById(part.getEventId());
            if (optEv.isPresent()) {
                Event event = optEv.get();                                 // Get the event object
                String reviewResult = reviewParticipation(part, event);    // Review the participation

                // Increment counters based on result
                if (reviewResult.equals("accepted")) {
                    countAccepted++;
                } else {
                    countRejected++;
                }

                // Save the updated participation to the repository
                participationRepository.save(part);

                // Print the result status for each event (For debugging purposes)
                System.out.println("Participation for volunteer " + part.getVolunteerId() +
                        " in event " + event.getName() + " was " + reviewResult + ".");
            }
        }

        // Update Dto with the results
        dispPartDto.setInfoMessage(
                countAccepted + " new participations accepted, " +
                countRejected + " new participations rejected, " +
                (countAccepted + countRejected) + " total participations reviewed"
        );

        dispPartDto.setTaskCompleted(true);

        return dispPartDto;
    }



    // Methods for Managing itself (login/signin - delete/alter)
    //////////////////////////////////////////////////////////////////////////////////////////

    public Organization signUp(SignUpDto request) {
        // Create a new organization instance with the
        Organization org = new Organization();
        org.setUsername(request.getUsername());
        org.setPassword(request.getPassword());
        org.setEmail(request.getEmail());
        org.setPhoneNumber(request.getPhoneNumber());
        org.setOrgName(request.getOrganizationName());
        org.setAddress(request.getAddress());
        org.setLocation(request.getLocation());
        org.setOrganizationType(request.getOrganizationType());
        org.setProfileDescription(request.getProfileDescription());
        org.setIsLoggedIn(true);
        // Save the created organization instance to the Data Base
        return organizationRepository.save(org);
    }

    @Override
    public User logIn(SignInDto request) {
        return organizationRepository.findByUsernameAndPassword(
                request.getUsername(),
                request.getPassword()
        );
    }

    @Override
    public User logOut(LogOutDto request) {
        Optional<Organization> org = organizationRepository.findById(request.getUserId());
        if (org.isPresent()) {
            // The Optional contains a non-null value
            org.get().setIsLoggedIn(false);
            return org.get();
        }

        return null;
    }

    @Override
    public List<String> displayProfileInfo(DisplayProfileDto request) {
        Optional<Organization> org = organizationRepository.findById(request.getUserId());
        return org.<List<String>>map(
                value -> List.of(
                        value.toString().split(",")
                )).orElse(null);
    }

    @Override
    public User editProfileInfo(EditProfileInfoDto request) {
        return organizationRepository.findById(request.getUserId()).map(org -> {
            org.setUsername(request.getUsername() != null ? request.getUsername() : org.getUsername());
            org.setPassword(request.getPassword() != null ? request.getPassword() : org.getPassword());
            org.setEmail(request.getEmail() != null ? request.getEmail() : org.getEmail());
            org.setOrgName(request.getOrganizationName() != null ? request.getOrganizationName() : org.getOrgName());
            org.setPhoneNumber(request.getPhoneNumber() != null ? request.getPhoneNumber() : org.getPhoneNumber());
            org.setAddress(request.getAddress() != null ? request.getAddress() : org.getAddress());
            org.setLocation(request.getLocation() != null ? request.getLocation() : org.getLocation());

            return organizationRepository.save(org);
        }).orElse(null);

    }

    @Override
    public User deleteAccount(DeleteDto request) {
        Optional<Organization> org = organizationRepository.findById(request.getUserId());
        if (org.isPresent()) {
            // The Optional contains a non-null value
            Organization currentOrg = org.get();

            // Validate password and special key before deleting for security purposes
            if (currentOrg.getPassword().equals(request.getPassword())) {

                // Delete the admin from the repository
                organizationRepository.deleteById(currentOrg.getId());
                return currentOrg;
            }
        }

        return null;
    }

    // Utility functions
    public String reviewParticipation(Participation part, Event ev) {
        // Check if the event has reached its maximum number of participants
        if (ev.getParticipationList().size() >= ev.getMaxNumbOfVolunteers()) {
            // Event is full, cannot accept more participants
            part.setStatus("Rejected");
            return "rejected";
        }

        // TODO:
        // -> Add more attributes to the Event and the Participation classes
        // Additional checks can be added here, e.g.,
        // matching event requirements with volunteer skills
        part.setStatus("Accepted");
        return "accepted";
    }
}


