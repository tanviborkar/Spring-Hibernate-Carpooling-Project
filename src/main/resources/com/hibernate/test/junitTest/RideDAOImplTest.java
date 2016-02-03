package com.hibernate.test.junitTest;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hibernate.test.DAO.RideDAOImpl;
import com.hibernate.test.api.RideDAOInterface;
import com.hibernate.test.pojo.Ride;

public class RideDAOImplTest {

	@Autowired
	RideDAOInterface rideInterface;
	
	@Test
	public void testCreateRide() {
		//RideDAOInterface rideInterface = RideDAOImpl.getRideDAOImpl();
		Ride newRide = new Ride();
		newRide.setStartPoint("New Brunswick");
		newRide.setDestination("New York");
		newRide.setStartTime(new Date());
		newRide.setMaxNoOfPassengers(5);
		newRide.setPickupOtherThanStartProvided(true);
		newRide.setPricePerUser(10);
		newRide.setComments("passed");
		newRide.setPriceNegotiable(false);
		newRide.setRidePostedOn(new Date());
		
		//User rideOwner = new User()
		rideInterface.createRide(newRide);
	}

	/*@Test
	public void testEditRide() {
		RideDAOInterface rideInterface = RideDAOImpl.getRideDAOImpl();
		Ride newRide = rideInterface.fetchRide(1);
		assertNotNull(newRide);
		
		newRide.setStartPoint("Seacaucus");
		newRide.setPickupOtherThanStartProvided(false);
		newRide.setPricePerUser(50);
				
		//User rideOwner = new User()
		rideInterface.editRide(newRide);
	}

	@Test
	public void testDeleteRide() {
		RideDAOInterface rideInterface = RideDAOImpl.getRideDAOImpl();
		Ride newRide = rideInterface.fetchRide(1);
		assertNotNull(newRide);
			
		//User rideOwner = new User()
		rideInterface.deleteRide(newRide);;
	}

	@Test
	public void testFetchRide() {
		RideDAOInterface rideInterface = RideDAOImpl.getRideDAOImpl();
		
		//User rideOwner = new User()
		Ride ride = rideInterface.fetchRide(1);
		if(ride != null){
			System.out.println(ride);
		}
		else{
			System.out.println("Object not found");
		}
		assertNotNull(ride);
	}*/
}
