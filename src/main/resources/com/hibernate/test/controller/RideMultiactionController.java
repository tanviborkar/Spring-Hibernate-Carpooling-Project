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

import com.hibernate.test.api.RideServiceInterface;
import com.hibernate.test.api.UserServiceInterface;
import com.hibernate.test.pojo.Request;
import com.hibernate.test.pojo.Ride;
import com.hibernate.test.pojo.User;
import com.hibernate.test.pojo.UserType;

@Controller
@RequestMapping(value="/rmc")
public class RideMultiactionController {
	
	@Autowired
	RideServiceInterface rideServiceInterface;
	@Autowired
	UserServiceInterface userServiceInterface;
	
	@RequestMapping(value="formNewRide")
	public ModelAndView formNewRide(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("new_ride");
		return modelAndView;
	}
	
	@RequestMapping(value="postNewRide")
	public ModelAndView postNewRide(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Ride ride, String userTypeId, String isPickupOtherThanStartProvided, String isPriceNegotiable){
		ModelAndView modelAndView = new ModelAndView();
		ride.setRidePostedOn(new Date());
		User postedBy = userServiceInterface.getUserByUserTypeAndId(UserType.FACEBOOK, userTypeId);		
		if(isPickupOtherThanStartProvided.equals("true")){
			ride.setPickupOtherThanStartProvided(true);
		}
		else{
			ride.setPickupOtherThanStartProvided(false);
		}
		if(isPriceNegotiable.equals("true")){
			ride.setPriceNegotiable(true);
		}
		else{
			ride.setPriceNegotiable(false);
		}
		ride.setRideOwner(postedBy);
		rideServiceInterface.createRide(ride);
		
		modelAndView.setViewName("test");
		return modelAndView;
	}
	
	@RequestMapping(value="showUserRides")
	public ModelAndView showUserRides(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String userTypeId){
		ModelAndView modelAndView = new ModelAndView();
		User postedBy = userServiceInterface.getUserByUserTypeAndId(UserType.FACEBOOK, userTypeId);
		if (postedBy != null) {
			List<Ride> userRideList = rideServiceInterface.getUserRides(postedBy);
			modelAndView.addObject("userRideList", userRideList);
		}
		modelAndView.addObject("userId", postedBy.getUserId());
		modelAndView.setViewName("show_rides");
		return modelAndView;
	}
	
	@RequestMapping(value="editRide")
	public ModelAndView editRide(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
		ModelAndView modelAndView = new ModelAndView();
		String rideId = (String)httpServletRequest.getParameter("rideId");
		Ride ride = rideServiceInterface.fetchRide(Long.parseLong(rideId));
		if(ride != null){
			modelAndView.addObject("rideToEdit", ride);
		}
		modelAndView.setViewName("edit_ride");
		return modelAndView;
		//rideServiceInterface.editRide(updatedRide);
	}
	
	@RequestMapping(value="updateRide")
	public ModelAndView updateRide(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, 
			Ride ride,String isPickupOtherThanStartProvided, String isPriceNegotiable){
		ModelAndView modelAndView = new ModelAndView();
		if(isPickupOtherThanStartProvided.equals("true")){
			ride.setPickupOtherThanStartProvided(true);
		}
		else{
			ride.setPickupOtherThanStartProvided(false);
		}
		if(isPriceNegotiable.equals("true")){
			ride.setPriceNegotiable(true);
		}
		else{
			ride.setPriceNegotiable(false);
		}
		
		rideServiceInterface.editRide(ride);
		modelAndView.setViewName("test");
		return modelAndView;
	}
	/*@RequestMapping(value="createRide", method = RequestMethod.GET)
	public ModelAndView createRide(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Ride ride){
		ModelAndView modelAndView = new ModelAndView();
		Ride newRide = ride;
		User user = new User();
		user.setUserId(1L);
		ride.setRideOwner(user);
		System.out.println(newRide.getStartPoint());
		System.out.println(newRide.getMaxNoOfPassengers());
		//newRide.setStartPoint("Start");
		//newRide.setDestination("Last");
		
		rideServiceInterface.createRide(newRide);
		
		modelAndView.setViewName("test");
		return modelAndView;
	}*/
	
	
	
	@RequestMapping(value="deleteRide", method = RequestMethod.GET)
	public ModelAndView deleteRide(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, long rideId){
		ModelAndView modelAndView = new ModelAndView();
		//long rideId = 1;//This data will be obtained from request
		rideServiceInterface.deleteRide(rideId);
		
		modelAndView.setViewName("test");
		return modelAndView;
	}
	
	/*@RequestMapping(value="fetchUserRides", method = RequestMethod.GET)
	public ModelAndView fetchUserRides(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int userId){
		ModelAndView modelAndView = new ModelAndView();
		//long userId=1;//This data will be obtained from requests
		List<Ride> userRideList = rideServiceInterface.getUserRides(userId);
		System.out.println(userRideList);
		modelAndView.setViewName("test");
		return modelAndView;
	}*/

	@RequestMapping(value="fetchRideDetails", method = RequestMethod.GET)
	public ModelAndView fetchRideDetails(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, long rideId){
		ModelAndView modelAndView = new ModelAndView();
		Ride ride = rideServiceInterface.fetchRide(rideId);
		System.out.println(ride.toString());
		modelAndView.setViewName("test");
		return modelAndView;
	}
	
	@RequestMapping(value="addRequestToRide", method = RequestMethod.GET)
	public ModelAndView addRequestToRide(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Request request, Ride ride){
		ModelAndView modelAndView = new ModelAndView();
		rideServiceInterface.addRequestToRide(request, ride);
		modelAndView.setViewName("test");
		return modelAndView;
	}
	
	@RequestMapping(value="showAllFilteredRides")
	public ModelAndView showAllFilteredRides(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String requestId){
		ModelAndView modelAndView = new ModelAndView();
		List<Ride> allRidesFilteredOnDateAndUser = rideServiceInterface.getAllRidesFilteredOnDateAndUser(Long.parseLong(requestId));
		modelAndView.addObject("allRidesForRequests", allRidesFilteredOnDateAndUser);
		modelAndView.addObject("requestId",requestId);
		modelAndView.setViewName("show_rides_dialog");
		return modelAndView;
	}
	
	@RequestMapping(value="mapRideToRequests")
	public void  mapRideToRequests(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Long[] selectedRideIds, Long requestId){
		int result = rideServiceInterface.createRideRequestMapping(selectedRideIds, requestId);
		 httpServletResponse.setContentType("text/html;charset=UTF-8");
	     try {
			httpServletResponse.getWriter().write(Integer.toString(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
