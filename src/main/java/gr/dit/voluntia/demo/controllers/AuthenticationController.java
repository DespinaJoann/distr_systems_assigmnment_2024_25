package gr.dit.voluntia.demo.controllers;


import gr.dit.voluntia.demo.dtos.auths.SignInDto;
import gr.dit.voluntia.demo.dtos.auths.SignUpDto;
import gr.dit.voluntia.demo.models.User;
import gr.dit.voluntia.demo.services.CustomUserDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import gr.dit.voluntia.demo.services.AuthenticationRooter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * Controller to handle authentication requests and route them through AuthenticationRooter.
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationRooter authenticationRooter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService; // Χρησιμοποιούμε μόνο το CustomUserDetailsService

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Handle Login Requests
     * Validates and routes the login request based on the role.
     */

    @PostMapping("/login")
    public ResponseEntity<?> handleLogin(@RequestBody SignInDto req, HttpSession session) {

        System.out.println("Username: " + req.getUsername());
        System.out.println("Password: " + req.getPassword());
        System.out.println("Role: " + req.getSignAs());

        User loggedInUser = customUserDetailsService.findUserByUsername(req.getUsername(), req.getSignAs());
        return ResponseEntity.ok(loggedInUser);
    }


    private String getRedirectUrlBasedOnRole(String role) {
        // Επιστρέφει τη σωστή URL ανακατεύθυνσης σύμφωνα με το ρόλο
        return switch (role) {
            case "ROLE_ADMIN" -> "/auth/admin/dashboard";
            case "ROLE_VOLUNTEER" -> "/auth/volunteer/dashboard";
            case "ROLE_ORGANIZATION" -> "/auth/organization/dashboard";
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        };
    }



    /**
     * Handle Signup Requests
     * Validates and routes the signup request based on the role.
     */
    @PostMapping("/signup")
    public ResponseEntity<?> handleSignup(@RequestBody SignUpDto req, HttpSession session) {
        try {
            // Process and Create a new User
            User newUser = authenticationRooter.signUp(req);

            // Save the new user to the current session
            session.setAttribute("user", newUser);

            // Return User as JSON (for frontend usage)
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());

            // Return Error in case of error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during sign up.");
        }
    }


}


