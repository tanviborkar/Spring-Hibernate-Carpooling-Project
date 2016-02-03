package com.hibernate.test.api;

import java.util.List;

import com.hibernate.test.pojo.Request;
import com.hibernate.test.pojo.RequestRideMapping;
import com.hibernate.test.pojo.Ride;
import com.hibernate.test.pojo.User;

public interface RideDAOInterface {

	public void createRide(Ride newRide);
	public void editRide(Ride updatedRide);
	public void deleteRide(long rideId);
	public Ride fetchRide(long rideId);
	public List<Ride> fetchUserRides(User user);
	public void addRequestToRide(RequestRideMapping mappingObj);
	public List<Ride> getAllRidesFilteredOnDateAndUser(Request request, List<Long> rideIds);
	public boolean createNewRideRequestMapping(Request request, Ride ride);
	public List<RequestRideMapping> getAllRideRequestMapping(Request request);
}
