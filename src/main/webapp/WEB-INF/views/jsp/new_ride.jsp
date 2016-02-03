<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.jsp"></jsp:include>
<html>
<body>
<div class="main_container">
<form id="new_ride" action="javascript:submit_new_ride();" method="post">
<label>Source : </label><input type="text" id="startPoint" name="startPoint"/><br/>
<label>Destination : </label><input type="text" id="destination" name="destination"/><br/>
<label>Start Time : </label><input type="text" id="startTime" name="startTime"/><br/>
<label>No. of Passengers : </label><input type="text" id="maxNoOfPassengers" name="maxNoOfPassengers"/><br/>
<label>Price per User : </label><input type="text" id="pricePerUser" name="pricePerUser"/><br/>
<label>Pickup other than start : </label><input type="checkbox" id="isPickupOtherThanStartProvided" name="isPickupOtherThanStartProvided"/><br/>
<label>Price Negotiable : </label><input type="checkbox" id="isPriceNegotiable" name="isPriceNegotiable"/><br/>
<label>Comments : </label><input type="text" id="comments" name="comments"/><br/>
<button type="submit">Post Ride</button>
</form>
</div>
</body>
<script src="../resources/js/new_ride.js"></script>
</html>