package com.gcu.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Framework configuration class for setting up application security and authentication
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private UserDetailsService service;
    
    /**
     * Overrides the default authentication configuration to use a custom UserDetailsService and password encoder.
     * 
     * @param auth The authentication manager builder.
     * @throws Exception If an error occurs during authentication configuration.
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service)
            .passwordEncoder(passwordEncoder());
    }
    
    /**
     * A password encoder bean that uses no encoding (plain text, for educational purposes, unsuitable for production)
     *
     * @return A password encoder instance that performs no encoding (plain text).
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    
    /**
     * Overrides the default Spring HTTP security configuration to define authorization rules. Restricts access to certain URLs based on authentication status and configures form-based login and logout behavior.
     *
     * @param http The HTTP security object used to configure request authorization and login/logout behavior.
     * @throws Exception If an error occurs during HTTP security configuration.
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/login", "/register", "/css/**", "/images/**").permitAll()
                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll() // Milestone 7
                .antMatchers("/api/**").authenticated()  // Milestone 7
                .anyRequest().authenticated()
            .and()
            .httpBasic()  // Milestone 7
            .and()  // Milestone 7
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    request.getSession().setAttribute("loggedInUser", authentication.getName());
                    response.sendRedirect("/products");
                })
                .failureUrl("/login?error=true")
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll();
    }
}