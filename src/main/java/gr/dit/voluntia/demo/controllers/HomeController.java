package gr.dit.voluntia.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/auth")
    public String GetAuthoriziationOptionsPage () {
        return "auth";
    }
}
