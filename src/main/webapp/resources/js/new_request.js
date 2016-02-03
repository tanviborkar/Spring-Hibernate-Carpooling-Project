function submit_new_request(){

	FB.api(
			"/me?fields=id",
			function (response) {
				if (response.id!=undefined){
					var param = {pickupPlace:$(pickupPlace).val(), 
							destination:$(destination).val(), 
							comments:$(comments).val(), 
							startTime:$(startTime).val(),
							userTypeId:response.id}

					$.ajax({
						url: "submitNewRequest",
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


$(document).ready(function() {
	$('#startTime').datetimepicker();
});