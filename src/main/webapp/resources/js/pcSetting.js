
function openPagePCSet(pageName, elmnt, color) {

	document.getElementById("panelcurrentPCSet").innerHTML = pageName;
	// Скрытие всех элементов с помощью class="tabcontent" by default */
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontentPCSet");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}

	// Удаление цвета фона всех tablinks/buttons
	tablinks = document.getElementsByClassName("tablinkPCSet");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].style.backgroundColor = "";
	}

	// Показать конкретное содержание вкладки
	document.getElementById(pageName).style.display = "block";

	// Добавьте определенный цвет к кнопке, используемой для открытия содержимого вкладки
	elmnt.style.backgroundColor = color;

	reloadPanelPCSet(pageName);

}
//Обновляем данные панели настрйки ППК при нажатии внутренних клавишь после введения номера объекта
function reloadPanelPCSet(namepanel) {
	

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');

	var kard = {

		accountKardSet: $("#accountPCSetting").val(),
		nameFunc: "setPanelPC"
	}


	$.ajax({
		beforeSend: function(xhr) {
			// here it is
			xhr.setRequestHeader(header, token);
		},
		type: "POST",

		url: url,
		data: {
			json: JSON.stringify(kard),
			panelcurrent: namepanel
		},

		cache: false,
		success: function(html) {
			
			if (namepanel == 'PlumeSet') {

				$("#plumetabSet").html($(html).find('#plumetabSet'));
			}
		}
	});

}
