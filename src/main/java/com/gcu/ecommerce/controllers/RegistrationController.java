package com.gcu.ecommerce.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.ecommerce.business.RegistrationBusinessService;
import com.gcu.ecommerce.models.LoginModel;
import com.gcu.ecommerce.models.UserModel;

/**
 * Spring Framework Controller class for handling requests to the registration page.
 */
@Controller
public class RegistrationController {

    private final RegistrationBusinessService registrationBusinessService;

    /**
     * Business service dependency injected through the constructor. This allows the controller to utilize the business service's methods to perform operations related to user registration.
     *
     * @param registrationBusinessService The business service to inject.
     */
    public RegistrationController(RegistrationBusinessService registrationBusinessService) {
        this.registrationBusinessService = registrationBusinessService;
    }
    
    /**
     * Handles requests to the "/register" url. Prepares the registration form by adding a new UserModel instance to the model.
     
     * @param model The model to which the registration form attributes will be added.
     * @return The name of the view to be rendered, in this case "register".
     */

    @GetMapping("/register")
    public String show(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "register";
    }
    
    /**
     * Handles post requests to the "/register" url. Validates the registration input and redirects back to the registration form if there are errors. If registration is successful, redirects to the login page.
     
     * @param userModel The inputted form information for the new user.
     * @param result The result of validating the userModel input.
     * @param model The model to which form attributes may be added in case of validation errors.
     * @return The name of the view to be rendered, either "register" if validation fails, or "login" if registration is successful.
     */

    @PostMapping("/register")
    public String submit(@Valid UserModel userModel, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "register";
        }

        boolean registered = registrationBusinessService.registerUser(userModel);

        if (!registered) {
            model.addAttribute("registerError", "Username already exists. Please choose another username.");
            return "register";
        }

        model.addAttribute("successMessage", "Registration successful. You can now log in.");
        model.addAttribute("loginModel", new LoginModel());

        return "login";
    }
}