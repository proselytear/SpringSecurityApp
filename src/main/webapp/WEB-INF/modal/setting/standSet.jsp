<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Search Result</title>
<script src="resources/js/click.js" type="text/javascript"></script>
</head>

<body>

	<div id="standSet" class="yyy">


		<form id="standform" onsubmit="return checkForm(this);" items="${perifinfoBanIdinfo}" var="perifinfoBanIdinfo" method="post" action="/SpringSecurityApp/home">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> <br> 
			<input type="button" class="btn btn-info" name="nameFunc" value="Добавить в стенды"	onclick="addStand('addStand',  'standform')"> 
			<input type="button" class="btn btn-info" name="nameFunc" value="Вывести из стендов" onclick="addStand('removeStand',  'standform')"><br><br> 
			<b class="card_middle_text">Объект</b> 
			<input type="text" class="card_middle_size" value="${perifinfoBanIdinfo.getMaineq().getAccountid()}" name="accountIdSet" id="accountIdstandSet"><br><br>
			<b class="card_middle_text">Группа</b> <input type="text" value="${perifinfoBanIdinfo.getGrouprel()}" name="grouprel" class="card_middle_size" id="grouprelstandSet"><br> <br>
			<b class="card_middle_text">Шлейф</b> <input type="text" class="card_middle_size" name="paramnum" id="paramnumstandSet"><br>




			<jsp:useBean id="obj" class="net.proselyte.springsecurityapp.model.Employees" scope="page" />
			<br>
			<b class="card_middle_text">Работает техник</b> <select class="card_middle_size" name="idTechnicianSet" id="idTechnicianSet">
				<c:forEach var="listTechnic" items="${listTechnic}">
					<option value="${listTechnic.getId()}">${listTechnic.getFullName()}</option>
				</c:forEach>
			</select> <br> <br> 
			<input type="radio" name="constTime1" onclick="handleClick(1);" value="0" checked="checked">
			<label for="scales"> <br><b class="card_middle_text">На время</b></label>
			<input id="timestandSet" type="text" class="card_big_text" name="appt_time" value="01:00"><br> <br> 
			<input type="radio" name="constTime1" onclick="handleClick(0);" value="1">
			<label for="scales"><b class="card_middle_text">Постоянные стенды</b></label>


		</form>
		<form id="form-allSet" method="post" action="home">


			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<div>
				<table class="table_small" id="standtabSet"class="table-striped">
					<thead>
						<tr>
							<th></th>
							<th>Номер</th>
							<th>Гр.</th>
							<th>Шлейфы</th>
							<th>Действует до</th>
							<th>Техник</th>
							<th>Оператор</th>

						</tr>
					</thead>

					<tbody id="t_bodyStSet">
						<c:forEach items="${perifinfoBanIdinfoFull}" var="perifinfoBanIdinfoFull" varStatus="status">
							<tr class="rowlink">
								<td><input type="hidden" name="idinfo" class="item" value="${perifinfoBanIdinfoFull.getIdinfoStand()}">${perifinfoBanIdinfoFull.getIdinfoStand()}</td>
								<td>${perifinfoBanIdinfoFull.getMaineq().getAccountid() }</td>

								<td>${perifinfoBanIdinfoFull.getGrouprel() }</td>
								<td>${perifinfoBanIdinfoFull.getParamnumSensor()  }</td>
								<td>${perifinfoBanIdinfoFull.getTimeBan() }</td>
								<td>${perifinfoBanIdinfoFull.getEmployees().getFullName()}</td>
								<td>${perifinfoBanIdinfoFull.getUser().getEmployees().getFullName()}</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>


		</form>
		<span id="StandSpanSet">${error}</span>


	</div>
	<script>
	function handleClick(constTime) {
		if (constTime == 0)
			document.getElementById("timestand").style.display = "none";
		else
			document.getElementById("timestand").style.display = "inline";

	}

	$(document).on('click', '#t_bodySt1>.rowlink', function() {
		$(this).addClass('selected').siblings().removeClass('selected');

		var value = $(this).find('td:first input').val();

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
		myclickStand(value);

	});

	function addStand(nameFunc, func) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var url = $(location).attr('pathname');
		//var nameFunc = $('#nameFunc').val();

		if (checkForm(document.getElementById(func))) {

			var accountIdstand = $('#accountIdstand').val();
			var grouprelstand = $('#grouprelstand').val();
			var paramnumstand = $('#paramnumstand').val();
			var idTechnician = $('#idTechnician').val();
			var timestand = $('#timestand').val();
			var constTime = document
					.querySelector('input[name="constTime1"]:checked').value;

			$.ajax({
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				type : "POST",

				url : "home/addStand",
				data : {
					nameFunc : nameFunc,
					accountId : accountIdstand,
					grouprel : grouprelstand,
					paramnum : paramnumstand,
					idTechnician : idTechnician,
					appt_time : timestand,
					constTime : constTime
				},

				cache : false,
				success : function(html) {

					$("#StandSpan").html($(html).find('#StandSpan'));

				}
			});
		}
	}
</script>

</body>
</html>