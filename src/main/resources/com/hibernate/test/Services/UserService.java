package com.hibernate.test.Services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.test.DAO.UserDAOImpl;
import com.hibernate.test.api.UserDAOInterface;
import com.hibernate.test.api.UserServiceInterface;
import com.hibernate.test.pojo.User;
import com.hibernate.test.pojo.UserType;

@Service
@Transactional
public class UserService implements UserServiceInterface {
	
	@Autowired
	UserDAOInterface userDao;
	
	public User authenticate(String username, String password)
	{
		try
		{
			//input validation
			//if valid input - create a DAO object
			//UserDAOInterface userDao = UserDAOImpl.getUserDAOImpl();
			return userDao.getUserInfo(username, password);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void createUser(User newUser) {
		userDao.createUser(newUser);
		
	}

	public User authenticateUser(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public void editProfile(User newUser) {
		
	}

	public User getUserByUserTypeAndId(UserType userType, String userTypeId) {
		return userDao.checkIfUserExistsByUserTypeAndId(userType, userTypeId);
	}
}
