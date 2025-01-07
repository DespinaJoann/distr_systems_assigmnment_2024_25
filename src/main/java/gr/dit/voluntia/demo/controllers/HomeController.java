package gr.dit.voluntia.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    /**Description:
     * Endpoint for the root URL
     * Returns a simple welcome message or application information
     * */
    public String home () {
        ResponseEntity.ok("Home page loaded!");
        return "index.html";
    }

}
