<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>

</head>

<body>
	<div class="modal" id="myModalTechnic">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h6 class="modal-title">Выслать техника на объект</h6>
					<button type="button" class="close" data-dismiss="modal"></button>
				</div>


				<div id="divmyModalTechnic" class="modal-body">

					<form id="myModalTechnicform" onsubmit="return checkForm(this);" items="${perifinfoBanIdinfo}" var="perifinfoBanIdinfo" method="post" action="/SpringSecurityApp/home">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> <br> 
						<input type="button" class="btn btn-info" name="nameFunc" value="Техник пришел на объект" onclick="arrivedTechnic('arrivedTechnic',  'myModalTechnicform')"> 
						<input type="button" class="btn btn-info" name="nameFunc" value="Техник ушел с объекта" onclick="arrivedTechnic('leftTechnic',  'myModalTechnicform')"><br> <br> 
						<span>${error}</span>Объект 
						<input type="text" value="${perifinfoBanIdinfo.getMaineq().getAccountid()}" name="accountId" id="accountTech"><br> <br>Группа 
						<input type="text" value="${perifinfoBanIdinfo.getGrouprel()}" name="grouprel" id="grouprelTech"><br> <br>Шлейф 
						<input type="text" value="" name="paramnum"><br> <br>На время 
						<input type="time" name="appt_time"	id="appt_time_tech" value="01:00"><br>
						
						<jsp:useBean id="obj3" class="net.proselyte.springsecurityapp.model.Employees" scope="page" />

						<br>Работает техник <select name="idTechnician" id="idTechnicAr">
							<c:forEach var="listTechnic" items="${listTechnic}">
								<div>
									<option value="${listTechnic.getId()}">${listTechnic.getFullName()}</option>
								</div>
							</c:forEach>
						</select> <br>


					</form>
					<div id="divTechArrived">
						<br />
						<h7 style="color:Red; text-align: center;">${error}</h7>
						<h7 style="color:Blue; text-align: center;">${messageSuccess}</h7>

					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Закрыть</button>

				</div>

			</div>
		</div>
	</div>
	<script>
		function arrivedTechnic(nameFunc, func) {

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//var nameFunc = $('#nameFunc').val();

			if (checkForm(document.getElementById(func))) {

				var accountIdstand = $('#accountTech').val();
				var grouprelstand = $('#grouprelTech').val();
				var idTechnician = $('#idTechnicAr').val();
				var paramnumstand = $('#grouprelTech').val();
				var appt_time = $('#appt_time_tech').val();

				$.ajax({
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
						appt_time : appt_time,
						idTechnician : idTechnician
					},

					cache : false,
					success : function(html) {
						$("#divTechArrived").html(
								$(html).find('#divTechArrived').html());
					}
				});
			}
		}
	</script>
</body>
</html>