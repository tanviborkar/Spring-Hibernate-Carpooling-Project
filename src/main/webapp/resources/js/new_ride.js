function submit_new_ride(){

	FB.api(
			"/me?fields=id",
			function (response) {
				if (response.id!=undefined){
					//var isPickupOtherThanStartProvided = $('#isPickupOtherThanStartProvided:checked').val();
					//alert(isPickupOtherThanStartProvided);
					var isPickupOtherThanStartProvided = false;
					if($('#isPickupOtherThanStartProvided').is(':checked')){
						isPickupOtherThanStartProvided = true;
					}
					
					var isPriceNegotiable = false;
					if($('#isPriceNegotiable').is(':checked')){
						isPriceNegotiable = true;
					}
					
					var param = {startPoint:$(startPoint).val(), 
							destination:$(destination).val(),
							startTime:$(startTime).val(),
							maxNoOfPassengers:$(maxNoOfPassengers).val(),
							pricePerUser:$(pricePerUser).val(),
							isPickupOtherThanStartProvided:isPickupOtherThanStartProvided,
							isPriceNegotiable:isPriceNegotiable,
							comments:$(comments).val(), 
							userTypeId:response.id}

					$.ajax({
						url: "postNewRide",
						type: "POST",
						data : param,
						success: function(result){
							alert("submitted");
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

function submit_edit_ride(){

	var isPickupOtherThanStartProvided = false;
	if($('#isPickupOtherThanStartProvided').is(':checked')){
		isPickupOtherThanStartProvided = true;
	}
					
	var isPriceNegotiable = false;
	if($('#isPriceNegotiable').is(':checked')){
		isPriceNegotiable = true;
	}
					
	var param = {rideId:$(rideId).val(),
				startPoint:$(startPoint).val(), 
				destination:$(destination).val(),
				startTime:$(startTime).val(),
				maxNoOfPassengers:$(maxNoOfPassengers).val(),
				pricePerUser:$(pricePerUser).val(),
				isPickupOtherThanStartProvided:isPickupOtherThanStartProvided,
				isPriceNegotiable:isPriceNegotiable,
				comments:$(comments).val()}

					$.ajax({
						url: "../rmc/updateRide",
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


$(document).ready(function() {
	$('#startTime').datetimepicker();
});