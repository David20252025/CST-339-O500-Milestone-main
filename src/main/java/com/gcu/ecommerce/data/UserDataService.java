package com.gcu.ecommerce.data;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.ecommerce.models.LoginModel;
import com.gcu.ecommerce.models.UserModel;

@Repository
public class UserDataService implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
    
    // Milestone 6 gets user account from MySQL for Spring Security.
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