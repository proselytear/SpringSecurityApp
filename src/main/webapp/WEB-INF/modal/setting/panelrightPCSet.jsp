<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>

<script src="resources/js/signal.js" type="text/javascript"></script>
<script src="resources/js/pcSetting.js" type="text/javascript"></script>
</head>

<body>
	<div id="panelrightPCSet"  class="rp">
		<!-- Панель кнопок -->
		<div id="PanelPCSet" class="tabcontent30" items="${perifinfoSensorSize}" var="perifinfoSensorSize">
			<jsp:include page="panelPCSet.jsp"></jsp:include>
		</div>
		<!-- Импортируем вкладки для панелей -->
		<!-- Расписание -->
		<div id="KardPCSet" class="tabcontentPCSet" items="${listCard}" var="listCard">
			<jsp:include page="kardPCSet.jsp"></jsp:include>
		</div>
		<!--Особености работы-->
		<div id="TimetablePCSet" class="tabcontentPCSet" items="${perifinfo}" var="perifinfo">
			<jsp:include page="timetablePCSet.jsp"></jsp:include>
		</div>


		<!-- Шлейфы -->

		<div id="PlumeSet" class="tabcontentPCSet" items="${perifinfoSensor}" var="perifinfoSensor">
			<jsp:include page="plumeSet.jsp"></jsp:include>
		</div>


	</div>

</body>
</html>