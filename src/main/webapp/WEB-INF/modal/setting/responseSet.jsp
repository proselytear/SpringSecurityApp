<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Search Result</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>

<body>
	<div id="responseSet">
		<div>

			<table class="table_card">
				<c:forEach items="${groups}" var="groups" varStatus="status">
					<tr>
						<th>${groups.getName() }</th>
					</tr>
				</c:forEach>
			</table>

		</div>
		<form id="GroupsAddSet" action="objectsetting" method="post">
			<b class="card_middle_text">Выберите группу</b>
			 <select tabindex="2" class="card_middle_size" id="idGroupSet">
				<c:forEach var="allgroups" items="${allgroups}">
					<option name="nameGroup" value="${allgroups.getGroups_id()}">${allgroups.getName()}</option>
				</c:forEach>
			</select> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			 <input type="hidden" /> 
			 <input type="button" onclick="add()" class="tablink31" name="nameFunc" value="Добавить">
			<input type="hidden" name="namePage" value="GroupsSet" />
			<table id="tabl" border='1' align='left'>

				<tr>



				</tr>

			</table>

		</form>
		<input type="button" onclick="addGroppsSet()" class="tablink31" name="nameFunc" value="Сохранить">

	</div>
	<script>
		function addGroppsSet() {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//var nameFunc = $('#nameFunc').val();

			var array = [];

			$('#tabl tr').each(function() {

				var array_row = [];
				$(this).find('td:first').each(function() {

					array_row.push($(this).find("input[class='item']").val());
				});
				array.push(array_row);
			});

			var group = {
				nameFunc : "addGroupSet",
				//1 level
				accountKardSet : $("#accountKardSet").val(),
				//current level  
				idGroupSet : array

			}

			//alert(JSON.stringify(group));

			$.ajax({
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				type : "POST",

				url : url,
				data : {
					json : JSON.stringify(group)
				},

				cache : false,
				success : function(html) {
					$("#responseSet").html($(html).find('#responseSet'));
				}
			});

		}
	</script>
	<script>
		function addGroupsTempSet() {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//var nameFunc = $('#nameFunc').val();
			alert("addGropsTempSet");

			var group = {
				nameFunc : "addGroupsTempSet",
				//1 level
				accountKardSet : $("#accountKardSet").val(),

				//current level  
				idGroupSet : $("#idGroupSet").val(),

			}

			$.ajax({
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				type : "POST",

				url : url,
				data : {
					json : JSON.stringify(group)
				},

				cache : false,
				success : function(html) {
					$("#GroupsAddSet").html($(html).find('#GroupsAddSet'));
				}
			});

		}

		function add() {
			var groupId = $("#idGroupSet").val();
			taxes = $('option[name=nameGroup]').val();

			var e = document.getElementById("idGroupSet");
			var strUser = e.options[e.selectedIndex].text;

			var nodet = document.createElement('tr');

			var node = document.createElement('td');

			node.innerHTML = "<td><input type=\"hidden\" name=\"1\" class=\"item\"\n" + "	 id='" + groupId +"'	 value='" + groupId + "'>"
					+ strUser

					+ "<td><input type=\"button\" value=\"&#10060\" onclick=\"deleteRow(this)\"></td>"

			document.getElementById('tabl').appendChild(nodet);

			nodet.appendChild(node);

		}

		function deleteRow(r) {
			var i = r.parentNode.parentNode.rowIndex;
			document.getElementById("tabl").deleteRow(i);
		}
	</script>
</body>
</html>