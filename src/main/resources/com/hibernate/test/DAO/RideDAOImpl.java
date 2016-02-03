package com.hibernate.test.DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.hibernate.test.api.RequestDAOInterface;
import com.hibernate.test.api.RideDAOInterface;
import com.hibernate.test.pojo.Request;
import com.hibernate.test.pojo.RequestRideMapping;
import com.hibernate.test.pojo.RequestRideStatus;
import com.hibernate.test.pojo.Ride;
import com.hibernate.test.pojo.User;
import com.hibernate.test.util.DateManipulation;
import com.hibernate.test.util.HibernateUtil;

@Repository
public class RideDAOImpl extends CustomHibernateDaoSupport implements RideDAOInterface {
	
	@Autowired
	RequestDAOInterface requestDAO;
	
	public void createRide(Ride newRide)
	{
		/*SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try
		{
			session.save(newRide);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();*/

		try {
			getHibernateTemplate().save(newRide);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editRide(Ride updatedRide)
	{
		try {
			getHibernateTemplate().saveOrUpdate(updatedRide);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try
		{
			session.saveOrUpdate(updatedRide);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/

	}
	
	public List<Ride> fetchUserRides(User postedBy){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Ride.class);
		criteria.add(Restrictions.eq("rideOwner", postedBy));
		criteria.add(Restrictions.ge("startTime", new Date()));
		List<Ride> list = criteria.list();
		for(Ride ride : list){
			for(RequestRideMapping requestRideMapping : ride.getRequestRideMappings()){
				requestRideMapping.getRequest().getRequestedBy().getEmailAddress();
				requestRideMapping.getRequest().getRequestedBy().getFirstName();
				requestRideMapping.getRequest().getPickupPlace();
				requestRideMapping.getRequest().getDestination();
				requestRideMapping.getRequest().getStartTime();
			}
		}
		return list;
		/*SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try
		{
			Criteria criteria = session.createCriteria(Ride.class);
			criteria.createAlias("rideOwner", "user"); 
			criteria.add(Restrictions.eq("user.userId", userId));
			List<Ride> userRideList = criteria.list();
			if(userRideList.isEmpty())
				return null;
			session.close();
			return userRideList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.close();
			return null;
		}*/
	}
	
	public Ride fetchRide(long rideId)
	{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Ride.class);
		criteria.add(Restrictions.eq("rideId",rideId));
		List<Ride> result = criteria.list();
		if(result.isEmpty())
			return null;
		return result.get(0);
		//return null;
		/*SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try
		{
			Criteria criteria = session.createCriteria(Ride.class);
			criteria.add(Restrictions.eq("rideId",rideId));
			
			List<Ride> result = criteria.list();
			if(result.isEmpty())
				return null;
			session.close();
			return result.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.close();
			return null;
		}*/

	}
	
	public void deleteRide(long rideId)
	{
		/*List<RequestRideMapping> result = fetchAllRequestRideMappings(rideId);
		if(result != null){
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			try
			{
				for(RequestRideMapping mappingObj : result){
					session.delete(mappingObj);
				}
				session.getTransaction().commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			session.close();
		}*/
	}
	
	public List<RequestRideMapping> fetchAllRequestRideMappings(long rideId)
	{
		return null;
		/*SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try
		{
			Criteria criteria = session.createCriteria(RequestRideMapping.class);
			criteria.createAlias("ride", "ride");
			criteria.add(Restrictions.eq("ride.rideId",rideId));
			
			List<RequestRideMapping> result = criteria.list();
			if(result.isEmpty())
				return null;
			session.close();
			return result;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.close();
			return null;
		}*/
	}

	public List<Ride> getAllRidesFilteredOnDateAndUser(Request request, List<Long> rideIds) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Ride.class);
		//criteria.createAlias("rideOwner", arg1)
		criteria.add(Restrictions.ge("startTime", DateManipulation.subtractDays(request.getStartTime(), 1))); 
		criteria.add(Restrictions.lt("startTime", DateManipulation.addDays(request.getStartTime(), 1)));
		//criteria.add(Restrictions.eq("startTime", request.getStartTime()));
		criteria.add(Restrictions.ne("rideOwner", request.getRequestedBy()));
		if(!rideIds.isEmpty()){
			criteria.add(Restrictions.not(Restrictions.in("rideId", rideIds)));
		}
		return criteria.list();
	}
	
	public List<RequestRideMapping> getAllRideRequestMapping(Request request){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(RequestRideMapping.class);
		criteria.add(Restrictions.eq("request", request));
		return criteria.list();
	}
	
	public boolean createNewRideRequestMapping(Request request, Ride ride){
		if(!requestDAO.isRideCompletelyFull(ride)){
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			RequestRideMapping requestRideMapping = new RequestRideMapping();
			requestRideMapping.setRequest(request);
			requestRideMapping.setRide(ride);
			requestRideMapping.setRequestRideStatus(RequestRideStatus.PENDING);
			requestRideMapping.setPendingWith(ride.getRideOwner());
			session.save(requestRideMapping);
			return true;
		}
		else{
			return false;
		}
	}
	
	public void addRequestToRide(RequestRideMapping mappingObj) {
		// TODO Auto-generated method stub
		
	}
}
