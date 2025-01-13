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

import java.util.List;

@Controller
@RequestMapping("/dashboard/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/confirm-users")
    public String confirmUsers(Model model) {
        List<Organization> pendingOrgs = adminService.getPendingOrganizations();
        List<Volunteer> pendingVols = adminService.getPendingVolunteers();

        if (pendingOrgs.isEmpty() && pendingVols.isEmpty()) {
            model.addAttribute("message", "All users have been confirmed!");
        } else {
            model.addAttribute("pendingOrgs", pendingOrgs);
            model.addAttribute("pendingVols", pendingVols);
        }
        return "dashboard/admins-func/confirm-users"; // confirm-users.html
    }

    @GetMapping("/confirm-events")
    public String confirmEvents(Model model) {
        List<Event> pendingEvents = adminService.getPendingEvents();
        if (pendingEvents.isEmpty()) {
            model.addAttribute("message", "All events have been confirmed!");
        } else {
            model.addAttribute("pendingEvents", pendingEvents);
        }
        return "dashboard/admins-func/confirm-events"; // confirm-events.html

    }


    // Approve Event
    @PostMapping("/confirm-events/approve/{id}")
    public String approveEvent(@PathVariable Long id) {
        adminService.approveEventById(id);
        return "redirect:/dashboard/admin/confirm-events";
    }

    // Reject Event
    @PostMapping("/confirm-events/reject/{id}")
    public String rejectEvent(@PathVariable Long id) {
        adminService.rejectEventById(id);
        return "redirect:/dashboard/admin/confirm-events";
    }

    // Approve Organization
    @PostMapping("/confirm-users/approve-org/{id}")
    public String approveOrganization(@PathVariable Long id) {
        adminService.approveOrganizationById(id);
        return "redirect:/dashboard/admin/confirm-users";
    }

    // Reject Organization
    @PostMapping("/confirm-users/reject-org/{id}")
    public String rejectOrganization(@PathVariable Long id) {
        adminService.rejectOrganizationById(id);
        return "redirect:/dashboard/admin/confirm-users";
    }

    // Approve Volunteer
    @PostMapping("/confirm-users/approve-vol/{id}")
    public String approveVolunteer(@PathVariable Long id) {
        adminService.approveVolunteerById(id);
        return "redirect:/dashboard/admin/confirm-users";
    }

    // Reject Volunteer
    @PostMapping("/confirm-users/reject-vol/{id}")
    public String rejectVolunteer(@PathVariable Long id) {
        adminService.rejectVolunteerById(id);
        return "redirect:/dashboard/admin/confirm-users";
    }


}
