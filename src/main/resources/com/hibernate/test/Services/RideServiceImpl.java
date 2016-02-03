package com.hibernate.test.Services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.test.api.RequestDAOInterface;
import com.hibernate.test.api.RideDAOInterface;
import com.hibernate.test.pojo.Request;
import com.hibernate.test.pojo.RequestRideMapping;
import com.hibernate.test.pojo.RequestRideStatus;
import com.hibernate.test.pojo.Ride;
import com.hibernate.test.pojo.User;

@Service
@Transactional
public class RideServiceImpl implements com.hibernate.test.api.RideServiceInterface {
	
	@Autowired
	RideDAOInterface rideDAO;
	@Autowired
	RequestDAOInterface requestDAO;

	public void createRide(Ride newRide) {
		rideDAO.createRide(newRide);	
	}

	public void editRide(Ride updatedRide) {
		rideDAO.editRide(updatedRide);	
	}

	public void deleteRide(long rideId) {
		//Ride rideToDelete = rideDAO.fetchRide(rideId);
		//if(rideToDelete != null){
			rideDAO.deleteRide(rideId);
		//}
	}

	public List<Ride> getUserRides(User user) {
		return rideDAO.fetchUserRides(user);	
	}
	
	public Ride fetchRide(long rideId) {
		return rideDAO.fetchRide(rideId);
	}
	
	public void addRequestToRide(Request request, Ride ride){
		RequestRideMapping mappingObj = new RequestRideMapping();
		mappingObj.setRequest(request);
		mappingObj.setRide(ride);
		mappingObj.setRequestRideStatus(RequestRideStatus.PENDING);
		rideDAO.addRequestToRide(mappingObj);
	}
	
	public List<Ride> getAllRidesFilteredOnDateAndUser(Long requestId){
		Request request = requestDAO.fetchRequest(requestId);
		List<RequestRideMapping> requestRideList = rideDAO.getAllRideRequestMapping(request);
		List<Long> rideIds = new ArrayList<Long>();
		for(RequestRideMapping requestRide : requestRideList){
			rideIds.add(requestRide.getRide().getRideId());
		}
		return rideDAO.getAllRidesFilteredOnDateAndUser(request, rideIds);
	}
	
	public int createRideRequestMapping(Long[] selectedRideIds, Long requestId){
		int countOfMapping=0;
		Request request = requestDAO.fetchRequest(requestId);
		for(int i=0; i<selectedRideIds.length ;i++){
			Ride ride = fetchRide(selectedRideIds[i]);
			if(rideDAO.createNewRideRequestMapping(request, ride)){
				countOfMapping++;
			}
		}
		if(countOfMapping == selectedRideIds.length){
			return 1;
		}
		else if((countOfMapping < selectedRideIds.length) && (countOfMapping > 0)){
			return 0;
		}
		else {
			return -1;
		}
	}
}
