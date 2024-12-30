package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.SignInRequest;
import gr.dit.voluntia.demo.dtos.SignUpRequest;
import gr.dit.voluntia.demo.exceptions.UsernameNotFoundException;
import gr.dit.voluntia.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: I think that i first must look and understand how spring security works !

@Service
public class AuthenticationService {

    // Instantiate Services
    @Autowired
    private AdminService adminService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private VolunteerService volunteerService;

    /**Description: Create new user account*/
    public User signUp(SignUpRequest request) {
        // Extract the role attribute of the request
        String role = request.getRole().toLowerCase();

        // Return the new instance (inserted to the corresponding table) otherwise throw an exception
        return switch (role) {
            case "admin" -> adminService.createAdmin(request);
            case "volunteer" -> volunteerService.createVolunteer(request);
            case "organization" -> organizationService.createNewOrganization(request);
            default -> throw new IllegalArgumentException("Invalid role: " + role);
        };
    }

    /**Description: Sign in with your existing account*/
    public User logIn(SignInRequest request) {
        String role = request.getSignAs().toLowerCase();
        String username = request.getUsername();
        String password = request.getPassword();

        // Find User based on the role
        return switch (role) {
            case "admin" -> adminService.findAdmin(request);
            case "volunteer" -> volunteerService.findVolunteer(request);
            case "organization" -> organizationService.findOrganization(request);
            default -> throw new IllegalArgumentException("Invalid role: " + role);
        };
    }

    /**Description: Delete an existing user account*/
    public User deleteAccount() {
        // TODO: ...
        return null;
    }
    // -> we will probably overload this method with email and not password


    // -> we will probably overload this method with email and not password


    /**Description: Sign out of your profile
     * - The User is signed in so the User is given as a whole object*/
    public User logOut( ) {
        // TODO: ...
        return null;
    }

    /**Description: Edit account/ profile properties and change some things there
     * - The User is signed in so the User is given as a whole object*/
    public User editProfileInfo(User user) {
        // TODO: ...
        return null;
    }

    public Boolean userExists(String username, String password) {
        // TODO: ...
        return false;
    }



}
