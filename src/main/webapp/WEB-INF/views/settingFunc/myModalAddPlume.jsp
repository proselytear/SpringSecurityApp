<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>

</head>
<body>

		<div class="modal-dialog" id="myModalAddPlume">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Добавить/Изменить шлейф</h4>
					
					<a href="#openModalPC" type="button" class="close">×</a>
				</div>


				<div class="modal-body">

					<div>

						<form>
							<div class="fieldkard">
								<div class="leftfield"><label for="kardphone">Номер шлейфа </label></div>
								<div class="rightfield">
									<input  type="text"  id="paramnumPlumeSet" value="" /> 
								</div>
						    </div>
						    <div class="fieldkard">
								<div class="leftfield"><label for="kardphone">Описание шлейфа </label></div>
								<div class="rightfield">
									<input  type="text"  id="perifdataKardSet" value="" />
								</div>
						    </div>
								<br>	
						


								<b>Тревожная кнопка</b>
								
								<input type="radio" name="sensor_typeSet" value="1"> Да
								
								<input type="radio" name="sensor_typeSet" checked value="0"> Нет
								
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

								<div id="addPlumeSet">
									<span><b class="messageRed">${error}</b></span>
								</div>
								 <input type="button" onclick="addPlumeSet()" class="tablink31" name="nameFunc" value="Сохранить">

							
						</form>

						<div id="divAddPlume">
							
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
//Добавляем шлейф в БД
function addPlumeSet() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');

//Обрабатываем тревожную кнопку
	var sensor_typeSet = document.getElementsByName('sensor_typeSet');
	var sensor_typeSet_value;
	for (var i = 0; i < sensor_typeSet.length; i++) {
		if (sensor_typeSet[i].checked) {
			sensor_typeSet_value = sensor_typeSet[i].value;
			
		}
	}

	var plume = {
		nameFunc : "addPlumeSet",
		//1 level
		paramnumPlumeSet : $("#paramnumPlumeSet").val(),
		perifdataKardSet : $("#perifdataKardSet").val(),
		sensor_typeSet_value : sensor_typeSet_value,
		accountKardSet : $("#accountPCSetting").val(),
		panelcurrent : "PlumeSet",
		grouprel : 1//создание объекта всегда начинается с первой кнопи
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
		}
	});

}
</script>
</body>
</html>