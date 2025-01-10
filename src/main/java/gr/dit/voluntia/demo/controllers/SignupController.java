package gr.dit.voluntia.demo.controllers;


import gr.dit.voluntia.demo.dtos.auths.NewUser;
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
    public String GetSignupAsVolunteerPage(Model model) {
        // Initialize an empty new user object tha will be sent to the thymeleaf template
        model.addAttribute("user", new NewUser());
        return "vol_signup";
    }

    @GetMapping("/signup/org")
    public String GetSignupAsOrganizationPage(Model model) {
        model.addAttribute("user", new NewUser());
        return "org_signup";
    }

    @GetMapping("/signup/admin")
    public String GetSignupAsAdminPage(Model model) {
        model.addAttribute("user", new NewUser());
        return "admin_signup";
    }


    @PostMapping("/signup/vol")
    public String processVolunteerSignup(NewUser newUser) {
        // Ensure correct role is set
        newUser.setRole("VOLUNTEER");
        // Call service method to handle logic
        usersService.registerVolunteer(newUser);
        // Redirect to login page after successful registration
        return "redirect:/login/vol";
    }

    @PostMapping("/signup/org")
    public String processOrganizationSignup(NewUser newUser) {
        newUser.setRole("ORGANIZATION");
        usersService.registerOrganization(newUser);
        return "redirect:/login/org";
    }

    @PostMapping("/signup/admin")
    public String processAdminSignup(NewUser newUser) {
        newUser.setRole("ADMIN");
        usersService.registerAdmin(newUser);
        return "redirect:/login/admin";
    }
}
