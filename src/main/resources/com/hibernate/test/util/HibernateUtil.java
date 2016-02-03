package com.hibernate.test.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;  
import org.hibernate.service.ServiceRegistry;

import com.hibernate.test.pojo.Request;
import com.hibernate.test.pojo.RequestRideMapping;
import com.hibernate.test.pojo.Ride;
import com.hibernate.test.pojo.User;  

public class HibernateUtil {  

	private static final SessionFactory sessionFactory;  

	static {  
		Configuration configuration=new Configuration().configure(); // configures settings from hibernate.cfg.xml

		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		//configuration.addAnnotatedClass(Student.class);
		configuration.addAnnotatedClass(User.class);
		configuration.addAnnotatedClass(Ride.class);
		configuration.addAnnotatedClass(Request.class);
		configuration.addAnnotatedClass(RequestRideMapping.class);
		
		// If you miss the below line then it will complaing about a missing dialect setting
		serviceRegistryBuilder.applySettings(configuration.getProperties());

		ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}  

	public static SessionFactory getSessionFactory() {  
		return sessionFactory;  
	}  
}  