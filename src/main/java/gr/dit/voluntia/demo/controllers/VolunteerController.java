package gr.dit.voluntia.demo.controllers;

import gr.dit.voluntia.demo.linkers.CurrentUser;
import gr.dit.voluntia.demo.models.Event;
import gr.dit.voluntia.demo.services.VolunteerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dashboard/vol")
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }


    /**
     * Handles the application of a volunteer to a specific event.
     * Redirects back to the events page after successful application.
     *
     * @param eventId the ID of the event to apply for
     * @return a redirection to the events list page
     */
    @PostMapping("/apply-to-event")
    public String applyToEvent(@RequestParam Long eventId, Model model) {
        // Get the current user's ID
        Long volId = CurrentUser.fromSecurityContext().getId();

        // Find the event by ID
        Event event = volunteerService.getAllConfirmedEvents()
                .stream()
                .filter(e -> e.getId().equals(eventId))
                .findFirst()
                .orElse(null);

        if (event == null) {
            model.addAttribute("error", "Event not found or no longer available.");
            return "redirect:/dashboard/vol"; // Redirect back to the events page
        }

        // Apply to the event
        volunteerService.applyToEvent(event, volId);

        // Redirect to the events page with a success message
        model.addAttribute("success", "Successfully applied to event: " + event.getName());
        return "redirect:/dashboard/vol";
    }
}
