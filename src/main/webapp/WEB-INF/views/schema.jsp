<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>

</head>
<body>
	<div id="schema">

		<div id="openModalSchema" class="modal">
			<jsp:include page="/WEB-INF/modal/myModalSchema.jsp" />
		</div>
		<p id="schemaObject">
			<b class="card_middle_text">Объект </b> <input type="text"
				class="card_middle_size" value="${maineq.getAccountid()}" /><br>
			<br>

		</p>
		<c:forEach items="${schemaList}" var="schemaList" varStatus="status">
			<a href="#openModalSchema" class="btn" type="button"
				onclick="newVal(this)" name="pathSchema"
				value="${schemaList.getColumnOne() }"> <img
				src=${schemaList.getColumnOne() }>
			</a>

		</c:forEach>

	</div>
	<script type="text/javascript">
		function newVal(t) {
			var res = $(t).attr('value');
			input.src = res;
			return false;
		}
	</script>
</body>
</html>