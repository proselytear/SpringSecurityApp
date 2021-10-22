<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>
<script src="resources/js/panel.js" type="text/javascript"></script>
</head>

<body>

	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h6 class="modal-title">Прибытие групп реагирования на объект</h6>
				<a href="#close" title="Close" class="close">×</a>
			</div>


			<div id="myModalGroupArrived" class="modal-body">

				<div>
					<p>
						<b>Выберите группу прибывшую на объект</b>

					</p>

					<form id="myModalGroupArrivedform" method="post" action="objectsignal">
						<div id="Kard2" items="${objectviewId}" var="objectviewId">

							<b class="card_middle_text">Объект </b><input type="text" readonly="readonly" class="card_middle_size" value="${objectviewId.getAccountID()}" /> <br> <br>
							<b class="card_middle_text">Адрес</b><input type="text" readonly="readonly" class="card_middle_big" value="${objectviewId.getFullname()}" /> <br> <br> 
							<b class="card_middle_text"> </b><input type="hidden" class="card_middle_size" name="idinfo" value="${objectviewId.getIdinfo()}" id="idinfoModArrived" /> 
							<input type="hidden" class="card_middle_size" name="nameFunc" value="myModalGroupArrived" />
						</div>


						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<table id="tabmyModGrArr">

							<c:forEach items="${alarmGroups}" var="alarmGroups" varStatus="status">
								<tr>

									<td id="checkid" class="bg"><input type="radio" name="groups_id" value=${alarmGroups.getGroups_id()} > ${alarmGroups.getName() }</td>
								</tr>
							</c:forEach>
						</table>

						<div id="divmyModGrArr">
							<br />
							<h7 style="color:Red; text-align: center;">${error}</h7>
							<h7 style="color:Blue; text-align: center;">${messageSuccess}</h7>

						</div>
						<br />
						<button class="tablink31" type="button" onclick="arrivedGroup()">&#10004;&#65039; Готово</button>

					</form>


				</div>

			</div>


		</div>
	</div>
	<script>
		function arrivedGroup() {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//var nameFunc = $('#nameFunc').val();

			var group = $('input[name="groups_id"]:radio:checked').val();
			var idinfo = document.getElementById("currentIdInfo").innerHTML;

			$.ajax({
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				type : "POST",

				url : "objectsignal",
				data : {
					nameFunc : "myModalGroupArrived",
					idinfo : idinfo,
					groups_id : group
				},

				cache : false,
				success : function(html) {
					$("#tabmyModGrArr").html(
							$(html).find('#tabmyModGrArr').html());
					$("#divmyModGrArr").html(
							$(html).find('#divmyModGrArr').html());
				}
			});

		}
	</script>
</body>
</html>