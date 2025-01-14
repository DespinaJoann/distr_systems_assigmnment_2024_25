package gr.dit.voluntia.demo.controllers;

import gr.dit.voluntia.demo.models.Event;
import gr.dit.voluntia.demo.models.Organization;
import gr.dit.voluntia.demo.models.Volunteer;
import gr.dit.voluntia.demo.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dashboard/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    // Approve Organization
    @PostMapping("/approve-org/{id}")
    public String approveOrganization(@PathVariable Long id) {
        adminService.approveOrganizationById(id);
        return "redirect:/dashboard/admin";
    }

    // Reject Organization
    @PostMapping("/reject-org/{id}")
    public String rejectOrganization(@PathVariable Long id) {
        adminService.rejectOrganizationById(id);
        return "redirect:/dashboard/admin";
    }

    // Approve Volunteer
    @PostMapping("/approve-vol/{id}")
    public String approveVolunteer(@PathVariable Long id) {
        adminService.approveVolunteerById(id);
        return "redirect:/dashboard/admin";
    }

    // Reject Volunteer
    @PostMapping("/reject-vol/{id}")
    public String rejectVolunteer(@PathVariable Long id) {
        adminService.rejectVolunteerById(id);
        return "redirect:/dashboard/admin";
    }

    // Approve Event
    @PostMapping("/approve-event/{id}")
    public String approveEvent(@PathVariable Long id) {
        adminService.approveEventById(id);
        return "redirect:/dashboard/admin";
    }

    // Reject Event
    @PostMapping("/reject-event/{id}")
    public String rejectEvent(@PathVariable Long id) {
        adminService.rejectEventById(id);
        return "redirect:/dashboard/admin";
    }


}
