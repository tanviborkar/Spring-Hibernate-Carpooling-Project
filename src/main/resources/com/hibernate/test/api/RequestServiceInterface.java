package com.hibernate.test.api;

import java.util.List;

import com.hibernate.test.pojo.Request;
import com.hibernate.test.pojo.Ride;
import com.hibernate.test.pojo.User;

public interface RequestServiceInterface {

	//This method will create a new ride in the system
	//@param Ride object with all user details
	public void createRequest(Request newRequest);
	
	//This method is used to update the status of the request
	//@param status-accepted or rejected
	public void respondToRequest(Long rideId, Long requestId, int actionType);
	
	//This method allows you to delete a request
	//@param identifier of the request
	public void cancelRequest(long requestId);
	
	//This method allows you to edit information for an existing request
	//@param Request object with updated request information
	public void editRequest(Request updatedRequest);
	
	//This method will fetch all the Requests
	public List<Request> getAllRequests();

	public List<Request> getAllRequestsForAUser(User requestedBy);

	public void createNewRequestRideMapping(Request request, Ride ride);
	public boolean isRideCompletelyFull(Ride ride);
	public boolean isRideForRequestFixed(Request request);
	public boolean isMappingExistingForRequestRideMap(Request request, Ride ride);

	public List<Request> getAllRequestsFilteredOnDateAndUser(Long rideId);
	public Request fetchRequest(Long requestId);
	public int createRequestRideMapping(Long[] selectedRequestIds, Long rideId);
}
