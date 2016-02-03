package com.hibernate.test.api;

import java.util.List;

import com.hibernate.test.pojo.Request;
import com.hibernate.test.pojo.RequestRideMapping;
import com.hibernate.test.pojo.Ride;
import com.hibernate.test.pojo.User;

public interface RequestDAOInterface {

	public void createRequest(Request newRequest);
	public void editRequest(Request updatedRequest);
	public void deleteRequest(Request RequestToDelete);
	public Request fetchRequest(long RequestId);
	public List<Request> getAllRequests();
	public List<Request> getAllRequestsForAUser(User requestedBy);

	public boolean createNewRequestRideMapping(Request request, Ride ride);
	public boolean isRideCompletelyFull(Ride ride);
	public boolean isRideForRequestFixed(Request request);
	public boolean isMappingExistingForRequestRideMap(Request request, Ride ride);
	public List<RequestRideMapping> getAllRideRequestMapping(Ride ride);
	public List<Request> getAllRequestsFilteredOnDateAndUser(Ride ride, List<Long> requestIds);
	public void respondToRequest(Long rideId, Long requestId, int actionType);
}
