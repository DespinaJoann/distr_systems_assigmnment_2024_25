package gr.dit.voluntia.demo.controllers;

import gr.dit.voluntia.demo.dtos.auths.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        CurrentUser currentUser = CurrentUser.fromSecurityContext();
        model.addAttribute("currentUser", currentUser);
        System.out.println(currentUser.toString());
        return "dashboard/admin"; // admin.html
    }

    @GetMapping("/org")
    public String organizationDashboard(Model model) {
        CurrentUser currentUser = CurrentUser.fromSecurityContext();
        model.addAttribute("currentUser", currentUser);
        System.out.println(currentUser.toString());
        return "dashboard/org"; // org.html
    }

    @GetMapping("/vol")
    public String volunteerDashboard(Model model) {
        CurrentUser currentUser = CurrentUser.fromSecurityContext();
        model.addAttribute("currentUser", currentUser);
        System.out.println(currentUser.toString());
        return "dashboard/vol"; // vol.html
    }
}
