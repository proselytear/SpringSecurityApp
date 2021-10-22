<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>
</head>

<body>
	<div class="container2" id="panelcardRight">
		<div class="rp3l" id="panelcardRightOne">
			<button id="defaultbutton" class="tablink3"
				onclick="openPage3('Kard','defaultbutton', 'panelcardRightOne')">
				<img src="resources/images/button/kard.png"
					style="vertical-align: middle"> Карточка
			</button>
			<button id="maineqPanelCard" class="tablink3"
				onclick="openPage3('Maineq',  'maineqPanelCard', 'panelcardRightOne')">
				<img src="resources/images/button/bsd_air.png"
					style="vertical-align: middle"> Оборудование
			</button>
			<button id="timetablePanelCard" class="tablink3"
				onclick="openPage3('Timetable', 'timetablePanelCard', 'panelcardRightOne')">
				<img src="resources/images/button/schedule.png"
					style="vertical-align: middle"> Особ. работы
			</button>

			<button id="responsePanelCard" class="tablink3"
				onclick="openPage3('Response','responsePanelCard', 'panelcardRightOne')">
				<img src="resources/images/button/car3.png"
					style="vertical-align: middle"> Реагирования
			</button>
		</div>
		<div class="rp3l" id="panelcardRightTwo">
			<button id="plumePanelCard" class="tablink3"
				onclick="openPage3('Plume', 'plumePanelCard', 'panelcardRightTwo')">
				<img src="resources/images/button/plume.png"
					style="vertical-align: middle"> Шлейфы
			</button>
			<button id="keyPanelCard" class="tablink3"
				onclick="openPage3('Key','keyPanelCard', 'panelcardRightTwo')">
				<img src="resources/images/button/keys.png"
					style="vertical-align: middle"> Ключи
			</button>

			<button id="responspeopPanelCard" class="tablink3"
				onclick="openPage3('ResponsPeop',  'responspeopPanelCard', 'panelcardRightTwo')">
				<img src="resources/images/button/people3.png"
					style="vertical-align: middle"> Отв. лица
			</button>
			<button id="statePanelCard" class="tablink3"
				onclick="openPage3('State','statePanelCard', 'panelcardRightTwo')">
				<img src="resources/images/button/state.png"
					style="vertical-align: middle"> Состояние
			</button>
		</div>
		<div class="rp3l" id="panelcardRightThree">
			<button id="eventsPanelCard" class="tablink3"
				onclick="openPage3('Events',  'eventsPanelCard', 'panelcardRightThree')">
				<img src="resources/images/button/events1.png"
					style="vertical-align: middle"> События
			</button>
			<button id="schemaPanelCard" class="tablink3"
				onclick="openPage3('Schema',  'schemaPanelCard', 'panelcardRightThree')">
				<img src="resources/images/button/schema2.png"
					style="vertical-align: middle"> Схема
			</button>
			<button id="photoPanelCard" class="tablink3"
				onclick="openPage3('Photo',  'photoPanelCard', 'panelcardRightThree')">
				<img src="resources/images/button/foto1.png"
					style="vertical-align: middle"> Фото
			</button>
			<button id="standPanelCard" class="tablink3"
				onclick="openPage3('Stand', 'standPanelCard', 'panelcardRightThree')">
				<img src="resources/images/button/stand.png"
					style="vertical-align: middle"> Стенды <span id="panelStand"
					class="badge badge-pill badge-info">${perifinfoSensorSizeFull}</span>
			</button>
		</div>
	</div>
	<div style='display: none'id="panelcurrent"></div>


	<script>
		document.addEventListener("DOMContentLoaded", function(event) {
			document.getElementById("defaultbutton").click();
		});
	</script>
</body>
</html>