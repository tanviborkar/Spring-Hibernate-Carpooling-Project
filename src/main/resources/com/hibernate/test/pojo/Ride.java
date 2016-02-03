package com.hibernate.test.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ride")
public class Ride {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ride_id")
	private long rideId;
	
	@Column(name="start_point")
	private String startPoint;
	
	@Column(name="destination")
	private String destination;
	
	@Column(name="other_pickup_available")
	private boolean isPickupOtherThanStartProvided;
	
	@Column(name="max_passengers")
	private int maxNoOfPassengers;
	
	@Temporal(TemporalType.TIMESTAMP)@Column(name="start_time")
	private Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)@Column(name="posted_on")
	private Date ridePostedOn; 
	
	@Column(name="price_per_user")
	private float pricePerUser;
	
	@Column(name="negotiable")
	private boolean isPriceNegotiable;
	
	@Column(name="comments")
	private String comments;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User rideOwner;
	
	@OneToMany(mappedBy="ride", fetch = FetchType.LAZY)
	private List<RequestRideMapping> requestRideMappings;

	public long getRideId() {
		return rideId;
	}

	public void setRideId(long rideId) {
		this.rideId = rideId;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public boolean isPickupOtherThanStartProvided() {
		return isPickupOtherThanStartProvided;
	}

	public void setPickupOtherThanStartProvided(boolean isPickupOtherThanStartProvided) {
		this.isPickupOtherThanStartProvided = isPickupOtherThanStartProvided;
	}

	public int getMaxNoOfPassengers() {
		return maxNoOfPassengers;
	}

	public void setMaxNoOfPassengers(int maxNoOfPassengers) {
		this.maxNoOfPassengers = maxNoOfPassengers;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getRidePostedOn() {
		return ridePostedOn;
	}

	public void setRidePostedOn(Date ridePostedOn) {
		this.ridePostedOn = ridePostedOn;
	}

	public float getPricePerUser() {
		return pricePerUser;
	}

	public void setPricePerUser(float pricePerUser) {
		this.pricePerUser = pricePerUser;
	}

	public boolean isPriceNegotiable() {
		return isPriceNegotiable;
	}

	public void setPriceNegotiable(boolean isPriceNegotiable) {
		this.isPriceNegotiable = isPriceNegotiable;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public User getRideOwner() {
		return rideOwner;
	}

	public void setRideOwner(User rideOwner) {
		this.rideOwner = rideOwner;
	}

	public List<RequestRideMapping> getRequestRideMappings() {
		return requestRideMappings;
	}


	public void setRequestRideMappings(List<RequestRideMapping> requestRideMappings) {
		this.requestRideMappings = requestRideMappings;
	}

	@Override
	public String toString() {
		return "Ride [startPoint=" + startPoint + ", destination=" + destination + ", isPickupOtherThanStartProvided="
				+ isPickupOtherThanStartProvided + ", maxNoOfPassengers=" + maxNoOfPassengers + ", startTime="
				+ startTime + ", ridePostedOn=" + ridePostedOn + ", pricePerUser=" + pricePerUser
				+ ", isPriceNegotiable=" + isPriceNegotiable + ", comments=" + comments + ", rideOwner=" + rideOwner
				+ "]";
	}

	
}
