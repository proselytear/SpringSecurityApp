<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Welcome</title>

<link href="${contextPath}/resources/css/welcome.css" rel="stylesheet">

</head>
<body>

	<div class="container">

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/Logout">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>

			<h2>
				Добро пожаловать ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Выйти</a>
			</h2>

		</c:if>

		<div class="scroller scroller-left">
			<i class="glyphicon glyphicon-chevron-left"></i>
		</div>
		<div class="scroller scroller-right">
			<i class="glyphicon glyphicon-chevron-right"></i>
		</div>

	</div>




	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

	<script src="${contextPath}/resources/js/welcome.js"></script>
</body>
</html>