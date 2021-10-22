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
	<div id="timetableSet">
		<p id="radiotypeSet"></p>
		<form id="formPCTime1" action="objectsetting" method="post">

			<input type="radio" name="timetable_typeSet" value="1" onclick="blockCalendar(1)"> Индивидуальное расписание
			<input type="radio" name="timetable_typeSet" onclick="noneCalendar(0)" checked value="0"> Другое			
			<input type="hidden" name="namePage" value="Kard" /> 
			
			<div class="cent">
				<div class="messageRed" id="errorPCSetting">${error}</div>
				<div class="messageBlue" id="messagePCSetting">${messageSuccess}</div>
			</div> 
			<input type="hidden" name="namePage" value="Kard" />

		</form>


		<form id="formPCTime" action="objectsetting" method="post" style="display: none">

			<div class="fieldkard">
				<div class="leftfield"><input type="checkbox" name="type1" onclick="blockType('type1')"> Понедельник</div>
				<div id="type1" style="display: none">
					<div class="leftfield small">с</div>
					<div class="rightfield"><input id="mondWithСlockPC" type="number" min="0" max="23" placeholder="00" /></div>
					<div class="leftfield small">:</div>
					<div class="rightfield"><input type="number" min="0" max="59" placeholder="00" id="mondWithMinutesPC"></div>
					<div class="leftfield small">по</div>
					<div class="rightfield"><input type="number" min="0" max="23" placeholder="00" id="mondOnСlockPC"></div>
					<div class="leftfield small">:</div>
					<div class="rightfield"><input type="number" min="0" max="59" placeholder="00" id="mondOnMinutesPC"></div>
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield"><input type="checkbox" name="type2" onclick="blockType('type2')"> Вторник</div>
				<div id="type2" style="display: none">
					<div class="leftfield small">с</div>
					<div class="rightfield"><input type="number" min="0" max="23" placeholder="00" id="tuesdWithСlockPC" /></div>
					<div class="leftfield small">:</div>
					<div class="rightfield"><input type="number" min="0" max="59" placeholder="00" id="tuesdWithMinutesPC"></div>
					<div class="leftfield small">по</div>
					<div class="rightfield"><input type="number" min="0" max="23" placeholder="00" id="tuesdOnСlockPC"></div>
					<div class="leftfield small">:</div>
					<div class="rightfield"><input type="number" min="0" max="59" placeholder="00" id="tuesdOnMinutesPC"></div>
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield"><input type="checkbox" name="type3" value="high" onclick="blockType('type3')"> Среда</div>
				<div id="type3" style="display: none">
					<div class="leftfield small">с</div>
					<div class="rightfield"><input type="number" min="0" max="23" placeholder="00" id="wendesWithСlockPC"/></div>
					<div class="leftfield small">:</div>
					<div class="rightfield"><input type="number" min="0" max="59" placeholder="00" id="wendesWithMinuteskPC"></div>
					<div class="leftfield small">по</div>
					<div class="rightfield"><input type="number" min="0" max="23" placeholder="00" id="wendesOnСlockPC"></div>
					<div class="leftfield small">:</div>
					<div class="rightfield"><input type="number" min="0" max="59" placeholder="00" id="wendesOnMinutesPC"></div>
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield"><input type="checkbox" name="type4" value="busy" onclick="blockType('type4')"> Четверг</div>
				<div id="type4" style="display: none">
					<div class="leftfield small">с</div>
					<div class="rightfield"><input type="number" min="0" max="23" placeholder="00" id="thursdWithСlockPC"/></div>
					<div class="leftfield small">:</div>
					<div class="rightfield"><input type="number" min="0" max="59" placeholder="00" id="thursdWithMinutesPC"></div>
					<div class="leftfield small">по</div>
					<div class="rightfield"><input type="number" min="0" max="23" placeholder="00" id="thursdOnСlockPC"></div>
					<div class="leftfield small">:</div>
					<div class="rightfield"><input type="number" min="0" max="59" placeholder="00" id="thursdOnMinutesPC"></div>
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield"><input type="checkbox" name="type5" value="cat" onclick="blockType('type5')"> Пятница</div>
				<div id="type5" style="display: none">
					<div class="leftfield small">с</div>
					<div class="rightfield"><input type="number" min="0" max="23" placeholder="00" id="fridaydWithСlockPC"/></div>
					<div class="leftfield small">:</div>
					<div class="rightfield"><input type="number" min="0" max="59" placeholder="00" id="fridayWithMinutesPC"></div>
					<div class="leftfield small">по</div>
					<div class="rightfield"><input type="number" min="0" max="23" placeholder="00" id="fridayOnСlockPC"></div>
					<div class="leftfield small">:</div>
					<div class="rightfield"><input type="number" min="0" max="59" placeholder="00" id="fridayOnMinutesPC"></div>
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield"><input type="checkbox" name="type6" value="cat" onclick="blockType('type6')"> Суббота</div>
				<div id="type6" style="display: none">
					<div class="leftfield small">с</div>
					<div class="rightfield"><input type="number" min="0" max="23" placeholder="00" id="saturdayWithСlockPC"/></div>
					<div class="leftfield small">:</div>
					<div class="rightfield"><input type="number" min="0" max="59" placeholder="00" id="saturdayWithMinutesPC"></div>
					<div class="leftfield small">по</div>
					<div class="rightfield"><input type="number" min="0" max="23" placeholder="00" id="saturdayOnСlockPC"></div>
					<div class="leftfield small">:</div>
					<div class="rightfield"><input type="number" min="0" max="59" placeholder="00" id="saturdayOnMinutesPC"></div>
				</div>
			</div>
			<div class="fieldkard">
				<div class="leftfield"><input type="checkbox" name="type7" value="cat" onclick="blockType('type7')"> Воскресенье</div>
				<div id="type7" style="display: none">
					<div class="leftfield small">с</div>
					<div class="rightfield"><input type="number" min="0" max="23" placeholder="00" id="sundayWithСlockPC"/></div>
					<div class="leftfield small">:</div>
					<div class="rightfield"><input type="number" min="0" max="59" placeholder="00" id="sundayWithMinutesPC"></div>
					<div class="leftfield small">по</div>
					<div class="rightfield"><input type="number" min="0" max="23" placeholder="00" id="sundayOnСlockPC"></div>
					<div class="leftfield small">:</div>
					<div class="rightfield"><input type="number" min="0" max="59" placeholder="00" id="sundayOnMinutesPC"></div>
				</div>
			</div>


 <br> 
			<input type="hidden" name="namePage" value="Kard" /> 
			<input type="button" onclick="сopyMond()" name="nameFunc" class="tablink31" value="Скопировать по понедельнику">

		</form>
		<input type="button" onclick="creatTimePCSet()" class="tablink31" name="nameFunc" value="Сохранить"> <br> 
	</div>
<script>
	//Проверяем правильность вводимой даты(часы) от 0 до 23
	function validHour(timeVar) {

		if (timeVar === undefined)
			return '00';
		if (timeVar >= 0 && timeVar <= 23)
			return true;
		else
			return false;
	}
	//Проверяем правильность вводимой даты(минуты) от 0 до 59
	function validMinutes(timeVar) {
		if (timeVar === undefined)
			return '00';
		if (timeVar >= 0 && timeVar <= 59)
			return true;
		else
			return false;
	}
	//скопировать по понедельнику все остальные дни недели при выборе времени в пнд
	function сopyMond() {
		//копируем часы С
		var numTime = document.getElementById("mondWithСlockPC").value;

		document.getElementById('tuesdWithСlockPC').value = numTime;
		document.getElementById('wendesWithСlockPC').value = numTime;
		document.getElementById('thursdWithСlockPC').value = numTime;
		document.getElementById('fridaydWithСlockPC').value = numTime;
		document.getElementById('saturdayWithСlockPC').value = numTime;
		document.getElementById('sundayWithСlockPC').value = numTime;

		//копируем время с
		numTime = document.getElementById("mondWithMinutesPC").value;

		document.getElementById('tuesdWithMinutesPC').value = numTime;
		document.getElementById('wendesWithMinuteskPC').value = numTime;
		document.getElementById('thursdWithMinutesPC').value = numTime;
		document.getElementById('fridayWithMinutesPC').value = numTime;
		document.getElementById('saturdayWithMinutesPC').value = numTime;
		document.getElementById('sundayWithMinutesPC').value = numTime;

		//копируем часы по
		numTime = document.getElementById("mondOnСlockPC").value;

		document.getElementById('tuesdOnСlockPC').value = numTime;
		document.getElementById('wendesOnСlockPC').value = numTime;
		document.getElementById('thursdOnСlockPC').value = numTime;
		document.getElementById('fridayOnСlockPC').value = numTime;
		document.getElementById('saturdayOnСlockPC').value = numTime;
		document.getElementById('sundayOnСlockPC').value = numTime;

		//копируем часы по
		numTime = document.getElementById("mondOnMinutesPC").value;

		document.getElementById('tuesdOnMinutesPC').value = numTime;
		document.getElementById('wendesOnMinutesPC').value = numTime;
		document.getElementById('thursdOnMinutesPC').value = numTime;
		document.getElementById('fridayOnMinutesPC').value = numTime;
		document.getElementById('saturdayOnMinutesPC').value = numTime;
		document.getElementById('sundayOnMinutesPC').value = numTime;

	}
	//Показать весь календадь при выборе Индивидуального расписания
	function blockCalendar(i) {
		var x = document.getElementById("formPCTime");
		x.style.display = 'block';
		document.getElementById("radiotypeSet").innerHTML = i;

	}
	//Скрыть весь календадь при выборе 'Другое'  расписания
	function noneCalendar(i) {
		var x = document.getElementById("formPCTime");
		x.style.display = 'none';
		document.getElementById("radiotypeSet").innerHTML = i;

	}
	//Показать/скрыть время при выборе дня недели
	function blockType(y) {
		var x = document.getElementById(y);
		if (x.style.display === 'none') {
			x.style.display = 'inline';
		} else {
			x.style.display = 'none';
		}

	}

	// преобразовывает пустое значение времени в 00 
	// pTime минуты или часы 0..23 0..59 или''

	function convertTime(pTime) {
		if (pTime == '')
			return '00'
		else
			return pTime;

	}
	function creatTimePCSet() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		var url = $(location).attr('pathname');
		//var nameFunc = $('#nameFunc').val();

		var mondWithСlockPC, mondWithMinutesPC, mondOnСlockPC, mondOnMinutesPC;
		var tuesdWithСlockPC, tuesdWithMinutesPC, tuesdOnСlockPC, tuesdOnMinutesPC;
		var wendesWithСlockPC, wendesdWithMinutesPC, wendesOnСlockPC, wendesOnMinutesPC;
		var thursdWithСlockPC, thursdWithMinutesPC, thursdOnСlockPC, thursdOnMinutesPC;
		var fridaydWithСlockPC, fridayWithMinutesPC, fridayOnСlockPC, fridayOnMinutesPC;
		var saturdayWithСlockPC, saturdayWithMinutesPC, saturdayOnСlockPC, saturdayOnMinutesPC;
		var sundayWithСlockPC, sundayWithMinutesPC, sundayOnСlockPC, sundayOnMinutesPC;
		//инициализируем значение времени
		mondWithСlockPC = mondWithMinutesPC = mondOnСlockPC = mondOnMinutesPC = tuesdWithСlockPC = tuesdWithMinutesPC = tuesdOnСlockPC = tuesdOnMinutesPC = wendesWithСlockPC = wendesWithMinuteskPC = wendesOnСlockPC = wendesOnMinutesPC = "00";
		thursdWithСlockPC = thursdWithMinutesPC = thursdOnСlockPC = thursdOnMinutesPC = fridaydWithСlockPC = fridayWithMinutesPC = fridayOnСlockPC = fridayOnMinutesPC = "00";
		saturdayWithСlockPC = saturdayWithMinutesPC = saturdayOnСlockPC = saturdayOnMinutesPC = sundayWithСlockPC = sundayWithMinutesPC = sundayOnСlockPC = sundayOnMinutesPC = "00";
		if (document.getElementById('radiotypeSet').innerHTML == 1) {
           //Значение Check выбранного дня недели
			var mondCheck = $('#formPCTime input[name=type1]').is(':checked');

			var tuesdCheck = $('#formPCTime input[name=type2]').is(':checked');

			var wendesCheck = $('#formPCTime input[name=type3]').is(':checked');

			var thursdCheck = $('#formPCTime input[name=type4]').is(':checked');

			var fridayCheck = $('#formPCTime input[name=type5]').is(':checked');

			var saturdayCheck = $('#formPCTime input[name=type6]').is(
					':checked');

			var sundayCheck = $('#formPCTime input[name=type7]').is(':checked');
//Если день недели выбран получаем для него значение времени
			if (mondCheck) {
				mondWithСlockPC = $("#mondWithСlockPC").val();
				mondWithСlockPC = convertTime(mondWithСlockPC);
				mondWithMinutesPC = $("#mondWithMinutesPC").val();
				mondWithMinutesPC = convertTime(mondWithMinutesPC);
				mondOnСlockPC = $("#mondOnСlockPC").val();
				mondOnСlockPC = convertTime(mondOnСlockPC);
				mondOnMinutesPC = $("#mondOnMinutesPC").val();
				mondOnMinutesPC = convertTime(mondOnMinutesPC);
			}
			if (tuesdCheck) {
				tuesdWithСlockPC = $("#tuesdWithСlockPC").val();
				tuesdWithСlockPC = convertTime(tuesdWithСlockPC);
				tuesdWithMinutesPC = $("#tuesdWithMinutesPC").val();
				tuesdWithMinutesPC = convertTime(tuesdWithMinutesPC);
				tuesdOnСlockPC = $("#tuesdOnСlockPC").val();
				tuesdOnСlockPC = convertTime(tuesdOnСlockPC);
				tuesdOnMinutesPC = $("#tuesdOnMinutesPC").val();
				tuesdOnMinutesPC = convertTime(tuesdOnMinutesPC);
			}
			if (wendesCheck) {
				wendesWithСlockPC = $("#wendesWithСlockPC").val();
				wendesWithСlockPC = convertTime(wendesWithСlockPC);

				wendesWithMinuteskPC = $("#wendesWithMinuteskPC").val();
				wendesWithMinuteskPC = convertTime(wendesWithMinuteskPC);

				wendesOnСlockPC = $("#wendesOnСlockPC").val();
				wendesOnСlockPC = convertTime(wendesOnСlockPC);

				wendesOnMinutesPC = $("#wendesOnMinutesPC").val();
				wendesOnMinutesPC = convertTime(wendesOnMinutesPC);
			}
			if (thursdCheck) {
				thursdWithСlockPC = $("#thursdWithСlockPC").val();
				thursdWithСlockPC = convertTime(thursdWithСlockPC);
				thursdWithMinutesPC = $("#thursdWithMinutesPC").val();
				thursdWithMinutesPC = convertTime(thursdWithMinutesPC);
				thursdOnСlockPC = $("#thursdOnСlockPC").val();
				thursdOnСlockPC = convertTime(thursdOnСlockPC);
				thursdOnMinutesPC = $("#thursdOnMinutesPC").val();
				thursdOnMinutesPC = convertTime(thursdOnMinutesPC);
			}
			if (fridayCheck) {
				fridaydWithСlockPC = $("#fridaydWithСlockPC").val();
				fridaydWithСlockPC = convertTime(fridaydWithСlockPC);
				fridayWithMinutesPC = $("#fridayWithMinutesPC").val();
				fridayWithMinutesPC = convertTime(fridayWithMinutesPC);
				fridayOnСlockPC = $("#fridayOnСlockPC").val();
				fridayOnСlockPC = convertTime(fridayOnСlockPC);
				fridayOnMinutesPC = $("#fridayOnMinutesPC").val();
				fridayOnMinutesPC = convertTime(fridayOnMinutesPC);
			}
			if (saturdayCheck) {
				saturdayWithСlockPC = $("#saturdayWithСlockPC").val();
				saturdayWithСlockPC = convertTime(saturdayWithСlockPC);
				saturdayWithMinutesPC = $("#saturdayWithMinutesPC").val();
				saturdayWithMinutesPC = convertTime(saturdayWithMinutesPC);
				saturdayOnСlockPC = $("#saturdayOnСlockPC").val();
				saturdayOnСlockPC = convertTime(saturdayOnСlockPC);
				saturdayOnMinutesPC = $("#saturdayOnMinutesPC").val();
				saturdayOnMinutesPC = convertTime(saturdayOnMinutesPC);
			}
			if (sundayCheck) {
				sundayWithСlockPC = $("#sundayWithСlockPC").val();
				sundayWithСlockPC = convertTime(sundayWithСlockPC);
				sundayWithMinutesPC = $("#sundayWithMinutesPC").val();
				sundayWithMinutesPC = convertTime(sundayWithMinutesPC);
				sundayOnСlockPC = $("#sundayOnСlockPC").val();
				sundayOnСlockPC = convertTime(sundayOnСlockPC);
				sundayOnMinutesPC = $("#sundayOnMinutesPC").val();
				sundayOnMinutesPC = convertTime(sundayOnMinutesPC);
			}

		}
//Формируем json для отправки для метода создать Maineq(Основное)
		var json = {
			nameFunc : "createMaineq",
			mondWithPC : mondWithСlockPC + ":" + mondWithMinutesPC + ":00",
			mondOnPC : mondOnСlockPC + ":" + mondOnMinutesPC + ":00",
			tuesdWithPC : tuesdWithСlockPC + ":" + tuesdWithMinutesPC + ":00",
			tuesdOnPC : tuesdOnСlockPC + ":" + tuesdOnMinutesPC + ":00",
			wendesWithPC : wendesWithСlockPC + ":" + wendesWithMinuteskPC
					+ ":00",
			wendesOnPC : wendesOnСlockPC + ":" + wendesOnMinutesPC + ":00",
			thursdWithPC : thursdWithСlockPC + ":" + thursdWithMinutesPC
					+ ":00",
			thursdOnPC : thursdOnСlockPC + ":" + thursdOnMinutesPC + ":00",
			fridayWithPC : fridaydWithСlockPC + ":" + fridayWithMinutesPC
					+ ":00",
			fridayOnPC : fridayOnСlockPC + ":" + fridayOnMinutesPC + ":00",
			saturdayWithPC : saturdayWithСlockPC + ":" + saturdayWithMinutesPC
					+ ":00",
			saturdayOnPC : saturdayOnСlockPC + ":" + saturdayOnMinutesPC
					+ ":00",
			sundayWithPC : sundayWithСlockPC + ":" + sundayWithMinutesPC
					+ ":00",
			sundayOnPC : sundayOnСlockPC + ":" + sundayOnMinutesPC + ":00",

			accountPCSetting : $("#accountPCSetting").val(),
			sim1PCSetting : $("#sim1PCSetting").val(),
			sim2PCSetting : $("#sim2PCSetting").val(),
			coordinatesPCSetting : $("#coordinatesPCSetting").val(),
			numGroupPCSetting : $("#numGroupPCSetting").val(),
			perifdataPCSetting : $("#perifdataPCSetting").val()

		}
//Проверяем правильность вводимого времени в календаре расписания
		if (!validHour(mondWithСlockPC) || !validHour(mondOnСlockPC)
				|| !validHour(tuesdWithСlockPC) || !validHour(tuesdOnСlockPC)
				|| !validHour(wendesWithСlockPC) || !validHour(wendesOnСlockPC)
				|| !validHour(thursdWithСlockPC) || !validHour(thursdOnСlockPC)
				|| !validHour(fridaydWithСlockPC)
				|| !validHour(fridayOnСlockPC)
				|| !validHour(saturdayWithСlockPC)
				|| !validHour(saturdayOnСlockPC)
				|| !validHour(sundayWithСlockPC) || !validHour(sundayOnСlockPC)) {
			document.getElementById('errorPCSetting').innerHTML = "Время (часы) должны быть в интервале 0..23";//Сообщение пользователю
		} else if (!validMinutes(mondWithMinutesPC)
				|| !validMinutes(mondOnMinutesPC)
				|| !validMinutes(tuesdWithMinutesPC)
				|| !validMinutes(tuesdOnMinutesPC)
				|| !validMinutes(wendesdWithMinutesPC)
				|| !validMinutes(wendesOnMinutesPC)
				|| !validMinutes(thursdWithMinutesPC)
				|| !validMinutes(thursdOnMinutesPC)
				|| !validMinutes(fridayWithMinutesPC)
				|| !validMinutes(fridayOnMinutesPC)
				|| !validMinutes(saturdayWithMinutesPC)
				|| !validMinutes(saturdayOnMinutesPC)
				|| !validMinutes(sundayWithMinutesPC)
				|| !validMinutes(sundayOnMinutesPC)) {
			document.getElementById('errorPCSetting').innerHTML = "Время (минуты) должны быть в интервале 0..59"; 
		}

		else {

			$.ajax({
				beforeSend : function(xhr) {
					// here it is
					xhr.setRequestHeader(header, token);
				},
				type : "POST",

				url : url,
				data : {
					json : JSON.stringify(json)
				},

				cache : false,
				success : function(html) {
					//Получаем сообщения для пользователя после выполнения функции
					$("#errorPCSetting").html($(html).find('#errorPCSetting'));
					$("#messagePCSetting").html(
							$(html).find('#messagePCSetting'));

				}
			});
		}

	}
</script>
</body>
</html>