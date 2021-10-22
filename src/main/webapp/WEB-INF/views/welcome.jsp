<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resources/css/welcome.css" />" rel="stylesheet">
<title>Document</title>
<link rel="icon" href="<c:url value="resources/images/icon.ico" />" type="image/x-icon">

</head>

<body>

	<div class="full">
		<div class="centered">
			<div class="container">
				<jsp:include page="panelhome.jsp" />
			</div>
		</div>

		<div class="split left">

			<div id="London" class="tabcontent">


				<div id="Status" class="tabcontent2">
					<jsp:include page="panelobject.jsp" />
					<jsp:include page="all.jsp" />
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