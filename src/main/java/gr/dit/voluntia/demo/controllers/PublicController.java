package gr.dit.voluntia.demo.controllers;

import gr.dit.voluntia.demo.models.Event;
import gr.dit.voluntia.demo.services.EventService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class PublicController {
    @Autowired
    private EventService eventService;

    @GetMapping("/public-calendar")
    public String getPublicCalendarPage(Model model) {
        List<Event> events = eventService.getAllEvents();
        return "public-calendar";
    }


    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/public")
    public String getPublicPage() {
        return "public";
    }


    @GetMapping("/auth")
    public String getAuthoriziationOptionsPage () {
        return "auth";
    }

    @GetMapping("/login")
    public String getLoginPage () {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage () {
        return "signupAs";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Clean up session and logout the user
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/";
    }

    @GetMapping("/profile/no/found")
    public String profileNotFound(HttpServletRequest request, HttpServletResponse response) {
        return "userNoFound";
    }


}
