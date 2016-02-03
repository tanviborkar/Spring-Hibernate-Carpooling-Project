<%@page import="com.hibernate.test.pojo.Ride"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.jsp"></jsp:include>
<html>
<%
Ride editRide = (Ride)request.getAttribute("rideToEdit");
%>
<body>
<form id="edit_ride" action="javascript:submit_edit_ride();" method="post">
<label>Source : </label><input type="text" id="startPoint" name="startPoint" value="<%=editRide.getStartPoint() %>"/><br/>
<label>Destination : </label><input type="text" id="destination" name="destination" value="<%=editRide.getDestination() %>"/><br/>
<label>Start Time : </label><input type="text" id="startTime" name="startTime" value="<%=editRide.getStartTime()%>"/><br/>
<label>No. of Passengers : </label><input type="text" id="maxNoOfPassengers" name="maxNoOfPassengers" value="<%=editRide.getMaxNoOfPassengers()%>"/><br/>
<label>Price per User : </label><input type="text" id="pricePerUser" name="pricePerUser" value="<%=editRide.getPricePerUser() %>"/><br/>
<%
	if(editRide.isPickupOtherThanStartProvided())
	{
%>
	<label>Pickup other than start : </label><input type="checkbox" id="isPickupOtherThanStartProvided" name="isPickupOtherThanStartProvided" checked/><br/>
<%
	}
	else{
%>
		<label>Pickup other than start : </label><input type="checkbox" id="isPickupOtherThanStartProvided" name="isPickupOtherThanStartProvided"/><br/>
<%
	}
%>
<%
	if(editRide.isPriceNegotiable())
	{
%>
	<label>Price Negotiable : </label><input type="checkbox" id="isPriceNegotiable" name="isPriceNegotiable" checked/><br/>
<%
	}
	else{
%>
	<label>Price Negotiable : </label><input type="checkbox" id="isPriceNegotiable" name="isPriceNegotiable"/><br/>
<%
	}
%>
<label>Comments : </label><input type="text" id="comments" name="comments" value="<%=editRide.getComments()%>"/><br/>

<button type="submit">Update Ride</button>
<input type="hidden" id="rideId" name="rideId" value="<%=editRide.getRideId() %>"></input>
</form>
</body>
<script src="../resources/js/new_ride.js"></script>
</html>