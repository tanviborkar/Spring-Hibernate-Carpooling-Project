<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Lets Pool</title>
<link href="../resources/css/jquery.datetimepicker.css" type="text/css"
	rel="stylesheet">
<link href="../resources/css/common.css" type="text/css"
	rel="stylesheet">
<link
	href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="../resources/js/jquery.datetimepicker.full.js"></script>
<script src="../resources/js/jquery.alerts.js"></script>
<script src="../resources/js/common.js"></script>
<script src="../resources/js/jquery.blockUI.js"></script>
</head>
<body>

	<table>
		<tr>
			<th colspan="5"><img alt="Lets Pool"
				src="../resources/images/logo_header.png" /></th>
		</tr>
		<tr>
			<th>
				<div id="fb_login_button">
					<fb:login-button scope="public_profile,email"
						onlogin="checkLoginState();"></fb:login-button>
				</div>
				<div id="status"></div>
			</th>
			<th class="main_container">
				<form id="showRequests" method="post" action="../reqmc/showRequests">
					<input type="hidden" id="userTypeId" name="userTypeId"
						class="userTypeId"> <input type="submit" value="Requests">
				</form>
			</th>
			<th class="main_container">
				<form method="post" action="../reqmc/formNewRequest">
					<input type="submit" value="New Request">
				</form>
			</th>
			<th class="main_container">
				<form id="showRides" method="post" action="../rmc/showUserRides">
					<input type="hidden" id="userTypeId" name="userTypeId"
						class="userTypeId"> <input type="submit" value="My Rides">
				</form>
			</th>
			<th class="main_container">
				<form method="post" action="../rmc/formNewRide">
					<input type="submit" value="New Ride">
				</form>
			</th>
		</tr>
	</table>
	<div class="main_container">
		<table>
			<tr></tr>
		</table>
	</div>
</body>
</html>