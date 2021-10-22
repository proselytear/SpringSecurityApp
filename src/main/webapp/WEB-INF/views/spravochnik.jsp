<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page pageEncoding="UTF-8"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Log in with your account</title>


<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>
<body>
	<div class="split left20">
		<ul>
			<li><a href="#">┓Сотрудник</a></li>
			<li><a href="#">┣Группы реагирования</a></li>
			<li><a href="#">┣Что-то</a></li>
			<li><a href="#">┣Что-то</a></li>
			<li><a href="#">┣Что-то</a></li>
			<li><a href="#">┣Что-то</a></li>
			<li><a href="#">┣Что-то</a></li>
			<li><a href="#">┣Что-то</a></li>
			<li><a href="#">┛Что-то</a></li>
		</ul>
	</div>
	<div class="split right80">
		<table class="table_sotrudniki">
			<thead>
				<tr>
					<th>ID</th>
					<th>Фамиля</th>
					<th>Имя</th>
					<th>Отчество</th>
					<th>Должность</th>
					<th>Логин</th>
					<th>Пароль</th>

				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
				</tr>

			</tbody>
		</table>
	</div>
</body>
</html>