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
	<div id="response">
		
			<p>
				<b class="card_middle_text">Объект </b> <input type="text" class="card_middle_size" value="${maineq.getAccountid()}" />
			</p>
			
				<b class="card_middle_text">Описание:</b>
			


<c:forEach items="${groups}" var="groups" varStatus="status">
			<div class="fieldkard">
			
				<div class="leftfield field165">${groups.getName() }</div>
				<div class="rightfield">
					${groups.getPhone() }
				</div>
				
			</div>
</c:forEach>
		
		<div items="${maineq}" var="maineq">

			<p>
				<b class="card_middle_text">Инструкция по реагированию для оператора:</b>
			</p>
			<p>
				<b class="one_cell">${maineq.getInstructionResponse() }</b>
			</p>

		</div>
	</div>
</body>
</html>