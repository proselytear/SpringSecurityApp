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
				<h4 class="modal-title">Опросить состояние объекта</h4>
				<a href="#close" title="Close" class="close">×</a>
			</div>


			<div id="myModalStatusPoll" class="modal-body">

				<div>

					<form id="myModalStatusPoolform" method="post" action="home" items="${perifinfoBanIdinfo}" var="perifinfoBanIdinfo" method="post" action="/SpringSecurityApp/home">
						<br> <b class="card_middle_text">Объект</b> 
						<input id="accountStatusPool" id="paramnumStatusPool" type="text" class="card_middle_size" value="${perifinfoBanIdinfo.getMaineq().getAccountid()}" name="accountId"><br></br> 
						<b class="card_middle_text">Группа</b> 
						<input id="paramnumStatusPool" type="text" class="card_middle_size" value="${perifinfoBanIdinfo.getGrouprel()}" name="accountId"><br> <br> 
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
						<input type="button" onclick="setStatusPoll()" class="tablink31" name="nameFunc" value="Опрос состояния"> <span>${error}</span><br>
					</form>

					<div id="divStatusPool">
						<br />
						<h7 style="color:Red; text-align: center;">${error}</h7>
						<h7 style="color:Blue; text-align: center;">${messageSuccess}</h7>

					</div>

				</div>

			</div>

			<!-- Modal footer -->
			<div class="modal-footer"></div>

		</div>
	</div>

	<script>
		function setStatusPoll() {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//var nameFunc = $('#nameFunc').val();
			var accountStatusPool = $('#accountStatusPool').val();
			var paramnum = $('#paramnumStatusPool').val();
			$.ajax({
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				type : "POST",

				url : "home",
				data : {
					nameFunc : "myModalStatusPool",
					accountId : accountStatusPool,
					paramnum : paramnum
				},

				cache : false,
				success : function(html) {
					$("#divStatusPool").html(
							$(html).find('#divStatusPool').html());
				}
			});
		}
	</script>
</body>
</html>