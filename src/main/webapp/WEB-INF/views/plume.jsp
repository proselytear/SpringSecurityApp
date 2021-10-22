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
	<div id="plume" class="tabcontent4">
		<p>
			<b class="card_middle_text">Объект </b> <input type="text" class="card_middle_size" value="${maineq.getAccountid()}" />
		</p>
		<div class="scroll-table-body0">
			<table id="plumetab" class="table_big  tabbg">
				<thead>
					<tr>
						<th>Группа</th>
						<th>Шлейф</th>
						<th>Статус</th>
						<th>Патруль</th>
						<th>Описание</th>
						<th>Тревожная кнопка</th>
					</tr>
				</thead>
				<c:forEach items="${perifinfoSensor}" var="perifinfoSensor" varStatus="status">
					<tr>
						<td>${perifinfoSensor.getGrouprelSensor() }</td>
						<td>${perifinfoSensor.getParamnumSensor() }</td>
						<td>${perifinfoSensor.getStatusyImg() }</td>
						<td><spаn style=" font-family: arial; font-size: 20px; color: red;">${perifinfoSensor.getPatrol()}</spаn></td>
						<td>${perifinfoSensor.getPerifnameVal()}</td>
						<td><spаn style="font-family: arial; font-size: 20px; color: red;">${perifinfoSensor.getAlarmButton()}</spаn></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
