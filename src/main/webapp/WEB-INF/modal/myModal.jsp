<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>
</head>
<body>
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">Перевод объекта в стенды</h4>
				<a href="#close" title="Close" class="close">×</a>
			</div>


			<div id="myModalbody" class="modal-body">

				<span><b class="messageRed">Внимание! От объектов,
						которые в стендах все события идут автосбросом! Вы уверены?</b></span> <br>
				<form id="myModalform" onsubmit="return checkForm(this);"
					items="${perifinfoBanIdinfo}" var="perifinfoBanIdinfo"
					method="post" action="/SpringSecurityApp/home">


					<br> <b class="card_middle_text"> Объект </b><input
						type="text" class="card_middle_size"
						value="${perifinfoBanIdinfo.getMaineq().getAccountid()}"
						name="accountId" id="accountIdstandMod"><br> <br>
					<b class="card_middle_text">Группа</b> <input type="text"
						class="card_middle_size"
						value="${perifinfoBanIdinfo.getGrouprel()}" name="grouprel"
						id="grouprelstandMod"><br> <br> <b
						class="card_middle_text">Шлейф</b> <input type="text"
						class="card_middle_size"
						value="${perifinfoBanIdinfo.getParamnumSensor()}" name="paramnum"
						id="paramnumstandMod"><br></br> <input type="radio"
						name="constTime" onclick="handleClickModel(1);" value="0"
						checked="checked"><label for="scales"> <b
						class="card_middle_text">На время</b></label> <input id="timestandModel"
						type="text" name="appt_time" value="01:00"><br> <br>
					<input type="radio" name="constTime" onclick="handleClickModel(0);"
						value="1"> <label for="scales"><b
						class="card_middle_text">Постоянные стенды</b></label>

					<jsp:useBean id="obj"
						class="net.proselyte.springsecurityapp.model.Employees"
						scope="page" />
					<br> <b class="card_middle_text">Работает техник</b> <select
						class="card_middle_size" name="idTechnician2" id="idTechnicianMod">
						<c:forEach var="listTechnic" items="${listTechnic}">
							<option value="${listTechnic.getId()}"><b>${listTechnic.getFullName()}</b></option>
						</c:forEach>
					</select>
					<div id="modalspan">
						<br /> <b class="messageRed">${error}</b> <b class="messageRed">${messageSuccess}</b>

					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="button" class="tablink31"
						name="nameFunc" value="Добавить в стенды"
						onclick="addStandMod('addStand',  'myModalform')"> <input
						type="button" class="tablink31" name="nameFunc"
						value="Вывести из стендов"
						onclick="addStandMod('removeStand',  'myModalform')"><br>
				</form>
			</div>



		</div>
	</div>

	<script>
		function handleClickModel(constTime) {
			if (constTime == 0)
				document.getElementById("timestandModel").style.display = "none";
			else
				document.getElementById("timestandModel").style.display = "inline";

		}
		function addStandMod(nameFunc, func) {

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//var nameFunc = $('#nameFunc').val();
			if (checkForm(document.getElementById(func))) {
				var accountIdstand = $('#accountIdstandMod').val();
				var grouprelstand = $('#grouprelstandMod').val();
				var paramnumstand = $('#paramnumstandMod').val();
				var idTechnician = $('#idTechnicianMod').val();
				var timestand = $('#timestandModel').val();
				var constTime = document
						.querySelector('input[name="constTime"]:checked').value;

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

								$("#standtab").html(
										$(html).find('#standtab').html());
								$("#standform").html(
										$(html).find('#standform').html());
								$("#modalspan").html(
										$(html).find('#modalspan').html());
								
							}
						});
			}


		}
	</script>
</body>
</html>