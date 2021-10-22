
function myclick(idinfo) {

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var url = $(location).attr('pathname');
	var sizeColumn = $('#sizeColumn').val();

	$.ajax({
		type: "post",
		dataType: 'html',
		beforeSend: function(xhr) {
			// here it is
			xhr.setRequestHeader(header, token);
		},
		data: {
			idinfo: idinfo,
			sizeColumn: sizeColumn,
			myclick: 1
		},

		url: url,

		cache: false,
		success: function(html) {
			
			$("#flagUpdateClickAll").html(
				$(html).find('#flagUpdateClickAll').html());
			$("#currentIdInfoAll").html(
				$(html).find('#currentIdInfoAll').html());

			$("#kard").html($(html).find('#kard'));
			$("#Note").html($(html).find('#Note'));
			$("#Additionally").html($(html).find('#Additionally'));
			$("#Change").html($(html).find('#Change'));
			$("#EquipmentRent").html($(html).find('#EquipmentRent'));
			$("#maineq").html($(html).find('#maineq'));
			$("#timetable").html($(html).find('#timetable'));
			$("#response").html($(html).find('#response'));
			$("#plume").html($(html).find('#plume'));
			$("#key").html($(html).find('#key'));
			$("#responspeop").html($(html).find('#responspeop'));
			$("#state").html($(html).find('#state'));
			$("#events").html($(html).find('#events'));
			$("#stand").html($(html).find('#stand'));
			$("#schema").html($(html).find('#schema'));


			$("#tabmyModalFree").html($(html).find('#tabmyModalFree'));
			$("divRespFree").html($(html).find('#divRespFree'));

			$("#divStatusPool").html($(html).find('#divStatusPool'));
			$("#listobjectviewSet").html($(html).find('#listobjectviewSet'));
			$("#t_bodysetting").html($(html).find('#t_bodysetting'));

		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});
	idinfo = document.getElementById("currentIdInfoAll").innerHTML;

	reloadObjectView(idinfo, 1);
}

function myclick2(idinfo) {

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
			idinfo: idinfo,
			myclick: 1
		},

		url: url,

		cache: false,
		success: function(html) {
			
			$("#flagUpdateClickAll").html(
				$(html).find('#flagUpdateClickAll').html());
			$("#currentIdInfoAll").html(
				$(html).find('#currentIdInfoAll').html());

			$("#kard").html($(html).find('#kard'));
			$("#Note").html($(html).find('#Note'));
			$("#Additionally").html($(html).find('#Additionally'));
			$("#Change").html($(html).find('#Change'));
			$("#EquipmentRent").html($(html).find('#EquipmentRent'));
			$("#maineq").html($(html).find('#maineq'));
			$("#timetable").html($(html).find('#timetable'));
			$("#response").html($(html).find('#response'));
			$("#plume").html($(html).find('#plume'));
			$("#key").html($(html).find('#key'));
			$("#responspeop").html($(html).find('#responspeop'));
			$("#state").html($(html).find('#state'));
			$("#events").html($(html).find('#events'));
			$("#stand").html($(html).find('#stand'));
			$("#schema").html($(html).find('#schema'));


			$("#tabmyModalFree").html($(html).find('#tabmyModalFree'));
			$("divRespFree").html($(html).find('#divRespFree'));

			$("#divStatusPool").html($(html).find('#divStatusPool'));
			$("#listobjectviewSet").html($(html).find('#listobjectviewSet'));
			$("#t_bodysetting").html($(html).find('#t_bodysetting'));

		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
		}
	});
	idinfo = document.getElementById("currentIdInfoAll").innerHTML;

	reloadObjectView(idinfo, 1);
}


