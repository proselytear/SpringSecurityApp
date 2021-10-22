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
	<div id="plumeSet">


		<table id="plumetabSet" class="table_small">

			<tr>

				<th>Шлейф</th>


				<th>Описание</th>
				<th>Тревожная кнопка</th>
			</tr>
			<c:forEach items="${perifinfoSensor}" var="perifinfoSensor" varStatus="status">
				<tr>

					<td>${perifinfoSensor.getParamnumSensor() }</td>


					<td>${perifinfoSensor.getPerifnameVal()}</td>
					<td><spаn style="font-family: arial; font-size: 20px; color: red;">${perifinfoSensor.getAlarmButton()}</spаn></td>
				</tr>
			</c:forEach>
		</table>
		<br> <a href="#openModalAddPlume" type="button" class="abutton" name="nameFunc">Добавить/Изменить шлейф</a> 
		<a href="#openModalDeletePlume" type="button" class="abutton" name="nameFunc">Удалить шлейф</a>

	</div>
</body>
</html>
