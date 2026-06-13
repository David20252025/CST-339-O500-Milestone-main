package com.gcu.ecommerce.data;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.ecommerce.models.LoginModel;
import com.gcu.ecommerce.models.UserModel;

/**
 * JDBC implementation of the UserDAO interface. Uses Spring's JdbcTemplate to execute SQL queries and manage user database operations including creation, authentication, and retrieval of user records.
 */

@Repository
public class UserDataService implements UserDAO {

    private final JdbcTemplate jdbcTemplate;
    
    /**
     * Spring JdbcTemplate class injected through the constructor. The JdbcTemplate is a Spring utility class that simplifies JDBC operations and handles the boilerplate code required for database access.
     *
     * @param jdbcTemplate The Spring JdbcTemplate object used to execute SQL queries against the database.
     */

    public UserDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    /**
     * Executes an INSERT query to add a new user record to the database. Uses parameterized queries to safely insert user information including first name, last name, email, phone number, username, and password.
     *
     * @param user The UserModel object containing the user information to be inserted into the database.
     * @return The number of rows affected by the insert operation.
     */

    @Override
    public int create(UserModel user) {
        String sql = "INSERT INTO users (first_name, last_name, email, phone_number, username, password) VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getUsername(),
                user.getPassword()
        );
    }
    
    /**
     * Executes a query to authenticate a user by verifying the provided credentials. Counts matching records in the users table where both username and password match the provided login credentials.
     *
     * @param loginModel The LoginModel object containing the username and password credentials to verify.
     * @return True if at least one user record matches both the username and password, false otherwise.
     */

    @Override
    public boolean authenticate(LoginModel loginModel) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                loginModel.getUsername(),
                loginModel.getPassword()
        );

        return count != null && count > 0;
    }
    
    /**
     * Executes a query to check if a specified username already exists in the database. Counts matching records in the users table for the provided username to determine availability.
     *
     * @param username The username to check for existence in the database.
     * @return True if a user with the specified username exists in the database, false otherwise.
     */

    @Override
    public boolean usernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";

        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
            return count != null && count > 0;
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
    }
    
    /**
     * Executes a SELECT query to retrieve a single user from the database by username. Queries the users table for a record matching the specified username and maps the result to a UserModel object containing all user information.
     *
     * @param username The username used to identify and retrieve the user record.
     * @return A UserModel object representing the user with the specified username, or null if no user is found.
     */
    
    @Override
    public UserModel findByUsername(String username) {
        String sql = "SELECT id, first_name, last_name, email, phone_number, username, password FROM users WHERE username = ?";

        try {
        	return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {

        	    UserModel user = new UserModel();


        	    user.setFirstName(rs.getString("first_name"));
        	    user.setLastName(rs.getString("last_name"));
        	    user.setEmail(rs.getString("email"));
        	    user.setPhoneNumber(rs.getString("phone_number"));
        	    user.setUsername(rs.getString("username"));
        	    user.setPassword(rs.getString("password"));

        	    return user;

        	}, username);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}