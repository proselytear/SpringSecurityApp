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
				<h4 class="modal-title">Обход зоны</h4>
				<a href="#close" title="Close" class="close">×</a>
			</div>


			<div id="myModalBypass" class="modal-body">

				<form id="myModalBypassform" method="post" action="home" items="${perifinfoBanIdinfo}" var="perifinfoBanIdinfo" method="post" action="/SpringSecurityApp/home">

					<div class="fieldkard ">
						<div class="leftfield small">Объект</div>
						<div class="rightfield">
							<input id="accountBypassObject" type="text" readonly value="${perifinfoBanIdinfo.getMaineq().getAccountid()}" name="accountId">
						</div>
					</div>


					<!-- <b class="card_middle_text">Объект</b> <input id="accountBypassObject" type="text" readonly class="card_middle_size"
								value="${perifinfoBanIdinfo.getMaineq().getAccountid()}"
								name="accountId">-->
					<br> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> <b>Выберите зону</b>

					<div id="tabmyModBypass" class="fieldkard">

						<c:forEach items="${zoneForAccount}" var="zoneForAccount" varStatus="status">


							<div id="checkid" class="group">
								<input type="radio" name="paramnum" value=${zoneForAccount.getParamnum()} > ${zoneForAccount.getPerifname() }
							</div>

						</c:forEach>
					</div>
					<!-- <table id="tabmyModBypass" >

								<c:forEach items="${zoneForAccount}" var="zoneForAccount"
									varStatus="status">
									<tr>


										<td><div id="checkid" class="group">
												<input type="radio" name="paramnum"
													value=${zoneForAccount.getParamnum()} >
											</div></td>
										<th>${zoneForAccount.getPerifname() }</th>
									</tr>
								</c:forEach>
							</table> -->
					<div id="divBypass">
						<br />
						<h7 style="color:Red; text-align: center;">${error}</h7>
						<h7 style="color:Blue; text-align: center;">${messageSuccess}</h7>

					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> <input type="button" onclick="bypassObject()" class="tablink31" name="nameFunc" value="Выполнить обход зон">
					<br>
				</form>

			</div>
		</div>
	</div>

	<script>
		function bypassObject() {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//var nameFunc = $('#nameFunc').val();
			var accountBypassObject = $('#accountBypassObject').val();
			var paramnum = $('#tabmyModBypass input:radio:checked').val();

			$.ajax({
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				type : "POST",

				url : "home",
				data : {
					nameFunc : "myModalBypass",
					accountId : accountBypassObject,
					paramnum : paramnum
				},

				cache : false,
				success : function(html) {
					$("#divBypass").html($(html).find('#divBypass').html());

				}
			});
		}
	</script>
</body>
</html>