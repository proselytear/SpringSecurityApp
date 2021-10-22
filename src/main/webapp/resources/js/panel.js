

function myclick(idinfo) {

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');
	var panelcurrent = document.getElementById("panelcurrent").innerHTML;

	$.ajax({

		type: "post",
		dataType: 'html',
		beforeSend: function(xhr) {
			// here it is
			xhr.setRequestHeader(header, token);
		},
		data: {
			idinfo: idinfo,

			myclick: 1,
			panelcurrent: panelcurrent
		},

		url: url,


		success: function(html) {


			//обновляем значения флаrов обновления

			$("#flagUpdateClick").html(
				$(html).find('#flagUpdateClick').html());
			//обновляем основную таблицу
			$("#listobjectview1").html(
				$(html).find('#listobjectview1').html());

			//Присваиваем параметры выбранного объекта кнопкам на панели прием согналов


			$("#myModalResponseform").html($(html).find('#myModalResponseform').first());
			$("divRespFree").html($(html).find('#divRespFree').html());


			$("#myModalGroupArrivedform").html($(html).find('#myModalGroupArrivedform').first());

			$("#divmyModGrCancel").html($(html).find('#divmyModGrCancel').html());

			$("#myModalGroupCancelform").html($(html).find('#myModalGroupCancelform').html());

			$("#myModalAlarmCancelform").html($(html).find('#myModalAlarmCancelform'));
			$("#kard").html($(html).find('#kard').html());





		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});

	idinfo = document.getElementById("currentIdInfoAll").innerHTML;

	reloadObjectView(idinfo, 1);

}
function myclickEvent(idEvent) {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');
	
	$.ajax({

		type: "post",
		dataType: 'html',
		beforeSend: function(xhr) {
			// here it is
			xhr.setRequestHeader(header, token);
		},
		data: {
			idEvent: idEvent,

			myclick: 1
		},

		url: url,


		success: function(html) {


			//обновляем значения флаrов обновления

			$("#flagUpdateClickEvent").html(
				$(html).find('#flagUpdateClickEvent').html());
			//обновляем основную таблицу
		$("#t_bodyEvf").html(
					$(html).find('#t_bodyEvf').html());

			//Присваиваем параметры выбранного объекта кнопкам на панели прием согналов


			$("#myModalResponseform").html($(html).find('#myModalResponseform').first());
			$("divRespFree").html($(html).find('#divRespFree').html());


			$("#myModalGroupArrivedform").html($(html).find('#myModalGroupArrivedform').first());

			$("#divmyModGrCancel").html($(html).find('#divmyModGrCancel').html());

			$("#myModalGroupCancelform").html($(html).find('#myModalGroupCancelform').html());

			$("#myModalAlarmCancelform").html($(html).find('#myModalAlarmCancelform'));
			$("#kard").html($(html).find('#kard').html());







		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});

	idEvent = document.getElementById("currentIdEvent").innerHTML;

	reloadEventView(idEvent, 1);

}
function myclick2(idinfo) {

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');
	var panelcurrent = document.getElementById("panelcurrent").innerHTML;

	$.ajax({

		type: "post",
		dataType: 'html',
		beforeSend: function(xhr) {
			// here it is
			xhr.setRequestHeader(header, token);
		},
		data: {
			idinfo: idinfo,

			myclick: 1,
			panelcurrent: panelcurrent
		},

		url: url,


		success: function(html) {

			$("#kard").html($(html).find('#kard').html());
			$("#schema").html($(html).find('#schema').html());

			$("#maineq").html($(html).find('#maineq').html());
			$("#timetable").html($(html).find('#timetable').html());
			$("#response").html($(html).find('#response').html());
			$("#plume").html($(html).find('#plume').html());
			$("#key").html($(html).find('#key').html());
			$("#responspeop").html($(html).find('#responspeop').html());
			$("#state").html($(html).find('#state').html());
			$("#events").html($(html).find('#events').html());
			$("#stand").html($(html).find('#stand').html());
			$("#signal").html($(html).find('#signal').html());
			$("#groups").html($(html).find('#groups').html());



			$("#divMyAlarm").html($(html).find('#divMyAlarm').first());
			$("#myModalbody").html($(html).find('#myModalbody').first());
			$("#myModalResponseform").html($(html).find('#myModalResponseform').first());
			$("#myModalResponseform2").html($(html).find('#myModalResponseform2').first());
			$("#myModalStatusPoolform").html($(html).find('#myModalStatusPoolform').first());

			$("#divmyModalTechnic").html($(html).find('#divmyModalTechnic').first());
			$("#myModalAlarmform").html($(html).find('#myModalAlarmform').first());
			$("#myModalCloseform").html($(html).find('#myModalCloseform').first());
			$("#myModalBypassform").html($(html).find('#myModalBypassform').first());
			$("#myModalPoint").html($(html).find('#myModalPoint').first());
			$("#latlng1").html($(html).find('#latlng1').first());


			$("#tabmyModalBusy").html($(html).find('#tabmyModalBusy').html());
			$("#tabmyModalFree").html($(html).find('#tabmyModalFree').html());
			$("divRespFree").html($(html).find('#divRespFree').html());






		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});



}
/**Обрабатываем события при клике на таблицу во вкладке objectsignal */
function myclickSignal(idinfo, clickflag) {


	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');

	$.ajax({

		type: "post",
		dataType: 'html',
		beforeSend: function(xhr) {
			// here it is
			xhr.setRequestHeader(header, token);
		},
		data: {
			myclick: 1,//флаг что был нажат клик
			idinfo: idinfo//ид выбранной строки

		},

		url: url,


		success: function(html) {

			//обновляем значения блаrов обновления

			$("#flagUpdateClick").html(
				$(html).find('#flagUpdateClick').html());

			//Присваиваем параметры выбранного объекта кнопкам на панели прием согналов


			$("#myModalResponseform").html($(html).find('#myModalResponseform').first());
			$("divRespFree").html($(html).find('#divRespFree').html());


			$("#myModalGroupArrivedform").html($(html).find('#myModalGroupArrivedform').first());

			$("#divmyModGrCancel").html($(html).find('#divmyModGrCancel').html());

			$("#myModalGroupCancelform").html($(html).find('#myModalGroupCancelform').html());

			$("#myModalAlarmCancelform").html($(html).find('#myModalAlarmCancelform'));

		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}


	});
	idinfo = document.getElementById("currentIdInfo").innerHTML;

	reloadSignal(idinfo, 1);





}
function myclickgroups(groups_id) {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');

	$.ajax({
		type: "post",
		dataType: 'html',
		beforeSend: function(xhr) {
			// here it is
			xhr.setRequestHeader(header, token);
		},
		data: {
			groups_id: groups_id,
			myclick: 1
		},

		url: url,

		cache: false,
		success: function(html) {

			$("#myModalSetGroupStatusForm").html($(html).find("#myModalSetGroupStatusForm").html());




		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});



}
function reloadArchive(alarm_id) {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');
	var date = $('#datefromArch').val();
	var date1 = $('#datetoArch').val();
	var keyword = $('#myInputMiddle').val();
	var statusnameid = $('#alarmstatusname').val();
	var sizeColumnArch = $('#sizeColumnArch').val();

	$.ajax({
		beforeSend: function(xhr) {
			// here it is
			xhr.setRequestHeader(header, token);
		},
		type: "POST",
		data: {

			date: date,
			date1: date1,
			keyword: keyword,
			statusnameid: statusnameid,
			sizeColumnArch: sizeColumnArch,
			alarm_id: alarm_id,
			myclick: 0

		},
		url: url,


		cache: false,
		success: function(html) {
			$("#objectarchiv").html($(html).find('#objectarchiv').html());
			$("#panelSignal").html($(html).find('#panelSignal').html());
			$("#panelStandFull").html($(html).find('#panelStandFull').html());
			$("#MusicAll").html($(html).find('#MusicAll').html());


		}
	});
}
function reloadCity(citySetName) {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');

	$.ajax({
		beforeSend: function(xhr) {
			// here it is
			xhr.setRequestHeader(header, token);
		},
		type: "POST",
		data: {
			citySetName: citySetName


		},
		url: url,


		cache: false,
		success: function(html) {
			$("#streetSet").html($(html).find('#streetSet'));


		}
	});
}

function reloadStreet(streetSetName) {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');


	$.ajax({
		beforeSend: function(xhr) {
			// here it is
			xhr.setRequestHeader(header, token);
		},
		type: "POST",
		data: {
			streetSetName: streetSetName


		},
		url: url,


		cache: false,
		success: function(html) {



		}
	});
}
function reloadRegion(regionSetName) {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');


	$.ajax({
		beforeSend: function(xhr) {
			// here it is
			xhr.setRequestHeader(header, token);
		},
		type: "POST",
		data: {
			regionSetName: regionSetName


		},
		url: url,


		cache: false,
		success: function(html) {
			//$("#streetTypeSet").html($(html).find('#streetTypeSet'));
			$("#citySet").html($(html).find('#citySet'));
			$("#streetSet").html($(html).find('#streetSet'));


		}
	});
}
//кнопка найти во вкладке события в левой панели
function foundEventIdInfo() {
	
	var url = $(location).attr('pathname');

	var idinfo;
	//находим ид выбранной строки
	if (url == '/SpringSecurityApp/objectsignal') {
		idinfo = document
			.getElementById("currentIdInfo").innerHTML;

		//обновляем вкладку
		reloadSignal(idinfo, 1);
	} else if (url == '/SpringSecurityApp/home') {
		var idinfo = document.getElementById("currentIdInfoAll").innerHTML;
		reloadObjectView(idinfo, 1);
	}

}
//Кнопка НАЙТИ на вкладке события. Котоая ищет по параметрам вводимые пользователем
function foundEvent() {

	var idinfo;

		var idinfo = document.getElementById("currentIdEvent").innerHTML;

		reloadEventView(idinfo, 1);
	

}
//Обновление объектов через указанный промежуток времени
/**
		  @param {idinfo} ид выбранной строки
		  @param {flagclick} флаг который указывает что метод вызван после клика по мышке

*/
function reloadObjectView(idinfo, flagClick) {
	var evrangid = "";

	var panelcurrent = document.getElementById("panelcurrent").innerHTML;//Название выбранной вкладки в правой панели окна

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');
	var keyword = $('#myInput').val(); //параметр поиска обїекта(Введите номер, название)

	var city_id = $('#city_id').val();
	var date = $('#datefrom').val();//Параметр поиска событий в правой части панели время с
	var date1 = $('#dateto').val();//Параметр поиска событий в правой части панели время по
	var sizeColumn = $('#sizeColumnAll').val(); //количество отображаемых объектов

	//Считываем значения флага обновления
	//1 обновляем 0-не обновляем. Расчитывается в контроллере =1 при клике на новую тревогу или если в таблице alarm были изменения


	var pMaineq = document.getElementById('flagUpdateMaineqAll');
	var textMaineq = pMaineq.textContent;
	var isUploadMaineq = Number(textMaineq);

	var pStand = document.getElementById('flagUpdateStandAll');
	var textStand = pStand.textContent;
	var isUploadStand = Number(textStand);

	var pEventlist = document.getElementById('flagUpdateEventlistAll');
	var textEventlist = pEventlist.textContent;
	var isUploadEventlist = Number(textEventlist);

	$.ajax({
		beforeSend: function(xhr) {
			// here it is
			xhr.setRequestHeader(header, token);
		},
		type: "POST",

		url: url,
		data: {
			keyword: keyword,

			city_id: city_id,
			date: date,
			date1: date1,
			evrangidStr: evrangid,
			sizeColumn: sizeColumn,
			idinfo: idinfo,
			myclick: flagClick,
			panelcurrent: panelcurrent
		},

		success: function(html) {

			//обновляем значения блаков обновления

			$("#flagUpdateClickAll").html(
				$(html).find('#flagUpdateClickAll').html());
			$("#flagUpdateMaineqAll").html(
				$(html).find('#flagUpdateMaineqAll').html());
			$("#flagUpdateStandAll").html(
				$(html).find('#flagUpdateStandAll').html());
			$("#flagUpdateEventlistAll")
				.html(
					$(html)
						.find(
							'#flagUpdateEventlistAll')
						.html());
			//Основные таблицы обновляем только если были обновления смотрим по таблице update_last
			//		if (isUploadMaineq > 0 || flagClick == 1) {
			if (isUploadMaineq == 1||isUploadStand > 0)
	
					$("#t_bodyall").html(
					$(html).find('#t_bodyall').html());
				

			//Вкладки на правой панели обновляем только по клику на кнопке и только на текущей вкладке
			if (flagClick == 1) {
				if (panelcurrent == 'Kard') {
					$("#kard").html(
						$(html).find('#kard').html());
					//Обновляем внутренние вкладки
					$("#Note").html(
						$(html).find('#Note').html());
					$("#Additionally").html(
						$(html).find('#Additionally')
							.html());
					$("#Change").html(
						$(html).find('#Change').html());
					$("#EquipmentRent").html(
						$(html).find('#EquipmentRent')
							.html());
				}
				if (panelcurrent == 'Maineq')
					$("#maineq").html(
						$(html).find('#maineq').html());
				if (panelcurrent == 'Timetable')
					$("#timetable").html(
						$(html).find('#timetable').html());
				if (panelcurrent == 'Response')
					$("#response").html(
						$(html).find('#response').html());
				//вкладку шлейфы с правой панели обновляем только если открыта данная вкладка и были обновления в таблице maineq
				if (panelcurrent == 'Plume')
					$("#plume").html(
						$(html).find('#plume').html());
				if (panelcurrent == 'Key')
					$("#key").html($(html).find('#key').html());
				if (panelcurrent == 'ResponsPeop')
					$("#responspeop")
						.html(
							$(html)
								.find(
									'#responspeop')
								.html());
				if (panelcurrent == 'State')
					$("#state").html($(html).find('#state'));
				if (panelcurrent == 'Events') {
					$("#event").html(
						$(html).find('#event').html());
					$("#datefrom").html(
						$(html).find('#datefrom').html());

					$("#accauntSign")
						.html(
							$(html)
								.find(
									'#accauntSign')
								.html());

				}

				if (panelcurrent == 'Schema')
					$("#schema").html($(html).find('#schema'));
				if (panelcurrent == 'Photo')
					$("#photo").html(
						$(html).find('#photo').html());
				if (panelcurrent == 'Stand')
					$("#stand").html(
						$(html).find('#stand').html());

			}
			//вкладку шлейфы с правой панели обновляем только если открыта данная вкладка и были обновления в таблице maineq

			if (isUploadMaineq > 0) {
				$("#plume").html($(html).find('#plume').html());
				$("#maineq").html(
					$(html).find('#maineq').html());
			}
			if (isUploadEventlist > 0)
				$("#event").html($(html).find('#event').html());
			if (isUploadStand > 0) {
				$("#stand").html($(html).find('#stand'));
				$("#listobjectview1").html($(html).find('#listobjectview1'));
				
			}
			

			//При выборе объекта в таблице тревоги передаем выбранный объект все функции в меню   выполнить и команды
			if (flagClick == 1) {
				$("#myModalform").html(
					$(html).find('#myModalform'));
				$("#myModalStatusPoolform").html(
					$(html).find('#myModalStatusPoolform')
						.first());
				$("#myModalCloseform").html(
					$(html).find('#myModalCloseform')
						.first());
				$("#divMyAlarm").html(
					$(html).find('#divMyAlarm').first());

				$("#myModalBypassform").html(
					$(html).find('#myModalBypassform')
						.first());
				//	divBypass
				//	tabmyModBypass

				$("#myModalPoint").html(
					$(html).find('#myModalPoint').first());
				$("#latlng1").html(
					$(html).find('#latlng1').first());
			}

			//Обновляем музыку
			$("#MusicSig").html(
				$(html).find('#MusicSig').html());

			//update span (количество на кнопках панели)
			$("#panelSignal").html(
				$(html).find('#panelSignal').html());
			$("#panelStand").html(
				$(html).find('#panelStand').html());
			$("#panelStandFull").html(
				$(html).find('#panelStandFull').html());
			$("#panelNotTime").html(
				$(html).find('#panelNotTime').html());
			$("#panelDefect").html(
				$(html).find('#panelDefect').html());



		}
	})


};
function reloadObjectView2(idinfo, flag) {
	var evrangid = "";
	/*   $('#evrangid option').each(function(i) {
	
			   if (this.selected == true) {
					   evrangid=evrangid+","+(this.value);
			   }

	   });
*/


	var panelcurrent = document.getElementById("panelcurrent").innerHTML;
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');
	var keyword = $('#myInput').val();
	var keyword2 = $('#myInputMiddle').val();
	var city_id = $('#city_id').val();
	var date = $('#datefrom').val();
	var date1 = $('#dateto').val();
	

	var sizeColumn = $('#sizeColumn').val();

	$.ajax({
		beforeSend: function(xhr) {
			// here it is
			xhr.setRequestHeader(header, token);
		},
		type: "POST",

		url: url,
		data: {
			keyword: keyword,
			keyword2: keyword2,
			city_id: city_id,
			date: date,
			date1: date1,
			evrangidStr: evrangid,
			sizeColumn: sizeColumn,
			idinfo: idinfo,
			myclick: 0,
			panelcurrent: panelcurrent
		},

		success: function(html) {

			$("#eventtabFull").html($(html).find('#eventtabFull').html());

			if (flag > 0)
				$("#listobjectview1").html($(html).find('#listobjectview1').html());
			$("#malfunction").html($(html).find('#malfunction').html());
			$("#event").html($(html).find('#event').html());
			$("#standtab").html($(html).find('#standtab').html());
			$("#standtabFull").html($(html).find('#standtabFull').html());
			$("#plumetab").html($(html).find('#plumetab').html());
			$("#responspeop").html($(html).find('#responspeop').html());
			$("#kard").html($(html).find('#kard').html());
			$("#Note").html($(html).find('#Note').html());
			$("#Additionally").html($(html).find('#Additionally').html());
			$("#Change").html($(html).find('#Change').html());
			$("#EquipmentRent").html($(html).find('#EquipmentRent').html());
			$("#maineq").html($(html).find('#maineq').html());
			$("#response").html($(html).find('#response').html());
			$("#key").html($(html).find('#key').html());

			$("#state").html($(html).find('#state').html());
			$("#perifinfoBan").html($(html).find('#perifinfoBan').html());
			$("#perifinfoBanMod").html($(html).find('#perifinfoBanMod').html());
			$("#objectstatus").html($(html).find('#objectstatus').html());
			$("#nottime").html($(html).find('#nottime').html());
			$("#panelSignal").html($(html).find('#panelSignal').html());
			$("#panelStand").html($(html).find('#panelStand').html());
			$("#panelStandFull").html($(html).find('#panelStandFull').html());
			$("#panelNotTime").html($(html).find('#panelNotTime').html());
			$("#panelDefect").html($(html).find('#panelDefect').html());

			$("#timetable").html($(html).find('#timetable').html());
			$("#MusicAll").html($(html).find('#MusicAll').html());
			$("#MusicNot").html($(html).find('#MusicNot').html());
			$("#MusicMal").html($(html).find('#MusicMal').html());
			$("#MusicSta").html($(html).find('#MusicSta').html());
			$("#MusicStf").html($(html).find('#MusicStf').html());
			$("#employeeFIO").html($(html).find('#employeeFIO').html());





		}
	});


}
//Обновление объектов через указанный промежуток времени
/**
		  @param {idinfo} ид выбранной строки
		  @param {flagclick} флаг который указывает что метод вызван после клика по мышке

*/
function reloadEventView(idEvent, flagClick) {

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');
	var keyword = $('#myInput').val();
	var keyword2 = $('#myInputMiddle').val();
	var date = $('#datefrom').val();
	var date1 = $('#dateto').val();
	var evrangid = $('#evrangid').serialize();
	var sizeColumn = $('#sizeColumn').val();

	var evrangidStr = "";
	
		//Считываем значения флага обновления
	//1 обновляем 0-не обновляем. Расчитывается в контроллере =1 при клике на новую тревогу или если в таблице alarm были изменения

	var pEventlist = document.getElementById('flagUpdateEventlistEvent');
	var textEventlist = pEventlist.textContent;
	var isUploadEventlist = Number(textEventlist);



	$('input[name="brands[]"]:checked').each(function() {

		evrangidStr = evrangidStr + "," + (this.value);
	});

	$.ajax({
		beforeSend: function(xhr) {
			// here it is
			xhr.setRequestHeader(header, token);
		},
		type: "POST",

		url: url,
		data: {
			keyword: keyword,
			keyword2: keyword2,
			date: date,
			date1: date1,
			evrangid: evrangid,
			idEvent: idEvent,
			evrangidStr: evrangidStr,
			sizeColumn: sizeColumn,

			myclick: flagClick

		},
		stateSave: true,
		cache: false,
		success: function(html) {
						//обновляем значения блаков обновления

			$("#flagUpdateClickEvent").html(
				$(html).find('#flagUpdateClickEvent').html());
			$("#flagUpdateEventlistEvent").html(
				$(html).find('#flagUpdateEventlistEvent').html());
			
				//Основные таблицы обновляем только если были обновления смотрим по таблице update_last
		if (isUploadEventlist == 1||flagClick==1)
	
					$("#t_bodyEvf").html(
					$(html).find('#t_bodyEvf').html());
					
					//При выборе объекта в таблице тревоги передаем выбранный объект все функции в меню   выполнить и команды
			if (flagClick == 1) {
				$("#myModalform").html(
					$(html).find('#myModalform'));
				$("#myModalStatusPoolform").html(
					$(html).find('#myModalStatusPoolform')
						.first());
				$("#myModalCloseform").html(
					$(html).find('#myModalCloseform')
						.first());
				$("#divMyAlarm").html(
					$(html).find('#divMyAlarm').first());

				$("#myModalBypassform").html(
					$(html).find('#myModalBypassform')
						.first());
				//	divBypass
				//	tabmyModBypass

				$("#myModalPoint").html(
					$(html).find('#myModalPoint').first());
				$("#latlng1").html(
					$(html).find('#latlng1').first());
			}
			
					//update span (количество на кнопках панели)
			$("#panelSignal").html(
				$(html).find('#panelSignal').html());
			$("#panelStand").html(
				$(html).find('#panelStand').html());
			$("#panelStandFull").html(
				$(html).find('#panelStandFull').html());
			$("#panelNotTime").html(
				$(html).find('#panelNotTime').html());
			$("#panelDefect").html(
				$(html).find('#panelDefect').html());


		}
	});

	


}
//Функция очистить на вкладке события в правой панели. Для сбрасывания вводимых пользователем данных
function clianEventlist() {
	document.getElementById('datefrom').value = null;
	document.getElementById('dateto').value = null;
	document.getElementById('sizeColumn').value = '50';
	//Обновляем данные до вывода данных для поиска по умолчанию
	foundEventIdInfo();

}


