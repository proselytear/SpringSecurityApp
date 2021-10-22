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

<body onload="clickMeth('buttonStand')">

	<div id="stand" class="yyy">

		<form id="standform" onsubmit="return checkForm(this);" class="padbot" 
		items="${perifinfoBanIdinfo}" var="perifinfoBanIdinfo" method="post" 
		action="/SpringSecurityApp/home">
<input	type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
			<p>
				<b class="card_middle_text">Объект </b> <input type="text" class="card_middle_size"
				value="${perifinfoBanIdinfo.getMaineq().getAccountid()}"
				name="accountId" id="accountIdstand">
			</p>

			<div class="fieldkard">
				<div class="leftfield">Группа</div>
				<div class="rightfield">
					<input type="text" value="${perifinfoBanIdinfo.getGrouprel()}" 
					name="grouprel" id="grouprelstand">
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield">Шлейф</div>
				<div class="rightfield">
					<input type="text" name="paramnum" id="paramnumstand">
				</div>
			</div>

			<jsp:useBean id="obj" class="net.proselyte.springsecurityapp.model.Employees" scope="page" />

			<div class="fieldkard">
				<div class="leftfield">Работает техник</div>
				<div class="rightfield">
					<select name="idTechnician" id="idTechnician">
						<c:forEach var="listTechnic" items="${listTechnic}">
							<option value="${listTechnic.getId()}">${listTechnic.getFullName()}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield">
					<input type="radio" name="constTime1" onclick="handleClick(1);" 
					value="0" checked="checked"><label for="scales"> На время</label>
				</div>
				<div class="rightfield">
					<input id="timestand" type="text" name="appt_time" value="01:00">
				</div>
			</div>



			<br> <input type="radio" name="constTime1" onclick="handleClick(0);" value="1"> 
			<label for="scales"><b class="card_middle_text">Постоянные стенды</b></label>
			
			<br> 
			<input type="button" class="tablink31" name="nameFunc" value="Добавить в стенды" 
			onclick="addStand('addStand',  'standform')">
			<input type="button" class="tablink31" name="nameFunc" value="Вывести из стендов" 
			onclick="addStand('removeStand',  'standform')"><br>
		</form>
		<form id="form-all1" method="post" action="home">


			 <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div>
				<table class="table_small" id="standtab" 	class="table-striped">
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

					<tbody id="t_bodySt1">
						<c:forEach items="${perifinfoBanIdinfoFull}"
							var="perifinfoBanIdinfoFull" varStatus="status">
							<tr class="rowlink">
								<td><input type="hidden" name="idinfo" class="item"
									value="${perifinfoBanIdinfoFull.getIdinfoStand()}">${perifinfoBanIdinfoFull.getIdinfoStand()}</td>
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
		<span id="StandSpan">${error}</span>


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

				$
						.ajax({
							beforeSend : function(xhr) {
								// here it is
								xhr.setRequestHeader(header, token);
							},
							type : "POST",

							url : "home",
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

								$("#StandSpan").html(
										$(html).find('#StandSpan').html());


							}
						});
			}
		}
	</script>


</body>
</html>