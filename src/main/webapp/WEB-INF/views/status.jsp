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

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
<script src="resources/js/panel.js" type="text/javascript"></script>

<script src="resources/js/musicAlarm.js" type="text/javascript"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
<script src="resources/js/panel.js" type="text/javascript"></script>
</head>

<body onload="clickMeth('buttonStatus')">
	<p id="currentIdStatus" hidden="true">
	<div style="display: none" id='MusicSta'>${alarmfullSize}</div>
	<form id="status" method="post" action="objectstatus">

		<input name="statusOch" type="radio" value="БЕЗ ОХРАНЫ"><b> БЕЗ ОХРАНЫ</b> <br> <input name="statusOch" type="radio" value="ПОД ОХРАНОЙ"><b> ПОД ОХРАНОЙ</b> <br>
		<input name="statusOch" type="radio" value="ТРЕВОГА"> <b> ТРЕВОГА</b><br>

		<p>
			<input type="submit" id="input-border-search" class="tablink31" value="Найти"> <select name="city_id" class="card_middle_size">
				<option value="-1"></option>
				<option value="190">Киев</option>
				<option value="172">Ирпень</option>
				<option value="245">Мелитополь</option>
				<option value="246">Никополь</option>
				<option value="52">Борисполь</option>
			</select>
		</p>

		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<div class="scroll-table-body0">
		<table id="objectstatus" class="table_big">
			<thead>
				<tr>

					<th style='display: none'></th>
					<th></th>
					<th>Объект</th>
					<th>Гр.</th>
					<th>Название</th>
					<th>Адрес</th>
					<th>Время</th>
					<th>Статус</th>

				</tr>
			</thead>
			<tbody id="t_bodystatus">
				<c:forEach items="${listObjectViewOchStatus}" var="listObjectViewOchStatus" varStatus="status">
					<p>${listObjectViewOchStatus.getTab()}</p>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {

			$('#objectstatus tbody').on('click', 'tr', function() {
				var data = table.row(this).data();

			});
		});
	</script>
	<script type="text/javascript">
		var flag = 1;
		$(document)
				.on(
						'click',
						'#t_bodystatus>.rowlink',
						function() {
							flag = 0;
							$(this).addClass('selected').siblings()
									.removeClass('selected');

							var value = $(this).find('td:first input').val();
							document.getElementById("currentIdStatus").innerHTML = value;

							var rows = window.document
									.querySelectorAll("table tbody tr");

							for (var i = 0; i < rows.length; i++) { // перебираем все строки

								var cols = rows[i].querySelectorAll('td'); // получаем столбцы

								for (var j = 0; j < cols.length; j++) { // перебираем все столбцы

									if (cols[j].textContent
											.indexOf('БЕЗ ОХРАНЫ') > -1) {
										cols[j].parentNode.style.backgroundColor = "#BCF5D8";
									} else if (cols[j].textContent
											.indexOf('ПОД ОХРАНОЙ') > -1) {

										cols[j].parentNode.style.backgroundColor = "#C6CFF8";
									} else if (cols[j].textContent
											.indexOf('ТРЕВОГА') > -1) {
										cols[j].parentNode.style.backgroundColor = "#EE9CA4";
									}

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
							var idinfo = document
									.getElementById("currentIdStatus").innerHTML;
							myclick2(idinfo);
							flag = 1;

						});
		$(document)
				.ready(
						function() {

							var timers = setTimeout(
									function ticks() {

										var idinfo = document
												.getElementById("currentIdStatus").innerHTML;
										reloadObjectView2(idinfo, flag);
										window.onbeforeunload = function() {
											return null;
										};
										timers = setTimeout(ticks, 500);

									}, 1);
						});
	</script>
	<script>
		$(document).ready(function() {

			Cheshka('MusicSta');
			setInterval("Cheshka('MusicSta')", 250);

		});
	</script>

</body>

</html>