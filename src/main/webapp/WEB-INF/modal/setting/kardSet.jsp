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

	<div id="kardSet">
		<form id="kardSet2" action="objectsetting" method="post">
			<input type="button" onclick="cleanForm('kardSet2')" value="&#129529;">

			<div class="fieldkard">
				<div class="leftfield">Объект &#10004</div>
				<div class="rightfield">
					<input tabindex="1" name="account" id="accountKardSet" type="text" class="card" value="" />
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield">Менеджер</div>
				<div class="rightfield">
					<select tabindex="2" class="card" id="idManagerSet">
						<c:forEach var="listManager" items="${listManager}">
							<option value="${listManager.getId()}">${listManager.getFullName()}</option>
						</c:forEach>
					</select>
				</div>

			</div>

			<div class="fieldkard">
				<div class="leftfield">Название &#10004</div>
				<div class="rightfield">
					<input tabindex="3" type="text" class="card" id="nameOrganizationSet" name="name" value="${listCard.name}" />
				</div>
			</div>


			<div class="fieldkard">
				<div class="leftfield">Адрес &#10004</div>
				<div class="rightfield">
					<input tabindex="4" type="text" class="card" id="addressFullSet" />
				</div>
			</div>

			<div class="fieldkard">
				<div class="leftfield">Телефоны</div>
				<div class="rightfield">
					<input tabindex="5" type="text" class="card" id="phoneKardSet" value="${listCard.phone}" />
				</div>
			</div>


			<div class="fieldkard">
				<div class="leftfield">Директор</div>
				<div class="rightfield">
					<input tabindex="6" type="text" class="card" id="directorKardSet" value="${listCard.director}" />
				</div>
			</div>

			<div class="fieldkard">
				<div class="leftfield">Доп. инфо</div>
				<div class="rightfield">
					<textarea tabindex="7" type="text" class="card" maxlength="250" id="kardInfoSet" value=""></textarea>
				</div>
			</div>


			<div class="fieldkard">
				<div class="leftfield">Примечание</div>
				<div class="rightfield">
					<input tabindex="8" type="text" class="card" id="kardNoteSet" value="" />
				</div>
			</div>

			<div class="fieldkard">
				<div class="leftfield">Дополнительно</div>
				<div class="rightfield">
					<textarea tabindex="9" type="text" class="card" maxlength="250" id="kardAdditionallySet" value=""></textarea>
				</div>
			</div>


			<div class="fieldkard">
				<div class="leftfield">Замены</div>
				<div class="rightfield">
					<input tabindex="10" type="text" class="card" id="replacementSet" value="" />
				</div>
			</div>

			<div class="fieldkard">
				<div class="leftfield">Оборудование в аренде</div>
				<div class="rightfield">
					<textarea tabindex="11" type="text" class="card" maxlength="250" id="equipmentSet" value=""></textarea>
				</div>
			</div>
			<br>

			<div id="errorKardSet" class="messageRed">${error}</div>

			<div id="messageKardSet" class="messageBlue">${messageSuccess}</div>

			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> <input type="hidden" name="namePage" value="Kard" /> <input type="button" onclick="setStatusPoll1()" class="tablink31"
				name="nameFunc" value="Сохранить">

		</form>
	</div>
	<script>
		document.addEventListener("DOMContentLoaded", function(event) {
			document.getElementById("defaultbuttonKard").click();
		});
		$(function() {
			$('input,select')
					.on(
							'keypress',
							function(e) {
								e.which !== 13
										|| $('[tabIndex='
												+ (+this.tabIndex + 1) + ']')[0]
												.focus();
							});
		});

		function setStatusPoll1() {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			var url = $(location).attr('pathname');
			//var nameFunc = $('#nameFunc').val();
			var accountKardSet = $("#accountKardSet").val().trim();
			var nameOrganizationSet = $("#nameOrganizationSet").val().trim();
			var addressFullSet = $("#addressFullSet").val().trim();
			idManagerSet = $("#idManagerSet").val();
			if (idManagerSet == null)
				idManagerSet = 0;

			var kard = {
				nameFunc : "kardSet",
				accountKardSet : accountKardSet,
				idManagerSet : idManagerSet,
				nameOrganizationSet : nameOrganizationSet,

				phoneKardSet : $("#phoneKardSet").val(),
				directorKardSet : $("#directorKardSet").val(),
				kardInfoSet : $("#kardInfoSet").val(),
				kardNoteSet : $("#kardNoteSet").val(),
				kardAdditionallySet : $("#kardAdditionallySet").val(),
				replacementSet : $("#replacementSet").val(),
				equipmentSet : $("#equipmentSet").val(),
				addressFullSet : addressFullSet,
				addInfoAdress : $("#addInfoAdress").val()
			}

			//Проверяем наличае обязательных полей
			if (accountKardSet == '' || nameOrganizationSet == ''
					|| addressFullSet == '') {
				document.getElementById('errorKardSet').innerHTML = "Заполните все обязательные поля( Объект,  Название, Адрес)";//Сообщение пользователю
			} else {
				document.getElementById('errorKardSet').innerHTML = '';//При успешном проверки вводимых параметров очищаем поле для вывода сообщения об ошибках
				alert(JSON.stringify(kard));
				//cleanForm('kardSet2');

				$.ajax({
					beforeSend : function(xhr) {
						// here it is
						xhr.setRequestHeader(header, token);
					},
					type : "POST",

					url : url,
					data : {
						json : JSON.stringify(kard)
					},

					cache : false,
					success : function(html) {
						$("#divStatusPool")
								.html($(html).find('#divStatusPool'));
						$("#errorKardSet").html($(html).find('#errorKardSet'));
					}
				});
			}

		}
	</script>
</body>
</html>