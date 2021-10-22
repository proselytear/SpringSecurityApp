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
	<div id="responspeopSet">

		<form id="ResponcePeopSet" action="objectsetting" method="post"
			onsubmit="return false">
			
		<div class="fieldkard" id="streetSet">
			<div class="leftfield big220"><label for="kardaddress">Выберите ответственное лицо</label></div>
			<div class="rightfield">
				<input list="browsers" id="inputResponcePeop" placeholder="Нажмите Enter после ввода" >
			</div>
		</div>
			
			

				<datalist id="browsers">
				 <c:forEach	var="perifinfoResponsible" items="${perifinfoResponsible}">
					<option name="nameResponcePeop"	id="${perifinfoResponsible.getPerifinfoResponsible_id()}"
						value="${perifinfoResponsible.getFullName()}">${perifinfoResponsible.getAddress()}</option>
				</c:forEach> </datalist>
				
			<a href="#openModalAddPlumeSet" id="modShow" type="button" class="abutton" name="nameFunc">Добавить</a>
			<table id="tablRespSet">
				<tr>
					<th></th>
					<th>Фамилия</th>
					<th>Имя</th>
					<th>Отчество</th>
					<th>Адрес проживания</th>
					<th>&#9742; Телефон</th>
					<th>Примечание</th>
					<th></th>
				</tr>

				<c:forEach items="${perifinfoResponsibleList}"
					var="perifinfoResponsibleList" varStatus="status">
					<tr>
						<td id="addPersId">${perifinfoResponsibleList.getPerifinfoResponsible_id() }</td>
						<td id="addPersLast">${perifinfoResponsibleList.getLastName() }</td>
						<td id="addPersFirst">${perifinfoResponsibleList.getFirstName() }</td>
						<td id="addPersPatronymic">${perifinfoResponsibleList.getPatronymic() }</td>
						<td id="addPersAddress">${perifinfoResponsibleList.getAddress() }</td>
						<td id="addPersgetPhone">${perifinfoResponsibleList.getPhone() }</td>
						<td id="addPersNote">${perifinfoResponsibleList.getNote() }</td>

					</tr>
				</c:forEach>
			</table>
			
			<div id="errorRespPeopSet" class="messageRed cent">${error}</div>
			
			
			<div id="messageRespPeopSet" class="messageBlue cent">${messageSuccess}</div>
			

		</form>



	</div>


<script>

	window.onload = function() {
		document.getElementById("inputResponcePeop").value = "";
		var Table = document.getElementById("t_tableRespReopSet");
		Table.innerHTML = '';
	};
	//Пишем выбранные ответственные лица в БД
	function addResponsPeop(responsePeop) {
		

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var url = $(location).attr('pathname');
		var accountKardSet = $("#accountKardSet").val().trim();
		//addGroppsSet() ;
		if (responsePeop > 0) {

			var kard = {
				nameFunc : "addListResponsPeop",
				responsePeop : responsePeop,
				accountKardSet : accountKardSet,
				panelcurrent : "ResponsPeopSet"
			}
			if (accountKardSet == '') {
				document.getElementById('errorRespPeopSet').innerHTML = "Заполните обязательное поле объект во вкладке Карточка";
				document.getElementById('messageRespPeopSet').innerHTML = '';
			} else {

				$.ajax({
					beforeSend : function(xhr) {
						// here it is
						xhr.setRequestHeader(header, token);
					},
					type : "POST",

					url : "objectsetting",
					data : {
						json : JSON.stringify(kard),
						panelcurrent : "ResponsPeopSet"
					},

					cache : false,
					success : function(html) {
						$("#tablRespSet").html($(html).find('#tablRespSet'));
						$("#errorRespPeopSet").html(
								$(html).find('#errorRespPeopSet'));
						$("#messageRespPeopSet").html(
								$(html).find('#messageRespPeopSet'));

						selectResponsPeop();
					}
				});
			}

		}

	}
	//Добавляем данные введенные пользователем в БД справичника
	function createNewPesponPeop() {
	

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var url = $(location).attr('pathname');
		var accountKardSet = $("#accountKardSet").val().trim();
		var responsPeop = {
			nameFunc : "createNewPesponPeop",
			responsPeopLast : $("#responsPeopLast").val(),
			responsPeopFirst : $("#responsPeopFirst").val(),
			responsPeopPatronom : $("#responsPeopPatronom").val(),
			responsPeopAddress : $("#responsPeopAddress").val(),
			responsPeopPhone : $("#responsPeopPhone").val(),
			responsPeopNote : $("#responsPeopNote").val(),
			panelcurrent : "ResponsPeopSet",
			accountKardSet : accountKardSet
		};

		$.ajax({
			beforeSend : function(xhr) {
				// here it is
				xhr.setRequestHeader(header, token);
			},
			type : "POST",

			url : url,
			data : {
				json : JSON.stringify(responsPeop),
				panelcurrent : "ResponsPeopSet"

			},

			cache : false,
			success : function(html) {
				$("#tablRespSet").html($(html).find('#tablRespSet'));
				$("#browsers").html($(html).find('#browsers'));
				$("#idPers").html($(html).find('#idPers'));//Обновляем ид справочника полученное при записи в БД
				$("#errorAddDictionarySet").html(
						$(html).find('#errorAddDictionarySet'));//Обновляем описание ошибки в модальном окне
				$("#messageAddDictionarySet").html(
						$(html).find('#messageAddDictionarySet'));//Обновляем описание сообщения в модальном окне

			}
		});

	}

	$(document)
			.ready(
					function() {
						$('input')
								.keydown(
										function(e) {
											if (e.keyCode === 13) {//активизируется после Enter
									
												var namePanel = document
														.getElementById("panelcurrentSet").innerHTML;//название текущей вкладки на панели
														
												if (namePanel == 'ResponsPeopSet') {
													
													//очищаем все input
													document
															.getElementById('errorRespPeopSet').innerHTML = '';
													document
															.getElementById('messageRespPeopSet').innerHTML = '';
													document
													.getElementById('idPers').value = '';
													document
													.getElementById('responsPeopAddress').value = '';
													document
													.getElementById('responsPeopPhone').value = '';
													document
													.getElementById('responsPeopNote').value = '';
									
													//Вызавается блок когда пользователь нажимает  Enter после ввода ответственного лица в input
													//Парсим ФИО введенного пользователя. Если его нет в словаре то нужно добавить в справичник. 
													var fio = document
															.getElementById("inputResponcePeop").value;
													alert("fio="+fio);
													const fioArr = fio
															.split(" ");
													
													var firstName = lastName = patronymic = "";
													//Нахождение

													for (i = 0; i < fioArr.length; i++) {

														if (i == 0)
															lastName = fioArr[i];
														if (i == 1) {
															firstName = fioArr[i];
														}
														if (i == 2)
															patronymic = fioArr[i];

													}
													//Переписываем введенное значение пользователем в модальное окно добавить
													document
															.getElementById("responsPeopLast").value = lastName;
													document
															.getElementById("responsPeopFirst").value = firstName;
													document
															.getElementById("responsPeopPatronom").value = patronymic;
													
													//Выводим подсказку пользователю
													document
															.getElementById("messageRespPeopSet").innerHTML = 'Воспользуйтесь кнопкой Добавить чтобы вставить новое ответственное лицо';

												}
											}
										});
					});

	$(function() {
		$('#inputResponcePeop').change(
				//Вызывается если пользователь выбрал отверственное лицо из списка
				function() {

					var inputResponcePeop = $(
							"#browsers option[value='"
									+ $('#inputResponcePeop').val() + "']")
							.attr('id');

					var responsePeop = {
						nameFunc : "addListResponsPeop",
						inputResponcePeop : inputResponcePeop
					}

					if (typeof inputResponcePeop === 'undefined') {
						inputResponcePeop = -1;

						//selectResponsPeopNew();

					}
					addResponsPeop(inputResponcePeop);

				});

	});
</script>
<script>
	//Обновляем страницу ответственные лица в функции добавить объект
	function reloadResponsPeopSet() {

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var url = $(location).attr('pathname');
		var accountKardSet = $("#accountKardSet").val().trim();
		var json = {

			accountKardSet : accountKardSet,
			nameFunc : ""
		};

		$.ajax({
			beforeSend : function(xhr) {
				// here it is
				xhr.setRequestHeader(header, token);
			},
			type : "POST",

			url : url,
			data : {
				json : JSON.stringify(json),
				panelcurrent : "ResponsPeopSet"
			},

			cache : false,
			success : function(html) {
				$("#tablRespSet").html($(html).find('#tablRespSet'));

			}
		});
	}
</script>
</body>
</html>
