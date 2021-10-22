//Очищаем значение всех вводимых значений пользователем кроме кнопок и hidden
//передаем ид формы
function cleanForm(formId) {


	var myForm = document.getElementById(formId);
	//Extract Each Element Value
	for (var i = 0; i < myForm.elements.length; i++) {
		if (myForm.elements[i].type != 'hidden'
			&& myForm.elements[i].type != 'button')
			myForm.elements[i].value = '';

	}

}
//Очищаем все значения 
function cleanDivForInput(DivId){
	$(DivId).find('input').each(function() {
		$(this).val('');
	});

} 
//Очищаем значения по ид
function cleanId(id){
	
}