var radio = new Audio();
	radio.src = "resources/music/Amelie.mp3";
	var res = "";
//Проигрывание музыки
	function Cheshka(a) {

		res = document.getElementById(a).innerHTML;



		if (res == "0" || res == "") {
		//если количество сигналов==0 приостановить музыку	
			radio.pause();
			$("#panelSignal").hide();
		} else {
			//если количество сигналов>0 играть музыку
			radio.play();
			$("#panelSignal").show();
		}

	}