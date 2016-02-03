package com.hibernate.test.util;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class DateManipulation {

	public static Date addDays(Date currentDate, int noOfDays){
		//Using Apache commons DateUtils to increment and decrement date in Java 
		Date increment = DateUtils.addDays(currentDate, noOfDays); 
		//System.out.println("Increment one day to date in Java using DateUtils " + increment); 
		return increment;
	}
	
	public static Date subtractDays(Date currentDate, int noOfDays){
		Date decrement = DateUtils.addDays(currentDate, -1 * noOfDays); 
		//System.out.println("Decrement one day from date in Java " + decrement); 
		return decrement;
	}
}
