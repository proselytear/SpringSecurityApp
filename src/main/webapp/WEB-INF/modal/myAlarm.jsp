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
	<div class="modal" id="Alarm">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h6 class="modal-title">Сгенерировать тревогу</h6>
					<button type="button" class="close" data-dismiss="modal"></button>
				</div>


				<div class="modal-body">

					<div>
						<p>
							<b>Выберите группу </b>

						</p>

						<form method="post" action="signal">

							<font size="2" face="Courier New">
								<form action="param" method="post" modelAttribute="personForm">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<table >

										<c:forEach items="${alarmGroups}" var="alarmGroups" varStatus="status">
											<tr>
												<td><input type="checkbox" name="groups_id" value=${alarmGroups.getGroups_id()} ></td>
												<th>${alarmGroups.getName() }</th>
											</tr>
										</c:forEach>
									</table>
									<button class="btn btn-large btn-success pull-right" type="submit">&#10004;&#65039; Готово</button>

									<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>

								</form>
						</form>


					</div>

				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Закрыть</button>

				</div>

			</div>
		</div>
	</div>
</body>
</html>