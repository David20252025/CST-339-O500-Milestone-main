package com.gcu.ecommerce.security;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gcu.ecommerce.data.UserDAO;
import com.gcu.ecommerce.models.UserModel;

/**
 * Spring Framework service class for integrating application user data with Spring Security's authentication system.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDAO userDAO;
    
    /**
     * Constructs a new CustomUserDetailsService with the specified UserDAO.
     *
     * @param userDAO The data access object for user information.
     */

    public CustomUserDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    /**
     * Loads user details for Spring Security authentication.
     *
     * @param username The username of the user to look up.
     * @return The Spring Security user details for the specified username.
     * @throws UsernameNotFoundException If the user with the specified username is not found.
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel user = userDAO.findByUsername(username.trim());

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new User(
                user.getUsername().trim(),
                user.getPassword().trim(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}