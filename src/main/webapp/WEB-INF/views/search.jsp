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
	<div align="center">
		<h2>Search Result</h2>
		<table>
			<tr>
				<th>ID</th>
				<th>Name</th>

			</tr>
			<c:forEach items="${result}" var="customer">
				<tr>
					<td>${customer.ideqval}</td>
					<td>${customer.accountid}</td>

				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>