package gr.dit.voluntia.demo.controllers;


import gr.dit.voluntia.demo.linkers.CurrentUser;
import gr.dit.voluntia.demo.linkers.NewUser;
import gr.dit.voluntia.demo.models.Event;
import gr.dit.voluntia.demo.repositories.AdminRepository;
import gr.dit.voluntia.demo.repositories.OrganizationRepository;
import gr.dit.voluntia.demo.repositories.VolunteerRepository;
import gr.dit.voluntia.demo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class SignupController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private AdminRepository adminRepository;


    @GetMapping("/signup/vol")
    public String getSignupAsVolunteerPage(Model model) {
        // Initialize an empty new user object tha will be sent to the thymeleaf template
        model.addAttribute("user", new NewUser());
        return "/signup/vol";
    }

    @GetMapping("/signup/org")
    public String getSignupAsOrganizationPage(Model model) {
        model.addAttribute("user", new NewUser());
        return "/signup/org";
    }

    @GetMapping("/signup/admin")
    public String getSignupAsAdminPage(Model model) {
        model.addAttribute("user", new NewUser());
        return "/signup/admin";
    }

    @PostMapping("/signup/vol")
    public String processVolunteerSignup(NewUser newUser, Model model) {
       // Check if user already exists
        if (volunteerRepository.findByUsername(newUser.getUsername()) != null) {
            model.addAttribute("message", "User with this username already exists. Please try again");
            return "redirect:/signup/vol";
        }

        // Ensure correct role is set
        newUser.setRole("VOLUNTEER");
        // Call service method to handle logic
        usersService.saveVolunteer(newUser);
        // Redirect to login page after successful registration
        return "redirect:/login/vol";
    }

    @PostMapping("/signup/org")
    public String processOrganizationSignup(NewUser newUser, Model model) {
        if (organizationRepository.findByUsername(newUser.getUsername()) != null) {
            model.addAttribute("message", "User with this username already exists. Please try again");
            return "redirect:/signup/org";
        }

        newUser.setRole("ORGANIZATION");
        usersService.saveOrganization(newUser);
        return "redirect:/login/org";
    }

    @PostMapping("/signup/admin")
    public String processAdminSignup(NewUser newUser, Model model) {
        if (adminRepository.findByUsername(newUser.getUsername()) != null
            || adminRepository.count() > 0) {
            model.addAttribute("message", "Admin is only one for this app!");
            return "redirect:/signup/admin";
        }

        newUser.setRole("ADMIN");
        usersService.saveAdmin(newUser);
        return "redirect:/login/admin";
    }
}
