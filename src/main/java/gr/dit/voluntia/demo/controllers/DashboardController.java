package gr.dit.voluntia.demo.controllers;

import gr.dit.voluntia.demo.linkers.CurrentUser;
import gr.dit.voluntia.demo.linkers.ParticipationObj;
import gr.dit.voluntia.demo.models.Event;
import gr.dit.voluntia.demo.models.Organization;
import gr.dit.voluntia.demo.models.Volunteer;
import gr.dit.voluntia.demo.models.Admin;
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

        // Find Details from current User
        Volunteer userObj = volunteerService.getVolunteer(currentUser.getId());
        // Print-lines just for debugging purposes
        if (userObj == null) {
            System.out.println("Something went wrong, The provided ID does not match with any object of the database");
        } else {
            System.out.println(userObj.toString());
        }

        // Fetch the organization’s data (volunteer participation statuses, events, etc.)
        List<Event> allConfirmedEvs = volunteerService.getAllConfirmedEvents();
        List<ParticipationObj> rejectedParts = volunteerService.getAllParticipationsWithStatus(currentUser.getId(),"rejected");
        List<ParticipationObj> acceptedParts = volunteerService.getAllParticipationsWithStatus(currentUser.getId(),"approved");
        List<ParticipationObj> pendingParts = volunteerService.getAllParticipationsWithStatus(currentUser.getId(),"pending");

        // Add data to the model
        model.addAttribute("allConfirmedEvs", allConfirmedEvs);
        model.addAttribute("rejectedParts", rejectedParts);
        model.addAttribute("acceptedParts", acceptedParts);
        model.addAttribute("pendingParts", pendingParts);
        model.addAttribute("userObj", userObj);

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


    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        
        // Retrieve current user from the security context
        CurrentUser currentUser = CurrentUser.fromSecurityContext();
        model.addAttribute("currentUser", currentUser);

        // Find Details from current User 
        Admin adminObj = adminService.getAdmin(currentUser.getId());
        
        // Print-lines just for debugging purposes 
        if (adminObj == null) {
            System.out.println("Something went wrong, The provided ID does not match with any object of the database");
        } else {
            System.out.println(adminObj.toString());
        }


        // Retrieve all data lists of Users and events
        List<Organization> pendingOrgs = adminService.getPendingOrganizations();
        List<Organization> allOrgs = adminService.getAllOrganizations();

        List<Volunteer> pendingVols = adminService.getPendingVolunteers();
        List<Volunteer> allVols = adminService.getAllVolunteers();

        List<Event> pendingEvents = adminService.getPendingEvents();
        List<Event> allEvents = adminService.getAllEvents();

        Event newEvent = new Event();

        // If the resulting lists are null, initialize them as empty
        if (pendingOrgs == null) pendingOrgs = new ArrayList<>();
        if (allOrgs == null) allOrgs = new ArrayList<>();

        if (pendingVols == null) pendingVols = new ArrayList<>();
        if (allVols == null) allVols = new ArrayList<>();

        if (pendingEvents == null) pendingEvents = new ArrayList<>();
        if (allEvents == null) allEvents = new ArrayList<>();

        // Send this data to the frontend
        model.addAttribute("pendingOrgs", pendingOrgs);
        model.addAttribute("allOrgs", allOrgs);

        model.addAttribute("pendingVols", pendingVols);
        model.addAttribute("allVols", allVols);

        model.addAttribute("pendingEvents", pendingEvents);
        model.addAttribute("allEvents", allEvents);

        model.addAttribute("event", newEvent);
        model.addAttribute("adminObj", adminObj);

        // Message messages in case of empty lists
        if (pendingOrgs.isEmpty() && pendingVols.isEmpty() && pendingEvents.isEmpty()) {
            model.addAttribute("message", "All users and events have been confirmed!");
        }

        return "dashboard/admin"; // admin.html
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

        // Find Details from current User
        Organization userObj = organizationService.getOrganization(currentUser.getId());
        // Print-lines just for debugging purposes
        if (userObj == null) {
            System.out.println("Something went wrong, The provided ID does not match with any object of the database");
        } else {
            System.out.println(userObj.toString());
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
        model.addAttribute("userObj", userObj);

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

}
