package net.proselyte.springsecurityapp.controllerFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import net.proselyte.springsecurityapp.model.Card;
import net.proselyte.springsecurityapp.service.CardService;
import net.proselyte.springsecurityapp.service.MaineqService;
import net.sf.json.JSONObject;

@Controller
public class PCSetting {
	@Autowired
	private CardService cardService;

	/**
	 * 
	 * @param jsonObject--номер объекта(обязательное поле), две симкарты, координаты
	 *                          объекта
	 * @param maineqService
	 * @return
	 */
	public ModelAndView createMaineq1(JSONObject jsonObject, MaineqService maineqService) {
		ModelAndView mav = new ModelAndView("welcome");
		ModelAndView mav2 = new ModelAndView("welcome");
		Card listCard = cardService.findObject(507);
		mav.addObject("listCard", listCard);
		mav.getModel();
		mav.getView();
		return mav;

	}

}
