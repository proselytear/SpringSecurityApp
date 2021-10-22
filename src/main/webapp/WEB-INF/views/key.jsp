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
	<div id="key">
		<p>
			<b class="card_middle_text">Объект </b> <input type="text" class="card_middle_size" value="${maineq.getAccountid()}" />
		</p>
		<form method="post">
			<table class="table-body1">
				<thead>
					<tr>
						<th>Ф.И.О владельца ключа</th>
					</tr>
				</thead>
				<c:forEach items="${perifinfoKey}" var="perifinfoKey" varStatus="status">
					<tr>
						<td>${perifinfoKey.getPerifdata() }</td>

					</tr>
				</c:forEach>
			</table>
		</form>

	</div>
</body>
</html>