package net.proselyte.springsecurityapp.controller;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.proselyte.springsecurityapp.calculation.JsonConvert.Convert;
import net.proselyte.springsecurityapp.calculation.MyException.AddDeleteException;
import net.proselyte.springsecurityapp.calculation.MyException.AddGroupCardException;
import net.proselyte.springsecurityapp.calculation.MyException.AddKardException;
import net.proselyte.springsecurityapp.calculation.MyException.AddListResponsException;
import net.proselyte.springsecurityapp.calculation.MyException.CreateMaineqException;
import net.proselyte.springsecurityapp.calculation.MyException.CreateNewResponPeopException;
import net.proselyte.springsecurityapp.calculation.function.Schema;
import net.proselyte.springsecurityapp.model.Card;
import net.proselyte.springsecurityapp.model.CardExchange;
import net.proselyte.springsecurityapp.model.CardNotation;
import net.proselyte.springsecurityapp.model.Employees;
import net.proselyte.springsecurityapp.model.FileColumn;
import net.proselyte.springsecurityapp.model.Groups;
import net.proselyte.springsecurityapp.model.GroupsMaineq;
import net.proselyte.springsecurityapp.model.LogFile;
import net.proselyte.springsecurityapp.model.Maineq;
import net.proselyte.springsecurityapp.model.ObjectView;
import net.proselyte.springsecurityapp.model.ObjectView2;
import net.proselyte.springsecurityapp.model.Perifinfo;
import net.proselyte.springsecurityapp.model.PerifinfoResponsible;
import net.proselyte.springsecurityapp.service.CardExchangeService;
import net.proselyte.springsecurityapp.service.CardNotationService;
import net.proselyte.springsecurityapp.service.CardService;
import net.proselyte.springsecurityapp.service.EmployeesService;
import net.proselyte.springsecurityapp.service.GroupsMaineqService;
import net.proselyte.springsecurityapp.service.GroupsService;
import net.proselyte.springsecurityapp.service.LogFileService;
import net.proselyte.springsecurityapp.service.MaineqService;
import net.proselyte.springsecurityapp.service.ObjectView2Service;
import net.proselyte.springsecurityapp.service.ObjectViewService;
import net.proselyte.springsecurityapp.service.PerifinfoResponsibleService;
import net.proselyte.springsecurityapp.service.PerifinfoResponslistService;
import net.proselyte.springsecurityapp.service.PerifinfoService;
import net.proselyte.springsecurityapp.var.Position;
import net.sf.json.JSONObject;

@Controller
public class ObjectSettingController {

	long MyidinfoStand = 0;
	Long MycitySetId = 0L;

	Long MystreetSetId = 0L;

	@Autowired
	private ObjectViewService objectViewService;

	@Autowired
	private ObjectView2Service objectView2Service;

	@Autowired
	private PerifinfoResponslistService perifinfoResponslistService;

	@Autowired
	private EmployeesService employeesService;

	@Autowired
	private GroupsMaineqService groupsMaineqService;

	@Autowired
	private CardService cardService;

	@Autowired
	private CardNotationService cardNotationService;

	@Autowired
	private CardExchangeService cardExchangeService;

	@Autowired
	private PerifinfoResponsibleService perifinfoResponsibleService;

	@Autowired
	private GroupsService groupsService;

	@Autowired
	private PerifinfoService perifinfoService;

	@Autowired
	private MaineqService maineqService;

	private String myKeyword;
	List<ObjectView> listObjectViewOchStatus;
	@Autowired
	private LogFileService logFileService;

	public void saveException(String message) {
		LogFile logfile = new LogFile(message);
		logFileService.save(logfile);
	}

	@RequestMapping(value = "/objectsetting", method = RequestMethod.GET)

	public ModelAndView home(@RequestParam(required = false) String accountId,
			@RequestParam(required = false) String username, @RequestParam(required = false) Integer alarm_id,
			@RequestParam(required = false) String nameFunc, @RequestParam(required = false) Long idinfo,
			@RequestParam(required = false) String engineer, @RequestParam(required = false) Long city_id) {
		ModelAndView mav = new ModelAndView("objectsetting");
		List<Groups> allgroups = groupsService.findAllGroup();
		mav.addObject("allgroups", allgroups);

		return mav;

	}

	ModelAndView modelAndView = new ModelAndView("objectsetting");

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ModelAndView handleDataIntegrityViolation(DataIntegrityViolationException exception) {

		modelAndView.addObject("error", "Ошибка записи данных в БД");
		return modelAndView;
	}

	@RequestMapping(value = "/objectsetting", method = RequestMethod.POST, produces = "application/json")

	public @ResponseBody ModelAndView home(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) String kard, String json,
			@RequestParam(required = false, name = "account") String account,
			@RequestParam(required = false) Long idinfo, @RequestParam(required = false) Long city_id,
			@RequestParam(required = false) String citySetName, @RequestParam(required = false) String panelcurrent,
			@RequestParam(required = false) String streetSetName, @RequestParam(required = false) Long citySetId,
			@RequestParam(required = false) String regionSetName) throws AddListResponsException {
		ModelAndView mav = new ModelAndView("objectsetting");
		Long streetSetId = 0l;

		Maineq maineqInfo = null;
		String messageSuccess = "Запрос отправлен.";
		String error = "";
		String nameFunc = "";
		Long card_idSet = null;// Ид карты получим после того как вставим строку в БД при заполнении kardSet
		Long ideqval = null;

		System.out.println("---------------------card------------------------" + panelcurrent);

		List<Groups> allgroups = groupsService.findAllGroup();

		System.out.println("-------------------------allgroups" + allgroups);
		mav.addObject("allgroups", allgroups);
		// Выбираем список всех групп реагирования

		int LevelPanel = 0;/// Номер шака в панеле. Если 1 шаг(карточка)то выполняем 1 шаг(Карта).Если шаг
							/// 2 то выполняем 1 и 2 шаг
		Long idinfoCard = null;
		if (json != null) {
			JSONObject jsonObject = JSONObject.fromObject(json);

			nameFunc = jsonObject.get("nameFunc").toString();
			switch (nameFunc) {
			case "kardSet":
				LevelPanel = 1;
				break;
			case "addGroupSet":
				LevelPanel = 4;
				break;

			case "addResponsPeopSet":

				LevelPanel = 7;
				break;

			}
			// Настройки ППК
			if (nameFunc.equals("createMaineq")) {

				try {

					Maineq maineqNew = maineqService.createMaineq(jsonObject);

					mav.addObject("messageSuccess", messageSuccess);

				} catch (CreateMaineqException e) {
					error = e.toString();
					System.out.println("--------------Error " + e);
					mav.addObject("error", error);

				} catch (Exception e) {
					error = "Ошибка настройки ППК. Обратитесь к разработчикам. " + e.toString();
					System.out.println("--------------Error " + e);
					mav.addObject("error", error);
					saveException(e.toString() + e.getLocalizedMessage());

				}

			} else if (nameFunc.equals("addPlumeSet") || nameFunc.equals("deletePlumeSet")) {
				try {
					// Добавляем/удаляем шлейф
					ideqval = 0l;
					String accountKardSet = Convert.JsonToString("accountKardSet", jsonObject);
					if (accountKardSet == null || accountKardSet.equals("")) {
						error = "Выберите объект";
						mav.addObject("error", error);

					} else {
						Maineq maineqKard = maineqService.findMaineqByAccount(accountKardSet);
						if (maineqKard != null) {
							ideqval = maineqKard.getIdeqval();
						}

						perifinfoService.addDeleteRerifinfo(jsonObject, ideqval, nameFunc, accountKardSet);
						panelcurrent = "PlumeSet";

					}
				} catch (AddDeleteException e) {
					error = e.toString();
					panelcurrent = "PlumeSet";// Обновляем таблицу шлейфов
					mav.addObject("error", error);

				} catch (Exception e) {
					error = "Ошибка настройки ППК. Обратитесь к разработчикам. " + e.toString();
					System.out.println("--------------Error " + e);
					mav.addObject("error", error);
					saveException(e.toString() + e.getLocalizedMessage());

				}
			} else if (nameFunc.equals("kardSet")) {
				try {

					// Заполняем доп инфо, замены и значения карточки
					Card cardAddSet = cardService.kardSet(jsonObject);
					CardNotation cardNotationSet = CardNotation.createCardNotation(jsonObject);
					CardExchange cardExchangeSet = CardExchange.createCardExchange(jsonObject);
					cardService.cardSaveFull(cardAddSet, cardNotationSet, cardExchangeSet);

				} catch (AddKardException e) {
					error = e.toString();

					mav.addObject("error", error);
				}

				catch (Exception e) {
					error = "Ошибка создания объекта . Обратитесь к разработчикам. " + e.toString();
					System.out.println("--------------Error " + e);

					mav.addObject("error", error);
					saveException(e.toString() + e.getLocalizedMessage());

				}

			} else if (nameFunc.equals("addGroupSet")) {
				try {

					Long maineqIdSet = cardService.getmaineqId(jsonObject);// получаем ид прибора
					ArrayList<GroupsMaineq> groupsMaineqList = new GroupsMaineq().getGroupList(jsonObject, maineqIdSet);// получаем
																														// список
																														// групп
					groupsMaineqService.saveList(groupsMaineqList);// сохраняем группы

				} catch (AddGroupCardException e) {
					error = e.toString();

					mav.addObject("error", error);
				}

			}

			if (nameFunc.equals("addGroupsTempSet")) {
				String idGroupSet = jsonObject.get("idGroupSet").toString();
				String accountKardSet = jsonObject.get("accountKardSet").toString().toUpperCase();
				Long idGroupSetLong = Long.parseLong(idGroupSet);
				ideqval = null;
				Maineq maineqKard = maineqService.findMaineqByAccount(accountKardSet);
				if (maineqKard != null) {
					ideqval = maineqKard.getIdeqval();

				}

			} else if (nameFunc.equals("addListResponsPeop")) {// Добавляем ответственное лицо в объект

				try {
//Подгружаем ответственное лицо выбранное из списка по ид
					String responsePeop = jsonObject.get("responsePeop").toString();
					String accountKardSet = jsonObject.get("accountKardSet").toString().toUpperCase();
					Long perifinfoResponsibleId = Long.parseLong(responsePeop);
//// находим ид perifinfo for one group in
					idinfoCard = perifinfoService.getIdinfoForCard(accountKardSet, 1l);
//Сохраняем ответственное лицо
					perifinfoResponslistService.savePerifinfoRespons(idinfoCard, perifinfoResponsibleId);

				} catch (DataIntegrityViolationException e) {
					mav.addObject("error", "Данное ответственное лицо уже приписано к данному объекту");

				} catch (AddListResponsException e) {
					error = e.toString();

					mav.addObject("error", error);
				}

			} else if (nameFunc.equals("createNewPesponPeop")) {

				try {
					String accountKardSet = jsonObject.get("accountKardSet").toString().toUpperCase();
					idinfoCard = perifinfoService.getIdinfoForCard(accountKardSet, 1l);
//Добавляем в справочник новое ответсвенное лицо
					PerifinfoResponsible perifinfoResponsibNew = perifinfoResponsibleService
							.saveResponsible(jsonObject);
					Long idRespNew = perifinfoResponsibNew.getPerifinfoResponsible_id();
					// Приписываем к объекту данное отв лицо
					perifinfoResponslistService.savePerifinfoRespons(idinfoCard, idRespNew);

					mav.addObject("messageModal", "Запрос отправлен.");

					if (idRespNew != null)
						mav.addObject("idNewPerson", idRespNew.toString());// Получаем ид нового объекта

				} catch (CreateNewResponPeopException e) {
					error = e.toString();

					mav.addObject("errorModal", error);
				} catch (AddListResponsException e) {
					error = e.toString();

					mav.addObject("errorModal", error);
				}

			}

		}
		if (account != null) {
			maineqInfo = maineqService.findMaineqByAccount(account);
			if (maineqInfo != null)
				System.out.println("---------------------ideqval" + maineqInfo.getIdeqval());
		}

		List<ObjectView2> listObjectView2 = null;
		Long idinfo_current = idinfo;
		if (idinfo != null && idinfo < 0)
			idinfo = -idinfo;
		if (idinfo == null)
			idinfo = 0l;

		if (keyword != null) {

			myKeyword = new String(keyword.getBytes(UTF_8));
		}

		if ((myKeyword != null && myKeyword != "") && city_id != null && city_id != -1) {
			myKeyword = '%' + myKeyword + '%';

			if (idinfo == null)
				idinfo = 0l;
			listObjectView2 = objectView2Service.findObject(myKeyword, city_id, idinfo);

			System.out.println("------1 " + myKeyword + " " + city_id);
		} else if ((myKeyword == null || myKeyword == "") && city_id != null && city_id != -1) {

			listObjectView2 = objectView2Service.getAll(city_id);
			System.out.println("2");
		} else if ((myKeyword != null && myKeyword != "") && (city_id == null || city_id == -1)) {

			myKeyword = '%' + myKeyword + '%';

			System.out.println(myKeyword);
			if (idinfo == null)
				idinfo = 0l;
			listObjectView2 = objectView2Service.findObject2(myKeyword, idinfo);

			System.out.println("3 " + listObjectView2.size());
		} else {
			System.out.println("4");

			listObjectView2 = objectView2Service.findAll(idinfo);
			System.out.println("4");
		}

		List<Employees> listPosition = employeesService.findPosition();

		Stream<Employees> listManagerStrim = listPosition.stream()
				.filter((a) -> a.getPosition_id() == Position.manager || a.getPosition_id() == -1).map((a) -> a);

		List<Employees> listManager = listManagerStrim.collect(Collectors.toList());

		mav.addObject("listManager", listManager);

		List<PerifinfoResponsible> perifinfoResponsible = perifinfoResponsibleService.findAll();
		mav.addObject("perifinfoResponsible", perifinfoResponsible);
		/** Список выпадающих списков */
		if (panelcurrent != null) {

			switch (panelcurrent) {

			case "PlumeSet":
				System.out.println("-----------------PlumeSet------------------");
				JSONObject jsonObject = JSONObject.fromObject(json);
				String accountKardSet;
				if (json != null && jsonObject.get("accountKardSet") != null) {
					accountKardSet = jsonObject.get("accountKardSet").toString().toUpperCase();

					List<Perifinfo> perifinfoSensor = perifinfoService.getParamnum(accountKardSet, 1);

					mav.addObject("perifinfoSensor", perifinfoSensor);
				}
				break;

			case "KardSet":

				Card listCard = cardService.findObject(idinfo);
				mav.addObject("listCard", listCard);

				List<CardNotation> listCardNotation = cardNotationService.findObject(idinfo);
				mav.addObject("listCardNotation", listCardNotation);

				List<CardExchange> listCardExchange = cardExchangeService.findObject(idinfo);
				mav.addObject("listCardExchange", listCardExchange);
				break;

			case "Timetable":
				Perifinfo perifinfo = perifinfoService.findObject(idinfo);
				mav.addObject("perifinfo", perifinfo);
				break;

			case "Response":
				List<Groups> groups = groupsService.findObject(idinfo);
				mav.addObject("groups", groups);
				break;

			case "Key":

				List<Perifinfo> perifinfoKey = perifinfoService.findKey(idinfo);
				mav.addObject("perifinfoKey", perifinfoKey);
				break;
			case "ResponsPeopSet":
				// Обновляем таблицу
				jsonObject = JSONObject.fromObject(json);
				if (jsonObject != null) {
					accountKardSet = jsonObject.get("accountKardSet").toString().toUpperCase();
					List<PerifinfoResponsible> perifinfoResponsibleList = perifinfoResponsibleService
							.getListForAccount(accountKardSet, 1L);

					mav.addObject("perifinfoResponsibleList", perifinfoResponsibleList);
					System.out.println("---------------reloadResponsPeopSet---------------------"
							+ perifinfoResponsibleList.size());
				}
				break;

			case "Schema":
				ObjectView accountSchema = objectViewService.findId(idinfo);
				String strAccountSchema = accountSchema.getAccountID();
				List<FileColumn> schemaList = Schema.getFileMaineq(strAccountSchema);

				mav.addObject("schemaList", schemaList);
				break;
			}
			List<Groups> groupsFree = groupsService.findObjectActive(idinfo);
			mav.addObject("groupsFree", groupsFree);

			List<Groups> groupsBusy = groupsService.findObjectNotActive(idinfo);
			mav.addObject("groupsBusy", groupsBusy);

		}
		if (listObjectView2 != null)
			for (

			ObjectView2 i : listObjectView2) {
				i.setCurrentIdinfo(idinfo_current);
			}

		mav.addObject("listObjectView2", listObjectView2);

		mav.addObject("error", error);

		return mav;
	}

}
