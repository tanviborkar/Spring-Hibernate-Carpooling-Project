package com.hibernate.test.demo;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.hibernate.test.pojo.Request;
import com.hibernate.test.pojo.RequestRideMapping;
import com.hibernate.test.pojo.RequestRideStatus;
import com.hibernate.test.pojo.Ride;
import com.hibernate.test.pojo.User;
import com.hibernate.test.util.HibernateUtil;  


public class DemoFirst {  

	/*public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  
		Session session = sessionFactory.openSession();  
		session.beginTransaction();  

		try {
			Student student = new Student();  
			student.setFirstName("Stro");  
			student.setAge(26);  

			session.save(student);  
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		session.close();  

	}  

	@Test
	public void saveAUser(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  
		Session session = sessionFactory.openSession();  
		session.beginTransaction(); 
		
		User user = new User();
		user.setFirstName("Pappu");
		user.setEmailAddress("sdfasdf");
		user.setLastName("Pappu");
		user.setMobileNo("sadfsagasd");
		user.setUsername("sadfasf");
		user.setPassword("asdfsdfg");
		
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void saveARequest(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  
		Session session = sessionFactory.openSession();  
		session.beginTransaction(); 
		
		Request request = new Request();
		request.setDestination("TD");
		request.setPickupPlace("PP");
		request.setRequestTime(new Date());
		request.setStartTime(new Date());
		User requestedBy = new User();
		requestedBy.setUserId(1L);
		request.setRequestedBy(requestedBy);
		
		session.save(request);
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void saveARide(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  
		Session session = sessionFactory.openSession();  
		session.beginTransaction(); 
		
		Ride ride = new Ride();
		ride.setStartPoint("SP");
		ride.setDestination("D");
		User rideOwner = new User();
		rideOwner.setUserId(1L);
		ride.setRideOwner(rideOwner);
		
		session.save(ride);
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void saveRRMapping(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  
		Session session = sessionFactory.openSession();  
		session.beginTransaction(); 
		
		Ride ride = new Ride();
		ride.setRideId(1L);
		
		Request request = new Request();
		request.setRequest_id(2L);

		RequestRideMapping mapping = new RequestRideMapping();
		mapping.setRequest(request);
		mapping.setRide(ride);
		mapping.setRequestRideStatus(RequestRideStatus.PENDING);
		
		session.save(mapping);
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void getRide(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  
		Session session = sessionFactory.openSession();  
		session.beginTransaction(); 
		
		Criteria criteria = session.createCriteria(Ride.class);
		//criteria.add(Restrictions.idEq(1L));
		criteria.add(Restrictions.eq("rideId", 1L));
		Ride ride = (Ride)criteria.uniqueResult();
		for(RequestRideMapping mapping: ride.getRequestRideMappings()){
			System.out.println(mapping.getRequest().getPickupPlace()+" to "+mapping.getRequest().getDestination());
		}

		session.close();
	}*/
}  