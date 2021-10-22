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

	<!-- The Modal -->


	<div class="modal-dialog" id="myModalSchema">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">Схема объекта</h4>
				<a href="#close" title="Close" class="close">×</a>

			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<img id="input" class="bigschema" src="" alt="Mountain View">

				<!--	<div class="container1">
					<div class="mySlides">						
						<img id="input" src="" style="width: 100%">
					</div>					

					<a class="prev" onclick="plusSlides(-1)">❮</a> 
					<a class="next" onclick="plusSlides(1)">❯</a>

					<div class="caption-container">
						<p id="caption"></p>
					</div>

					<div class="row">
						<div class="column">
							<img class="demo cursor" src="" style="width: 100%" onclick="currentSlide(1)">
						</div>
						
					</div>
				</div>-->
			</div>



		</div>
	</div>

	<!--  
<script>
var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("demo");
  var captionText = document.getElementById("caption");
  if (n > slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
  captionText.innerHTML = dots[slideIndex-1].alt;
}
</script>-->

</body>
</html>