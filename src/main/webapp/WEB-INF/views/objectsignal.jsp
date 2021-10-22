<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Document</title>
<script src="<c:url value="/resources/js/signal.js" />"></script>

</head>

<body onload="clickMeth(['buttonSignal', 'defaultbutton', 'defaultbuttonKard'])">
	<div class="full">

		<div class="centered">
			<div class="container">
				<jsp:include page="panelhome.jsp" />
			</div>
		</div>

		<div class="split left">

			<div id="Paris" class="tabcontent">
				<div id="Signal" class="tabcontent1">
					<form method="post" action="archiv">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<button class="tablink3" id="buttonArch" onclick="openPage2('archiv', this, '#DCEEF8')">Архив</button>
					</form>


					<jsp:include page="signal.jsp" />
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