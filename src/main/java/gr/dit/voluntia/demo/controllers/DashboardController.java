package gr.dit.voluntia.demo.controllers;

import gr.dit.voluntia.demo.dtos.auths.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/volunteer_dashboard")
    public String getVolunteerDashboard(Model model) {
        CurrentUser currentUser = CurrentUser.fromSecurityContext();
        model.addAttribute("currentUser", currentUser);
        return "volunteer_dashboard";  // Το view για τον Volunteer Dashboard
    }

    @GetMapping("/organization_dashboard")
    public String getOrganizationDashboard(Model model) {
        CurrentUser currentUser = CurrentUser.fromSecurityContext();
        model.addAttribute("currentUser", currentUser);
        return "organization_dashboard";  // Το view για τον Organization Dashboard
    }

    @GetMapping("/admin_dashboard")
    public String getAdminDashboard(Model model) {
        CurrentUser currentUser = CurrentUser.fromSecurityContext();
        model.addAttribute("currentUser", currentUser);
        return "admin_dashboard";  // Το view για τον Admin Dashboard
    }
}


