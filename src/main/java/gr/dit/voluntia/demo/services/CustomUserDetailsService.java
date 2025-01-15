package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.models.Admin;
import gr.dit.voluntia.demo.models.Organization;
import gr.dit.voluntia.demo.models.Volunteer;
import gr.dit.voluntia.demo.repositories.AdminRepository;
import gr.dit.voluntia.demo.repositories.OrganizationRepository;
import gr.dit.voluntia.demo.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;



@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;


    /**
     * Description:
     * Loads user details based on the provided username. It searches for the user 
     * in the Admin, Organization, and Volunteer repositories in this order.
     * 
     * If the user is found, a {@link CustomUserDetails} object is created with 
     * appropriate roles assigned. If no user is found, an exception is thrown.
     *
     * @param username the username of the user to be authenticated.
     * @return a {@link UserDetails} object containing user information and roles.
     * @throws UsernameNotFoundException if no user is found with the given username.
     * * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Search in Admin table
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null) {
            return new CustomUserDetails(
                    admin.getId(),
                    admin.getUsername(),
                    admin.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
        }

        // Search in Organization table
        Organization organization = organizationRepository.findByUsername(username);
        if (organization != null) {
            return new CustomUserDetails(
                    organization.getId(),
                    organization.getUsername(),
                    organization.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ORGANIZATION"))
            );
        }

        // Search in Volunteer table
        Volunteer volunteer = volunteerRepository.findByUsername(username);
        if (volunteer != null) {
            return new CustomUserDetails(
                    volunteer.getId(),
                    volunteer.getUsername(),
                    volunteer.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_VOLUNTEER"))
            );
        }

        // If user is not found
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
