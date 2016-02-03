function postFilter(id){
	//alert(id);
}

function showAllRequestsFilteredOnDate(rideId){
	FB.api(
		"/me?fields=id",
		function (response) {
			if (response.id!=undefined){
				var param = {rideId:rideId,
						userTypeId:response.id};
				$.ajax({
					url: "../reqmc/showAllFilteredRequests",
					type: "POST",
					data : param,
					success: function(data){
						$("#dialogForRequests").html(data);	 
						$("#dialogForRequests").dialog('open');
						 return false;
					},
					error: function(result){
						alert("error");
					}
				});
				} else {
					alert("You seem to be logged out");
					//Show Home Page Later
				}
		}
	);
}

function mapRequestsToRide(rideId){
	var selectedRequestIds = [];
	
	if(($('input[name="requestCheckbox"]:checked').size())>0){
		$('input[name="requestCheckbox"]:checked').each(function() {
			selectedRequestIds.push(this.value);
		});
		
		var param = {selectedRequestIds:selectedRequestIds, rideId:rideId};
		$.ajax({
			url: "../reqmc/mapRequestsToRide",
			type: "POST",
		    data: param,
		    traditional:true,
		    success: function(data) {
		    	closeDialog();
		    	if(data == '1'){
		    		alert("All requests submitted successfully");
		    	}
		    	if(data == '0'){
		    		alert("Maximum capacity for the ride has been reached. Some requests submitted successfully");
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
		alert("There is no ride selected to map to.")
	}
	
}

function closeDialog(){
	$("#dialogForRequests").dialog('close');
}