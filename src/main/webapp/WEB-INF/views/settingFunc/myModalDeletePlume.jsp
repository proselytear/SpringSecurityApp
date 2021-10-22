<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>

</head>
<body>
	
		<div class="modal-dialog" id="myModalDeletePlume">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Удалить шлейф</h4>
					
					<a href="#openModalPC" type="button" class="close">×</a>
				</div>


				<div class="modal-body">

					<div>

						<form id="DeletePlume">
						
							<div class="fieldkard">
								<div class="leftfield"><label for="kardphone" >Номер шлейфа </label></div>
								<div class="rightfield">
									<input  type="text"  id="paramnumDeletPlumeSet" value="" />
								</div>
							</div>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> <br> 
								<span>${error}</span> 
								<input type="button" onclick="deletePlumeSet()" class="tablink31"
									name="nameFunc" value="Сохранить">
					
						</form>

						<div id="divDeletePlume">
							<br />
							<b class="messageRed cent">${error}</b>
							<b class="messageBlue cent">${messageSuccess}</b>

						</div>

					</div>

				</div>


				<!-- Modal footer -->
				<div class="modal-footer">					
					<a href="#openModalPC" type="button" title="Close" class="abutton">Закрыть</a>
				</div>

			</div>
		</div>
<script>
	//Удаляем шлейф из БД
	function deletePlumeSet() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var url = $(location).attr('pathname');
		//var nameFunc = $('#nameFunc').val();

		var plume = {
			nameFunc : "deletePlumeSet",
			//1 level
			paramnumPlumeSet : $("#paramnumDeletPlumeSet").val(),
			accountKardSet : $("#accountPCSetting").val(),
			panelcurrent : "PlumeSet",
			grouprel : 1
		}

		alert(JSON.stringify(plume));

		$.ajax({
			beforeSend : function(xhr) {
				// here it is
				xhr.setRequestHeader(header, token);
			},
			type : "POST",

			url : url,
			data : {
				json : JSON.stringify(plume)
			},

			cache : false,
			success : function(html) {
				$("#plumetabSet").html($(html).find('#plumetabSet'));
				$("#addPlumeSet").html($(html).find('#addPlumeSet'));
				$("#divDeletePlume").html($(html).find('#divDeletePlume'));
			}
		});

	}
</script>
</body>
</html>