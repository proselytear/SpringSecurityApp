<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>

<script src="resources/js/cleanForm.js" type="text/javascript"></script>
<script src="resources/js/signal.js" type="text/javascript"></script>
</head>

<body>
	<div id="rightpanel" class="rp">
		<div id="PanelSet" class="tabcontent30" items="${perifinfoSensorSize}" var="perifinfoSensorSize">
			<jsp:include page="panelcardSet.jsp"></jsp:include>
		</div>
		<div id="KardSet" class="tabcontentSet" items="${listCard}" var="listCard">
			<jsp:include page="kardSet.jsp"></jsp:include>
		</div>

		<div id="ResponseSet" class="tabcontentSet" items="${groups}" var="groups">
			<jsp:include page="responseSet.jsp"></jsp:include>
		</div>

		<div id="KeySet" class="tabcontentSet" items="${perifinfoKey}" var="perifinfoKey">
			<jsp:include page="keySet.jsp"></jsp:include>
		</div>

		<div id="ResponsPeopSet" class="tabcontentSet" items="${perifinfoKey}" var="perifinfoResponsible">
			<jsp:include page="responspeopSet.jsp"></jsp:include>
		</div>

		<div id="PhotoSet" class="tabcontentSet" items="${perifinfoKey}" var="perifinfoKey">
			<jsp:include page="photoSet.jsp"></jsp:include>
		</div>
		<div id="SchemaSet" class="tabcontentSet" items="${perifinfoKey}" var="perifinfoKey">
			<jsp:include page="schemaSet.jsp"></jsp:include>
		</div>

	</div>




</body>
</html>