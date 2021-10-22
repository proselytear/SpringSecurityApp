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
	<div id="responspeop" class="tabcontent4">
		<p>
			<b class="card_middle_text">Объект </b> <input type="text" class="card_middle_size" value="${maineq.getAccountid()}" />
		</p>
		<div class="scroll-table-body0">
			<table class="table_big tabbg">
				<thead>
					<tr>
						<th>Ф.И.О ответственного лица</th>
						<th>Адрес проживания</th>
						<th>&#9742; Телефон</th>
						<th>Примечание</th>
					</tr>
				</thead>
				<c:forEach items="${perifinfoResponsible}" var="perifinfoResponsible" varStatus="status">
					<tr>
						<td>${perifinfoResponsible.getFullName() }</td>
						<td>${perifinfoResponsible.getAddress() }</td>
						<td>${perifinfoResponsible.getPhone() }</td>
						<td>${perifinfoResponsible.getNote() }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
