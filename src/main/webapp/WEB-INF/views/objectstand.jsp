
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Document</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
<script src="resources/js/panel.js" type="text/javascript"></script>
<script src="resources/js/reloadCurrentPage.js" type="text/javascript"></script>
<script src="resources/js/signal.js" type="text/javascript"></script>
<script src="resources/js/musicAlarm.js" type="text/javascript"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
</head>

<body onload="clickMeth(['buttonStand'])">
	<div class="full">
		<div class="centered">
			<div class="container">
				<jsp:include page="panelhome.jsp" />
			</div>
		</div>

		<div class="split left">
			<!-- Tab content -->
			<div class="tabcontent">



				<div id="StandFull" class="tabcontent2">
					<jsp:include page="standFull.jsp"></jsp:include>
				</div>
			</div>

		</div>
	</div>
</body>
</html>