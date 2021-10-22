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


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="resources/js/reloadEventPage.js" type="text/javascript"></script>
</head>

<body>
	<div class="full">
		<div class="centered">
			<div class="container">
				<jsp:include page="panelsetting.jsp" />
			</div>
		</div>

		<div class="split left">
			<div class="tabcontent">
				<!-- Tab content -->
				<div id="Setting" class="tabcontent2">

					<jsp:include page="setting.jsp"></jsp:include>

				</div>
			</div>
		</div>
	</div>
</body>
</html>