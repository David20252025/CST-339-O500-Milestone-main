package com.gcu.ecommerce.business;

import org.springframework.stereotype.Service;

import com.gcu.ecommerce.data.UserDAO;
import com.gcu.ecommerce.models.LoginModel;

/**
 * Service class responsible for handling login-related business logic
 */
@Service
public class LoginBusinessService {

    private final UserDAO userDAO;
    
    /**
     * Constructs a new LoginBusinessService with the specified UserDAO dependency.
     *
     * @param userDAO The UserDAO instance used for accessing user data.
     */

    public LoginBusinessService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Authenticates the user based on the provided login credentials.
     *
     * @param loginModel The login credentials input.
     * @return true if authentication is successful, false otherwise.
     */
    
    public boolean authenticate(LoginModel loginModel) {
        return userDAO.authenticate(loginModel);
    }
}