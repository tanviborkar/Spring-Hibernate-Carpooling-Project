package com.hibernate.test.api;

import com.hibernate.test.pojo.User;
import com.hibernate.test.pojo.UserType;

public interface UserServiceInterface {

	//This method will create a new user in the system
	//@param User object with all user details
	public void createUser(User newUser);
	
	//This method will check whether the user is a valid one or not
	//@param username and password of the user
	//@return User if authenticated, else null
	public User authenticateUser(String username, String password);
	
	//This method allows you to edit information for an existing user
	//@param User object with updated user information
	public void editProfile(User newUser);

	public User getUserByUserTypeAndId(UserType facebook, String userTypeId);
}
