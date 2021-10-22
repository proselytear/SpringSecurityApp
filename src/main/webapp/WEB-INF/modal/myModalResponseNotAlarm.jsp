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
				<h4 class="modal-title">Выслать группу на объект(без тревоги)</h4>
				<a href="#close" title="Close" class="close">×</a>
			</div>

			<div id="myModalResponseNotAlarm" class="modal-body">

				<form id="myModalResponseNotAlarmform" method="post" action="signal">

					<div id="Kard3" items="${objectviewId}" var="objectviewId">

						<b class="card_middle_text">Объект </b><input type="text" readonly="readonly" class="card_middle_size" name="accountId" id="accountIdModResponNotAlarm" value="${objectviewId.getAccountID()}" /> <br> <br> 
						<b class="card_middle_text">Группа </b><input type="text" readonly="readonly" class="card_middle_size" name="paramnum" value="${objectviewId.getParamNum()}" /> 
						<b class="card_middle_text"> </b><input type="hidden" class="card_middle_size" name="idinfo" id="idinfoModResponNotAlarm" value="${objectviewId.getIdinfo()}" /> 
						
					</div>
					<br> <b>Свободные группы:</b> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<table id="tabmyModalNotAlarmFree">

						<c:forEach items="${groupsFree}" var="groupsFree" varStatus="status">
							<tr>
								<td class="bg"><div class="group">
										<input type="radio" name="groups_id" value="${groupsFree.getGroups_id()}">
									</div></td>
								<td class="bg">${groupsFree.getName() }</td>
							</tr>
						</c:forEach>
					</table>
					<br> <b>Занятые группы:</b>

					<table id="tabmyModalNotAlarmBusy" bgcolor="red" >
						<c:forEach items="${groupsBusy}" var="groupsBusy" varStatus="status">
							<tr color=red>
								<td class="bg">${groupsBusy.getName() }</td>
							</tr>
						</c:forEach>
					</table>
					<br>
					<button class="tablink31" type="button" onclick="sendGroupNotAlarm()">Выслать группу</button>
				</form>


				<div id="divRespNotAlarmFree">

					<h7 id="errorespNotAlarmFree" style="color:Red; text-align: center;">${error}</h7>
					<h7 style="color:Blue; text-align: center;">${messageSuccess}</h7>

				</div>

			</div>
		</div>
	</div>

	<script>
		var group;
//Вызываем группу реагирования без тревоги
		function sendGroupNotAlarm() {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//var nameFunc = $('#nameFunc').val();

			var group = $('input[name="groups_id"]:radio:checked').val();
			

			idinfo = document.getElementById("idinfoModResponNotAlarm").value;
			
			if(idinfo==''){
						document.getElementById('errorespNotAlarmFree').innerHTML = "Выберите объект из таблицы";//Сообщение пользователю
			}else{
			
			$
					.ajax({
						beforeSend : function(xhr) {
							// here it is
							xhr.setRequestHeader(header, token);
						},
						type : "POST",

						url : "objectsignal",
						data : {
							nameFunc : "myModalResponseNotAlarm",
							idinfo_group : idinfo,
							id_groups_status_name: 5,//Статус объекта на объекте
							groups_id : group
						},

						cache : false,
						success : function(html) {
							$("#tabmyModalNotAlarmFree").html(
									$(html).find('#tabmyModalNotAlarmFree').html());
							$("#divRespNotAlarmFree").html(
									$(html).find('#divRespNotAlarmFree').html());
							$("#tabmyModalNotAlarmBusy").html(
									$(html).find('#tabmyModalNotAlarmBusy').html());

						}
					});
			}
		}

	</script>

</body>
</html>