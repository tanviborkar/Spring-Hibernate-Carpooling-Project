package com.hibernate.test.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hibernate.test.api.RequestServiceInterface;
import com.hibernate.test.api.UserServiceInterface;
//import com.hibernate.test.api.RideServiceInterface;
import com.hibernate.test.pojo.Request;
import com.hibernate.test.pojo.User;
import com.hibernate.test.pojo.UserType;

@Controller
@RequestMapping(value="/reqmc")
public class RequestMultiactionController {
	
	@Autowired
	RequestServiceInterface requestServiceInterface;
	@Autowired
	UserServiceInterface userServiceInterface;
	
	@RequestMapping(value="test", method = RequestMethod.GET)
	public ModelAndView test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String name){
		ModelAndView modelAndView = new ModelAndView();
		Request request = new Request();
		request.setDestination("TD");
		request.setPickupPlace("PP");
		request.setRequestTime(new Date());
		request.setStartTime(new Date());
		User requestedBy = new User();
		requestedBy.setUserId(1L);
		request.setRequestedBy(requestedBy);
		
		requestServiceInterface.createRequest(request);
		
		modelAndView.setViewName("test");
		return modelAndView;
	}
	
	@RequestMapping(value="fetchTest", method = RequestMethod.GET)
	public ModelAndView fetchTest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String name){
		ModelAndView modelAndView = new ModelAndView();
		
		requestServiceInterface.getAllRequests();
		
		modelAndView.setViewName("test");
		return modelAndView;
	}
	
	@RequestMapping(value="formNewRequest")
	public ModelAndView formNewRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("new_request");
		return modelAndView;
	}
	
	@RequestMapping(value="submitNewRequest")
	public ModelAndView newRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Request request, String userTypeId){
		ModelAndView modelAndView = new ModelAndView();
		//Request request = new Request();
		request.setRequestTime(new Date());
		User requestedBy = userServiceInterface.getUserByUserTypeAndId(UserType.FACEBOOK, userTypeId);
		//requestedBy.setUserId(1L);
		request.setRequestedBy(requestedBy);
		
		requestServiceInterface.createRequest(request);
		
		modelAndView.setViewName("test");
		return modelAndView;
	}
	
	@RequestMapping(value="showRequests")
	public ModelAndView showRequests(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String userTypeId){
		ModelAndView modelAndView = new ModelAndView();
		User requestedBy = userServiceInterface.getUserByUserTypeAndId(UserType.FACEBOOK, userTypeId);
		if (requestedBy!=null) {
			List<Request> allRequestsForAUser = requestServiceInterface.getAllRequestsForAUser(requestedBy);
			modelAndView.addObject("allRequests", allRequestsForAUser);
		}
		modelAndView.addObject("userId", requestedBy.getUserId());
		modelAndView.setViewName("show_requests");
		return modelAndView;
	}

	
	@RequestMapping(value="showAllFilteredRequests")
	public ModelAndView showAllFilteredRequests(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String userTypeId, String rideId){
		ModelAndView modelAndView = new ModelAndView();
		User requestedBy = userServiceInterface.getUserByUserTypeAndId(UserType.FACEBOOK, userTypeId);
		if (requestedBy!=null) {
			List<Request> allRequestsFilteredOnDateAndUser = requestServiceInterface.getAllRequestsFilteredOnDateAndUser(Long.parseLong(rideId));
			modelAndView.addObject("allRequestsForRide", allRequestsFilteredOnDateAndUser);
			modelAndView.addObject("rideId",rideId);
		}
		modelAndView.setViewName("show_requests_dialog");
		return modelAndView;
	}
	
	@RequestMapping(value="mapRequestsToRide")
	public void  mapRequestsToRide(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Long[] selectedRequestIds, Long rideId){
		//ModelAndView modelAndView = new ModelAndView();
		int result = requestServiceInterface.createRequestRideMapping(selectedRequestIds, rideId);
		 httpServletResponse.setContentType("text/html;charset=UTF-8");
	     try {
			httpServletResponse.getWriter().write(Integer.toString(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
