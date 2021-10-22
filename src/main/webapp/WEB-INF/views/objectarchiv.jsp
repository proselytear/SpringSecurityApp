<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Document</title>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>

<!-- <script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCX14WTLwOO_i1udpZllbB-bRAo4YYlr3I&callback=myMap"
	async defer></script> -->
</head>
<body onload="clickMeth(['buttonSignal'])">

	<div class="full">

		<div class="centered">
			<div class="container">
				<jsp:include page="panelhome.jsp" />
			</div>
		</div>

		<div class="split left">


			<div id="Paris" class="tabcontent">
				<div id="Signal" class="tabcontent2">
				<form method="post" action="objectsignal">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button class="tablink31" id="buttonCurrentSign" onclick="openPage2('objectsignal', this, '#DCEEF8')">Текущие</button>
				</form>

				
					<jsp:include page="archiv.jsp" />
				</div>

			</div>

		</div>
	</div>
</body>
</html>