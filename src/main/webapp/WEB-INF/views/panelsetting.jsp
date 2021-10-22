<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Document</title>
</head>

<body>

	<div id="openModalObject" class="modal">
		<jsp:include page="/WEB-INF/modal/setting/myModalObject.jsp" />
	</div>
	<div id="openModalPC" class="modal">
		<jsp:include page="/WEB-INF/modal/setting/myModalPC.jsp" />
	</div>
	<div id="settinghome" class="tab">
		<form class="tablink0" method="post" action="home">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button class="tablinks" id="buttonObject" onclick="openCity(event, 'London')">
				<img src="resources/images/button/setting.png" style="vertical-align: middle"> Оператор ПЦ
			</button>
		</form>

		<div class="dropdown">
			<button onclick="myFunction()" class="dropbtn">
				<img src="resources/images/button/done2.png" style="vertical-align: middle">Объект
			</button>

			<div id="myDropdown" class="dropdown-content">
			
				<a href="#openModalObject" type="button">
				<img src="resources/images/button/plus.png" style="vertical-align: middle"> 
				<b class="card_middle_text">Добавить объект</b></a>
				<a href="#openModalPC" type="button">
				<img src="resources/images/button/setting3.png" style="vertical-align: middle"> 
				<b class="card_middle_text">Настройки ППК</b></a>

			</div>
		</div>
		<form method="get" action="login">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button class="tablinks" onclick="openCity(event, 'London')">
				<img src="resources/images/button/exit.png" style="vertical-align: middle">Выход
			</button>
		</form>



	</div>
	<script>
		function myFunction() {

			document.getElementById("myDropdown").classList.toggle("show");
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

</body>
</html>