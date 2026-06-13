package com.gcu.ecommerce.models;
import javax.validation.constraints.*;

/**
 * Model class for login form data. Stores the username and password entered by the user.
 */
public class LoginModel {

	@NotBlank(message="Username is required.") @Size(min=4,max=20,message="Username must be between 4 and 20 characters.")
	private String username;

	@NotBlank(message="Password is required.") @Size(min=6,max=30,message="Password must be at least 6 characters.")
	private String password;

	/**
	 * Retrieves the username entered in the login form.
	 *
	 * @return The username value.
	 */
	public String getUsername() { return username; }

	/**
	 * Sets the username value for the login form.
	 *
	 * @param v The username to store.
	 */
	public void setUsername(String v) { username = v; }

	/**
	 * Retrieves the password entered in the login form.
	 *
	 * @return The password value.
	 */
	public String getPassword() { return password; }

	/**
	 * Sets the password value for the login form.
	 *
	 * @param v The password to store.
	 */
	public void setPassword(String v) { password = v; }
}