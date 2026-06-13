package com.gcu.ecommerce.models;
import javax.validation.constraints.*;
/**
 * Model class for user registration form data. Stores the information entered by a new user during registration.
 */

public class UserModel {
 @NotBlank(message="First name is required.") @Size(min=2,max=30,message="First name must be between 2 and 30 characters.")
 private String firstName;
 @NotBlank(message="Last name is required.") @Size(min=2,max=30,message="Last name must be between 2 and 30 characters.")
 private String lastName;
 @NotBlank(message="Email is required.") @Email(message="Enter a valid email address.")
 private String email;
 @NotBlank(message="Phone number is required.") @Pattern(regexp="^[0-9]{10}$",message="Phone number must be 10 digits.")
 private String phoneNumber;
 @NotBlank(message="Username is required.") @Size(min=4,max=20,message="Username must be between 4 and 20 characters.")
 private String username;
 @NotBlank(message="Password is required.") @Size(min=6,max=30,message="Password must be at least 6 characters.")
 private String password;
 
	/**
	 * Retrieves the user's first name.
	 *
	 * @return The first name value.
	 */
 
 public String getFirstName(){return firstName;}
 
	/**
	 * Sets the user's first name.
	 *
	 * @param v The first name to store.
	 */
 
 public void setFirstName(String v){firstName=v;}
 
	/**
	 * Retrieves the user's last name.
	 *
	 * @return The last name value.
	 */
 
 public String getLastName(){return lastName;} 
 
	/**
	 * Sets the user's last name.
	 *
	 * @param v The last name to store.
	 */
 
 public void setLastName(String v){lastName=v;}
 
	/**
	 * Retrieves the user's email address.
	 *
	 * @return The email value.
	 */
 
 public String getEmail(){return email;}
 
	/**
	 * Sets the user's email address.
	 *
	 * @param v The email to store.
	 */
 
 public void setEmail(String v){email=v;}
 
	/**
	 * Retrieves the user's phone number.
	 *
	 * @return The phone number value.
	 */
 
 public String getPhoneNumber(){return phoneNumber;}
 
	/**
	 * Sets the user's phone number.
	 *
	 * @param v The phone number to store.
	 */
 
 public void setPhoneNumber(String v){phoneNumber=v;}
 
	/**
	 * Retrieves the user's username.
	 *
	 * @return The username value.
	 */
 
 public String getUsername(){return username;} 
 
	/**
	 * Sets the user's username.
	 *
	 * @param v The username to store.
	 */
 
 public void setUsername(String v){username=v;}
 
	/**
	 * Retrieves the user's password.
	 *
	 * @return The password value.
	 */
 
 public String getPassword(){return password;}
 
	/**
	 * Sets the user's password.
	 *
	 * @param v The password to store.
	 */
 
 public void setPassword(String v){password=v;}
}
