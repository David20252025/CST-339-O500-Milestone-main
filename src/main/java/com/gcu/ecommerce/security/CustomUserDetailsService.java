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

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDAO userDAO;

    public CustomUserDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

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