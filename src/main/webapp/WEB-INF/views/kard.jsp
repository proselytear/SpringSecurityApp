<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>

</head>
<body onload="clickMeth('defaultbutton')">
	<div id="openModalMap" class="modal">
		<jsp:include page="/WEB-INF/modal/myModalMap.jsp" />
	</div>
	<div id="kard" class="minw">

		<p>
			<b class="card_middle_text">Объект </b> <input type="text" class="card_middle_size" value="${maineq.getAccountid()}" />
		</p>
		
		
				
		<div class="fieldkard">
			<div class="leftfield">Менеджер</div>
			<div class="rightfield">
				<input type="text" value="${listCard.getEmployeesmanager().getFullName()}" readonly />
			</div>
		</div>
		<div class="fieldkard">
			<div class="leftfield">Название</div>
			<div class="rightfield">
				<input type="text" id="kardname" value="${listCard.name}" readonly />
			</div>
		</div>
		<div class="fieldkard">
			<div class="leftfield">Адрес</div>
			<div class="rightfield" id="kardaddress">${listCard.getAddressFull()}</div>
		</div>
		<div class="fieldkard">
			<div class="leftfield">Телефоны</div>
			<div class="rightfield" id="kardphone">${listCard.phone}</div>
		</div>
		<div class="fieldkard">
			<div class="leftfield">Директор</div>
			<div class="rightfield" id="karddirector">${listCard.director}</div>
		</div>
		<div class="fieldkard">
			<div class="leftfield">Ответственный</div>
			<div class="rightfield" id="kardresponsible">${listCard.responsible}</div>
		</div>
		<form action="param" method="post">
			<table id="nonetable">
				<c:forEach items="${listObjectViewTech}" var="listObjectViewTech" varStatus="status">
					<tr>
						<td>${listObjectViewTech.getHastechnicStr()}</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
		<div class="cent visot"><a href="#openModalMap" type="button" class="abutton">
				<img src="resources/images/button/maps.png" style="vertical-align: middle"> 
				<b class="card_middle_text"> Показать объект на карте</b></a></div><br>
	<div id="kardTable">

	<div id="buttonTableKard">
		<button id="defaultbuttonKard" class="tablink31" onclick="openPage31('Note', this, '#DCEEF8', 'defaultbuttonKard', 'buttonTableKard')">Примечание</button>
		<button id="AdditionallyKard" class="tablink31" onclick="openPage31('Additionally', this, '#DCEEF8', 'AdditionallyKard', 'buttonTableKard')">Дополнительно</button>
		<button id="ChangeKard" class="tablink31" onclick="openPage31('Change', this, '#DCEEF8', 'ChangeKard', 'buttonTableKard')">Замены</button>
		<button id="EquipmentRentKard" class="tablink31" onclick="openPage31('EquipmentRent', this, '#DCEEF8', 'EquipmentRentKard', 'buttonTableKard')">Оборудование в аренде</button>
	</div>
		<p></p>
		<div id="Note" class="tabcontent31">
			<form action="param" method="post">
				<table id="objectview">
					<thead>
						<tr>
							<th>Примечание</th>
							<th>Дата</th>
						</tr>
					</thead>
					<c:forEach items="${listCardNotation}" var="listCardNotation" varStatus="status">
						<tr>
							<td>${listCardNotation.notation}</td>
							<td>${listCardNotation.notationDate}</td>
						</tr>
					</c:forEach>
				</table>
			</form>

		</div>
		<div id="Additionally" class="tabcontent31">
			<p>${listCard.getAdditionally()}</p>
		</div>
		<div id="Change" class="tabcontent31">

			<table id="objectview">
				<thead>
					<tr>
						<th>Описание замены</th>
						<th>Дата замена</th>
					</tr>
				</thead>
				<c:forEach items="${listCardExchange}" var="listCardExchange" varStatus="status">
					<tr>
						<td>${listCardExchange.getExchangeDescription()}</td>
						<td>${listCardExchange.getExchangeDate()}</td>
					</tr>
				</c:forEach>
			</table>

		</div>
		<div id="EquipmentRent" class="tabcontent31">
			<p>${listCard.getEquipmentRent()}</p>
		</div>
	</div>
	<script>
		document.addEventListener("DOMContentLoaded", function(event) {
			document.getElementById("defaultbuttonKard").click();
		});
	<%--выбирает первую закладку по умолчанию--%>
		
	</script>
</body>
</html>


