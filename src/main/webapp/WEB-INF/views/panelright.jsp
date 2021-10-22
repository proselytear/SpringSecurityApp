<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>
</head>
<body>
	<div id="rightpanel" class="rp">

		<div id="Panel" class="tabcontent30" items="${perifinfoSensorSize}" var="perifinfoSensorSize">
			<jsp:include page="panelcard.jsp"></jsp:include>
		</div>
		<div id="Kard" class="tabcontent3 ow" items="${listCard}" var="listCard">
			<jsp:include page="kard.jsp"></jsp:include>
		</div>
		<div id="Timetable" class="tabcontent3 ow" items="${perifinfo}" var="perifinfo">
			<jsp:include page="timetable.jsp"></jsp:include>
		</div>

		<div id="Maineq" class="tabcontent3 ow" items="${maineq}" var="maineq">
			<jsp:include page="maineq.jsp"></jsp:include>
		</div>

		<div id="Response" class="tabcontent3" items="${groups}" var="groups">
			<jsp:include page="response.jsp"></jsp:include>
		</div>
		<!-- Шлейфы -->

		<div id="Plume" class="tabcontent3 ow" items="${perifinfoSensor}" var="perifinfoSensor">
			<jsp:include page="plume.jsp"></jsp:include>
		</div>

		<div id="Key" class="tabcontent3" items="${perifinfoKey}" var="perifinfoKey">
			<jsp:include page="key.jsp"></jsp:include>
		</div>

		<div id="ResponsPeop" class="tabcontent3" items="${perifinfoKey}" var="perifinfoResponsible">
			<jsp:include page="responspeop.jsp"></jsp:include>
		</div>

		<div id="State" class="tabcontent3 ow" items="${maineq}" var="maineq">
			<jsp:include page="state.jsp"></jsp:include>
		</div>

		<div id="Events" class="tabcontent3" items="${eventlistV}" var="perifinfoResponsible">
			<jsp:include page="events.jsp"></jsp:include>
		</div>

		<div id="Stand" class="tabcontent3" items="${perifinfoBanIdinfo}" var="perifinfoBanIdinfo">
			<jsp:include page="stand.jsp"></jsp:include>
		</div>

		<div id="Photo" class="tabcontent3" items="${perifinfoKey}" var="perifinfoKey">
			<jsp:include page="photo.jsp"></jsp:include>
		</div>
		<div id="Schema" class="tabcontent3" items="${perifinfoKey}" var="perifinfoKey">
			<jsp:include page="schema.jsp"></jsp:include>
		</div>
	</div>



</body>
</html>