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
