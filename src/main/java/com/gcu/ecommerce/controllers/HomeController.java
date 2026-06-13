package com.gcu.ecommerce.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring Framework Controller class for handling requests to the home page
 */

@Controller
public class HomeController {
	/**
	 * Handles requests to the root url "/"
	 *
	 * @return The name of the view to be rendered, in this case "index".
	 */
	
	@GetMapping("/") public String home(){ return "index"; } }
