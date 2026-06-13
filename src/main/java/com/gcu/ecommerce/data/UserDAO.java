package com.gcu.ecommerce.data;

import com.gcu.ecommerce.models.LoginModel;
import com.gcu.ecommerce.models.UserModel;

/**
 * Data access object interface for user persistence. Defines the contract for all database operations related to users, including creation, authentication, and retrieval of user records.
 */

public interface UserDAO {
	
    /**
     * Inserts a new user record into the database. Takes a UserModel object containing the user information and persists it to the database.
     *
     * @param user The UserModel object containing the user information to be inserted.
     * @return The number of rows affected by the insert operation.
     */

    int create(UserModel user);
    
    /**
     * Authenticates a user by verifying the provided credentials against the database. Checks if a user record exists with the given username and password combination.
     *
     * @param loginModel The LoginModel object containing the username and password credentials to verify.
     * @return True if a user with matching credentials is found in the database, false otherwise.
     */
    
    boolean authenticate(LoginModel loginModel);
    
    /**
     * Checks whether a username already exists in the database. Queries the users table to determine if the specified username has been registered.
     *
     * @param username The username to check for existence in the database.
     * @return True if the username already exists in the database, false otherwise.
     */

    boolean usernameExists(String username);
    
    /**
     * Retrieves a single user from the database by username. Queries the users table for a record matching the specified username and returns the corresponding UserModel object.
     *
     * @param username The username used to identify which user record to retrieve.
     * @return A UserModel object representing the user with the specified username, or null if no user is found.
     */
    
    UserModel findByUsername(String username);
}