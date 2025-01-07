package gr.dit.voluntia.demo.services;

import gr.dit.voluntia.demo.models.User;
import gr.dit.voluntia.demo.repositories.AdminRepository;
import gr.dit.voluntia.demo.repositories.OrganizationRepository;
import gr.dit.voluntia.demo.repositories.VolunteerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    // Repositories
    private final AdminRepository adminRepository;
    private final VolunteerRepository volunteerRepository;
    private final OrganizationRepository organizationRepository;

    public CustomUserDetailsService(AdminRepository adminRepository, VolunteerRepository volunteerRepository, OrganizationRepository organizationRepository) {
        this.adminRepository = adminRepository;
        this.volunteerRepository = volunteerRepository;
        this.organizationRepository = organizationRepository;
    }

    public User findUserByUsername(String username, String role) {

        // Create a new user instance
        User user = null;

        System.out.println("Trying to authenticate user: " + username);
        user = adminRepository.findByUsername(username);
        if (user != null) {
            role = "ROLE_ADMIN";
            logger.info("User found in Admin repository: {}", username);
        }

        if (user == null) {
            user = volunteerRepository.findByUsername(username);
            if (user != null) {
                role = "ROLE_VOLUNTEER";
                logger.info("User found in Volunteer repository: {}", username);
            }
        }

        if (user == null) {
            user = organizationRepository.findByUsername(username);
            if (user != null) {
                role = "ROLE_ORGANIZATION";
                logger.info("User found in Organization repository: {}", username);
            }
        }

        // Αν δεν βρεθεί ο χρήστης
        if (user == null) {
            logger.error("User not found in any repository: {}", username);
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Attempting to authenticate user: {}", username);

        // Εκτύπωση για έλεγχο του username
        System.out.println("Trying to authenticate user: " + username);

        User user = null;
        String role = null;

        // Έλεγχος στα Repositories
        user = adminRepository.findByUsername(username);
        if (user != null) {
            role = "ROLE_ADMIN";
            logger.info("User found in Admin repository: {}", username);
        }

        if (user == null) {
            user = volunteerRepository.findByUsername(username);
            if (user != null) {
                role = "ROLE_VOLUNTEER";
                logger.info("User found in Volunteer repository: {}", username);
            }
        }

        if (user == null) {
            user = organizationRepository.findByUsername(username);
            if (user != null) {
                role = "ROLE_ORGANIZATION";
                logger.info("User found in Organization repository: {}", username);
            }
        }

        // Αν δεν βρεθεί ο χρήστης
        if (user == null) {
            logger.error("User not found in any repository: {}", username);
            throw new UsernameNotFoundException("User not found: " + username);
        }

        logger.info("User authenticated successfully: {} with role: {}", username, role);

        // Επιστροφή του UserDetails
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),  // Επιστροφή του κρυπτογραφημένου password
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }


}
