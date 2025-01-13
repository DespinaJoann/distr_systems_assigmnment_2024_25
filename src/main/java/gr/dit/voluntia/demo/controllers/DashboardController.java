package gr.dit.voluntia.demo.controllers;

import gr.dit.voluntia.demo.dtos.auths.CurrentUser;
import gr.dit.voluntia.demo.dtos.auths.NewUser;
import gr.dit.voluntia.demo.services.AdminService;
import gr.dit.voluntia.demo.services.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final AdminService adminService;

    public DashboardController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        // Retrieve current user from the security context
        CurrentUser currentUser = CurrentUser.fromSecurityContext();
        model.addAttribute("currentUser", currentUser);

        // Load the pending organizations and volunteers
        model.addAttribute("pendingOrgs", adminService.getPendingOrganizations());
        model.addAttribute("pendingVols", adminService.getPendingVolunteers());

        // Just for debugging
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
