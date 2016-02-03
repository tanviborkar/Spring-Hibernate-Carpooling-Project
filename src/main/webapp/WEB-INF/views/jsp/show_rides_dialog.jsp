<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.hibernate.test.pojo.Ride"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<%
List<Ride> rideList = (ArrayList<Ride>)request.getAttribute("allRidesForRequests");
Long requestId = Long.parseLong((String)request.getAttribute("requestId"));
%>
<body>
		<table>
		<%if(rideList.size()>0){%>
		<tr>
		<th></th>
		<th>Source</th>
		<th>Destination</th>
		<th>Start Time</th>
		<th>No. of Passengers</th>
		<th>Price per user</th>
		<th>Additional Pickup Points Available</th>
		<th>Price Negotiable</th>
		<th>Ride Owner</th>
		</tr>
<%for(Ride ride : rideList){
	%><tr>
		<td align="center"><input type="checkbox" id="ride_<%=rideList.indexOf(ride) %>" name="rideCheckbox" value="<%=ride.getRideId()%>"/></td>
		<td align="center"><%=ride.getStartPoint()%> </td>
		<td align="center"> <%=ride.getDestination()%> </td>
		<td align="center"> <%=ride.getStartTime()%></td> 
		<td align="center"> <%=ride.getMaxNoOfPassengers()%></td> 
		<td align="center"> <%=ride.getPricePerUser()%></td> 
<%
	if(ride.isPickupOtherThanStartProvided())
	{
%>
		<td align="center">Yes</td> 
<%
	}
	else{
%>
		<td align="center">No</td>
<%
	}
%>
<%
	if(ride.isPriceNegotiable())
	{
%>
		<td align="center">Yes</td> 
<%
	}
	else{
%>
		<td align="center">No</td>
<%
	}
%> 
		<td align="center"><%=ride.getRideOwner().getFirstName()+" "+ride.getRideOwner()
		.getLastName()%></td></tr>
<%
} %>
		<%}else{
			%><tr><td align="center">---No matching rides. Please try again later.---</td></tr><%
		} %>
	</table>
	<input type="button" value="Add Rides" onclick="mapRideToRequests(<%= requestId%>);">
	<input type="button" value="Cancel" onclick="closeDialog();">	
</body>
</html>