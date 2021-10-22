
var audio = new Audio(
	'https://www.yapfiles.ru/files/180923/185ccd51ca65e026d944ef6d6efdcd55.mp3?token=MDAyNDMxNDEtMTYyMTg0MzAwMw');



function play() {
	var audio = new Audio(
		'https://www.yapfiles.ru/files/180923/185ccd51ca65e026d944ef6d6efdcd55.mp3?token=MDAyNDMxNDEtMTYyMTg0MzAwMw');

	audio.play();
}
function checkForm(form) {
	// Заранее объявим необходимые переменные
	var el, // Сам элемент
		elName, // Имя элемента формы
		value, // Значение
		type; // Атрибут type для input-ов
	// Массив списка ошибок, по дефолту пустой
	var errorList = [];
	// Хэш с текстом ошибок (ключ - ID ошибки)
	var errorText = {
		1: "Не заполнено поле 'группа'",
		2: "Не заполнено поле 'шлейф'",
		3: "Не заполнено поле 'объект'",
		4: "Неправильно заполнено числовое поле 'группа'",
		5: "Неправильно заполнено числовое поле 'шлейф'",
		6: "Неправильно заполнено числовое поле 'на время' (от '00:01' до '23:59')"
	}
	// Получаем семейство всех элементов формы
	// Проходимся по ним в цикле
	for (var i = 0; i < form.elements.length; i++) {
		el = form.elements[i];
		elName = el.nodeName.toLowerCase();
		value = el.value;
		if (elName == "input") { // INPUT
			// Определяем тип input-а
			type = el.type.toLowerCase();
			// Разбираем все инпуты по типам и обрабатываем содержимое
			switch (type) {
				case "text":


					if (el.name == "grouprel" && value != ""
						&& !(/^[-]?\d+$/.test(value)))
						errorList.push(4);

					else if (el.name == "paramnum" && (value != ""
						&& !(/^[-]?\d+$/.test(value)))) {
						errorList.push(5);
					}
					if (el.name == "accountId" && value == "")
						errorList.push(3);

					if (el.name == "appt_time" && !(/^([01]?[0-9]|2[0-3]):[0-5][0-9]/.test(value)))
						errorList.push(6);

					break;

				default:
					// Сюда попадают input-ы, которые не требуют обработки
					// type = hidden, submit, button, image
					break;
			}
		}
	}
	// Финальная стадия
	// Если массив ошибок пуст - возвращаем true
	if (!errorList.length)
		return true;
	// Если есть ошибки - формируем сообщение, выовдим alert
	// и возвращаем false
	var errorMsg = "При заполнении формы допущены следующие ошибки:\n";
	for (i = 0; i < errorList.length; i++) {
		errorMsg += errorText[errorList[i]] + "\n";
	}

	alert(errorMsg);
	return false;
}
function myFunctionz() {
	// Объявить переменные
	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("myInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("myTable");
	tr = table.getElementsByTagName("tr");

	// Перебирайте все строки таблицы и скрывайте тех, кто не соответствует поисковому запросу
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[0];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}


function tableSearch() {
	console.log(21);
	var phrase = document.getElementById('search-text');
	var table = document.getElementById('info-table');
	var regPhrase = new RegExp(phrase.value, 'i');
	var flag = false;
	for (var i = 1; i < table.rows.length; i++) {
		flag = false;
		for (var j = table.rows[i].cells.length - 1; j >= 0; j--) {
			flag = regPhrase.test(table.rows[i].cells[j].innerHTML);
			if (flag)
				break;
		}
		if (flag) {
			table.rows[i].style.display = "";
		} else {
			table.rows[i].style.display = "none";
		}

	}
}

function foo() {
	var i, tabcontent, tabcontent2, tab;
	tabcontent = document.getElementsByClassName("tabcontent31");
	for (i = 1; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 1; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}
	tabcontent = document.getElementsByClassName("tabcontent3");
	for (i = 1; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}
	tabcontent2 = document.getElementsByClassName("tabcontent2");
	for (i = 1; i < tabcontent2.length; i++) {
		tabcontent2[i].style.display = "none";
	}

}
//Выделяем выбранную кнопку на левой панели другим цветом все остальные кнопки делаем обычным чветом
function setColorButtonPanel(idButton, idPanel) {


	var stroka = Array.from(document.getElementById(idPanel).children);
	var array = []; // Массив из id
	var element = '';
	for (let i = 0; i < stroka.length; i++) {
		element = stroka[i].getAttribute('id');

		if (element === idButton)
			document.getElementById(element).style.background = 'linear-gradient(#bbe693, #385830f7)';

		else
			document.getElementById(element).style.background = 'linear-gradient(#b1d0b3, #6f8668)';


	}


}
//Выбираем ряды панелей которые делаем бесцветными
function setColorButtonPanelRow( idPanel) {

var fullPanel='panelcardRight';
	var stroka = Array.from(document.getElementById(fullPanel).children);

	var element = '';
	for (let i = 0; i < stroka.length; i++) {
		element = stroka[i].getAttribute('id');

		if (element != idPanel)
	
				setColorButtonPanelNone( element);

	}


}
//делаем остальные ряды кнопок обычным цветом
function setColorButtonPanelNone( idPanel) {
		var stroka = Array.from(document.getElementById(idPanel).children);
	var array = []; // Массив из id
	var element = '';
	for (let i = 0; i < stroka.length; i++) {
		element = stroka[i].getAttribute('id');

			document.getElementById(element).style.background = 'linear-gradient(#b1d0b3, #6f8668)';


	}
	
	}
function openPage3(pageName, idButton, idPanel) {
	//Установливаем цвета выделенной кнопки отдельно для каждого ряда
	setColorButtonPanel(idButton, idPanel);
	setColorButtonPanelRow(idPanel);


	document.getElementById("panelcurrent").innerHTML = pageName;
	// Скрытие всех элементов с помощью class="tabcontent" by default */
	var i, tabcontent;
	tabcontent = document.getElementsByClassName("tabcontent3");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}

	// Показать конкретное содержание вкладки
	document.getElementById(pageName).style.display = "block";
	
	
	var url = $(location).attr('pathname');
	if(url=='/SpringSecurityApp/objectsignal')//обновляем клик метода на том url на котором сейчас находимся
	{
		var idinfo = document.getElementById("currentIdInfo").innerHTML;
		myclickSignal(idinfo,1);
		
	}else  if(url=='/SpringSecurityApp/home'){

		var idinfo = document.getElementById("currentIdInfoAll").innerHTML;
		myclick(idinfo,1);
	}



}
function reloadPanel(pageName) {
	if (pageName == 'ResponsPeopSet')
		reloadResponsPeopSet();
}
function openPageSet(pageName, elmnt, color) {
	reloadPanel(pageName);

	document.getElementById("panelcurrentSet").innerHTML = pageName;
	// Скрытие всех элементов с помощью class="tabcontent" by default */
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontentSet");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}

	// Удаление цвета фона всех tablinks/buttons
	tablinks = document.getElementsByClassName("tablinkSet");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].style.backgroundColor = "";
	}

	// Показать конкретное содержание вкладки
	document.getElementById(pageName).style.display = "block";

	// Добавьте определенный цвет к кнопке, используемой для открытия содержимого вкладки
	elmnt.style.backgroundColor = color;
	if (pageName == "Schema") getSchema();

	reloadPanelSet(pageName);

}

function reloadPanelSet(namepanel) {

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');

	var kard = {

		accountKardSet: $("#accountKardSet").val(),
		nameFunc: "setPanel",
		groupNum: 1
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
			$("#plumetabSet").html($(html).find('#plumetabSet'));
		}
	});

}
function getSchema() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');
	//var nameFunc = $('#nameFunc').val();




}


document.addEventListener('click', event => {
	if (event.target.matches('button')) {
		event.target.focus()
	}
})


function openPage31(pageName, elmnt, color, idButton, idPanel) {
	setColorButtonPanel(idButton, idPanel);
	// Скрытие всех элементов с помощью class="tabcontent" by default */

	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent31");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}

	// Удаление цвета фона всех tablinks/buttons
	tablinks = document.getElementsByClassName("tablink31");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].style.backgroundColor = "";
	}

	// Показать конкретное содержание вкладки
	document.getElementById(pageName).style.display = "block";

	// Добавьте определенный цвет к кнопке, используемой для открытия содержимого вкладки
	elmnt.style.backgroundColor = color;
}
function openCity(evt, cityName) {
	// Declare all variables
	var i, tabcontent, tablinks;

	// Получите все элементы с class="tabcontent" и спрячьте их
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}

	// Получите все элементы с class="tablinks" и удалите класс "active"
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace("active", "");
	}

	// Показать текущую вкладку и добавить класс "active" к кнопке, открывшей вкладку
	document.getElementById(cityName).style.display = "block";
	evt.currentTarget.className += "active";

}
function openPage(pageName, elmnt, color) {
	// Скрытие всех элементов с помощью class="tabcontent" by default */
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}

	// Удаление цвета фона всех tablinks/buttons
	tablinks = document.getElementsByClassName("tablink");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].style.backgroundColor = "";
	}

	// Показать конкретное содержание вкладки
	document.getElementById(pageName).style.display = "block";

	// Добавьте определенный цвет к кнопке, используемой для открытия содержимого вкладки
	elmnt.style.backgroundColor = color;
}
function openPage2(pageName, elmnt, color) {
	// Скрытие всех элементов с помощью class="tabcontent" by default */

	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent2");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";

	}

	// Удаление цвета фона всех tablinks/buttons
	tablinks = document.getElementsByClassName("tablink2");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].style.backgroundColor = "";
	}

	// Показать конкретное содержание вкладки
	document.getElementById(pageName).style.display = "block";

	// Добавьте определенный цвет к кнопке, используемой для открытия содержимого вкладки
	elmnt.style.backgroundColor = color;
}

function myFunction() {

	// Declare variables
	var input, filter, table, tr, td, i;
	input = document.getElementById("myInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("myTable");
	tr = table.getElementsByTagName("tr");
	console.log('regPhrase');

	// Loop through all table rows, and hide those who don't match the search query
	for (i = 0; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[0];
		if (td) {
			if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}


function clickMeth(params) {
	for (i = 0; i < params.length; i++) {
		document.getElementById(params[i]).style.background = 'linear-gradient(#bbe693, #385830f7)';
		if(params[i]=='buttonStand'||params[i]=='buttonGroups')//для панели стендыи группы реагирования функцию выслать группу реаширования делаем невидимой
		document.getElementById('panelGroupSend').style.display='none';
	
	}
}
