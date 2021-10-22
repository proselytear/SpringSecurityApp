<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resources/css/welcome.css" />"
	rel="stylesheet">

</head>
<body>

	<div id="openModal" class="modal">
		<jsp:include page="/WEB-INF/modal/myModal.jsp" />
	</div>
	<div id="openModalResponse" class="modal">
		<jsp:include page="/WEB-INF/modal/myModalResponse.jsp" />
	</div>
	<div id="openModalTechnicAlarm" class="modal">
		<jsp:include page="/WEB-INF/modal/myModalTechnicAlarm.jsp" />
	</div>
	<div id="openModalMap" class="modal">
		<jsp:include page="/WEB-INF/modal/myModalMap.jsp" />
	</div>
	<div id="openModalStatusPoll" class="modal">
		<jsp:include page="/WEB-INF/modal/comand/myModalStatusPoll.jsp" />
	</div>
	<div id="openModalClose" class="modal">
		<jsp:include page="/WEB-INF/modal/comand/myModalClose.jsp" />
	</div>
	<div id="openModalBypass" class="modal">
		<jsp:include page="/WEB-INF/modal/comand/myModalBypass.jsp" />
	</div>

	<div id="panelhome" class="tab">

		<form class="tablink0" method="post" action="home">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<button class="tablinks" id="buttonObject"
				onclick="openCity(event, 'London')">
				<img src="resources/images/button/object.png"
					style="vertical-align: middle"> Объекты
			</button>
		</form>





		<form class="tablink0" method="post" action="objectsignal">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<button class="tablinks" id="buttonSignal"
				onclick="openCity(event, 'Paris')">
				<img src="resources/images/button/alarm.png"
					style="vertical-align: middle"> Прием сигналов <span
					id="panelSignal" class="badge badge-pill badge-danger">${alarmfullSize}</span>
			</button>
		</form>
		<form class="tablink0" method="post" action="objectevent">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<button class="tablinks" id="buttonEvent"
				onclick="openCity(event, 'Paris')">
				<img src="resources/images/button/events1.png"
					style="vertical-align: middle"> События
			</button>
		</form>

		<form class="tablink0" method="post" action="objectgroupsstatus">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<button class="tablinks" id="buttonGroups"
				onclick="openCity(event, 'Paris')">
				<img src="resources/images/button/car3.png"
					style="vertical-align: middle"> Группы <br> реагирования
			</button>
		</form>
		<form class="tablink0" method="post" action="objectstand">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<button class="tablinks" id="buttonStand"
				onclick="openCity(event, 'Paris')">
				<img src="resources/images/button/stand.png"
					style="vertical-align: middle"> Стенды <span
					id="panelStandFull" class="badge badge-pill badge-info">${perifinfoBanFullSize}</span>
			</button>
		</form>
		<div class="dropdown">
			<button onclick="myFunction1()" class="dropbtn" aria-haspopup="true"
				aria-expanded="false">
				<img src="resources/images/button/done2.png"
					style="vertical-align: middle"> Выполнить &#9660;
			</button>


			<div id="myDropdown1" class="dropdown-content">
				<a href="#openModal" type="button"> <img
					src="resources/images/button/stand2.png"
					style="vertical-align: middle"> <b class="card_middle_text">
						Добавить/вывести в стенды</b></a>
				

				<a href="#openModalTechnicAlarm"> <img
					src="resources/images/button/alarm1.png"
					style="vertical-align: middle"> <b class="card_middle_text">
						Сгенерировать тревогу</b></a> <a href="#openModalMap"> <img
					src="resources/images/button/maps.png"
					style="vertical-align: middle"> <b class="card_middle_text">
						Показать объект на карте</b></a>
			</div>
		</div>
		<div class="dropdown">
			<button onclick="myFunction2()" class="dropbtn" aria-haspopup="true"
				aria-expanded="false">
				<img src="resources/images/button/command.png"
					style="vertical-align: middle"> Команды &#9660;
			</button>

			<div id="myDropdown2" class="dropdown-content">


				<a href="#openModalStatusPoll"><img
					src="resources/images/button/done1.png"
					style="vertical-align: middle"> <b class="card_middle_text">
						Опрос состояния</b></a> <a href="#openModalClose"><img
					src="resources/images/button/protection2.png"
					style="vertical-align: middle"> <b class="card_middle_text">
						Взятие/снятие под охрану</b></a> <a href="#openModalBypass"><img
					src="resources/images/button/detour1.png"
					style="vertical-align: middle"> <b class="card_middle_text">
						Обход зоны</b></a>

			</div>
		</div>

		<form id="buttonEvent" method="post" action="objectsetting">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<button class="tablinks" onclick="openCity(event, 'Paris')">
				<img src="resources/images/button/setting2.png"
					style="vertical-align: middle"><br> Инженер ПЦ
			</button>
		</form>


		<form class="tablink0" method="get" action="login">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<button class="tablinks" onclick="openCity(event, 'London')">
				<img src="resources/images/button/exit.png"
					style="vertical-align: middle"> Выход
			</button>
		</form>

		<div id="just-line-break">
			<p>
				<b> Оператор: </b> <input id="employeeFIO" type="text"
					class="tablink0" value="${employeeFIO}" readonly />
			</p>
		</div>

	</div>
	<script>
		function myFunction1() {

			document.getElementById("myDropdown1").classList.toggle("show");
		}

		function myFunction2() {

			document.getElementById("myDropdown2").classList.toggle("show");
		}

		// Close the dropdown menu if the user clicks outside of it
		window.onclick = function(event) {
			if (!event.target.matches('.dropbtn')) {
				var dropdowns = document
						.getElementsByClassName("dropdown-content");
				var i;
				for (i = 0; i < dropdowns.length; i++) {
					var openDropdown = dropdowns[i];
					if (openDropdown.classList.contains('show')) {
						openDropdown.classList.remove('show');

					}
				}
			}
		}
	</script>

	<script>
		function openCity(evt, cityName) {
			var i, tabcontent, tablinks;
			tabcontent = document.getElementsByClassName("tabcontent1");
			for (i = 0; i < tabcontent.length; i++) {
				tabcontent[i].style.display = "none";
			}
			tablinks = document.getElementsByClassName("tablinks");
			for (i = 0; i < tablinks.length; i++) {
				tablinks[i].className = tablinks[i].className.replace(
						" active", "");
			}
			document.getElementById(cityName).style.display = "block";
			evt.currentTarget.className += " active";
		}
	</script>


</body>
</html>