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
				<h4 class="modal-title">Сгенерировать тревогу</h4>
				<a href="#close" title="Close" class="close">×</a>
			</div>


			<div id="divMyAlarm" class="modal-body">

				<form id="myModalTechnicAlarmform" onsubmit="return checkForm(this);" items="${perifinfoBanIdinfo}" var="perifinfoBanIdinfo" method="post" action="/SpringSecurityApp/objectsignal">

					<b> Объект</b> 
					<input type="text" class="card_middle_size" value="${perifinfoBanIdinfo.getMaineq().getAccountid()}" name="accountId" id="accountIdAlarm"><br> 
					<b>Группа</b> <input type="text" class="card_middle_size" value="${perifinfoBanIdinfo.getGrouprel()}" name="grouprel" id="grouprelAlarm"><br> 
					<input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}" />
					<input type="button" class="tablink31" name="nameFunc" value="Сгенерировать тревогу" onclick="addAlarm('createAlarm',  'myModalTechnicAlarmform')"> 
					<span>${error}</span><br>
				</form>
				<div id="modaltecnicspan" class="cent" >

					<b class="messageBlue">${messageSuccess}</b>

				</div>
			</div>



		</div>
	</div>

	<script>
		function checkFormAlarm(form) {
			// Заранее объявим необходимые переменные
			var el, // Сам элемент
			elName, // Имя элемента формы
			value, // Значение
			type; // Атрибут type для input-ов
			// Массив списка ошибок, по дефолту пустой
			var errorList = [];
			// Хэш с текстом ошибок (ключ - ID ошибки)
			var errorText = {
				1 : "Не заполнено поле 'группа'",

				2 : "Не заполнено поле 'объект'",
				3 : "Неправильно заполнено числовое поле 'группа'",

			}
			// Получаем семейство всех элементов формы
			// Проходимся по ним в цикле

			for (var i = 0; i < form.elements.length; i++) {
				el = form.elements[i];
				elName = el.nodeName.toLowerCase();
				value = el.value;
				if (elName == "input") { // INPUT
					// Определяем тип input-а
					type = el.type.toLowerCase();
					// Разбираем все инпуты по типам и обрабатываем содержимое
					switch (type) {
					case "text":

						if (el.name == "grouprel" && value == "")
							errorList.push(1);
						else if (el.name == "grouprel"
								&& !(/^[-]?\d+$/.test(value)))
							errorList.push(3);

						if (el.name == "accountId" && value == "")
							errorList.push(2);

						break;

					default:
						// Сюда попадают input-ы, которые не требуют обработки
						// type = hidden, submit, button, image
						break;
					}
				}
			}
			// Финальная стадия
			// Если массив ошибок пуст - возвращаем true
			if (!errorList.length)
				return true;
			// Если есть ошибки - формируем сообщение, выовдим alert
			// и возвращаем false
			var errorMsg = "При заполнении формы допущены следующие ошибки:\n";
			for (i = 0; i < errorList.length; i++) {
				errorMsg += errorText[errorList[i]] + "\n";
			}

			alert(errorMsg);
			return false;
		}

		function addAlarm(nameFunc, func) {

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//var nameFunc = $('#nameFunc').val();

			if (checkFormAlarm(document.getElementById(func))) {

				var accountIdstand = $('#accountIdAlarm').val();
				var grouprelstand = $('#grouprelAlarm').val();

				$.ajax({
					beforeSend : function(xhr) {
						// here it is
						xhr.setRequestHeader(header, token);
					},
					type : "POST",

					url : "home",
					data : {
						nameFunc : nameFunc,
						accountId : accountIdstand,
						grouprel : grouprelstand,

					},

					cache : false,
					success : function(html) {
						$("#modaltecnicspan").html(
								$(html).find('#modaltecnicspan').html());
					}
				});
			}
		}
	</script>
</body>
</html>