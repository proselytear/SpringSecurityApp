
<!DOCTYPE html>
<html lang="en">
<head>
<title>Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

	<h1>My First Google Map</h1>

	<div id="map" style="width: 100%; height: 400px;"></div>

	<script>
		function myMap() {
			var mapCanvas = document.getElementById("map");
			var mapOptions = {
				center : new google.maps.LatLng(51.5, -0.2),
				zoom : 10
			};
			var map = new google.maps.Map(mapCanvas, mapOptions);
		}
	</script>

	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDR8P72HMX3zllKeeZt8X89dJWMEu0LSX8&callback=myMap" async defer></script>


</body>
</html>
