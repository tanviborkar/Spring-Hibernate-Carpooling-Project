<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.hibernate.test.pojo.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<%
List<Request> requestList = (ArrayList<Request>)request.getAttribute("allRequestsForRide");
Long rideId = Long.parseLong((String)request.getAttribute("rideId"));
%>
<body>
		<table>
		<%if(requestList.size()>0){%>
		<tr>
		<th></th>
		<th>Pickup</th>
		<th>Destination</th>
		<th>Pickup Time</th>
		</tr>
<%for(Request requests : requestList){
	%><tr>
		<td align="center"><input type="checkbox" id="request_<%=requestList.indexOf(requests) %>" name="requestCheckbox" value="<%=requests.getRequest_id()%>"/></td>
		<td align="center"><%=requests.getPickupPlace()%> </td>
		<td align="center"> <%=requests.getDestination()%> </td>
		<td align="center"> <%=requests.getStartTime()%></td> </tr><%
} %>
		<%}else{ %>
		<tr><td>---No matching requests. Please try again later.---</td></tr>
		<%} %>
	</table>
	<input type="button" value="Add Requests" onclick="mapRequestsToRide(<%= rideId%>);">
	<input type="button" value="Cancel" onclick="closeDialog();">	
</body>
</html>