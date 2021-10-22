<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>


<script src="<c:url value="/resources/js/signal.js" />"></script>

</head>
<body>

	<div class="paneloject">
		<form method="post" action="home" class="block">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button id="buttonAll" class="tablink2" onclick="openPage2('All', this, '#DCEEF8')">Все</button>
		</form>

		<form method="post" action="objectstatus" class="block">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button class="tablink2" id="buttonStatus" onclick="openPage2('Status', this, '#DCEEF8')">Статус</button>
		</form>

		<form method="post" action="objectnottime" class="block">

			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button class="tablink2" id="buttonNotTime" onclick="openPage2('NotTime', this, '#DCEEF8')">
				Нет вовремя теста <span id="panelNotTime" class="badge badge-pill badge-primary">${objectViewSconnSize}</span>
			</button>
		</form>

		<form method="post" action="objectmalfunction" class="block">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button class="tablink2" id="buttonMalfunc" onclick="openPage2('Malfunctions', this, '#DCEEF8')">
				Неисправности <span id="panelDefect" class="badge  badge-pill badge-warning">${alDefectSize}</span>
			</button>
		</form>
	</div>

</body>
</html>