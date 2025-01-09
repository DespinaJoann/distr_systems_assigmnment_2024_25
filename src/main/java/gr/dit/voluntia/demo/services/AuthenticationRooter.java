package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.auths.SignInDto;
import gr.dit.voluntia.demo.dtos.auths.SignUpDto;
import gr.dit.voluntia.demo.models.Admin;
import gr.dit.voluntia.demo.models.Organization;
import gr.dit.voluntia.demo.models.User;
import gr.dit.voluntia.demo.models.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public User signUp(SignUpDto request) {
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Log in method checks credentials based on role and username
    public User logIn(SignInDto request) {
        String role = request.getSignAs().toLowerCase();
        String username = request.getUsername();
        String password = request.getPassword();

        switch (role) {
            case "admin":
                Admin admin = adminService.logIn(username);
                if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
                    return admin;
                }
                break;

            case "volunteer":
                Volunteer volunteer = volunteerService.logIn(username);
                if (volunteer != null && passwordEncoder.matches(password, volunteer.getPassword())) {
                    return volunteer;
                }
                break;

            case "organization":
                Organization organization = organizationService.logIn(username);
                if (organization != null && passwordEncoder.matches(password, organization.getPassword())) {
                    return organization;
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }

        return null; // Invalid credentials
    }

}



