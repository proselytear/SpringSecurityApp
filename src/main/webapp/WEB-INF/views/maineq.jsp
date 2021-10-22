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
	<div id="maineq" class="minw">
		<p>
			<b class="card_middle_text">Объект </b> <input type="text" class="card_middle_size" value="${maineq.getAccountid()}" />
		</p>

		<div class="fieldkard">
			<div class="leftfield big">Установленный прибор</div>
			<div class="rightfield">${maineq.getLosernord() }</div>

		</div>
		<div class="fieldkard">
			<div class="leftfield big">Тип и версия прибора</div>
			<div class="rightfield">${maineq.getWifiname() }</div>

		</div>
		<div class="fieldkard">
			<div class="leftfield big">Набор кодов</div>
			<div class="rightfield">${maineq.getCodeName().getNameCode() }</div>

		</div>
		<div class="fieldkard">
			<div class="leftfield big">Дата подключения</div>
			<div class="rightfield">${maineq.getFirstcon() }</div>

		</div>
		<div class="fieldkard">
			<div class="leftfield big">SIM1</div>
			<div class="rightfield">${maineq.getEqphone() }</div>

		</div>
		<div class="fieldkard">
			<div class="leftfield big">SIM2</div>
			<div class="rightfield">${maineq.getEqphone2() }</div>
		</div>
	</div>
</body>
</html>