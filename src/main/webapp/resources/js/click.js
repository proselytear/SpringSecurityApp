function myclickStand(value) {
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
			idinfoStand: value,
			idEvent: value,
		myclick: 1
			
		},

		url: url,

		cache: false,
		success: function(html) {
//Обновляем модальные окна
			$("#myModalbody").html($(html).find('#myModalbody').first());
			$("#myModalResponseform").html($(html).find('#myModalResponseform').first());
			$("#myModalResponseform2").html($(html).find('#myModalResponseform2').first());
			$("#myModalResponseNotAlarmform").html($(html).find('#myModalResponseNotAlarmform').first());
			$("#myModalStatusPoolform").html($(html).find('#myModalStatusPoolform').first());
			$("#myModalTechnicAlarmform").html($(html).find('#myModalTechnicAlarmform').first());

			$("#divmyModalTechnic").html($(html).find('#divmyModalTechnic').first());
			$("#myModalAlarmform").html($(html).find('#myModalAlarmform').first());
			$("#myModalCloseform").html($(html).find('#myModalCloseform').first());
			$("#myModalBypassform").html($(html).find('#myModalBypassform').first());
			$("#myModalPoint").html($(html).find('#myModalPoint').first());
			$("#latlng1").html($(html).find('#latlng1').first());


			$("#tabmyModalBusy").html($(html).find('#tabmyModalBusy').html());
			$("#tabmyModalFree").html($(html).find('#tabmyModalFree').html());
			$("#divRespFree").html($(html).find('#divRespFree').html());
		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError + "\r\n" + xhr.statusText + "\r\n"
				+ xhr.responseText);
		}
	});

}
function myclickArch(value) {
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
			alarm_id: value
		},

		url: url,

		cache: false,
		success: function(html) {

			$("#myModalbody").html($(html).find('#myModalbody').first());
			$("#myModalResponseform").html($(html).find('#myModalResponseform').first());
			$("#myModalResponseNotAlarmform").html($(html).find('#myModalResponseNotAlarmform').first());
			$("#myModalResponseform2").html($(html).find('#myModalResponseform2').first());
			$("#myModalStatusPoolform").html($(html).find('#myModalStatusPoolform').first());
			$("#myModalTechnicAlarmform").html($(html).find('#myModalTechnicAlarmform').first());

			$("#divmyModalTechnic").html($(html).find('#divmyModalTechnic').first());
			$("#myModalAlarmform").html($(html).find('#myModalAlarmform').first());
			$("#myModalCloseform").html($(html).find('#myModalCloseform').first());
			$("#myModalBypassform").html($(html).find('#myModalBypassform').first());
			$("#myModalPoint").html($(html).find('#myModalPoint').first());
			$("#latlng1").html($(html).find('#latlng1').first());


			$("#tabmyModalBusy").html($(html).find('#tabmyModalBusy').html());
			$("#tabmyModalFree").html($(html).find('#tabmyModalFree').html());
			$("#divRespFree").html($(html).find('#divRespFree').html());
		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError + "\r\n" + xhr.statusText + "\r\n"
				+ xhr.responseText);
		}
	});

}