package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.requests.acts.DeleteRequest;
import gr.dit.voluntia.demo.dtos.requests.acts.LogOutRequest;
import gr.dit.voluntia.demo.dtos.requests.acts.SignInRequest;
import gr.dit.voluntia.demo.dtos.requests.acts.SignUpRequest;
import gr.dit.voluntia.demo.dtos.requests.tasks.CreateNewEventRequest;
import gr.dit.voluntia.demo.dtos.requests.tasks.DisplayProfileRequest;
import gr.dit.voluntia.demo.dtos.requests.tasks.EditProfileInfoRequest;
import gr.dit.voluntia.demo.models.*;
import gr.dit.voluntia.demo.repositories.EventRepository;
import gr.dit.voluntia.demo.repositories.OrganizationRepository;
import gr.dit.voluntia.demo.repositories.ParticipationRepository;
import gr.dit.voluntia.demo.services.blueprints.AuthenticationService;
import gr.dit.voluntia.demo.services.blueprints.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // Methods for Organization Activities
    // -> (for every single organization  -- things that can do)
    //////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Description:
     * Creates a new event for the organization.
     *
     * @param request to create new event
     * */
    public Event createEvent(
            CreateNewEventRequest request
    ) {
        // Create a new Event instance and populate it with data from the request
        Event event = new Event();
        event.setOrganizationId(request.getOrganizationId());
        event.setName(request.getName());
        event.setDescription(request.getDescription());
        event.setLocation(request.getLocation());
        event.setDate(request.getDate());
        event.setMaxNumbOfVolunteers(request.getMaxNumberOfPeople());

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
     * Reviews a volunteer's request to participate in an event.*/

    public Participation reviewVolunteerParticipation() {

        // Get All participation that have status "Pending" and The event belongs to this organization
        List<Participation> pendingParticipations = participationRepository.findPendingParticipationsForOrganization(
                organizationId
        );
        // 2. If the list is empty -> return: "Everything is up to date! ..."
        // 3. If the list is not empty -> loop over all the list and review

    }

    /**
     * Description:
     * Confirms a volunteer's participation in a specific event.
     * It is called after the reviewVolunteerParticipation method
     *
     * @param volunteer the volunteer object representing the volunteer requesting participation.
     * @param participation the participation object that keeps all the information.
     * */
    public void confirmVolunteerParticipation(Volunteer volunteer, Participation participation ) {
        // TODO: ...
    }


    // Methods for Managing itself (login/signin - delete/alter)
    //////////////////////////////////////////////////////////////////////////////////////////

    public Organization signUp(SignUpRequest request) {
        // Create a new organization instance with the
        Organization org = new Organization();
        org.setUsername(request.getUsername());
        org.setPassword(request.getPassword());
        org.setEmail(request.getEmail());
        org.setPhoneNumber(request.getPhoneNumber());
        org.setOrgName(request.getOrganizationName());
        org.setAddress(request.getAddress());
        org.setLocation(request.getLocation());
        org.setIsLoggedIn(true);
        // Save the created organization instance to the Data Base
        return organizationRepository.save(org);
    }

    @Override
    public User logIn(SignInRequest request) {
        return organizationRepository.findByUsernameAndPassword(
                request.getUsername(),
                request.getPassword()
        );
    }

    @Override
    public User logOut(LogOutRequest request) {
        Optional<Organization> org = organizationRepository.findById(request.getUserId());
        if (org.isPresent()) {
            // The Optional contains a non-null value
            org.get().setIsLoggedIn(false);
            return org.get();
        }

        return null;
    }

    @Override
    public List<String> displayProfileInfo(DisplayProfileRequest request) {
        Optional<Organization> org = organizationRepository.findById(request.getUserId());
        return org.<List<String>>map(
                value -> List.of(
                        value.toString().split(",")
                )).orElse(null);
    }

    @Override
    public User editProfileInfo(EditProfileInfoRequest request) {
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
    public User deleteAccount(DeleteRequest request) {
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


