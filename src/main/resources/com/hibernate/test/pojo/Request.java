package com.hibernate.test.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="request")
public class Request {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="request_Id")
	private long request_id;
	
	@Column(name="pickup_place")
	private String pickupPlace;
	
	@Column(name="destination")
	private String destination;
	
	@Temporal(TemporalType.TIMESTAMP)@Column(name="start_time")
	private Date startTime;
	
	@Column(name="comments")
	private String comments;
	
	@Temporal(TemporalType.TIMESTAMP)@Column(name="request_time")
	private Date requestTime;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User requestedBy;
	
	@OneToMany(mappedBy="request")
	private List<RequestRideMapping> requestRideMappings;

	public long getRequest_id() {
		return request_id;
	}

	public void setRequest_id(long request_id) {
		this.request_id = request_id;
	}

	public String getPickupPlace() {
		return pickupPlace;
	}

	public void setPickupPlace(String pickupPlace) {
		this.pickupPlace = pickupPlace;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public User getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(User requestedBy) {
		this.requestedBy = requestedBy;
	}

	public List<RequestRideMapping> getRequestRideMappings() {
		return requestRideMappings;
	}

	public void setRequestRideMappings(List<RequestRideMapping> requestRideMappings) {
		this.requestRideMappings = requestRideMappings;
	}
	
	
	
	
}