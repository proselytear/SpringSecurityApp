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

	<div id="events" class="tabcontent4">
		<p id="accauntSign">
			<b class="card_middle_text">Объект </b> <input type="text" class="card_middle_size" value="${maineq.getAccountid()}" />
		</p>


		<form id="myForm" action="home" method="post" class="padbot">
			<input class="card_middle_size" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

			<div class="fieldkard">
				<div class="leftfield big">
					<label for="localdate">Дата и время с: </label>
				</div>
				<div class="rightfield">
					<input type="datetime-local" name="date" id="datefrom" />
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield big">
					<label for="localdate" class="card_middle_text">Дата и время по: </label>
				</div>
				<div class="rightfield">
					<input type="datetime-local" name="date1" id="dateto" />
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield big">Отображать количество событий</div>
				<div class="rightfield">
					<select id="sizeColumn" name="sizeColumn">

						<option value="50">50</option>
						<option value="100">100</option>
						<option value="250">250</option>
						<option value="500">500</option>
						<option value="1000">1000</option>

					</select>
				</div>
			</div>
			<input type="button" class="tablink31" name="nameFunc" value="Найти" 
			onclick="foundEventIdInfo()">
			<input type="button" class="tablink31" onclick="clianEventlist()" value="Очистить">

		</form>
		<div class="scroll-table-body0">

			<table id="event" class="table_big">
				<thead>
					<tr>
						<th>Код</th>
						<th>Тип кода</th>
						<th>Дата</th>
						<th>Время</th>
						<th>Гр.</th>
						<th>Название</th>
						<th>Шл.</th>
						<th>Название</th>
						<th>Описание события</th>
					</tr>
				</thead>
				<c:forEach items="${eventlistV}" var="eventlistV" varStatus="status">
					<p>${eventlistV.getTabPanel()}</p>
				</c:forEach>
			</table>
		</div>

	</div>
</body>
</html>