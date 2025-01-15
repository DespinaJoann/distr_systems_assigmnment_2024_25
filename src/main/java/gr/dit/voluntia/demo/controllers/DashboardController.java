package gr.dit.voluntia.demo.controllers;

import gr.dit.voluntia.demo.linkers.CurrentUser;
import gr.dit.voluntia.demo.linkers.ParticipationObj;
import gr.dit.voluntia.demo.models.Event;
import gr.dit.voluntia.demo.models.Organization;
import gr.dit.voluntia.demo.models.Volunteer;
import gr.dit.voluntia.demo.services.AdminService;
import gr.dit.voluntia.demo.services.OrganizationService;
import gr.dit.voluntia.demo.services.VolunteerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final AdminService adminService;
    private final OrganizationService organizationService;
    private final VolunteerService volunteerService;

    public DashboardController(AdminService adminService, OrganizationService organizationService, VolunteerService volunteerService) {
        this.adminService = adminService;
        this.organizationService = organizationService;
        this.volunteerService = volunteerService;
    }



    @GetMapping("/vol")
    public String volunteerDashboard(Model model) {
        CurrentUser currentUser = CurrentUser.fromSecurityContext();
        model.addAttribute("currentUser", currentUser);

        // Check if the volunteer is rejected by the admin
        if (volunteerService.isVolunteerPending(currentUser.getId())) {
            return "errors/user-not-approved";
        }
        if (volunteerService.isVolunteerRejected(currentUser.getId())) {
            return "errors/user-rejected";
        }

        // Fetch the organization’s data (volunteer participation statuses, events, etc.)
        List<Event> allConfirmedEvs = volunteerService.getAllConfirmedEvents();
        List<ParticipationObj> rejectedParts = volunteerService.getAllParticipationWithStatus(currentUser.getId(),"rejected");
        List<ParticipationObj> acceptedParts = volunteerService.getAllParticipationWithStatus(currentUser.getId(),"accepted");
        List<ParticipationObj> pendingParts = volunteerService.getAllParticipationWithStatus(currentUser.getId(),"pending");

        // Add data to the model
        model.addAttribute("allConfirmedEvs", allConfirmedEvs);
        model.addAttribute("rejectedParts", rejectedParts);
        model.addAttribute("acceptedParts", acceptedParts);
        model.addAttribute("pendingParts", pendingParts);

        // Messages to display if the respective lists are empty
        if (allConfirmedEvs.isEmpty()) {
            model.addAttribute("message", "There is no active event!");
        }

        if (rejectedParts.isEmpty()) {
            model.addAttribute("message", "There is no rejected participation.");
        }

        if (acceptedParts.isEmpty()) {
            model.addAttribute("message", "There is no accepted participation.");
        }
        if (rejectedParts.isEmpty()) {
            model.addAttribute("message", "There is no rejected participation.");
        }

        System.out.println(currentUser.toString());
        return "dashboard/vol"; // vol.html
    }


    @GetMapping("/org")
    public String organizationDashboard(Model model) {
        // Get the current logged-in user
        CurrentUser currentUser = CurrentUser.fromSecurityContext();
        model.addAttribute("currentUser", currentUser);

        // Check if the organization is rejected by the admin
        if (organizationService.isOrganizationRejected(currentUser.getId())) {
            return "errors/user-rejected";
        }
        if (organizationService.isOrganizationPending(currentUser.getId())) {
            return "errors/user-not-approved";
        }

        // Fetch the organization’s data (volunteer participation statuses, events, etc.)
        List<ParticipationObj> pendingParts = organizationService.getAllParticipationsOfAnOrgWithStatus(currentUser.getId(), "pending");
        List<Event> rejectedEvents = organizationService.getAllRejectedEvents(currentUser.getId());
        List<Event> activeEvents = organizationService.getActiveEvents(currentUser.getId());
        List<Event> expiredEvents = organizationService.getAllExpiredEvents(currentUser.getId());

        // Add data to the model
        model.addAttribute("pendingParts", pendingParts);
        model.addAttribute("rejectedEvents", rejectedEvents);
        model.addAttribute("activeEvents", activeEvents);
        model.addAttribute("expiredEvents", expiredEvents);
        model.addAttribute("event", new Event());

        // Messages to display if the respective lists are empty
        if (pendingParts.isEmpty()) {
            model.addAttribute("message", "No participation is pending!");
        }

        if (rejectedEvents.isEmpty()) {
            model.addAttribute("message", "No rejected events.");
        }

        if (activeEvents.isEmpty()) {
            model.addAttribute("message", "No active events.");
        }

        if (expiredEvents.isEmpty()) {
            model.addAttribute("message", "No expired events.");
        }

        return "dashboard/org"; // Return the org dashboard page (Thymeleaf template)
    }



    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        // Retrieve current user from the security context
        CurrentUser currentUser = CurrentUser.fromSecurityContext();
        model.addAttribute("currentUser", currentUser);

        // Pending organizations, volunteers and events
        List<Organization> pendingOrgs = adminService.getPendingOrganizations();
        List<Volunteer> pendingVols = adminService.getPendingVolunteers();
        List<Event> pendingEvents = adminService.getPendingEvents();
        Event newEvent = new Event();

        // If the resulting lists are null, initialize them as empty
        if (pendingOrgs == null) pendingOrgs = new ArrayList<>();
        if (pendingVols == null) pendingVols = new ArrayList<>();
        if (pendingEvents == null) pendingEvents = new ArrayList<>();

        model.addAttribute("pendingOrgs", pendingOrgs);
        model.addAttribute("pendingVols", pendingVols);
        model.addAttribute("pendingEvents", pendingEvents);
        model.addAttribute("event", newEvent);
        // Message for no pending items
        if (pendingOrgs.isEmpty() && pendingVols.isEmpty() && pendingEvents.isEmpty()) {
            model.addAttribute("message", "All users and events have been confirmed!");
        }

        return "dashboard/admin"; // admin.html
    }

  /**
   // Edit Profile Form for the respective dashboard
   @GetMapping("/edit-profile")
   public String showEditProfileForm(Model model) {
   // Get the current user from the session context
   CurrentUser currentUser = CurrentUser.fromSecurityContext();

   // Create a NewUser object pre-filled with the current user's data
   NewUser newUser = new NewUser();
   newUser.setUsername(currentUser.getUsername());
   newUser.setRole(currentUser.getRole());

   // Add the user data to the model
   model.addAttribute("newUser", newUser);

   // Return the appropriate view based on the dashboard
   String dashboard = currentUser.getRole().toLowerCase(); // Assuming role is either 'admin', 'org', or 'vol'
   System.out.println(dashboard);
   return "dashboard/" + dashboard; // Dynamically choosing the view
   }

   // Update Profile Information
   @PostMapping("/update-profile")
   public String updateProfile(@ModelAttribute("newUser") NewUser updatedUser) {
   // Call service to update profile
   usersService.updateProfile(updatedUser);

   // Redirect to the dashboard with a success message
   return "redirect:/dashboard?update=success";
   }*/
}
