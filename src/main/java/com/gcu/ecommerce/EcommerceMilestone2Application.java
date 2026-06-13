package com.gcu.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application entry point for the Ecommerce Milestone application. Initializes and runs the Spring Boot application.
 */
@SpringBootApplication
public class EcommerceMilestone2Application {
	
    /**
     * The main method serves as the entry point for the application. It delegates to Spring Boot's SpringApplication.run() method to launch the application.
     *
     * @param args any command-line arguments passed to the application.
     */

    public static void main(String[] args) {
        SpringApplication.run(EcommerceMilestone2Application.class, args);
    }
}