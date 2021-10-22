<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">


<script src="resources/js/signal.js" type="text/javascript"></script>
</head>

<body onload="clickMeth(['buttonGroups'])">
	<div class="full">
		<div class="centered">
			<div class="container">
				<jsp:include page="panelhome.jsp" />
			</div>
		</div>

		<div class="split left">

			<!-- Tab content -->
			<div id="London" class="tabcontent">

				<jsp:include page="groupsstatus.jsp"></jsp:include>


			</div>

		</div>
	</div>
</body>
</html>