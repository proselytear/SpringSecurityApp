<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Search Result</title>
<script src="resources/js/cleanForm.js" type="text/javascript"></script>
</head>

<body>
	<button id="defaultbuttonSet" class="tablink3" onclick="openPageSet('KardSet', this, '#DCEEF8')">
		<img src="resources/images/button/kard.png" style="vertical-align: middle">Карточка
	</button>

	<button class="tablink3" onclick="openPageSet('ResponseSet', this, '#DCEEF8')">
		<img src="resources/images/button/car3.png" style="vertical-align: middle">Реагирования
	</button>


	<button class="tablink3" onclick="openPageSet('KeySet', this, '#DCEEF8')">
		<img src="resources/images/button/keys.png" style="vertical-align: middle">Ключи
	</button>
	<button class="tablink3" onclick="openPageSet('ResponsPeopSet', this, '#DCEEF8')">
		<img src="resources/images/button/people3.png" style="vertical-align: middle">Отв. лица
	</button>

	<button class="tablink3" onclick="openPageSet('SchemaSet', this, '#DCEEF8')">
		<img src="resources/images/button/schema2.png" style="vertical-align: middle">Схема
	</button>
	<button class="tablink3" onclick="openPageSet('PhotoSet', this, '#DCEEF8')">
		<img src="resources/images/button/foto1.png" style="vertical-align: middle">Фото
	</button>
	<br></br>

	<div id="panelcurrentSet"></div>
	<script>
		document.addEventListener("DOMContentLoaded", function(event) {
			document.getElementById("defaultbuttonSet").click();
		});
	</script>
</body>
</html>