<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Document</title>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>

<!-- <script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCX14WTLwOO_i1udpZllbB-bRAo4YYlr3I&callback=myMap"
	async defer></script> -->


</head>

<body onload="clickMeth(['buttonObject', 'buttonMalfunc', 'defaultbutton', 'defaultbuttonKard'])">
	<div class="full">
		<div class="centered">
			<div class="container">
				<jsp:include page="panelhome.jsp" />
			</div>
		</div>

		<div class="split left">

			<!-- Tab content -->
			<div id="London" class="tabcontent">
				<div id="Malfunctions" class="tabcontent2">
					<jsp:include page="panelobject.jsp" />
					<jsp:include page="malfunction.jsp"></jsp:include>
				</div>
			</div>
		</div>
		<div class="split right">
			<div class="container">
				<jsp:include page="panelright.jsp"></jsp:include>
			</div>
		</div>
	</div>
</body>
</html>