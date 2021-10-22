<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>
<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBgHIiohz41akOf0RuaWhXiWB3pcYLDpHE&callback=initMap&libraries=&v=weekly" async defer></script>
</head>

<body>

	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">Карта</h4>
				<a href="#close" title="Close" class="close">×</a>
			</div>



			<!-- Modal footer -->
			<div id="myModalMap" class="modal-body">
				<div id="myModalPoint" items="${objectviewId}" var="objectviewId">
					<div class="fieldkard">
						<div class="leftfield">Объект</div>
						<div class="rightfield">
							<input type="text" readonly="readonly" value="${objectviewId.getAccountID()}" />
						</div>
					</div>
					<div class="fieldkard">
						<div class="leftfield">Адрес</div>
						<div class="rightfield">
							<input type="text" readonly="readonly" value="${objectviewId.getFullname()}" />
						</div>
					</div>
					<div class="fieldkard">
						<div class="leftfield">Координаты</div>
						<div class="rightfield">
							<input id="latlng" type="text" readonly="readonly" value="${objectviewId.getXYpoint()}" />
						</div>
					</div>

				</div>
				<br>
				<div id="map" style="width: 100%; height: 500px;"></div>
			</div>
			<div class="modal-footer" items="${objectviewId}" var="objectviewId">
				<input type="hidden" id="latlng1" value="${objectviewId.getXYpoint()}" /> 
				<input id="submit" class="abutton" type="button" value="Найти объект" />


			</div>

		</div>

	</div>


	<script>
      function initMap() {
        const map = new google.maps.Map(document.getElementById("map"), {
          zoom: 8,
          center: { lat: 50.4494809, lng: 30.52540809 },
        });
        const geocoder = new google.maps.Geocoder();
        const infowindow = new google.maps.InfoWindow();
        document.getElementById("submit").addEventListener("click", () => {
          geocodeLatLng(geocoder, map, infowindow);
        });
      }

      function geocodeLatLng(geocoder, map, infowindow) {
        const input = document.getElementById("latlng").value;
        const latlngStr = input.split(",", 2);
        const latlng = {
          lat: parseFloat(latlngStr[0]),
          lng: parseFloat(latlngStr[1]),
        };
        geocoder.geocode({ location: latlng }, (results, status) => {
          if (status === "OK") {
            if (results[0]) {
              map.setZoom(11);
              const marker = new google.maps.Marker({
                position: latlng,
                map: map,
              });
              infowindow.setContent(results[0].formatted_address);
              infowindow.open(map, marker);
            } else {
              window.alert("No results found");
            }
          } else {
            window.alert("Geocoder failed due to: " + status);
          }
        });
      }
    </script>
</body>
</html>