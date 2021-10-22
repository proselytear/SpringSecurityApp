<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<title>Search Result</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css">
<script src="resources/js/panel.js" type="text/javascript"></script>
<script src="resources/js/musicAlarm.js" type="text/javascript"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>

</head>
<body onload="clickMeth('buttonGroups')">

	<div id="openModalSetGroupStatus" class="modal">
		<jsp:include page="/WEB-INF/modal/myModalSetGroupStatus.jsp" />
	</div>
<div class="nav1">
	<p id="currentIdGroups"></p>
	</div>
	<div style="display: none" id='MusicGro'>${alarmfullSize}</div>

	<div id="Groupsstatus" class="tabcontent2">

		<form id="formgroup" method="post" action="objectgroupsstatus">
			<a href="#openModalSetGroupStatus" type="submit" class="abutton">Перевести в новое состояние</a> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<div class="scroll-table-body0">
			<table id="groupsobject" class="table_big">
				<thead>
					<tr>

						<th style='display: none'></th>
						<th>Описание группы</th>
						<th>Статус</th>
						<th>Объект</th>
					</tr>
				</thead>
				<tbody id="t_group">
					<c:forEach items="${groupsStatus}" var="groupsStatus" varStatus="status">

						<p>${groupsStatus.getTab()}</p>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>

	<script>
	$(document).on('click', '#t_group>.rowlink', function() {
		$(this).addClass('selected').siblings().removeClass('selected');

		var value = $(this).find('td:first input').val();
		document.getElementById("currentIdGroups").innerHTML = value;

		var rows = window.document.querySelectorAll("table tbody tr");

		for (var i = 0; i < rows.length; i++) { // перебираем все строки

			var cols = rows[i].querySelectorAll('td'); // получаем столбцы

			for (var j = 0; j < cols.length; j++) { // перебираем все столбцы

				cols[j].parentNode.style.backgroundColor = null;

			}
		}

		for (var i = 0; i < rows.length; i++) { // перебираем все строки

			var cols = rows[i].querySelectorAll('td'); // получаем столбцы

			for (var j = 0; j < cols.length; j++) { // перебираем все столбцы
				if (cols[j].textContent == value) {

					cols[j].parentNode.style.backgroundColor = "#FFFF00";

				}// выводим текст из столбца

			}
		}
		
		var idinfo = document.getElementById("currentIdGroups").innerHTML;
		myclickgroups(idinfo);

	});
</script>

	<script>
	function reloadGroups(groups_id) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var url = $(location).attr('pathname');
		$.ajax({
			beforeSend : function(xhr) {
				// here it is
				xhr.setRequestHeader(header, token);
			},
			type : "POST",
			data : {

				groups_id : groups_id,
				myclick : 0
			},

			url : "objectgroupsstatus",
			success : function(html) {

				$("#groupsobject").html($(html).find('#groupsobject').html());
				$("#MusicGro").html($(html).find('#MusicGro').html());
				$("#panelSignal").html($(html).find('#panelSignal').html());
				$("#panelStandFull").html($(html).find('#panelStandFull').html());
			}
		});
	}

	$(document).ready(function() {

		var timers = setTimeout(function ticks() {

			var groups_id = document.getElementById("currentIdGroups").innerHTML;
			reloadGroups(groups_id);
			window.onbeforeunload = function() {
				return null;
			};
			timers = setTimeout(ticks, 500);

		}, 1);

	});
</script>
	<script>
	$(document)
			.ready(
					function() {
						
						Cheshka('MusicGro');
						setInterval("Cheshka('MusicGro')", 1000);

					});
</script>



</body>

</html>