package com.gcu.ecommerce.business;

import org.springframework.stereotype.Service;

import com.gcu.ecommerce.models.UserModel;

/**
 * Service class responsible for handling registration-related business logic
 */

import com.gcu.ecommerce.data.UserDAO;
import com.gcu.ecommerce.models.UserModel;

@Service
public class RegistrationBusinessService {

    private final UserDAO userDAO;
    
    /**
     * Constructs a new RegistrationBusinessService with the specified UserDAO dependency.
     *
     * @param userDAO The UserDAO instance used for accessing user data.
     */

    public RegistrationBusinessService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    /**
     * Registers a new user if the username does not already exist.

     * @param userModel The user data to be registered.
     * @return true if registration is successful, false if the username already exists.
     */

    public boolean registerUser(UserModel userModel) {
        if (userDAO.usernameExists(userModel.getUsername())) {
            return false;
        }

        userDAO.create(userModel);
        return true;
    }
}