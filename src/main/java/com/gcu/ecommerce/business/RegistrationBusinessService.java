package com.gcu.ecommerce.business;

import org.springframework.stereotype.Service;

import com.gcu.ecommerce.models.UserModel;

/**
 * RegistrationBusinessService handles registration business logic.
 *  Milestone 4 database.
 */

import com.gcu.ecommerce.data.UserDAO;
import com.gcu.ecommerce.models.UserModel;

@Service
public class RegistrationBusinessService {

    private final UserDAO userDAO;

    public RegistrationBusinessService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean registerUser(UserModel userModel) {
        if (userDAO.usernameExists(userModel.getUsername())) {
            return false;
        }

        userDAO.create(userModel);
        return true;
    }
}