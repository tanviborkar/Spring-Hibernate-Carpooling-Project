/**
 * 
 */

// This is called with the results from from FB.getLoginStatus().
function statusChangeCallback(response) {
	console.log('statusChangeCallback');
	console.log(response);
	// The response object is returned with a status field that lets the
	// app know the current login status of the person.
	// Full docs on the response object can be found in the documentation
	// for FB.getLoginStatus().
	if (response.status === 'connected') {
		// Logged into your app and Facebook.
		testAPI();
	} else if (response.status === 'not_authorized') {
		// The person is logged into Facebook, but not your app.
		$('#fb_login_button').show();
		$('.main_container').hide();
		document.getElementById('status').innerHTML = 'Please log ' +
		'into this app.';
	} else {
		// The person is not logged into Facebook, so we're not sure if
		// they are logged into this app or not.
		$('#fb_login_button').show();
		$('.main_container').hide();
		document.getElementById('status').innerHTML = 'Please log ' +
		'into Facebook.';
	}
}

// This function is called when someone finishes with the Login
// Button.  See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
}

window.fbAsyncInit = function() {
	FB.init({
		appId      : '779300492196612',
		cookie     : true,  // enable cookies to allow the server to access 
		// the session
		xfbml      : true,  // parse social plugins on this page
		version    : 'v2.4' // use version 2.2
	});

	// Now that we've initialized the JavaScript SDK, we call 
	// FB.getLoginStatus().  This function gets the state of the
	// person visiting this page and can return one of three states to
	// the callback you provide.  They can be:
	//
	// 1. Logged into your app ('connected')
	// 2. Logged into Facebook, but not your app ('not_authorized')
	// 3. Not logged into Facebook and can't tell if they are logged into
	//    your app or not.
	//
	// These three cases are handled in the callback function.

	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});

};

// Load the SDK asynchronously
(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id)) return;
	js = d.createElement(s); js.id = id;
	js.src = "//connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Here we run a very simple test of the Graph API after login is
/*// successful.  See statusChangeCallback() for when this call is made.
function testAPI() {
	console.log('Welcome!  Fetching your information.... ');
	$('#fb_login_button').hide();
	FB.api('/me', function(response) {
		console.log('Successful login for: ' + response.name);
		document.getElementById('status').innerHTML =
			'Logged in as, ' + response.name + '! <a href="javascript:logout();">Logout</a>';
	});
}*/
function testAPI() {
	console.log('Welcome!  Fetching your information.... ');
	$('#fb_login_button').hide();
	$('.main_container').show();
	FB.api('/me?fields=first_name,last_name,email', function(response) {
		//console.log('Successful login for: ' + response.name);
		
		$.ajax({
			url: "../first/fbToLP",
			type: "POST",
		    data : response,
			success: function(result){
				$('.userTypeId').val(response.id);
				//alert("submitted");
				/*if(typeof(postFilter!=undefined)){
					window["postFilter"](response.id);
				}*/
			},
			error: function(result){
				alert("error");
			}
		});
		
		document.getElementById('status').innerHTML =
			'Logged in as ' + response.first_name + '. <a href="javascript:logout();">Logout</a>';
	});
}

function logout(){
	FB.logout(function(response) {
		statusChangeCallback(response);
	});
}

$(document).ready(function() {
	$.ajaxSetup({ cache: true });
	$.getScript('//connect.facebook.net/en_US/sdk.js', function(){
		FB.init({
			appId: '779300492196612',
			version: 'v2.4' // or v2.0, v2.1, v2.2, v2.3
		});     
		$('#loginbutton,#feedbutton').removeAttr('disabled');
	});
});

function respondToRequest(rideId, requestId, actionType){
	var pageType = $("#pageType").val();
	var param = {rideId:rideId, requestId:requestId, actionType:actionType};
	$.ajax({
		url: "../cmc/respondToRequest",
		type: "POST",
		data : param,
		success: function(data){
			if(pageType == "request"){
				$("#showRequests").submit();
			}
			else{
				$("#showRides").submit();
			}
		},
		error: function(result){
			alert("error");
		}
	});	
}
