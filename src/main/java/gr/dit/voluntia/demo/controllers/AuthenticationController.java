package gr.dit.voluntia.demo.controllers;

import gr.dit.voluntia.demo.dtos.auths.SignInDto;
import gr.dit.voluntia.demo.dtos.auths.SignUpDto;
import gr.dit.voluntia.demo.models.User;
import gr.dit.voluntia.demo.services.AuthenticationRooter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle authentication requests and route them
 * through AuthenticationRooter.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationRooter authenticationRooter;

    /**
     * Unified Endpoint for Login and Signup.
     * Handles both login and signup based on the "type" parameter.
     *
     * @param type The type of operation ("login" or "signup").
     * @param req  The request body (SignInDto or SignUpDto).
     * @return ResponseEntity with the appropriate response.
     */
    @PostMapping("/{type}")
    public ResponseEntity<?> handleAuth(@PathVariable String type, @RequestBody Object req) {
        try {
            return switch (type.toLowerCase()) {
                case "login" -> handleLogin((SignInDto) req); // Handle login
                case "signup" -> handleSignup((SignUpDto) req); // Handle signup
                default -> ResponseEntity.badRequest().body("Invalid type specified. Use 'login' or 'signup'.");
            };
        } catch (ClassCastException e) {
            return ResponseEntity.badRequest().body("Invalid request format: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Handle Login Requests
     * Validates and routes the login request based on the role.
     */
    private ResponseEntity<?> handleLogin(SignInDto req) {
        try {
            if (req.getSignAs() == null || req.getSignAs().isEmpty()) {
                return ResponseEntity.badRequest().body("Role (signAs) is required for login.");
            }

            // Handle special admin key
            if ("Admin".equalsIgnoreCase(req.getSignAs()) && (req.getSpecialAdminKey() == null || req.getSpecialAdminKey().isEmpty())) {
                return ResponseEntity.badRequest().body("Admin key is required for Admin login.");
            }

            User loggedInUser = authenticationRooter.logIn(req);
            return ResponseEntity.ok(loggedInUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Handle Signup Requests
     * Validates and routes the signup request based on the role.
     */
    private ResponseEntity<?> handleSignup(SignUpDto req) {
        try {
            if (req.getRole() == null || req.getRole().isEmpty()) {
                return ResponseEntity.badRequest().body("Role is required for signup.");
            }

            // Handle special admin key
            if ("Admin".equalsIgnoreCase(req.getRole()) && (req.getSpecialAdminKey() == null || req.getSpecialAdminKey().isEmpty())) {
                return ResponseEntity.badRequest().body("Admin key is required for Admin signup.");
            }

            User newUser = authenticationRooter.signUp(req);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
