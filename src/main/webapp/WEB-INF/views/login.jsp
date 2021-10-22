<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<title>Log in with your account</title>

<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<link href="<c:url value="/resources/css/welcome.css" />" rel="stylesheet">

<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>
<body>

	<form method="POST" action="${contextPath}/login" class="form-signin">
		<h2 class="form-heading">Вход</h2>

		<div class="form-group ${error != null ? 'has-error' : ''}">
			<span>${message}</span> <input name="username" type="text" class="form-control" placeholder="Логин" autofocus />
			<input name="password" type="password" class="form-control" placeholder="Пароль" />
			<span>${error}</span> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /><br>

			<button class="logbtn" type="submit">Вход</button>
			<h4>
				<a href="${contextPath}/registration" class="logbtn">Создать аккаунт</a>
			</h4>
		</div>

	</form>

	<!-- /container -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>