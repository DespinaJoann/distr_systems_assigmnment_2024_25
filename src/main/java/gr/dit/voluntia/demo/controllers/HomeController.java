package gr.dit.voluntia.demo.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private HttpSession session; // Εισάγεις το session για να ελέγξεις αν ο χρήστης είναι ήδη συνδεδεμένος

    public String home () {
        // Έλεγχος αν ο χρήστης είναι ήδη συνδεδεμένος
        if (session.getAttribute("user") != null) {
            return "redirect:/dashboard"; // Αν ο χρήστης είναι συνδεδεμένος, στείλτον στο dashboard
        }
        return "index";  // Επιστρέφει την αρχική σελίδα
    }

    @GetMapping("/auth/login")
    public String login() {
        // Έλεγχος αν ο χρήστης είναι ήδη συνδεδεμένος
        if (session.getAttribute("user") != null) {
            return "redirect:/dashboard"; // Αν ο χρήστης είναι συνδεδεμένος, στείλτον στο dashboard
        }
        return "/pages/login";  // Επιστρέφει το login.html
    }


    @GetMapping("/auth/signup")
    public String signup() {
        return "/pages/signup";  // Επιστρέφει το signup.html
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/pages/dashboard";  // Επιστρέφει το dashboard.html
    }
}

