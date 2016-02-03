package com.hibernate.test.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hibernate.test.api.UserServiceInterface;
import com.hibernate.test.pojo.User;
import com.hibernate.test.pojo.UserType;

@Controller
@RequestMapping(value="/first")
public class FirstController {
	
	@Autowired
	UserServiceInterface userService;

	@RequestMapping(value="test", method = RequestMethod.GET)
	public ModelAndView test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String name){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("test");
		return modelAndView;
	}

	@RequestMapping(value="login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value="fbToLP")
	public void fbToLP(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String first_name, String last_name, String email, String id){
		User existingUser = userService.getUserByUserTypeAndId(UserType.FACEBOOK, id);
		
		if(existingUser==null){
			User user = new User();
			user.setUserType(UserType.FACEBOOK);
			user.setUserTypeId(id);
			user.setFirstName(first_name);
			user.setEmailAddress(email);
			user.setLastName(last_name);
			user.setMobileNo("NOT_REQUIRED");
			user.setUsername("NOT_REQUIRED");
			user.setPassword("NOT_REQUIRED");
			userService.createUser(user);
		}
		
	}
}
