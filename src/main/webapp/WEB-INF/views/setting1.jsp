<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<title>Search Result</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>


<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
<script src="resources/js/panel.js" type="text/javascript"></script>
<script src="resources/js/reloadCurrentPage.js" type="text/javascript"></script>
<script src="resources/js/musicAlarm.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript"
	src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" language="javascript"
	src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>







<script>
	function myclickSet(idinfo) {

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var url = $(location).attr('pathname');
		var panelcurrent = document.getElementById("panelcurrentSet").innerHTML;

		$.ajax({
			type : "post",

			beforeSend : function(xhr) {
				// here it is
				xhr.setRequestHeader(header, token);
			},
			data : {
				idinfo : idinfo,
				myclick : 1,
				panelcurrent: panelcurrent
			},

			url : "objectsetting",

			cache : false,
			success : function(html) {

				$("#listobjectviewSet")
						.html($(html).find('#listobjectviewSet'));
				$("#kardSet")
				.html($(html).find('#kardSet'));

			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert(thrownError + "\r\n" + xhr.statusText + "\r\n"
						+ xhr.responseText);
			}
		});

	}

	$(document).on('click', '#t_bodysetting>.rowlink', function() {

		$(this).addClass('selected').siblings().removeClass('selected');

		var value = $(this).find('td:first input').val();
		document.getElementById("currentIdSetting").innerHTML = value;

		var rows = window.document.querySelectorAll("table tbody tr");

		for (var i = 0; i < rows.length; i++) { // перебираем все строки

			var cols = rows[i].querySelectorAll('td'); // получаем столбцы

			for (var j = 0; j < cols.length; j++) { // перебираем все столбцы
				
				cols[j].parentNode.style.сolor = "#000000";
					cols[j].parentNode.style.backgroundColor = "#E0E0E0";
	

				

			}
		}

		for (var i = 0; i < rows.length; i++) { // перебираем все строки

			var cols = rows[i].querySelectorAll('td'); // получаем столбцы

			for (var j = 0; j < cols.length; j++) { // перебираем все столбцы
				if (cols[j].textContent != value) {

				} else {

					cols[j].parentNode.style.backgroundColor = "#000080";
					cols[j].parentNode.style.сolor = "#ffffff";

				}

				// выводим текст из столбца

			}
		}
		var idinfo = document.getElementById("currentIdSetting").innerHTML;

		myclickSet(idinfo);

	});
	function reloadSetting() {

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var url = $(location).attr('pathname');
		var keyword = $('#myInputSet').val();
		var city_id = $('#city_idSet').val();
		 var panelcurrent = document.getElementById("panelcurrentSet").innerHTML;

		$.ajax({
			beforeSend : function(xhr) {
				// here it is
				xhr.setRequestHeader(header, token);
			},
			type : "POST",

			url : "objectsetting",
			data : {
				keyword : keyword,

				city_id : city_id,
				panelcurrent: panelcurrent

			},

			success : function(html) {
				$("#listobjectviewSet")
						.html($(html).find('#listobjectviewSet'));
				
			}
		});
	}

	$(function() {
		$(".button").click(function() {
			reloadSetting();

		});
	});

</script>



</head>
<body>



	<br>
	<p id="currentIdSetting" hidden="true"></p>


	<form id="form-all" class="tablinks" onsubmit="return false"
		method="post" action="home">


		<br></br> <input type="text" id="myInputSet" onkeyup="myFunction()"
			class="card_middle_size" placeholder="Введите номер, название "
			name="keyword" autocomplete="on" /> <select id="city_idSet"
			name="city_idSet" class="card_middle_size">
			<option value="-1"></option>
			<option value="190">Киев</option>
			<option value="172">Ирпень</option>
			<option value="245">Мелитополь</option>
			<option value="246">Никополь</option>
			<option value="52">Борисполь</option>
		</select> <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> <input type="submit" name="submit"
			class="button" id="submit_btn" value="Найти" />

		<div class="scroll-table-body">

			<table id="listobjectviewSet" border="1" cellpadding="5"
				class="table_big">
				<thead>
					<tr>

						<th></th>
						<th></th>
						<th>Объект</th>
						<th>Гр.</th>
						<th>Название</th>
						<th>Адрес</th>
						<th>Время</th>

						<th>Сегодня с</th>
						<th>По</th>
					</tr>
				</thead>


				<tbody id="t_bodysetting">
					<c:forEach items="${listObjectView2}" var="listObjectView2"
						varStatus="status">
						<p>${listObjectView2.getTabSet()}</p>


					</c:forEach>
				</tbody>
			</table>
		</div>



	</form>

</body>


</head>

</html>