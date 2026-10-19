package org.example.minionlinestore.controller;


//import org.example.minionlinestore.service.UserService;
import org.example.minionlinestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/")
    public String indexPage() {
        return "index";
    }

    @GetMapping(value="/sign-in-page")
    public String signinPage() { return "signin"; }

    @GetMapping(value="/sign-up-page")
    public String signupPage() { return "signup"; }

    @GetMapping(value="/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage() { return "profile"; }

    @GetMapping(value="/403-page")
    public String acessDeniedage(){ return "403"; }


}
