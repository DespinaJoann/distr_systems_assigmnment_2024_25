package gr.dit.voluntia.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login/vol")
    public String loginAsVolunteer(@RequestParam String username, @RequestParam String password) {
        // Ανάλογη λογική για το login του Volunteer
        return "redirect:/volunteer_dashboard";
    }

    @PostMapping("/login/org")
    public String loginAsOrganization(@RequestParam String username, @RequestParam String password) {
        // Ανάλογη λογική για το login της Organization
        return "redirect:/organization_dashboard";
    }

    @PostMapping("/login/admin")
    public String loginAsAdmin(@RequestParam String username, @RequestParam String password) {
        // Ανάλογη λογική για το login του Admin
        return "redirect:/admin_dashboard";
    }
}
