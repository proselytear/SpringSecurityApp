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
	<div id="kardPCSet">

		<form id="kardPCSet2" action="objectsetting" method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<div class="fieldkard">
				<div class="leftfield">Объект &#10004</div>
				<div class="rightfield">
					<input tabindex="1"	name="account" id="accountPCSetting" type="text" class="card_middle_size" value="" />
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield">Номер группы &#10004</div>
				<div class="rightfield">
					<input tabindex="2" name="account" id="numGroupPCSetting" type="text" class="card_middle_size" value="" />
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield">Название группы</div>
				<div class="rightfield">
					<input tabindex="2" name="account" id="perifdataPCSetting" type="text" class="card_middle_size" value="" />
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield">SIM1</div>
				<div class="rightfield">
					<input tabindex="2" name="account" id="sim1PCSetting" type="text" class="card_middle_size" value="" />					
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield">SIM2</div>
				<div class="rightfield">					
					<input tabindex="3" name="account" id="sim2PCSetting" type="text" class="card_middle_size" value="" />
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield">Координаты</div>
				<div class="rightfield">
					<input tabindex="4" name="account" id="coordinatesPCSetting" type="text" class="card_middle_size" value="" />
					
				</div>
			</div>
		</form>
	</div>
	<script>
	function createMaineq() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var url = $(location).attr('pathname');
		//var nameFunc = $('#nameFunc').val();

		var maineq = {
			nameFunc : "createMaineq",
			accountPCSetting : $("#accountPCSetting").val(),
			sim1PCSetting : $("#sim1PCSetting").val(),
			sim2PCSetting : $("#sim2PCSetting").val(),
			coordinatesPCSetting : $("#coordinatesPCSetting").val(),
			numGroupPCSetting : $("#numGroupPCSetting").val(),
			perifdataPCSetting : $("#perifdataPCSetting").val()
			

		}
		alert(JSON.stringify(maineq));

		$.ajax({
			beforeSend : function(xhr) {
				// here it is
				xhr.setRequestHeader(header, token);
			},
			type : "POST",

			url : url,
			data : {
				json : JSON.stringify(maineq)
			},

			cache : false,
			success : function(html) {
				$("#errorPCSetting").html($(html).find('#errorPCSetting'));
				
				
				
			}
		});

	}


</script>
</body>
</html>