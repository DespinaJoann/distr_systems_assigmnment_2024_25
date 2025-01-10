package gr.dit.voluntia.demo.controllers;


import gr.dit.voluntia.demo.dtos.auths.UserForm;
import gr.dit.voluntia.demo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    @Autowired
    private UsersService usersService;


    @GetMapping("/signup/vol")
    public String GetSignupAsVolunteerPage( ) {
        return "vol_signup";
    }

    @GetMapping("/signup/org")
    public String GetSignupAsOrganizationPage( ) {
        return "org_signup";
    }

    @GetMapping("/signup/admin")
    public String GetSignupAsAdminPage( ) {
        return "admin_signup";
    }


    @PostMapping("/signup/vol")
    public String processVolunteerSignup(UserForm userForm) {
        usersService.registerVolunteer(userForm);
        return "redirect:/login/vol";
    }

    @PostMapping("/signup/org")
    public String processOrganizationSignup(UserForm userForm) {
        usersService.registerOrganization(userForm);
        return "redirect:/login/org";
    }

    @PostMapping("/signup/admin")
    public String processAdminSignup(UserForm userForm) {
        usersService.registerAdmin(userForm);
        return "redirect:/login/admin";
    }
}
