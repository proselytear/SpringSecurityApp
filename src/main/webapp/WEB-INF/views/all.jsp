<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<title>Search Result</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>


<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
<script src="resources/js/panel.js" type="text/javascript"></script>
<script src="resources/js/reloadCurrentPage.js" type="text/javascript"></script>
<script src="resources/js/musicAlarm.js" type="text/javascript"></script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>

</head>
<body
	onload="clickMeth(['buttonObject', 'buttonAll', 'defaultbutton', 'defaultbuttonKard'])">






	<div style="display: none" id='MusicAll'>${alarmfullSize}</div>


	<div class="nav1">
		<p id="currentIdInfoAll"></p>
		<p id="flagUpdateClickAll">${flagUpdateClickAll}</p>
		<p id="flagUpdateMaineqAll">${flagUpdateMaineqAll}</p>
		<p id="flagUpdateStandAll">${flagUpdateStandAll}</p>
		<p id="flagUpdateEventlistAll">${flagUpdateEventlistAll}</p>
	</div>


	<form id="form-all" onsubmit="return false" method="post" action="home">


		<input type="text" id="myInput" onkeyup="foundObjectView()"
			placeholder="Введите номер, название " name="keyword"
			autocomplete="on" /> 
			
			<select id="city_id" name="city_id"  hidden="true"
			class="card_middle_size">
			<option value="-1"></option>
			<option value="190">Киев</option>
			<option value="172">Ирпень</option>
			<option value="245">Мелитополь</option>
			<option value="246">Никополь</option>
			<option value="52">Борисполь</option>
		</select> 
		<b class="card_middle_text"> Отображать количество событий</b>
				<select id="sizeColumnAll" name="sizeColumn" class="card_middle_size4" onkeyup="foundObjectView()">
                    <option value="30">30</option>
			        <option value="50">50</option>
					<option value="100">100</option>
					<option value="250">250</option>
					<option value="500">500</option>
					

				</select>
</br>
	</form>
	<div class="scroll-table-body0">

		<table id="listobjectview1" class="table_big">
			<thead>
				<tr>

					<th style='display: none'></th>
					<th></th>
					<th>Объект <!--<svg width="17px" height="12px"><circle cx="6" cy="6" r="6" fill="red  " /></svg>-->
					</th>
					<th>Гр.</th>
					<th>Название</th>
					<th>Адрес</th>
					<th>Время</th>
					<th>Статус</th>

				</tr>
			</thead>


			<tbody id="t_bodyall">
				<c:forEach items="${listObjectView2}" var="listObjectView2"
					varStatus="status">
					<p>${listObjectView2.getTab()}</p>


				</c:forEach>
			</tbody>
		</table>
	</div>







	<script>
		$("div").scroll(function() { // задаем функцию при срабатывании события "scroll" на элементе <div>
			//alert("scroll");
		});


		    $("select").click(function(e){
		    	foundObjectView();

		    });



		//Обрабатываем клик по выбранной строке
		$(document)
				.on(
						'click',
						'#t_bodyall>.rowlink',
						function() {

							flag = 0;

							$(this).addClass('selected').siblings()
									.removeClass('selected');

							var value = $(this).find('td:first input').val();//находим значения  первого столбца выбранной строки

							document.getElementById("currentIdInfoAll").innerHTML = value; //записываем значение этой переменной на странице

							var rows = window.document
									.querySelectorAll("table tbody tr"); //получаем все строки таблицы

							for (var i = 0; i < rows.length; i++) { // перебираем все строки

								var cols = rows[i].querySelectorAll('td'); // получаем столбцы

								for (var j = 0; j < cols.length; j++) { // перебираем все столбцы
									//красим строки в зависимости от статуса объекта
									cols[j].parentNode.style.color = "black";
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

										cols[j].parentNode.style.backgroundColor = "navy";
										cols[j].parentNode.style.color = "white";

									}

									// выводим текст из столбца

								}
							}
							//var idinfo = document.getElementById("currentIdAll").innerHTML;
							value = document.getElementById("currentIdInfoAll").innerHTML;

							myclick(value, 1);
							//вызываем функцию для отправки данных клика на конироллер и прорисовка данных после возврата 

						});
		$(document).ready(
				function() {
					//функция срабатывает каждые пол секунды для обновления данных
					var timers = setTimeout(function ticks() {

						var idinfo = document
								.getElementById("currentIdInfoAll").innerHTML;

						reloadObjectView(idinfo, 0);//отправка обновления данных на контролер и прорисовка данных после возврата 
						window.onbeforeunload = function() {
							return null;
						};

						timers = setTimeout(ticks, 500);

					}, 1);
				});
	</script>
	<script>
	    //При нажатии Enter вызываем функцию Найти
		// Get the input field
		var input = document.getElementById("myInput");

		// Execute a function when the user releases a key on the keyboard
		input.addEventListener("keyup", function(event) {
			// Number 13 is the "Enter" key on the keyboard
			if (event.keyCode === 13) {
				// Cancel the default action, if needed
				event.preventDefault();
				// Trigger the button element with a click
				document.getElementById("foundObject").click();
			}
		});
		
		
		
		
		//Запуск музыки
		$(document).ready(function() {

			Cheshka('MusicAll');
			setInterval("Cheshka('MusicAll')", 250);

		});
		//Обновление объектов через указанный промежуток времени
		/**
				  @param {idinfo} ид выбранной строки
				  @param {flagclick} флаг который указывает что метод вызван после клика по мышке

		 */
		function foundObjectView() {

			var evrangid = "";
			var panelcurrent = document.getElementById("panelcurrent").innerHTML;//Название выбранной вкладки в правой панели окна

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			var keyword = $('#myInput').val(); //параметр поиска обїекта(Введите номер, название)

			var city_id = $('#city_id').val();
			var date = $('#datefrom').val();//Параметр поиска событий в правой части панели время с
			var date1 = $('#dateto').val();//Параметр поиска событий в правой части панели время по
			var sizeColumn = $('#sizeColumnAll').val(); //количество отображаемых объектов

			$
					.ajax({
						beforeSend : function(xhr) {
							// here it is
							xhr.setRequestHeader(header, token);
						},
						type : "POST",

						url : url,
						data : {
							keyword : keyword,

							city_id : city_id,
							date : date,
							date1 : date1,
							evrangidStr : evrangid,
							sizeColumn : sizeColumn,

							panelcurrent : panelcurrent,
							nameFunc : "foundObjectView"
						},

						success : function(html) {

							$("#listobjectview1").html(
									$(html).find('#listobjectview1').html());

							if (panelcurrent == 'Kard') {
								$("#kard").html($(html).find('#kard').html());
								//Обновляем внутренние вкладки
								$("#Note").html($(html).find('#Note').html());
								$("#Additionally").html(
										$(html).find('#Additionally').html());
								$("#Change").html(
										$(html).find('#Change').html());
								$("#EquipmentRent").html(
										$(html).find('#EquipmentRent').html());
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
								$("#plume").html($(html).find('#plume').html());
							if (panelcurrent == 'Key')
								$("#key").html($(html).find('#key').html());
							if (panelcurrent == 'ResponsPeop')
								$("#responspeop").html(
										$(html).find('#responspeop').html());
							if (panelcurrent == 'State')
								$("#state").html($(html).find('#state'));
							if (panelcurrent == 'Events') {
								$("#event").html($(html).find('#event').html());
								$("#datefrom").html(
										$(html).find('#datefrom').html());

								$("#accauntSign").html(
										$(html).find('#accauntSign').html());

							}

							if (panelcurrent == 'Schema')
								$("#schema").html($(html).find('#schema'));
							if (panelcurrent == 'Photo')
								$("#photo").html($(html).find('#photo').html());
							if (panelcurrent == 'Stand')
								$("#stand").html($(html).find('#stand').html());

						}
					})

		};
	</script>


</body>
</html>


