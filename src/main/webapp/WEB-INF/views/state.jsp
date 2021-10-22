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
	<div id="state">
		<p>
			<b class="card_middle_text">Объект </b> <input type="text" class="card_middle_size" value="${maineq.getAccountid()}" />
		</p>

		<div class="fieldkard">
			<div class="leftfield">Установленные приборы</div>
			<div class="rightfield" id="kardresponsible">${maineq.getFixedMaineq() }</div>
		</div>
		<!-- 	<p>
		<textarea rows="3" cols="30" class="card_small_text" name="text">${maineq.getFixedMaineq() }</textarea>
	</p> -->


		<br>
		<form>
			<fieldset class="card_small_text">
				<legend class="card_big_text">Дополнительно</legend>
				<br>

				<div class="fieldkard">
					<div class="leftfield">Основное питание</div>
					<div class="rightfield">
						<input type="text" value="${maineq.getStatusPowerRuImg()}" readonly />
					</div>

				</div>
				<div class="fieldkard">
					<div class="leftfield">Резервное питание</div>
					<div class="rightfield">
						<input type="text" value="${maineq.getStatusBattaryRuImg()}" readonly />
					</div>

				</div>
				<div class="fieldkard">
					<div class="leftfield">Последний тест</div>
					<div class="rightfield">
						<input type="text" value="${maineq.getLastcon()}" readonly />
					</div>

				</div>

			</fieldset>

		</form>
	</div>
</body>
</html>