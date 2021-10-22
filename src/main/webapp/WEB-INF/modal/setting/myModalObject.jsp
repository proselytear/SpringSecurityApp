<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Search Result</title>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
</head>

<body>


  <div class="modal-dialog" id="myModalObject">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title">Добавить объект</h3>
        <a href="#close" title="Close" class="close">×</a>
      </div>
      <div class="modal-body">    
        <jsp:include page="/WEB-INF/modal/setting/panelrightSet.jsp"/>
      </div>
      <!-- Modal footer -->
				<div class="modal-footer">					
					<a href="#close" type="button" title="Close" class="abutton">Закрыть</a>
				</div>
    </div>
  </div>


</body>
</html>

