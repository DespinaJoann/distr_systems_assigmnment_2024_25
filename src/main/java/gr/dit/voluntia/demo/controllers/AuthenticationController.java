package gr.dit.voluntia.demo.controllers;

import gr.dit.voluntia.demo.dtos.auths.SignInDto;
import gr.dit.voluntia.demo.dtos.auths.SignUpDto;
import gr.dit.voluntia.demo.models.User;
import gr.dit.voluntia.demo.services.AuthenticationRooter;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller to handle authentication requests and route them through AuthenticationRooter.
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationRooter authenticationRooter;

    /**
     * Handle Login Requests
     * Validates and routes the login request based on the role.
     */
    @PostMapping("/login")
    public String handleLogin(@ModelAttribute SignInDto req, Model model) {
        try {
            if (req.getSignAs() == null || req.getSignAs().isEmpty()) {
                model.addAttribute("error", "Role (signAs) is required for login.");
                return "login"; // Επιστρέφει στην σελίδα login
            }

            // Handle special admin key
            if ("Admin".equalsIgnoreCase(req.getSignAs()) && (req.getSpecialAdminKey() == null || req.getSpecialAdminKey().isEmpty())) {
                model.addAttribute("error", "Admin key is required for Admin login.");
                return "login"; // Επιστρέφει στην σελίδα login
            }

            User loggedInUser = authenticationRooter.logIn(req);
            model.addAttribute("user", loggedInUser);
            return "dashboard"; // Επιστρέφει στην σελίδα του dashboard

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login"; // Επιστρέφει στην σελίδα login με το μήνυμα σφάλματος
        }
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


