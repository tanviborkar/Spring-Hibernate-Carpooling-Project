package com.hibernate.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hibernate.test.api.RequestServiceInterface;
import com.hibernate.test.pojo.User;

@Controller
@RequestMapping(value="/cmc")
public class CommonMultiActionController {

	@Autowired
	RequestServiceInterface requestServiceInterface;
	
	@RequestMapping(value="respondToRequest")
	public void respondToRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
	Long rideId, Long requestId, int actionType)
	{
		requestServiceInterface.respondToRequest(rideId, requestId, actionType);
	}
	
	@RequestMapping(value="fbusercreation", method = RequestMethod.GET)
	public ModelAndView FBUserCreation(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	{
		User user = new User();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("test");
		return modelAndView;
	}
	
}
