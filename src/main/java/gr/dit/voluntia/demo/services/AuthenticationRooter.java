package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.requests.LogOutRequest;
import gr.dit.voluntia.demo.dtos.requests.SignInRequest;
import gr.dit.voluntia.demo.dtos.requests.SignUpRequest;
import gr.dit.voluntia.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Definition: A class that manges only for the Authentication requests based on the role of the User.
 *  - Redirects to the appropriate services.
 * */
@Service
public class AuthenticationRooter {

    // Instantiate the services
    @Autowired
    private AdminService adminService;
    @Autowired
    public OrganizationService organizationService;
    @Autowired
    private VolunteerService volunteerService;

    /**Description: Create new user account*/
    public User signUp(SignUpRequest request) {
        // Extract the role attribute of the request
        String role = request.getRole().toLowerCase();

        // Return the new instance (inserted to the corresponding table) otherwise
        // throw an exception
        return switch (role) {
            case "admin" -> adminService.signUp(request);
            case "volunteer" -> volunteerService.signUp(request);
            case "organization" -> organizationService.signUp(request);
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
            case "admin" -> adminService.logIn(request);
            case "volunteer" -> volunteerService.logIn(request);
            case "organization" -> organizationService.logIn(request);
            default -> throw new IllegalArgumentException("Invalid role: " + role);
        };
    }

}
