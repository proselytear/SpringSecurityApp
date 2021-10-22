<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Search Result</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="resources/js/musicAlarm.js" type="text/javascript"></script>
<script src="resources/js/click.js" type="text/javascript"></script>
<script src="resources/js/panel.js" type="text/javascript"></script>
<script src="resources/js/signal.js" type="text/javascript"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="<c:url value="/resources/js/signal.js" />"></script>
</head>
<body >

	<div style="display: none" id='MusicAll'>${alarmfullSize}</div>

	<p id="currentIdArch" hidden="true"></p>
<div class="width700">
	<form method="post" action="archiv" onsubmit="return false">


		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
		<input type="text" id="myInputMiddle" onkeyup="myFunction()"
		placeholder="Введите номер объекта" name="keyword" autocomplete="on" class="card_middle_size" />

		<div class="line">

			<label for="localdate"><b class="card_middle_text">Дата и время с:</b> </label> 
			<input class="card_middle_size" type="datetime-local" name="date" id="datefromArch" /> 
			<label for="localdate"><b class="card_middle_text">Дата и время по: </b></label> 
			<input class="card_middle_size" type="datetime-local" name="date1" id="datetoArch" />
		
		<b class="card_middle_text">Состояние события</b> <select name="alarmstatusname" id="alarmstatusname" class="card_middle_size">
			<c:forEach var="alarmstatusname" items="${alarmstatusname}">
				<option value="${alarmstatusname.getId_status()}">${alarmstatusname.getStatusName()}</option>
			</c:forEach>
		</select> <b class="card_middle_text">Отображать количество событий</b>
		<select id="sizeColumnArch" name="sizeColumnArch" class="card_middle_size4">

			<option value="50">50</option>
			<option value="100">100</option>
			<option value="250">250</option>
			<option value="500">500</option>
			<option value="1000">1000</option>

		</select>
		</div>
		</form>
		</div>
		<div class="scroll-table-body0">
			<table id="objectarchiv" class="table_big">
				<thead>
					<tr>

						<th style='display: none'></th>
						<th></th>
						<th>Объект</th>
						<th>Гр.</th>
						<th>Название</th>
						<th>Адрес</th>
						<th>Состояние</th>
						<th>Описание события</th>
						<th>Дата</th>
						<th>Время</th>
						<th>Код</th>
						<th>Оператор</th>
						<th>Причина отмены тревоги</th>
					</tr>
				</thead>
				<tbody id="t_bodyArc">
					<c:forEach items="${alarmarchive}" var="alarmarchive" varStatus="status">
						<tr class="rowlink">
							<p>${alarmarchive.getTab()}</p>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	


	<script>
	$(document).on('click', '#t_bodyArc>.rowlink', function() {
		$(this).addClass('selected').siblings().removeClass('selected');

		var value = $(this).find('td:first input').val();
		document.getElementById("currentIdArch").innerHTML = value;

		var rows = window.document.querySelectorAll("table tbody tr");

		for (var i = 0; i < rows.length; i++) { // перебираем все строки

			var cols = rows[i].querySelectorAll('td'); // получаем столбцы

			for (var j = 0; j < cols.length; j++) { // перебираем все столбцы

				cols[j].parentNode.style.backgroundColor = null;

			}
		}

		for (var i = 0; i < rows.length; i++) { // перебираем все строки

			var cols = rows[i].querySelectorAll('td'); // получаем столбцы

			for (var j = 0; j < cols.length; j++) { // перебираем все столбцы
				if (cols[j].textContent == value) {

					cols[j].parentNode.style.backgroundColor = "#FFFF00";

				}// выводим текст из столбца

			}
		}
		var idinfo=document.getElementById("currentIdArch").innerHTML;

		myclickArch(value);

	});
	$(document).ready(function() {
		
		var timers = setTimeout(function ticks() {

		var idinfo=	document.getElementById("currentIdArch").innerHTML;
		reloadArchive(idinfo);
			window.onbeforeunload = function() {
				return null;
			};
			timers = setTimeout(ticks, 500);


	  
		}, 1);
	});
</script>
	<script>
	$(document)
			.ready(
					function() {
					
						Cheshka('MusicAll');
						setInterval("Cheshka('MusicAll')", 1000);

					});
</script>



</body>

</html>