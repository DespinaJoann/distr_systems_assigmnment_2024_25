package gr.dit.voluntia.demo.controllers;


import gr.dit.voluntia.demo.links.NewUser;
import gr.dit.voluntia.demo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class SignupController {

    @Autowired
    private UsersService usersService;


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
    public String processVolunteerSignup(NewUser newUser) {
        // Ensure correct role is set
        newUser.setRole("VOLUNTEER");
        // Call service method to handle logic
        usersService.saveVolunteer(newUser);
        // Redirect to login page after successful registration
        return "redirect:/login/vol";
    }

    @PostMapping("/signup/org")
    public String processOrganizationSignup(NewUser newUser) {
        newUser.setRole("ORGANIZATION");
        usersService.saveOrganization(newUser);
        return "redirect:/login/org";
    }

    @PostMapping("/signup/admin")
    public String processAdminSignup(NewUser newUser) {
        newUser.setRole("ADMIN");
        usersService.saveAdmin(newUser);
        return "redirect:/login/admin";
    }
}
