package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.requests.*;
import gr.dit.voluntia.demo.models.*;
import gr.dit.voluntia.demo.repositories.OrganizationRepository;
import gr.dit.voluntia.demo.services.blueprints.AuthenticationService;
import gr.dit.voluntia.demo.services.blueprints.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService implements UserService, AuthenticationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    // Methods for Organization Activities
    // -> (for every single organization  -- things that can do)
    //////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Description:
     * Creates a new event for the organization.
     *
     * @param eventName the name of the event to be created.
     * @param eventDate the date that will take part.
     * @param location the location of the event.
     * @param maxNumberOfParticipants the maximum number of volunteers
     * @param eventDescription a description of the event.
     * */
    public Event createEvent(
            String eventName,
            LocalDateTime eventDate,
            String location,
            Integer maxNumberOfParticipants,
            String eventDescription
    ) {
        // TODO: ...
        return new Event();
    }

    /**
     * Description:
     * Reviews a volunteer's request to participate in an event.
     *
     * @param volunteer the volunteer object representing the volunteer requesting participation.
     * @param event the event object the volunteer wants to participate in.
     * */
    public Participation reviewVolunteerParticipation(Volunteer volunteer, Event event ) {
        // TODO: ...
        // if the Event is well suited for the Volunteer

        return null;
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
}
