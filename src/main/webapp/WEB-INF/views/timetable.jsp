<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>
</head>

<body onload="clickMeth('buttonNotTime')">
	<div id="timetable" class="minw1">
		<p>
			<b class="card_middle_text">Объект <input type="text" class="card_middle_size" value="${maineq.getAccountid()}" /></b>
		</p>
		<b class="card_middle_text">Тип работы группы: Индивидуальное расписание</b>


		<div class="fieldkard">
			<div class="leftfield">Понедельник</div>
			<div class="leftfield small">с</div>
			<div class="rightfield">
				<input type="text" value="${perifinfo.getTimefrommonday() }" readonly />
			</div>
			<div class="leftfield small">по</div>
			<div class="rightfield">
				<input type="text" value="${perifinfo.getTimetomonday() }" readonly />
			</div>
		</div>
		<div class="fieldkard">
			<div class="leftfield">Вторник</div>
			<div class="leftfield small">с</div>
			<div class="rightfield">
				<input type="text" value="${perifinfo.getTimefromtuesday() }" readonly />
			</div>
			<div class="leftfield small">по</div>
			<div class="rightfield">
				<input type="text" value="${perifinfo.getTimetotuesday() }" readonly />
			</div>
		</div>
		<div class="fieldkard">
			<div class="leftfield">Среда</div>
			<div class="leftfield small">с</div>
			<div class="rightfield">
				<input type="text" value="${perifinfo.getTimefromwednesday() }" readonly />
			</div>
			<div class="leftfield small">по</div>
			<div class="rightfield">
				<input type="text" value="${perifinfo.getTimetowednesday() }" readonly />
			</div>
		</div>
		<div class="fieldkard">
			<div class="leftfield">Четверг</div>
			<div class="leftfield small">с</div>
			<div class="rightfield">
				<input type="text" value="${perifinfo.getTimefromthursday() }" readonly />
			</div>
			<div class="leftfield small">по</div>
			<div class="rightfield">
				<input type="text" value="${perifinfo.getTimetothursday() }" readonly />
			</div>
		</div>
		<div class="fieldkard">
			<div class="leftfield">Пятница</div>
			<div class="leftfield small">с</div>
			<div class="rightfield">
				<input type="text" value="${perifinfo.getTimefromfriday() }" readonly />
			</div>
			<div class="leftfield small">по</div>
			<div class="rightfield">
				<input type="text" value="${perifinfo.getTimetofriday() }" readonly />
			</div>
		</div>
		<div class="fieldkard">
			<div class="leftfield">Суббота</div>
			<div class="leftfield small">с</div>
			<div class="rightfield">
				<input type="text" value="${perifinfo.getTimefromsaturday() }" readonly />
			</div>
			<div class="leftfield small">по</div>
			<div class="rightfield">
				<input type="text" value="${perifinfo.getTimetosaturday() }" readonly />
			</div>
		</div>
		<div class="fieldkard">
			<div class="leftfield">Воскресенье</div>
			<div class="leftfield small">с</div>
			<div class="rightfield">
				<input type="text" value="${perifinfo.getTimefromsunday() }" readonly />
			</div>
			<div class="leftfield small">по</div>
			<div class="rightfield">
				<input type="text" value="${perifinfo.getTimetosunday() }" readonly />
			</div>
		</div>


	</div>
</body>
</html>