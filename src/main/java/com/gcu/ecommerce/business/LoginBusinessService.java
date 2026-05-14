package com.gcu.ecommerce.business;

import org.springframework.stereotype.Service;

import com.gcu.ecommerce.data.UserDAO;
import com.gcu.ecommerce.models.LoginModel;

/**
 * LoginBusinessService handles login business logic.
 * For Milestone 4 database.
 */
@Service
public class LoginBusinessService {

    private final UserDAO userDAO;

    public LoginBusinessService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean authenticate(LoginModel loginModel) {
        return userDAO.authenticate(loginModel);
    }
}