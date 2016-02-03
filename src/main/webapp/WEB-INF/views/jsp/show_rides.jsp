<%@page import="com.hibernate.test.pojo.RequestRideMapping"%>
<%@page import="com.hibernate.test.pojo.RequestRideStatus"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hibernate.test.pojo.Ride"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<script>
         $(function() {
            $( "#dialogForRequests" ).dialog({
               autoOpen: false,
               title:"Add Requests"
            });
            /*$( "#openDialog" ).click(function() {
               $( "#dialogForRequests" ).dialog( "open" );
            });*/
         });
      </script>
<%
Long currentUserId = (Long)request.getAttribute("userId");
%>
<body>
	<div class="main_container">
	<div id="dialogForRequests"></div>
		<table>
		<tr>
		<th>Source</th>
		<th>Destination</th>
		<th>Start Time</th>
		<th>No. of Passengers</th>
		<th>Price per user</th>
		<th>Additional Pickup Points Available</th>
		<th>Price Negotiable</th>
		<th>Actions</th>
		</tr>
<%ArrayList<Ride> userRideList =(ArrayList<Ride>)request.getAttribute("userRideList"); 
for(Ride ride : userRideList){
%>	<tr>
		<td align="left"><%=ride.getStartPoint()%> </td>
		<td align="left"> <%=ride.getDestination()%> </td>
		<td align="left"> <%=ride.getStartTime()%></td> 
		<td align="left"> <%=ride.getMaxNoOfPassengers()%></td> 
		<td align="left"> <%=ride.getPricePerUser()%></td> 
<%
	if(ride.isPickupOtherThanStartProvided())
	{
%>
		<td align="left">Yes</td> 
<%
	}
	else{
%>
		<td align="left">No</td>
<%
	}
%>
<%
	if(ride.isPriceNegotiable())
	{
%>
		<td align="left">Yes</td> 
<%
	}
	else{
%>
		<td align="left">No</td>
<%
	}
%>
		<td> 
		<form method="post" action="../rmc/editRide">
			<input type="hidden" id="rideId" name="rideId" value="<%=ride.getRideId() %>"></input>
			<!-- <input type="submit" value="Edit Ride"> -->
		</form>
		<input id="openDialog" type="button" onclick="showAllRequestsFilteredOnDate(<%=ride.getRideId()%>);" value="View All Requests"></input>
		<!--  <input type="button" onclick="deleteUsersRide(<%=ride.getRideId()%>);" value="Delete Ride"></input> 
			<input type="button" onclick="viewRideRequests(<%=ride.getRideId()%>);" value="View Requests"></input> 
			<input type="button" onclick="viewAndPostComments(<%=ride.getRideId()%>);" value="View & Post Comments"></input> 			
		--></td>
	</tr><%
	if(ride.getRequestRideMappings()!=null && ride.getRequestRideMappings().size()>0){
		for(RequestRideMapping requestRideMapping : ride.getRequestRideMappings()){
			%>
			<tr>
				<td colspan="6"><i>&nbsp &nbsp <b><%=requestRideMapping.getRequest().getRequestedBy().getFirstName() %>(<%=requestRideMapping.getRequest().getRequestedBy().getEmailAddress() %>)</b> needs to be picked up from <b><%=requestRideMapping.getRequest().getPickupPlace() %></b> for <b><%=requestRideMapping.getRequest().getDestination() %></b> at <b><%=requestRideMapping.getRequest().getStartTime() %></b></i></td>
				<td>Status: <%= requestRideMapping.getRequestRideStatus()%></td>
				<td>
				<%
				if(currentUserId == requestRideMapping.getPendingWith().getUserId()){
					if(requestRideMapping.getRequestRideStatus().equals(RequestRideStatus.PENDING)){	
				%>
					<input type="button" onclick="respondToRequest(<%=ride.getRideId() %>,<%=requestRideMapping.getRequest().getRequest_id() %>,1);" value="Accept Request"></input> 
					<input type="button" onclick="respondToRequest(<%=ride.getRideId() %>,<%=requestRideMapping.getRequest().getRequest_id() %>, 2);" value="Reject Request"></input> 	
				<%
					}
					else{
				%>
					<input type="button" value="Accept Request" disabled></input> 
					<input type="button" value="Reject Request" disabled></input> 	
				<%
					}
				}
				%>
				</td>
			</tr>
			<%
		}
	}
} %>
</table>
</div>
<input type="hidden" id="pageType" value="rides"/>
</body>
<script src="../resources/js/show_ride.js"></script>
<script src="../resources/js/show_request.js"></script>
</html>