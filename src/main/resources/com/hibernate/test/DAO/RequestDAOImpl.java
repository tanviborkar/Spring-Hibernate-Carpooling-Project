package com.hibernate.test.DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
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
public class RequestDAOImpl extends CustomHibernateDaoSupport implements RequestDAOInterface {
	@Autowired
	RideDAOInterface rideDAO;
	
	public void createRequest(Request newRequest)
	{
		/*SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try
		{
			session.save(newRequest);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();*/
		/*SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(newRequest);*/
		try {
			getHibernateTemplate().save(newRequest);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editRequest(Request updatedRequest)
	{
		/*SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try
		{
			session.saveOrUpdate(updatedRequest);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();*/
		try {
			getHibernateTemplate().saveOrUpdate(updatedRequest);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteRequest(Request requestToDelete)
	{
		/*SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try
		{
			session.delete(requestToDelete);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();*/
	}
	
	public Request fetchRequest(long RequestId)
	{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		/*SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();*/
		
		try
		{
			Criteria criteria = session.createCriteria(Request.class);
			criteria.add(Restrictions.eq("request_id",RequestId));
			
			List<Request> result = criteria.list();
			if(result.isEmpty())
				return null;
			return result.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public List<Request> getAllRequests() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Request.class);
		return criteria.list();
	}

	public List<Request> getAllRequestsForAUser(User requestedBy) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Request.class);
		criteria.add(Restrictions.eq("requestedBy", requestedBy));
		criteria.add(Restrictions.ge("startTime", new Date()));
		List<Request> list = criteria.list();
		for(Request request : list){
			for(RequestRideMapping requestRideMapping : request.getRequestRideMappings()){
				requestRideMapping.getRide().getRideOwner().getEmailAddress();
				requestRideMapping.getRide().getRideOwner().getFirstName();
				requestRideMapping.getRide().getStartPoint();
				requestRideMapping.getRide().getDestination();
				requestRideMapping.getRide().getStartTime();
			}
		}
		return list;
	}
	
	/*public void createNewRequestRideMapping(Request request, Ride ride){
		if(!this.isMappingExistingForRequestRideMap(request, ride) &&
				!this.isRideCompletelyFull(ride) &&
				!this.isRideForRequestFixed(request)){
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			RequestRideMapping requestRideMapping = new RequestRideMapping();
			requestRideMapping.setRequest(request);
			requestRideMapping.setRide(ride);
			requestRideMapping.setRequestRideStatus(RequestRideStatus.PENDING);
			session.save(requestRideMapping);
		}
	}*/
	
	public boolean createNewRequestRideMapping(Request request, Ride ride){
		if(!this.isRideCompletelyFull(ride)){
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			RequestRideMapping requestRideMapping = new RequestRideMapping();
			requestRideMapping.setRequest(request);
			requestRideMapping.setRide(ride);
			requestRideMapping.setRequestRideStatus(RequestRideStatus.PENDING);
			requestRideMapping.setPendingWith(request.getRequestedBy());
			session.save(requestRideMapping);
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isMappingExistingForRequestRideMap(Request request, Ride ride){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(RequestRideMapping.class);
		criteria.add(Restrictions.eq("request", request));
		criteria.add(Restrictions.eq("ride", ride));
		criteria.setProjection(Projections.rowCount());
		int count = (Integer) criteria.uniqueResult();
		if (count!=0){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isRideForRequestFixed(Request request){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(RequestRideMapping.class);
		criteria.add(Restrictions.eq("request", request));
		criteria.add(Restrictions.eq("requestRideStatus", RequestRideStatus.ACCEPTED));
		criteria.setProjection(Projections.rowCount());
		int count = (Integer) criteria.uniqueResult();
		if (count!=0){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isRideCompletelyFull(Ride ride){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(RequestRideMapping.class);
		criteria.add(Restrictions.eq("ride", ride));
		criteria.add(Restrictions.eq("requestRideStatus", RequestRideStatus.ACCEPTED));
		criteria.setProjection(Projections.rowCount());
		long count = (Long) criteria.uniqueResult();
		if (ride.getMaxNoOfPassengers()>count){
			return false;
		} else {
			return true;
		}
	}
	
	public List<Request> getAllRequestsFilteredOnDateAndUser(Ride ride, List<Long> requestIds) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Request.class);
		//criteria.add(Restrictions.eq("startTime", ride.getStartTime()));
		criteria.add(Restrictions.ge("startTime", DateManipulation.subtractDays(ride.getStartTime(), 1))); 
		criteria.add(Restrictions.lt("startTime", DateManipulation.addDays(ride.getStartTime(), 1)));
		criteria.add(Restrictions.ne("requestedBy", ride.getRideOwner()));
		if(!requestIds.isEmpty()){
			criteria.add(Restrictions.not(Restrictions.in("request_id", requestIds)));
		}
		return criteria.list();
	}
	
	public List<RequestRideMapping> getAllRideRequestMapping(Ride ride){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(RequestRideMapping.class);
		criteria.add(Restrictions.eq("ride", ride));
		return criteria.list();
	}
	
	public void editRequestRideMapping(RequestRideMapping updateMapping)
	{
		try {
			getHibernateTemplate().saveOrUpdate(updateMapping);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void respondToRequest(Long rideId, Long requestId, int actionType){
		Ride ride = rideDAO.fetchRide(rideId);
		if(isRideCompletelyFull(ride)){
			changeStatusOfAllRequestsForRide(ride);
		}
		else{
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			Criteria criteria = session.createCriteria(RequestRideMapping.class);
			criteria.createAlias("request", "request");
			criteria.add(Restrictions.eq("request.request_id", requestId));
			criteria.createAlias("ride", "ride");
			criteria.add(Restrictions.eq("ride.rideId", rideId));
			List<RequestRideMapping> reqRideList = criteria.list();
			if(!reqRideList.isEmpty()){
				if(actionType == 1){
					reqRideList.get(0).setRequestRideStatus(RequestRideStatus.ACCEPTED);
						
				}
				else{
					reqRideList.get(0).setRequestRideStatus(RequestRideStatus.REJECTED);
				}
				editRequestRideMapping(reqRideList.get(0));
			}
		}
	}
	
	public void changeStatusOfAllRequestsForRide(Ride ride){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(RequestRideMapping.class);
		criteria.add(Restrictions.eq("ride", ride));
		criteria.add(Restrictions.ne("requestRideStatus", RequestRideStatus.ACCEPTED));
		List<RequestRideMapping> reqRideList = criteria.list();
		
		for(RequestRideMapping mapping : reqRideList){
			mapping.setRequestRideStatus(RequestRideStatus.FULL_CAPACITY);
			editRequestRideMapping(mapping);
		}
	}
}
