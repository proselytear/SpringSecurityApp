<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>

<script src="resources/js/pcSetting.js" type="text/javascript"></script>
<script src="resources/js/signal.js" type="text/javascript"></script>
</head>
<body>
	<button id="defaultbuttonPCSet" class="tablink3" onclick="openPagePCSet('KardPCSet', this, '#DCEEF8')">
		<img src="resources/images/button/kard.png" style="vertical-align: middle">Основное
	</button>

	<button class="tablink3" onclick="openPagePCSet('TimetablePCSet', this, '#DCEEF8')">
		<img src="resources/images/button/schedule.png" style="vertical-align: middle">Особ. работы
	</button>

	<button class="tablink3" onclick="openPagePCSet('PlumeSet', this, '#DCEEF8')">
		<img src="resources/images/button/plume.png" style="vertical-align: middle">Шлейфы
	</button>

	<div id="panelcurrentPCSet"></div>
	<script>
	document.addEventListener("DOMContentLoaded", function(event) {
		document.getElementById("defaultbuttonPCSet").click();
	});
</script>
</body>
</html>