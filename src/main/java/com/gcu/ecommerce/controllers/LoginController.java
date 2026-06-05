package com.gcu.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gcu.ecommerce.models.LoginModel;

//Milestone 6
@Controller
public class LoginController {

    @GetMapping("/login")
    public String show(Model model) {
        model.addAttribute("loginModel", new LoginModel());
        return "login";
    }
}