package com.gcu.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gcu.ecommerce.models.LoginModel;

/**
 * Spring Framework Controller class for handling requests to the login page.
 */

@Controller
public class LoginController {
	
    /**
     * Handles requests to the "/login" url. Prepares the login form by adding a new LoginModel instance to the model.
     *
     * @param model The model to which the login form attributes will be added.
     * @return The name of the view to be rendered, in this case "login".
     */

    @GetMapping("/login")
    public String show(Model model) {
        model.addAttribute("loginModel", new LoginModel());
        return "login";
    }
}