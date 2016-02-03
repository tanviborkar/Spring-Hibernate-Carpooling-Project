function editUsersRide(rideId){

	var param = {rideId:rideId};
	$.ajax({
		url: "../rmc/editRide",
		type: "POST",
		data : param,
		success: function(result){
			alert("submitted");
		},
		error: function(result){
			alert("error");
		}
	});
}

function showAllRidesFilteredOnDate(requestId){
	var param = {requestId:requestId};
	$.ajax({
		url: "../rmc/showAllFilteredRides",
		type: "POST",
		data : param,
		success: function(data){
			$("#dialogForRides").html(data);	
			$("#dialogForRides").dialog('open'); 
			return false;
		},
		error: function(result){
			alert("error");
		}
	});			
}

function mapRideToRequests(requestId){
	var selectedRideIds = [];
	
	if(($('input[name="rideCheckbox"]:checked').size())>0){
		$('input[name="rideCheckbox"]:checked').each(function() {
			selectedRideIds.push(this.value);
		});
		
		var param = {selectedRideIds:selectedRideIds, requestId:requestId};
		$.ajax({
			url: "../rmc/mapRideToRequests",
			type: "POST",
		    data: param,
		    traditional:true,
		    success: function(data) {
		    	closeDialog();
		    	if(data == '1'){
		    		alert("All ride requests submitted successfully");
		    	}
		    	if(data == '0'){
		    		alert("Maximum capacity for the ride has been reached. Some ride requests submitted successfully");
		    	}
		    	if(data == '-1'){
		    		alert("Maximum capacity for the ride has been reached.");
		    	}
		    },
		    error: function(e) {
		    	alert("error");
		    }
		});
	} else{
		alert("There is no request selected to map to.")
	}
	
}

function closeDialog(){
	$("#dialogForRides").dialog('close');
}