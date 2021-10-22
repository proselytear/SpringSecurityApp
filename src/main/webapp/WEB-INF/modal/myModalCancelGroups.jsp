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

	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h6 class="modal-title">Отмена прибытия групп реагирования на объект</h6>
				<a href="#close" title="Close" class="close">×</a>
			</div>


			<div id="myModalCancelGroups" class="modal-body">

				<div>


					<form id="myModalGroupCancelform" method="post" action="objectsignal">
						<div id="divcancel" items="${objectviewId}" var="objectviewId">

							<b class="card_middle_text">Объект </b><input type="text" readonly="readonly" class="card_middle_size" value="${objectviewId.getAccountID()}" /> <br> <br> 
							<b class="card_middle_text">Адрес </b><input type="text" readonly="readonly" class="card_middle_big" value="${objectviewId.getFullname()}" /> <br> <br>
							<b class="card_middle_text"> </b><input type="hidden" class="card_middle_size" name="idinfo" value="${objectviewId.getIdinfo()}" id="idinfoModCancel" />
							<input type="hidden" class="card_middle_size" name="nameFunc" value="myModalCancelGroups" />
						</div>

						<b>Выберите группу </b> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<table id="tabmyModGrCancel" >

							<c:forEach items="${alarmGroupsForCancel}" var="alarmGroupsForCancel" varStatus="status">
								<tr>
									<td class="bg"><input type="radio" name="groups_id" value="${alarmGroupsForCancel.getGroups_id()}"> ${alarmGroupsForCancel.getName() }</td>
								</tr>
							</c:forEach>
						</table>
					</form>
					<div id="divmyModGrCancel">
						<br />
						<h7 style="color:Red; text-align: center;">${error}</h7>
						<h7 style="color:Blue; text-align: center;">${messageSuccess}</h7>

					</div>
					<br />

					<button class="tablink31" type="button" onclick="cancelGroup()">&#10004;&#65039; Готово</button>



				</div>

			</div>


		</div>
	</div>
	<script>
		function cancelGroup() {

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//var nameFunc = $('#nameFunc').val();

			var group = $('#tabmyModGrCancel input:radio:checked').val();
			idinfo = document.getElementById("currentIdInfo").innerHTML;

			$.ajax({
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				type : "POST",

				url : "objectsignal",
				data : {
					nameFunc : "myModalCancelGroups",
					idinfo : idinfo,
					groups_id : group
				},

				cache : false,
				success : function(html) {

					$("#divmyModGrCancel").html(
							$(html).find('#divmyModGrCancel').html());
					$("#tabmyModGrCancel").html(
							$(html).find('#tabmyModGrCancel').html());
				}
			});

		}
	</script>
</body>
</html>