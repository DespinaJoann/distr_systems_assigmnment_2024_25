package gr.dit.voluntia.demo.controllers;

import gr.dit.voluntia.demo.links.CurrentUser;
import gr.dit.voluntia.demo.links.NewUser;
import gr.dit.voluntia.demo.models.Event;
import gr.dit.voluntia.demo.services.EventService;
import gr.dit.voluntia.demo.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard/org")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private EventService eventService;

    @PostMapping("/create-event")
    public String createEvent(@ModelAttribute Event ev, Model model) {
        try {
            if (eventService.eventExistsByName(ev.getName())) {
                model.addAttribute("message", "This event already exists.");
                return "dashboard/org"; // Stay on the same page and show the error message
            }
            CurrentUser currentUser = CurrentUser.fromSecurityContext();
            Long orgId = currentUser.getId();
            organizationService.saveEvent(ev, orgId);
            model.addAttribute("message", "Event created successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Failed to create event.");
        }
        return "redirect:/dashboard/org";
    }


    // Approve participation for a volunteer
    @PostMapping("/approve-part/{id}")
    public String approveParticipation(@PathVariable Long id) {
        organizationService.approveParticipationById(id); // Approve the volunteer's participation
        return "redirect:/dashboard/org"; // Redirect back to the dashboard
    }

    // Reject participation for a volunteer
    @PostMapping("/reject-part/{id}")
    public String rejectParticipation(@PathVariable Long id) {
        organizationService.rejectParticipationById(id); // Reject the volunteer's participation
        return "redirect:/dashboard/org"; // Redirect back to the dashboard
    }

    // Delete all rejected events for the organization
    @GetMapping("/delete-rejected-events")
    public String deleteRejectedEvents(@RequestParam Long orgId) {
        organizationService.deleteAllRejectedEvents(orgId); // Delete all rejected events for the organization
        return "redirect:/dashboard/org"; // Redirect back to the dashboard
    }

    // Delete all expired events for the organization
    @GetMapping("/delete-expired-events")
    public String deleteExpiredEvents(@RequestParam Long orgId) {
        organizationService.deleteAllExpiredEvents(orgId); // Delete all expired events for the organization
        return "redirect:/dashboard/org"; // Redirect back to the dashboard
    }
}
