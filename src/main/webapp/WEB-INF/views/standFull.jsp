<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>
<script src="resources/js/click.js" type="text/javascript"></script>
<script src="resources/js/musicAlarm.js" type="text/javascript"></script>
<script src="resources/js/panel.js" type="text/javascript"></script>
</head>

<body  >

	<div id="openModal" class="modal">
		<jsp:include page="/WEB-INF/modal/myModal.jsp" />
	</div>

	<div style="display: none" id='MusicStf'>${alarmfullSize}</div>
	<p id="currentIdStand" hidden="true">
	<div id="stand" class="yyy">



		<form id="form-all3" method="post" action="home">
			<a href="#openModal" class="abutton">Добавить/вывести в стенды</a> <br> 
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
	<div class="scroll-table-body0">
		<table id="standtabFull" class="table_big">
			<thead>
				<tr>
					<th style='display: none'></th>
					<th>Номер</th>
					<th>Гр.</th>
					<th>Шлейфы</th>
					<th>Действует до</th>
					<th>Техник</th>
					<th>Оператор</th>

				</tr>
			</thead>

			<tbody id="t_bodySt2">
				<c:forEach items="${perifinfoBanFull}" var="perifinfoBanFull" varStatus="status">
					<p>${perifinfoBanFull.getTab()}</p>
				</c:forEach>
			</tbody>
		</table>

	</div>




	<script>
		$(document).on('click', '#t_bodySt2>.rowlink', function() {
			$(this).addClass('selected').siblings().removeClass('selected');

			var value = $(this).find('td:first input').val();
			document.getElementById("currentIdStand").innerHTML = value;

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
			var idinfo = document.getElementById("currentIdStand").innerHTML;
			myclickStand(idinfo);

		});
	</script>
	<script>
	
		$(document).ready(function() {

			Cheshka('MusicStf');
			setInterval("Cheshka('MusicStf')", 250);
			

		});
		$(document).ready(
						function() {

							var timers = setTimeout(
									function ticks() {

										var idinfo = document
												.getElementById("currentIdStand").innerHTML;
										reloadStand(idinfo);
										window.onbeforeunload = function() {
											return null;
										};
										timers = setTimeout(ticks, 500);

									}, 1);
						});
		function reloadStand(idinfo) {
			var evrangid = "";
			
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			$
					.ajax({
						beforeSend : function(xhr) {
							// here it is
							xhr.setRequestHeader(header, token);
						},
						type : "POST",

						url : url,
						data : {

							idinfoStand : idinfo,
							myclick : 0
						},

						success : function(html) {

							$("#eventtabFull").html(
									$(html).find('#eventtabFull').html());
							$("#listobjectview1").html(
									$(html).find('#listobjectview1').html());
							$("#malfunction").html(
									$(html).find('#malfunction').html());
							$("#event").html($(html).find('#event').html());
							$("#standtab").html($(html).find('#standtab'));
							$("#standtabFull").html(
									$(html).find('#standtabFull').html());
							$("#plumetab").html(
									$(html).find('#plumetab').html());
							$("#kard").html($(html).find('#kard').html());
							$("#Note").html($(html).find('#Note').html());
							$("#Additionally").html(
									$(html).find('#Additionally').html());
							$("#Change").html($(html).find('#Change').html());
							$("#EquipmentRent").html(
									$(html).find('#EquipmentRent').html());
							$("#maineq").html($(html).find('#maineq').html());
							$("#response").html(
									$(html).find('#response').html());
							$("#key").html($(html).find('#key').html());
							$("#state").html($(html).find('#state').html());
							$("#perifinfoBan").html(
									$(html).find('#perifinfoBan').html());
							$("#perifinfoBanMod").html(
									$(html).find('#perifinfoBanMod').html());
							$("#objectstatus").html(
									$(html).find('#objectstatus').html());
							$("#nottime").html($(html).find('#nottime').html());
							$("#panelSignal").html(
									$(html).find('#panelSignal').html());
							$("#panelStand").html(
									$(html).find('#panelStand').html());
							$("#panelStandFull").html(
									$(html).find('#panelStandFull').html());
							$("#panelNotTime").html(
									$(html).find('#panelNotTime').html());
							$("#panelDefect").html(
									$(html).find('#panelDefect').html());

							$("#timetable").html(
									$(html).find('#timetable').html());
							$("#MusicAll").html(
									$(html).find('#MusicAll').html());
							$("#MusicNot").html(
									$(html).find('#MusicNot').html());
							$("#MusicMal").html(
									$(html).find('#MusicMal').html());
							$("#MusicSta").html(
									$(html).find('#MusicSta').html());
							$("#MusicStf").html(
									$(html).find('#MusicStf').html());
							$("#employeeFIO").html(
									$(html).find('#employeeFIO').html());

						}
					});

		}
	</script>

</body>
</html>