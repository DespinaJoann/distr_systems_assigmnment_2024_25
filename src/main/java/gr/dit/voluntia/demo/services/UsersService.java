package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.auths.UserForm;
import gr.dit.voluntia.demo.models.Admin;
import gr.dit.voluntia.demo.models.Organization;
import gr.dit.voluntia.demo.models.Volunteer;
import gr.dit.voluntia.demo.repositories.AdminRepository;
import gr.dit.voluntia.demo.repositories.OrganizationRepository;
import gr.dit.voluntia.demo.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Definition: A class that manges only for the Authentication requests based on the role of the User.
 *  - Redirects to the appropriate services.
 * */
@Service
public class UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private AdminRepository adminRepository;

    // Register Volunteer
    public void registerVolunteer(UserForm request) {
        // Ensure password is hashed before saving
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // Create a new Volunteer object and fill it with the data
        Volunteer vol = new Volunteer();
        vol.setUsername(request.getUsername());
        vol.setPassword(hashedPassword);
        vol.setEmail(request.getEmail());
        vol.setFirstName(request.getFirstName());
        vol.setLastName(request.getLastName());
        vol.setPhoneNumber(request.getPhoneNumber());
        vol.setDateOfBirth(request.getDateOfBirth());
        vol.setProfileDescription(request.getProfileDescription());
        vol.setIsLoggedIn(false);
        // Save the new volunteer to the Data Base
        volunteerRepository.save(vol);
    }

    // Register Organization
    public void registerOrganization(UserForm request) {
        // Ensure password is hashed before saving
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // Create a new organization instance with the
        Organization org = new Organization();
        org.setUsername(request.getUsername());
        org.setPassword(hashedPassword);
        org.setEmail(request.getEmail());
        org.setPhoneNumber(request.getPhoneNumber());
        org.setOrgName(request.getOrganizationName());
        org.setAddress(request.getAddress());
        org.setLocation(request.getLocation());
        org.setOrgType(request.getOrganizationType());
        org.setProfileDescription(request.getProfileDescription());
        org.setIsLoggedIn(false);
        // Save the created organization instance to the Data Base
        organizationRepository.save(org);
    }

    // Register Admin
    public void registerAdmin(UserForm request) {
        // Ensure password is hashed before saving
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        if (!uniqueAdmin()) {
            // The admin is already logged in or if the table is not empty,
            // then return null (-> the controller will display and error msg)
            throw new IllegalArgumentException("Something went wrong");
        }
        // Create a new admin
        Admin admin = new Admin();
        admin.setUsername(request.getUsername());
        admin.setPassword(hashedPassword);
        admin.setEmail(request.getEmail());
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setIsLoggedIn(false);
        // Save the new instance to the Data Base
        // Return the new object or null if something went wrong
         adminRepository.save(admin);
    }


    // Utility functions
    /**Description:
     * Helper method to check if there is already a logged-in admin or if the database is empty
     * */
    private Boolean uniqueAdmin() {
        // Check if there is already an admin logged in
        Optional<Admin> existingAdmin = Optional.ofNullable(adminRepository.findByIsLoggedInTrue());
        if (existingAdmin.isPresent()) {
            return false; // Return false if there is already a logged-in admin
        }

        // Check if the database is empty (i.e., no admins exist)
        return adminRepository.findAll().isEmpty(); // Return true if the table is empty
    }
}



