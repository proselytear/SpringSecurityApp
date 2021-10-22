<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Document</title>
</head>

<body>

	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">Взять/снять объект под охрану</h4>
				<a href="#close" title="Close" class="close">×</a>
			</div>

			<div id="myModalClose" class="modal-body">

				<form id="myModalCloseform" method="post" action="home" items="${perifinfoBanIdinfo}" var="perifinfoBanIdinfo" method="post" action="/SpringSecurityApp/home">

					<b class="card_middle_text">Объект</b> 
					<input id="accountCloseObject" type="text" class="card_middle_size" value="${perifinfoBanIdinfo.getMaineq().getAccountid()}" name="accountId"><br>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> <br>
					<p class="cent">Выберите группу на объекте</p>
					<table id="tabmyModClose" >

						<c:forEach items="${groupForAccount}" var="groupForAccount" varStatus="status">
							<tr>

								<td id="checkid" class="bg"><input type="radio" name="paramnum" value=${groupForAccount.getParamnum()} > ${groupForAccount.getPerifname() }</td>
							</tr>
						</c:forEach>
					</table>
					<div id="divClose">
						<br />
						<b class="messageRed">${error}</b>
						<b class="messageBlue">${messageSuccess}</b>
 
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
					<input type="button" onclick="closeObject('myModalClose')" class="tablink31" name="nameFunc" value="Взять объект под охрану"> 
					<input type="button" onclick="closeObject('myModalOpen')" class="tablink31" name="nameFunc" value="Cнять объект с охраны"> <br>
				</form>

			</div>
		</div>
	</div>
	<script>
		function closeObject(nameFunc) {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//var nameFunc = $('#nameFunc').val();
			var accountCloseObject = $('#accountCloseObject').val();
			var paramnum = $('#tabmyModClose input:radio:checked').val();

			$.ajax({
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				type : "POST",

				url : "home",
				data : {
					nameFunc : nameFunc,
					accountId : accountCloseObject,
					paramnum : paramnum
				},

				cache : false,
				success : function(html) {
					$("#divClose").html($(html).find('#divClose').html());

				}
			});
		}
	</script>

</body>
</html>