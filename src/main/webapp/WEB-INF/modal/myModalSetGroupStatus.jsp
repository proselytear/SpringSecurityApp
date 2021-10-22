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
				<h4 class="modal-title">Выбрать новое состояние</h4>
				<a href="#close" title="Close" class="close">×</a>

			</div>


			<div class="modal-body">
				<form method="post" action="objectgroupsstatus" id="myModalSetGroupStatusForm">
					<div id="Kard2" items="${groupsStOne}" var="groupsStOne">
						<div class="fieldkard">
							<div class="leftfield">Название группы</div>
							<div class="rightfield">
								<input id="groupsetstatus" type="text" readonly="readonly" value="${groupsStOne.getName()}" />
							</div>
						</div>
						<input id="groupIdSetStatus" type="hidden" value="${groupsStOne.getGroups_id()}" />
						<div class="fieldkard">
							<div class="leftfield">Объект</div>
							<div class="rightfield">
								<input type="text" readonly="readonly" value="${groupsStOne.getObjectview().getAccountID()}" />
							</div>
						</div>
						<div class="fieldkard">
							<div class="leftfield">Адрес</div>
							<div class="rightfield">
								<input type="text" readonly="readonly" value="${groupsStOne.getObjectview().getFullname()}" />
							</div>
						</div>


					</div>

					<div class="messageRed">${groupsStOne.getMessageStatus()}</div>


					<br> <label id="cargo_weight"> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" > <input type="hidden" class="card_middle_size" name="nameFunc"
						value="myModalSetGroupStatus" /></label>

						 <table id="setgroup">
							<thead>
								<tr>
									<th>Состояние</th>
								</tr>
							</thead>
							<c:forEach items="${groupsStatusName}" var="groupsStatusName" varStatus="status">
								<tr>
									<td><input type="radio" name="id_groups_status_name" value=${groupsStatusName.getId_groups_status_name() }> 
									${groupsStatusName.getStatusName()} 
									${groupsStatusName.getInput()}</td>									
								</tr>
							</c:forEach>
						</table> 
						<!-- <b>Состояние</b>
						<c:forEach items="${groupsStatusName}" var="groupsStatusName" varStatus="status">
							<div id="setgroup" class="fieldkard">
							<div class="leftfield"><input type="radio" name="id_groups_status_name" value=${groupsStatusName.getId_groups_status_name() }> 
									${groupsStatusName.getStatusName()} 
									${groupsStatusName.getInput()}</div>
							
						</div>					
						</c:forEach> -->
						
						
						<button class="tablink31" type="button" onclick="setStatusGroup()">&#10004;&#65039;Готово</button>
				</form>

			</div>

			<!-- Modal footer -->
			<div id="divmyModSetGroupStatus" class="cent" >

				<b class="messageRed">${error}</b>
				<b class="messageBlue">${messageSuccess}</b>

			</div>

		</div>
	</div>

	<script>
		function setStatusGroup() {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//var nameFunc = $('#nameFunc').val();

			var id_groups_status_name = $('#setgroup input:radio:checked')
					.val();
			var accountId = "";
			if (id_groups_status_name == 4)
				accountId = $('#idAccountSetStGr').val();
			var groupIdSetStatus = $('#groupIdSetStatus').val();

			$.ajax({
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				type : "POST",

				url : "objectgroupsstatus",
				data : {
					nameFunc : "myModalSetGroupStatus",
					accountId : accountId,
					groups_id : groupIdSetStatus,
					id_groups_status_name : id_groups_status_name
				},

				cache : false,
				success : function(html) {
					$("#divmyModSetGroupStatus").html(
							$(html).find('#divmyModSetGroupStatus').html());

				}
			});
		}
	</script>
</body>
</html>