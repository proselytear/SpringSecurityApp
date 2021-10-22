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
<script src="resources/js/panel.js" type="text/javascript"></script>
<script src="resources/js/musicAlarm.js" type="text/javascript"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
</head>
<body>

	<p id="currentIdGroups"></p>
	<div style="display: none" id='MusicGro'>${alarmfullSize}</div>

	<div id="Groupsstatus" class="tabcontent2">
		<form id="formgroup" method="post" action="objectgroupsstatus">
			<a class="tablink31" href="" data-toggle="modal" data-target="#myModalSetGroupStatus" type="submit">Перевести в новое состояние</a> <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<div class="scroll-table-body0">
			<table class="table_big" id="groupsobject">

				<tr>

					<th></th>
					<th>Описание группы</th>
					<th>Статус</th>
					<th>Объект</th>
				</tr>
				<tbody id="t_group">
					<c:forEach items="${groupsStatus}" var="groupsStatus" varStatus="status">

						<p>${groupsStatus.getTab()}</p>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
	<script>

	var gblIndex = 0; //this will save row clicked index

	function setFocus(){ 
	$($('#t_group tbody > tr')[gblIndex]).addClass('selected'); 


	}


	$(document).ready(function() 
	{
	  // set datatable
	  $('#table_big').dataTable({   
		  "stateSave": true,
		  "stateSaveCallback": function (settings, data) {

		    // Send an Ajax request to the server with the state object
				$.ajax({
					beforeSend : function(xhr) {
						// here it is
						xhr.setRequestHeader(header, token);
					},
					type : "POST",
					data : {

						groups_id : gblIndex,
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
		} );

	  // reload datatable every 30 seconds
	  setInterval(function()
	  {

	    var table = $('#table_big').dataTable();


	    table.ajax.reload();


	    setFocus(); // this will set focus/highlight row

	  }, 10000);

	  // highlight row
	 $(document).on('click', '#t_group>.rowlink', function() {

	   $(this).addClass('selected').siblings().removeClass('selected');
	    $(this).addClass('selected');
	    gblIndex = $(this).find('td:first input').val(); // this will save the index clicked

	  });
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