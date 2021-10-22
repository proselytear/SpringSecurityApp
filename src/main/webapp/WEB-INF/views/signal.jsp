<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Search Result</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>

<script src="resources/js/panel.js" type="text/javascript"></script>
<script src="resources/js/musicAlarm.js" type="text/javascript"></script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
</head>

<body onload="clickMeth('buttonSignal')">



	<div id="openModalResponse" class="modal">
		<jsp:include page="/WEB-INF/modal/myModalResponse.jsp" />
	</div>
	<div id="openModalGroupArrived" class="modal">
		<jsp:include page="/WEB-INF/modal/myModalGroupArrived.jsp" />
	</div>
	<div id="openModalCancelGroups" class="modal">
		<jsp:include page="/WEB-INF/modal/myModalCancelGroups.jsp" />
	</div>
	<div id="openModalCancelAlarm" class="modal">
		<jsp:include page="/WEB-INF/modal/myModalCancelAlarm.jsp" />
	</div>

	<iframe id="txtArea1" style="display: none"></iframe>
	<div style="display: none" id='MusicSig'>${alarmfullSize}</div>


	<form id="objectsignal" method="post" class="bodfor1">

		<button class="tablink31" name="nameFunc" value="takeProcessing"
			type="button" onclick="takeProcessing()">Взять в обработку</button>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<div class="body1">
			<div class="table-body">
				<table id="signalfull" class="table_big">
					<thead>
						<tr>

							<th style="display: none"></th>
							<th>&#128276</th>
							<th>Объект</th>
							<th>Гр.</th>
							<th>Назв.</th>
							<th>Шл.</th>
							<th>Назв.</th>
							<th>Адрес</th>
							<th>Состояние</th>
							<th>Описание события</th>
							<th>Дата</th>
							<th>Время</th>
							<th>Код</th>
						</tr>
					</thead>
					<tbody id="t_bodysignal">
						<c:forEach items="${alarmnew}" var="alarmnew" varStatus="status">
							<p>${alarmnew.getTab()}</p>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</form>

	<!--  	<p id="currentIdSignal1"></p>-->
	<div class="nav1">
		<p id="clickFlag"></p>
		<p id="currentIdInfo"></p>
		<p id="flagUpdateClick">${flagUpdateClick}</p>
		<p id="flagUpdateSignal">${flagUpdateSignal}</p>
		<p id="flagUpdateMaineq">${flagUpdateMaineq}</p>
		<p id="flagUpdateStand">${flagUpdateStand}</p>
		<p id="flagUpdateEventlist">${flagUpdateEventlist}</p>
	</div>


	<form method="post" action="objectsignal" class="bodfor1">
		<div class="body3">
			<a href="#openModalResponse" type="button" name="nameFunc"
				value="GroupLeft" class="abutton" onclick="myclickSignal(null)">&#128660;Выслыть
				группу</a> <a href="#openModalGroupArrived" type="button"
				name="nameFunc" value="GroupArrived" class="abutton"
				onclick="myclickSignal(null)">&#128660;&#10004;&#65039;Группы
				прибыли</a> <a href="#openModalCancelGroups" type="button"
				name="nameFunc" value="CancelGroup" class="abutton"
				onclick="myclickSignal(null)">&#128660;&#10060;Отмена групп</a> <a
				href="#openModalCancelAlarm" type="button" name="nameFunc"
				value="CancelAlarm" class="abutton" onclick="myclickSignal(null)">&#10004;&#65039;Завершение
				обработки</a> <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</div>
		<div class="body2">
			<div class="table-body">
				<table id="signalnew" class="table_big">
					<thead>
						<tr>
							<th style="display: none"></th>
							<th></th>
							<th></th>
							<th>Объект</th>
							<th></th>
							<th>Описание события</th>
							<th>Гр.</th>
							<th>Название</th>
							<th>Шл.</th>
							<th>Название</th>
							<th>Адрес</th>
							<th>Статус</th>

							<th>Состояние</th>

							<th>Дата</th>
							<th>Время</th>
							<th>Код</th>
							<th>Оператор</th>
						</tr>
					</thead>
					<tbody id="t_bodyalarmnotnew1">
						<c:forEach items="${alarmnotnew}" var="alarmnotnew"
							varStatus="status">
							<p>${alarmnotnew.getTabWork()}</p>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</form>

	<script type="text/javascript">
		$(document)
				.on(
						'click',
						'#t_bodysignal>.rowlink',
						function() {

							$(this).addClass('selected').siblings()
									.removeClass('selected');

							var value = $(this).find('td:first input').val();
						
							document.getElementById("currentIdInfo").innerHTML = value;
							document.getElementById("clickFlag").innerHTML = 1;

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
									if (cols[j].textContent != value) {

									} else {

										cols[j].parentNode.style.backgroundColor = "#000080";
										cols[j].parentNode.style.color = "#ffffff";

									}

									// выводим текст из столбца

								}
							}
							var idinfo = document
									.getElementById("currentIdInfo").innerHTML;

							myclickSignal(idinfo, 1);

						});
	</script>
	<script>
		function takeProcessing(nameFunc, func) {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			idinfo = document.getElementById("currentIdInfo").innerHTML;

			$.ajax({
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				type : "POST",

				url : "objectsignal",
				data : {
					nameFunc : "takeProcessing",
					idinfo : idinfo,

				},

				cache : false,
				success : function(html) {
					$("#standtab").html($(html).find('#standtab'));
					$("#standform").html($(html).find('#standform'));
				}
			});

		}
	</script>

	<script type="text/javascript">
		/**
		  @param {idinfo} ид выбранной строки
		  @param {flagclick} флаг который указывает что метод вызван после клика по мышке

		 */
		function reloadSignal(idinfo, flagClick) {
	
			var val = 0;

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//Название выбранного окна с правой панели
			var panelcurrent = document.getElementById("panelcurrent").innerHTML;

			var date = $('#datefrom').val();
			var date1 = $('#dateto').val();
			var rowCount = $('#plumetab tr').length;

			//Считываем значения флага обновления
			//1 обновляем 0-не обновляем. Расчитывается в контроллере =1 при клике на новую тревогу или если в таблице alarm были изменения

			var p = document.getElementById('flagUpdateSignal');
			var text = p.textContent;
			var isUpload = Number(text);

			var pMaineq = document.getElementById('flagUpdateMaineq');
			var textMaineq = pMaineq.textContent;
			var isUploadMaineq = Number(textMaineq);

			var pStand = document.getElementById('flagUpdateStand');
			var textStand = pStand.textContent;
			var isUploadStand = Number(textStand);

			var pEventlist = document.getElementById('flagUpdateEventlist');
			var textEventlist = pEventlist.textContent;
			var isUploadEventlist = Number(textEventlist);

			$
					.ajax({
						beforeSend : function(xhr) {
							// here it is
							xhr.setRequestHeader(header, token);
						},
						type : "POST",

						url : url,
						data : {
							date : date,
							date1 : date1,
							idinfo : idinfo,
							panelcurrent : panelcurrent,
							myclick : flagClick
							//myclick : 0
						},

						cache : false,
						success : function(html) {
							//обновляем значения благов обновления
							$("#flagUpdateSignal").html(
									$(html).find('#flagUpdateSignal').html());
							$("#flagUpdateClick").html(
									$(html).find('#flagUpdateClick').html());
							$("#flagUpdateMaineq").html(
									$(html).find('#flagUpdateMaineq').html());
							$("#flagUpdateStand").html(
									$(html).find('#flagUpdateStand').html());
							$("#flagUpdateEventlist")
									.html(
											$(html)
													.find(
															'#flagUpdateEventlist')
													.html());
							//Основные таблицы обновляем только если были обновления смотрим по таблице update_last
							if (isUpload > 0 || flagClick == 1) {
								$("#signalfull").html(
										$(html).find('#signalfull').html());

								$("#signalnew").html(
										$(html).find('#signalnew').html());

							}

							//$("#standtab").html($(html).find('#standtab').html())
							//Вкладки на правой панели обновляем только по клику на кнопке и только на текущей вкладке
							if (flagClick == 1) {
								if (panelcurrent == 'Kard') {
									$("#kard").html(
											$(html).find('#kard').html());
									//Обновляем внутренние вкладки
									$("#Note").html(
											$(html).find('#Note').html());
									$("#Additionally").html(
											$(html).find('#Additionally')
													.html());
									$("#Change").html(
											$(html).find('#Change').html());
									$("#EquipmentRent").html(
											$(html).find('#EquipmentRent')
													.html());
								}
								if (panelcurrent == 'Maineq')
									$("#maineq").html(
											$(html).find('#maineq').html());
								if (panelcurrent == 'Timetable')
									$("#timetable").html(
											$(html).find('#timetable').html());
								if (panelcurrent == 'Response')
									$("#response").html(
											$(html).find('#response').html());
								//вкладку шлейфы с правой панели обновляем только если открыта данная вкладка и были обновления в таблице maineq
								if (panelcurrent == 'Plume')
									$("#plume").html(
											$(html).find('#plume').html());
								if (panelcurrent == 'Key')
									$("#key").html($(html).find('#key').html());
								if (panelcurrent == 'ResponsPeop')
									$("#responspeop")
											.html(
													$(html)
															.find(
																	'#responspeop')
															.html());
								if (panelcurrent == 'State')
									$("#state").html($(html).find('#state'));
								if (panelcurrent == 'Events') {
									$("#event").html(
											$(html).find('#event').html());
									$("#datefrom").html(
											$(html).find('#datefrom').html());

									$("#accauntSign")
											.html(
													$(html)
															.find(
																	'#accauntSign')
															.html());

								}

								if (panelcurrent == 'Schema')
									$("#schema").html($(html).find('#schema'));
								if (panelcurrent == 'Photo')
									$("#photo").html(
											$(html).find('#photo').html());
								if (panelcurrent == 'Stand')
									$("#stand").html(
											$(html).find('#stand').html());

								//$("#objectEventPanel").html($(html).find('#objectEventPanel').html());

							}
							//вкладку шлейфы с правой панели обновляем только если открыта данная вкладка и были обновления в таблице maineq
							
							if (isUploadMaineq > 0){
								$("#plume").html($(html).find('#plume').html());
								$("#maineq").html(
										$(html).find('#maineq').html());
							}
							if (isUploadEventlist > 0)
								$("#event").html($(html).find('#event').html());
							if (isUploadStand > 0)
								$("#stand").html($(html).find('#stand'));

							//При выборе объекта в таблице тревоги передаем выбранный объект все функции в меню   выполнить и команды
							if (flagClick == 1) {
								$("#myModalform").html(
										$(html).find('#myModalform'));
								$("#myModalStatusPoolform").html(
										$(html).find('#myModalStatusPoolform')
												.first());
								$("#myModalCloseform").html(
										$(html).find('#myModalCloseform')
												.first());
								$("#divMyAlarm").html(
										$(html).find('#divMyAlarm').first());

								$("#myModalBypassform").html(
										$(html).find('#myModalBypassform')
												.first());
								//	divBypass
								//	tabmyModBypass

								$("#myModalPoint").html(
										$(html).find('#myModalPoint').first());
								$("#latlng1").html(
										$(html).find('#latlng1').first());
							}

							//	$("#perifinfoBan").html($(html).find('#perifinfoBan').html());
							//	$("#perifinfoBanMod").html($(html).find('#perifinfoBanMod').html());
							//	$("#objectstatus").html($(html).find('#objectstatus').html());
							//$("#nottime").html($(html).find('#nottime').html());
							//$("#malfunction").html($(html).find('#malfunction').html());
							//	$("#divClose").html($(html).find('#divClose').html());
							//Обновляем музыку
							$("#MusicSig").html(
									$(html).find('#MusicSig').html());

							//update span (количество на кнопках панели)
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
							
							

						}
					})

		};
	</script>
	<script>
		$(document).ready(function() {

			var timers = setTimeout(function ticks() {

				idinfo = document.getElementById("currentIdInfo").innerHTML;

				reloadSignal(idinfo, 0);
				window.onbeforeunload = function() {
					return null;
				};
				timers = setTimeout(ticks, 500);

			}, 1);
		});
		$(document).ready(function() {
			Cheshka('MusicSig');
			setInterval("Cheshka('MusicSig')", 250);

		});
	</script>

	<script>
		var group;

		function reloadForm() {
			$("#tabmyModGrArr").html($(html).find('#tabmyModGrArr'));
			$("#divmyModGrArr").html($(html).find('#divmyModGrArr'));

		}
	</script>
	<script>
		var flag = 1;
		var audio = document.getElementById("myaudio");
		audio.volume = 0.2;
	</script>

</body>

</html>