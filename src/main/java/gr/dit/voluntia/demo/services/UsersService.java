package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.dtos.auths.NewUser;
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
    public void saveVolunteer(NewUser request) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        Volunteer vol = new Volunteer();
        vol.setUsername(request.getUsername());
        vol.setPassword(hashedPassword);
        vol.setEmail(request.getEmail());
        vol.setFirstName(request.getFirstName());
        vol.setLastName(request.getLastName());
        vol.setPhoneNumber(request.getPhoneNumber());
        vol.setDateOfBirth(request.getDateOfBirth());
        vol.setProfileDescription(request.getProfileDescription());
        vol.setRole("VOLUNTEER");
        vol.setIsLoggedIn(false);
        volunteerRepository.save(vol);
    }

    // Register Organization
    public void saveOrganization(NewUser request) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        Organization org = new Organization();
        org.setUsername(request.getUsername());
        org.setPassword(hashedPassword);
        org.setEmail(request.getEmail());
        org.setPhoneNumber(request.getPhoneNumber());
        org.setOrgName(request.getOrgName());
        org.setAddress(request.getAddress());
        org.setLocation(request.getLocation());
        if (request.getOrgType() == null || request.getOrgType().trim().isEmpty()) {
            org.setOrgType("general");
        } else {
            org.setOrgType(request.getOrgType());
        }
        org.setProfileDescription(request.getProfileDescription());
        org.setRole("ORGANIZATION");
        org.setIsLoggedIn(false);
        organizationRepository.save(org);
    }


    // Register Admin
    public void saveAdmin(NewUser request) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        if (!uniqueAdmin()) {
            throw new IllegalArgumentException("An admin already exists.");
        }
        Admin admin = new Admin();
        admin.setUsername(request.getUsername());
        admin.setPassword(hashedPassword);
        admin.setEmail(request.getEmail());
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setRole("ADMIN");
        admin.setIsLoggedIn(false);
        adminRepository.save(admin);
    }

    private Boolean uniqueAdmin() {
        Optional<Admin> existingAdmin = Optional.ofNullable(adminRepository.findByIsLoggedInTrue());
        return existingAdmin.isEmpty() && adminRepository.findAll().isEmpty();
    }

    public void updateProfile(NewUser updatedUser) {
        switch (updatedUser.getRole()) {
            case "VOLUNTEER" , "ROLE_VOLUNTEER":
                // Handle Volunteer Profile Update
                Volunteer volunteer = volunteerRepository.findByUsername(updatedUser.getUsername());
                volunteer.setFirstName(updatedUser.getFirstName());
                volunteer.setLastName(updatedUser.getLastName());
                volunteer.setEmail(updatedUser.getEmail());
                volunteerRepository.save(volunteer);
                break;

            case "ORGANIZATION", "ROLE_ORGANIZATION":
                // Handle Organization Profile Update
                Organization organization = organizationRepository.findByUsername(updatedUser.getUsername());
                organization.setAddress(updatedUser.getAddress());
                organization.setLocation(updatedUser.getLocation());
                organization.setOrgType(updatedUser.getOrgType());
                organization.setEmail(updatedUser.getEmail());
                organizationRepository.save(organization);
                break;

            case "ADMIN", "ROLE_ADMIN":
                // Handle Admin Profile Update
                Admin admin = adminRepository.findByUsername(updatedUser.getUsername());
                admin.setFirstName(updatedUser.getFirstName());
                admin.setLastName(updatedUser.getLastName());
                admin.setEmail(updatedUser.getEmail());
                adminRepository.save(admin);
                break;

            default:
                throw new IllegalArgumentException("Invalid role");
        }
    }

}



