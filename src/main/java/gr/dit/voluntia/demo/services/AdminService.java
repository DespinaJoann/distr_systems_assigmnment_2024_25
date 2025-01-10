package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.admins.ConfirmEventsDto;
import gr.dit.voluntia.demo.dtos.admins.ConfirmUserDto;
import gr.dit.voluntia.demo.dtos.auths.DeleteDto;
import gr.dit.voluntia.demo.dtos.glob.DisplayProfileDto;
import gr.dit.voluntia.demo.dtos.glob.EditProfileInfoDto;
import gr.dit.voluntia.demo.models.*;
import gr.dit.voluntia.demo.repositories.AdminRepository;
import gr.dit.voluntia.demo.repositories.EventRepository;
import gr.dit.voluntia.demo.repositories.OrganizationRepository;
import gr.dit.voluntia.demo.repositories.VolunteerRepository;
import gr.dit.voluntia.demo.services.blueprints.UserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// NOTE: In this class in each method, when the result is null means failure
// The failure or the success response will be voiced from the Controller

@Service
public class AdminService implements UserSettings {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Methods for managing other Users
    //////////////////////////////////////////////////////////////////////////////////////////

    /**Description:
     * Approve new user and activate their profile */
    public ConfirmUserDto confirmUsers(ConfirmUserDto confdto) {
        // Counters for info message visualization
        int countAcceptedOrgs = 0;
        int countAcceptedVols = 0;
        int countRejectedOrgs = 0;
        int countRejectedVols = 0;

        // Get All Organizations and Volunteers with account status = "pending"
        List<Organization> pendingOrganizations = organizationRepository.findAllPendingOrganizations();
        List<Volunteer> pendingVolunteers = volunteerRepository.findAllPendingVolunteers();

        if (pendingOrganizations.isEmpty() && pendingVolunteers.isEmpty()) {
            confdto.setNothingToUpdate(true);
            return confdto;
        }
        // Process pending organizations
        for (Organization org : pendingOrganizations) {
            if(approveOrg(org)) {
                org.setAccountStatus("Approved");
                countAcceptedOrgs ++;
            } else {
                org.setAccountStatus("Rejected");
                countRejectedOrgs++;
            }
            organizationRepository.save(org); // Save updated organization
        }

        // Process pending volunteers
        for (Volunteer vol : pendingVolunteers) {
            if(approveVol(vol)) {
                vol.setAccountStatus("Approved");
                countAcceptedVols ++;
            } else {
                vol.setAccountStatus("Rejected");
                countRejectedVols++;
            }
            volunteerRepository.save(vol); // Save updated volunteer
        }

        // Fill the ConfitmUserDto object and return it
        confdto.setAcceptedOrgs(countAcceptedOrgs);
        confdto.setAcceptedVols(countAcceptedVols);
        confdto.setRejectedOrgs(countRejectedOrgs);
        confdto.setRejectedVols(countRejectedVols);
        confdto.setUpdatedOrganizations(pendingOrganizations);
        confdto.setUpdatedVolunteers(pendingVolunteers);
        confdto.setNothingToUpdate(false);

        return confdto;
    }



    /**Description:
     * Approve new event and change its status*/
    public ConfirmEventsDto confirmEvent (ConfirmEventsDto confdto) {
        // Initialize counters
        int countAcceptedEvents = 0;
        int countRejectedEvents = 0;

        // Get All Events with account status = "pending"
        List<Event> pendingEvents = eventRepository.findAllPendingEvents();

        // If there is no pending event
        if (pendingEvents.isEmpty()) {
            confdto.setNothingToUpdate(true);
            return confdto;
        }

        // Process pending events
        for (Event ev : pendingEvents) {
            if (approveEvent(ev)) {
                ev.setStatus("Approved");
                countAcceptedEvents ++;
            } else {
                ev.setStatus("Rejected");
                countRejectedEvents++;
            }
            eventRepository.save(ev);           // Save updated event
        }

        // Update ConfirmEventDto object
        confdto.setAcceptedEvents(countAcceptedEvents);
        confdto.setRejectedEvents(countRejectedEvents);
        confdto.setUpdatedEvents(pendingEvents);
        confdto.setNothingToUpdate(false);

        return confdto;
    }


    // TODO:
    /// ///////////////////////////////////////////////////////////////

    @Override
    public User deleteAccount(DeleteDto request) {
        // Retrieve the admin by ID and validate the provided password and special key
        Optional<Admin> admin = adminRepository.findById(request.getUserId());
        if (admin.isPresent()) {
            // The Optional contains a non-null value
            Admin currentAdmin = admin.get();

            // Ensure the provided password and special admin key match before deleting
            if (currentAdmin.getPassword().equals(request.getPassword())) {

                // If validated, delete the admin from the repository and return the deleted admin
                adminRepository.deleteById(currentAdmin.getId());
                return currentAdmin;
            }
        }
        // Return null if no admin is found or the credentials don't match
        return null;
    }
    // Business logic for User actions
    @Override
    public List<String> displayProfileInfo(DisplayProfileDto request) {
        // Retrieve the admin's profile information and return it as a list of strings
        Optional<Admin> admin = adminRepository.findById(request.getUserId());
        return admin.<List<String>>map(
                value -> List.of(
                        value.toString().split(",")
                )).orElse(null); // Return null if no admin is found
    }

    @Override
    public User editProfileInfo(EditProfileInfoDto request) {
        // Retrieve the admin by ID and update their profile information based on the provided request
        return adminRepository.findById(request.getUserId()).map(admin -> {
            admin.setUsername(request.getUsername() != null ? request.getUsername() : admin.getUsername());
            admin.setPassword(request.getPassword() != null ? request.getPassword() : admin.getPassword());
            admin.setEmail(request.getEmail() != null ? request.getEmail() : admin.getEmail());
            admin.setFirstName(request.getFirstName() != null ? request.getFirstName() : admin.getFirstName());
            admin.setLastName(request.getLastName() != null ? request.getLastName() : admin.getLastName());
            // Save the updated admin profile and return it
            return adminRepository.save(admin);
        }).orElse(null); // Return null if no admin is found

    }

    /// ///////////////////////////////////////////////////////////////




    // Utility function
    private Boolean approveOrg(Organization org) {
        // Check if organization is unique to the database
        boolean isUnique = organizationRepository.findByOrgName(
                org.getOrgName()
        );

        if (!isUnique) {
            // Delete duplicate organization
            // TODO: call a function that sends a message to this Org
            organizationRepository.delete(org);
            return false;
        }
        // Check for bad words in profile description or info
        String profDescr = org.getProfileDescription();
        String orgInfo = org.getOrgType();
        if (containsEvilContent(profDescr) || containsEvilContent(orgInfo)) {
            // Delete organizations with evil content
            // TODO: call a function that sends a message to this Org
            organizationRepository.delete(org);
            return false;
        }

        //  Check if address and  location exists
        return  org.getAddress() != null
                && !org.getAddress().isBlank()
                && org.getLocation() != null; // Missing address or location
    }

    private Boolean approveVol(Volunteer vol) {
        // Check if volunteer is unique to the database
        boolean isUnique = volunteerRepository.findByEmail(vol.getEmail()).isEmpty();
        if (!isUnique) {
            // Delete duplicate volunteer
            // TODO: call a function that sends a message to this Org
            volunteerRepository.delete(vol);
            return false;
        }

        // Check if age is  not valid (>= 16)
        if (vol.calculateAge() < 16) {
            // Delete volunteers that are not to the desired age group
            // TODO: call a function that sends a message to this Org
            volunteerRepository.delete(vol);
            return false;
        }

        // Check for bad words in profile description
        String profDescr = vol.getProfileDescription();
        if (containsEvilContent(profDescr)) {
            // Delete volunteers with evil content
            // TODO: call a function that sends a message to this Org
            volunteerRepository.delete(vol);
            return false;
        }
        return true;
    }

    private Boolean approveEvent(Event ev) {
        // Check if volunteer is unique to the database
        boolean isUnique = eventRepository.findAllByName(ev.getName()).isEmpty();
        if (!isUnique) {
            // Delete duplicate events
            // TODO: call a function that sends a message to the Org of that event
            eventRepository.delete(ev);
            return false;
        }

        // Check event description and name contains evil content or is empty
        if (containsEvilContent(ev.getDescription()) || containsEvilContent(ev.getName())
             || ev.getDescription().isBlank() || ev.getName().isBlank()) {
            // Delete events with evil content or empty content
            // TODO: call a function that sends a message to this Org of that event
            eventRepository.delete(ev);
            return false;
        }

        // Check if the location is valid
        if (!validLocation(ev.getLocation())) {
            // Delete events with not valid location
            // TODO: call a function that sends a message to this Org of that event
            eventRepository.delete(ev);
            return false;
        }

        return true;
    }

    /**Description:
     * Check if a string contains any bad words from a predefined list. */
    private Boolean containsEvilContent(String text) {
        if (text == null || text.isBlank()) {
            return false;
        }

        List<String> evilContent = List.of(
                "violence", "hate", "racist", "terror", "drugs",
                "weapon", "abuse", "exploit", "illegal", "scam",
                "fraud", "nudity", "porn", "kill", "steal",
                "slavery", "harass", "bully", "curse", "swear",
                "sex", "alcohol", "gamble", "discriminate",
                "inappropriate", "extremist", "offend", "unethical"
        );

        for (String evilCont : evilContent) {
            if (text.toLowerCase().contains(evilCont)) {
                return true;
            }
        }
        return false;
    }

    /**Description:
     * Helper method to validate event location.*/
    private boolean validLocation(String location) {
        // TODO: make it better
        return location != null && !location.isEmpty();
    }
}





