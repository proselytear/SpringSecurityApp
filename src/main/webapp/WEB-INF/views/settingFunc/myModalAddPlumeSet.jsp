<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>

</head>

<body>
	
		<div class="modal-dialog" id="myModalAddPlumeSet">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Добавить в справичник ответственное лицо</h4>
					
					<a href="#openModalObject" type="button" class="close">×</a>
				</div>


				<div class="modal-body">
					<div id="addDictionary">
					
						<div class="fieldkard">
							<div class="leftfield" id="idPers"><label for="kardname">Ид </label></div>
							<div class="rightfield">
								<input type="text" id="responsPeopId" value="${idNewPerson}" />
							</div>
						</div>
						<div class="fieldkard">
							<div class="leftfield"><label for="kardname" >Фамилия </label></div>
							<div class="rightfield">
								<input type="text" id="responsPeopLast" value="" />
							</div>
						</div>
						<div class="fieldkard">
							<div class="leftfield"><label for="kardname" >Имя </label></div>
							<div class="rightfield">
								<input type="text" id="responsPeopFirst" value="" />
							</div>
						</div>
						<div class="fieldkard">
							<div class="leftfield"><label for="kardname" >Отчество </label></div>
							<div class="rightfield">
								<input type="text" id="responsPeopPatronom" value="" />
							</div>
						</div>
						<div class="fieldkard">
							<div class="leftfield"><label for="kardname" >Адрес проживания </label></div>
							<div class="rightfield">
								<input type="text"  id="responsPeopAddress" value="" />
							</div>
						</div>
						<div class="fieldkard">
							<div class="leftfield"><label for="kardname">Телефон &#10004 </label></div>
							<div class="rightfield">
								<input type="text" id="responsPeopPhone" value="" />
							</div>
						</div>
						<div class="fieldkard">
							<div class="leftfield"><label for="kardname" >Примечание </label></div>
							<div class="rightfield">
								<input type="text" id="responsPeopNote" value="" />
							</div>
						</div>
						

					</div>
					<div id="errorAddDictionarySet" class="messageRed cent">${errorModal}</div>
					<div id="messageAddDictionarySet" class="messageBlue cent">${messageModal}</div>
					
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
					<input type="hidden" /> 
					<input type="button" onclick="createNewPesponPeop()" class="tablink31" name="nameFunc"
						value="Добавить"> 
					


				</div>
				
				<!-- Modal footer -->
				<div class="modal-footer">					
					<a href="#openModalObject" type="button" title="Close" class="abutton">Закрыть</a>
				</div>

			</div>
		</div>
	

</body>
</html>