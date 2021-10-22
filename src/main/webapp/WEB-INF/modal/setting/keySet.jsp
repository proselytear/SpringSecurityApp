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
	<div id="keySet">
		<p>
			<b class="card_middle_text">Объект </b> <input type="text" class="card_middle_size" 
			value="${maineq.getAccountid()}" />
		</p>
		<form method="post">

					<div class="fieldkard">
			<div class="leftfield">Ф.И.О владельца ключа</div>
			<c:forEach items="${perifinfoKey}" var="perifinfoKey" varStatus="status">
			<div class="rightfield">
				${perifinfoKey.getPerifdata() }
			</div></c:forEach>
		</div>
		</form>

	</div>
</body>
</html>