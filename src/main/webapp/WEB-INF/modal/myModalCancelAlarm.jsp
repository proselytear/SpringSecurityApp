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
				<h6 class="modal-title">Выбор причины тревоги</h6>
				<a href="#close" title="Close" class="close">×</a>
			</div>


			<div id="myModalCancelAlarm" class="modal-body">

				<div>

					<b>Выберите категорию причины тревоги</b> <br>
					<br>

					<form id="myModalAlarmCancelform" method="post" action="objectsignal">
						<div id="divAlarmcancel" items="${objectviewId}" var="objectviewId">

							<b class="card_middle_text">Объект </b><input type="text" readonly="readonly" class="card_middle_size" value="${objectviewId.getAccountID()}" /> <br>
							<br> <b class="card_middle_text">Адрес </b><input type="text" readonly="readonly" class="card_middle_big" value="${objectviewId.getFullname()}" /> <br> 
							<b class="card_middle_text"></b><input type="hidden" class="card_middle_size" name="idinfo" id="idinfoModCancelAlarm" value="${objectviewId.getIdinfo()}" />
							<input type="hidden" class="card_middle_size" name="nameFunc" value="myModalCancelAlarm" />
						</div>

						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> <br>

						<table id="tabmyModAlarmCancel" class="mtab">

							<c:forEach items="${alarmcancelname}" var="alarmcancelname" varStatus="status">
								<tr>
									<td class="bg"><input type="radio" name="id_alarm_cancel" value="${alarmcancelname.getId_alarm_cancel()}"> ${alarmcancelname.getNameAlarmCancel() }</td>
								</tr>
							</c:forEach>
						</table>

						<div id="divmyModAlarmCancel">
							<br />
							<h6 style="color: Red; text-align: center;">${error}</h6>
							<h6 style="color: Blue; text-align: center;">${messageSuccess}</h6>

						</div>

						<button class="tablink31" type="button" onclick="cancelAlarm()">&#10004;&#65039; Готово</button>



					</form>


				</div>

			</div>


		</div>
	</div>
	<script>
	//Завершение обработки(отмена тревоги)
		function cancelAlarm() {

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//var nameFunc = $('#nameFunc').val();

			var id_alarm_cancel = $('#tabmyModAlarmCancel input:radio:checked')
					.val();//получаем ид причины отмены тревоги выбранной пользователем
			idinfo = document.getElementById("currentIdInfo").innerHTML;//выбираем ид номера объекта группы из переменной записанной на стринице 

			$.ajax({
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				type : "POST",

				url : "objectsignal",
				data : {
					nameFunc : "myModalCancelAlarm",
					idinfo : idinfo,
					id_alarm_cancel : id_alarm_cancel
				},

				cache : false,
				success : function(html) {

					$("#divmyModAlarmCancel").html(
							$(html).find('#divmyModAlarmCancel'));
					$("#tabmyModAlarmCancel").html(
							$(html).find('#tabmyModAlarmCancel'));

				}
			});
			document.getElementById("currentIdInfo").innerHTML = null;//Очищаем значение выбранной строки сохраненной на странице так как после завершения тревоги уйдет объект с таблицы

		}
	</script>
</body>
</html>