<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.jsp"></jsp:include>
<html>
<body>
<div class="main_container">
<form id="new_request" action="javascript:submit_new_request();" method="post">
<label>Pick Up : </label><input type="text" id="pickupPlace" name="pickupPlace"/><br/>
<label>Destination : </label><input type="text" id="destination" name="destination"/><br/>
<label>Pickup Time : </label><input type="text" id="startTime" name="startTime"/><br/>
<label>Comments : </label><input type="text" id="comments" name="comments"/><br/>
<button type="submit">Submit Request</button>
</form>
</div>
</body>
<script src="../resources/js/new_request.js"></script>
</html>