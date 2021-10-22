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
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
<script src="resources/js/panel.js" type="text/javascript"></script>
<script src="resources/js/click.js" type="text/javascript"></script>
<script src="resources/js/musicAlarm.js" type="text/javascript"></script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
</head>

<body onload="clickMeth('buttonEvent')">
	<div class="nav1">
		<p id="currentIdEvent" hidden="true"></p>
		<p id="flagUpdateClickEvent">${flagUpdateClickEvent}</p>
		<p id="flagUpdateEventlistEvent">${flagUpdateEventlistEvent}</p>
	</div>


	<div style="display: none" id='MusicAll'>${alarmfullSize}</div>
	<div class="width700">

		<button id="btnExport" class="tablink31" onclick="ExportFile()">
			<img src="resources/images/button/download.png"
				style="vertical-align: middle">Export
		</button>
<!--  	<button class="tablink31" onclick="foundEvent()" id="foundEvent">Найти</button> -->

		<input placeholder="Введите имя файла" type="text" name="fileName"
			id="fileName" />
		<div id="fileError" class="messageRed">${error}</div>

		<form id="form-event" method="post" action="home">
			<div class="line">
				<input type="text" id="myInput" onkeyup="foundEvent()"
					class="card_middle_size" placeholder="Введите описание события"
					name="keyword" autocomplete="on" /> <input type="text"
					id="myInputMiddle" class="card_middle_size" onkeyup="foundEvent()"
					placeholder="Введите номер объекта" name="keyword2"
					autocomplete="on" />
			</div>
			<div class="line">
				<label for="localdate"><b class="card_middle_text">Дата
						и время c:</b> </label> <input class="card_middle_size" type="datetime-local" onkeyup="foundEvent()"
					name="date" id="datefrom" /> <label for="localdate"><b
					class="card_middle_text"> по: </b></label> <input class="card_middle_size" onkeyup="foundEvent()"
					type="datetime-local" name="date1" id="dateto" /> <b
					class="card_middle_text">Ранг события</b>
				<div id="evrangid" onkeyup="foundEvent()" name="evrangid" class="checkselect">
					<c:forEach var="evrangFull" items="${evrangFull}">
						<label><input type="checkbox" name="brands[]"
							class="messageCheckbox" value="${evrangFull.getEvrangid()}">${evrangFull.getNameevrang()}</label>
					</c:forEach>
				</div>
				<b class="card_middle_text"> Отображать количество событий</b> <select
					id="sizeColumn" name="sizeColumn" class="card_middle_size4" onkeyup="foundEvent()">

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
		<table id="eventtabFull" class="table_big">
			<thead>
				<tr>
					<th style='display: none'></th>

					<th>Объект</th>
					<th>Код</th>
					<th>Тип кода</th>
					<th>Ранг</th>
					<th>Дата</th>
					<th>Время</th>
					<th>Гр.</th>
					<th>Название</th>
					<th>Шл.</th>
					<th>Название</th>
					<th>Описание события</th>

				</tr>
			</thead>

			<tbody id="t_bodyEvf">
				<c:forEach items="${eventlistObjFull}" var="eventlistObjFull"
					varStatus="status">

					<p>${eventlistObjFull.getTab()}</p>

				</c:forEach>
			</tbody>
		</table>
	</div>


	<script>
	  //При нажатии Enter вызываем функцию Найти
	// Get the input field
	var input = document.getElementById("myInput");

	// Execute a function when the user releases a key on the keyboard
	input.addEventListener("keyup", function(event) {
		// Number 13 is the "Enter" key on the keyboard
		if (event.keyCode === 13) {
			alert("foundEvent");
			// Cancel the default action, if needed
			event.preventDefault();
			// Trigger the button element with a click
			document.getElementById("foundEvent").click();
		}
	});
	
		function ExportFile() {
			var evrangid = "";
			$('input[name="brands[]"]:checked').each(function() {

				evrangidStr = evrangidStr + "," + (this.value);
			});

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//var nameFunc = $('#nameFunc').val();
			var keyword = $('#myInput').val();
			var keyword2 = $('#myInputMiddle').val();
			var city_id = $('#city_id').val();
			var date = $('#datefrom').val();
			var date1 = $('#dateto').val();
			var fileName = $('#fileName').val();
			var sizeColumn = $('#sizeColumn').val();

			$.ajax({
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				type : "POST",

				url : "objectevent",
				data : {
					nameFunc : "ExportFile",
					keyword : keyword,
					keyword2 : keyword2,
					city_id : city_id,
					date : date,
					date1 : date1,
					evrangidStr : evrangid,
					sizeColumn : sizeColumn,
					fileName : fileName
				},

				cache : false,
				success : function(html) {
					$("#fileError").html($(html).find('#fileError').html());
				}
			});

		}
	</script>
	<script>
		$(document).on('click', '#t_bodyEvf>.rowlink', function() {
			$(this).addClass('selected').siblings().removeClass('selected');

			var value = $(this).find('td:first input').val();
			document.getElementById("currentIdEvent").innerHTML = value;

			var rows = window.document.querySelectorAll("table tbody tr");

			for (var i = 0; i < rows.length; i++) { // перебираем все строки

				var cols = rows[i].querySelectorAll('td'); // получаем столбцы

				for (var j = 0; j < cols.length; j++) { // перебираем все столбцы

				}
			}

			for (var i = 0; i < rows.length; i++) { // перебираем все строки

				var cols = rows[i].querySelectorAll('td'); // получаем столбцы

				for (var j = 0; j < cols.length; j++) { // перебираем все столбцы
					if (cols[j].textContent == value) {

						cols[j].parentNode.style.backgroundColor = "#000080";
						cols[j].parentNode.style.сolor = "#ffffff";

					}// выводим текст из столбца

				}
			}
			var idinfo = document.getElementById("currentIdEvent").innerHTML;
			myclickEvent(idinfo);

		});
		$(document)
				.ready(
						function() {

							var timers = setTimeout(
									function ticks() {

										var idinfo = document
												.getElementById("currentIdEvent").innerHTML;
										reloadEventView(idinfo, 0);
										window.onbeforeunload = function() {
											return null;
										};
										timers = setTimeout(ticks, 500);

									}, 1);
						});
	</script>

	<script>
		$(document).ready(function() {

			Cheshka('MusicAll');
			setInterval("Cheshka('MusicAll')", 250);

		});
	</script>
	<script>
		//Функция для выборки элементов из ранга событий
		(function($) {
			function setChecked(target) {
				var checked = $(target).find("input[type='checkbox']:checked").length;
				if (checked) {
					$(target).find('select option:first').html(
							'Выбрано: ' + checked);
				} else {
					$(target).find('select option:first').html(
							'Выберите из списка');
				}
			}

			$.fn.checkselect = function() {
				this.wrapInner('<div class="checkselect-popup"></div>');
				this
						.prepend('<div class="checkselect-control">'
								+ '<select class="form-control"><option></option></select>'
								+ '<div class="checkselect-over"></div>'
								+ '</div>');

				this.each(function() {
					setChecked(this);
				});
				this.find('input[type="checkbox"]').click(function() {
					setChecked($(this).parents('.checkselect'));
				});

				this.parent().find('.checkselect-control').on(
						'click',
						function() {
							$popup = $(this).next();
							$('.checkselect-popup').not($popup).css('display',
									'none');
							if ($popup.is(':hidden')) {
								$popup.css('display', 'block');
								$(this).find('select').focus();
							} else {
								$popup.css('display', 'none');
							}
						});

				$('html, body').on('click', function(e) {
					if ($(e.target).closest('.checkselect').length == 0) {
						$('.checkselect-popup').css('display', 'none');
					}
				});
			};
		})(jQuery);

		$('.checkselect').checkselect();
	</script>

</body>
</html>