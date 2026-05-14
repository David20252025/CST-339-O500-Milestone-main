package com.gcu.ecommerce.data;

import com.gcu.ecommerce.models.LoginModel;
import com.gcu.ecommerce.models.UserModel;

public interface UserDAO {

    int create(UserModel user);

    boolean authenticate(LoginModel loginModel);

    boolean usernameExists(String username);
}