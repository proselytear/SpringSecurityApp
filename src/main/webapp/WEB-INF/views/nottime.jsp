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
<script src="resources/js/reloadCurrentPage.js" type="text/javascript"></script>
<script src="resources/js/musicAlarm.js" type="text/javascript"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>

</head>
<body>
	<p id="currentIdNotTime" hidden="true">
	<div style="display: none" id='MusicNot'>${alarmfullSize}</div>
	<form method="post" action="objectnottime" id="objectnottime">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<div class="scroll-table-body0">
		<table id="nottime" class="table_big">
			<thead>
				<tr>

					<th></th>
					<th></th>
					<th>Объект</th>
					<th>Гр.</th>
					<th>Название</th>
					<th>Адрес</th>
					<th>Время</th>
					<th>Статус</th>
				</tr>
			</thead>

			<tbody id="t_bodynottime">
				<c:forEach items="${listObjectViewSconn}" var="listObjectViewSconn" varStatus="status">
					<p>${listObjectViewSconn.getTab()}</p>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<script type="text/javascript">
		var flag = 1;
		$(document)
				.on(
						'click',
						'#t_bodynottime>.rowlink',
						function() {
							flag = 1;
							$(this).addClass('selected').siblings()
									.removeClass('selected');

							var value = $(this).find('td:first input').val();

							document.getElementById("currentIdNotTime").innerHTML = value;

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
									;

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
									.getElementById("currentIdNotTime").innerHTML;
							myclick2(value);
							flag = 1;
							reloadObjectView2(idinfo);

						});
	</script>
	<script>
		$(document).ready(function() {

			Cheshka('MusicNot');
			setInterval("Cheshka('MusicNot')", 250);

		});
	</script>
	<script>
		$(document).ready(
				function() {

					var timers = setTimeout(function ticks() {

						var idinfo = document
								.getElementById("currentIdNotTime").innerHTML;
						reloadObjectView2(idinfo, flag);
						window.onbeforeunload = function() {
							return null;
						};
						timers = setTimeout(ticks, 500);

					}, 1);
				});
	</script>
</body>

</html>