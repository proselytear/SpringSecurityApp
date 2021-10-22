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
<script type="text/javascript" language="javascript"
	src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" language="javascript"
	src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
</head>
<body>




	<p id="currentIdEvent" hidden="true"></p>
	<div style="display: none" id='MusicAll'>${alarmfullSize}</div>
	<div class="width700" >

		<button id="btnExport" class="tablink31" onclick="ExportFile()">
			<img src="resources/images/button/download.png"
				style="vertical-align: middle">Export
		</button>
		<input placeholder="Введите имя файла" type="text" name="fileName" id="fileName" />
		<div id="fileError" class="cent">
		
			<b class="messageRed">${error}</b>

		</div>

		<form id="form-event" method="post" action="home">
			<div class="fieldkard">
			<div class="rightfield"><input type="text" id="myInput" onkeyup="myFunction()" class="card_middle_size"
				placeholder="Введите описание события" name="keyword"
				autocomplete="on" /> </div>
				<div class="rightfield"><input type="text" id="myInputMiddle" class="card_middle_size"
				onkeyup="myFunction()" placeholder="Введите объект" name="keyword2"
				autocomplete="on" /></div>
			</div>		
		<!--<div class="fieldkard">
			<div class="leftfield">Дата и время с </div>			
			<div class="rightfield"><input type="datetime-local" name="date" id="datefrom" /></div>
			<div class="leftfield small">по</div>		
			<div class="rightfield"><input type="datetime-local" name="date1" id="dateto" /></div>
		</div>	-->	
			<p>
				<label for="localdate" ><b class="card_middle_text">Дата и время c:</b> </label> <input class="card_middle_size"
					type="datetime-local" name="date" id="datefrom" /> <label
					for="localdate"><b class="card_middle_text"> по: </b></label> <input class="card_middle_size"
					type="datetime-local" name="date1" id="dateto" />
			</p>
			<b class="card_middle_text">Ранг события</b> <select size="6" multiple="multiple" name="evrangid" class="card_middle_size"
				id="evrangid">
				<c:forEach var="evrangFull" items="${evrangFull}">
					<option value="${evrangFull.getEvrangid()}">${evrangFull.getNameevrang()}</option>
				</c:forEach>

			</select><b class="card_middle_text"> Отображать количество событий</b><select id="sizeColumn"
				name="sizeColumn" class="card_middle_size4">

				<option value="50">50</option>
				<option value="100">100</option>
				<option value="250">250</option>
				<option value="500">500</option>
				<option value="1000">1000</option>

			</select> <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <br></br> CTRL(множественный выбор)
			<div>
<div class="scroll-table-body">
				<table id="eventtabFull"  class="table_big">
					<thead>
						<tr>
							<th></th>

							<th>Объект</th>
							<th>Код</th>
							<th>Тип кода</th>
							<th>Ранк</th>
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
			</div>


		</form>



	</div>

</body>



<script>
	function ExportFile() {
		var evrangid = "";
		$('#evrangid option').each(function(i) {

			if (this.selected == true) {
				evrangid = evrangid + "," + (this.value);
			}

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
	$(document).ready(function() {

		var timers = setTimeout(function ticks() {

			var idinfo = document.getElementById("currentIdEvent").innerHTML;
			reloadEventView(idinfo);
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
						setInterval("Cheshka('MusicAll')", 250);

					});
	
</script>
</html>