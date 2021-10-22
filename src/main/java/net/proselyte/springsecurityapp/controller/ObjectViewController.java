package net.proselyte.springsecurityapp.controller;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import static net.proselyte.springsecurityapp.var.StaticVarDefault.dateMax;
import static net.proselyte.springsecurityapp.var.StaticVarDefault.dateMin;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
/**
 * Controller for {@link net.proselyte.springsecurityapp.model.User}'s pages.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import net.proselyte.springsecurityapp.calculation.MyTime;
import net.proselyte.springsecurityapp.calculation.MyException.NotAddStandException;
import net.proselyte.springsecurityapp.calculation.MyException.NotFoundDBException;
import net.proselyte.springsecurityapp.calculation.MyException.NotInputGroupException;
import net.proselyte.springsecurityapp.calculation.MyException.NotInputParamnumException;
import net.proselyte.springsecurityapp.calculation.MyException.NotRemoveStandException;
import net.proselyte.springsecurityapp.calculation.function.Schema;
import net.proselyte.springsecurityapp.model.AlarmNew;
import net.proselyte.springsecurityapp.model.Card;
import net.proselyte.springsecurityapp.model.CardExchange;
import net.proselyte.springsecurityapp.model.CardNotation;
import net.proselyte.springsecurityapp.model.City;
import net.proselyte.springsecurityapp.model.Defect;
import net.proselyte.springsecurityapp.model.District;
import net.proselyte.springsecurityapp.model.Employees;
import net.proselyte.springsecurityapp.model.EventList;
import net.proselyte.springsecurityapp.model.EventListV;
import net.proselyte.springsecurityapp.model.FileColumn;
import net.proselyte.springsecurityapp.model.Groups;
import net.proselyte.springsecurityapp.model.LogFile;
import net.proselyte.springsecurityapp.model.Maineq;
import net.proselyte.springsecurityapp.model.ObjectView;
import net.proselyte.springsecurityapp.model.ObjectView2;
import net.proselyte.springsecurityapp.model.Perifinfo;
import net.proselyte.springsecurityapp.model.PerifinfoResponsible;
import net.proselyte.springsecurityapp.model.TypeName;
import net.proselyte.springsecurityapp.model.UpdateLast;
import net.proselyte.springsecurityapp.model.User;
import net.proselyte.springsecurityapp.service.AlarmNewService;
import net.proselyte.springsecurityapp.service.CardExchangeService;
import net.proselyte.springsecurityapp.service.CardNotationService;
import net.proselyte.springsecurityapp.service.CardService;
import net.proselyte.springsecurityapp.service.CityService;
import net.proselyte.springsecurityapp.service.DistrictService;
import net.proselyte.springsecurityapp.service.EmployeesService;
import net.proselyte.springsecurityapp.service.EventListService;
import net.proselyte.springsecurityapp.service.EventListVService;
import net.proselyte.springsecurityapp.service.GroupsService;
import net.proselyte.springsecurityapp.service.LogFileService;
import net.proselyte.springsecurityapp.service.MaineqService;
import net.proselyte.springsecurityapp.service.ObjectView2Service;
import net.proselyte.springsecurityapp.service.ObjectViewService;
import net.proselyte.springsecurityapp.service.PerifinfoResponsibleService;
import net.proselyte.springsecurityapp.service.PerifinfoService;
import net.proselyte.springsecurityapp.service.StreetService;
import net.proselyte.springsecurityapp.service.TypeNameService;
import net.proselyte.springsecurityapp.service.UpdateLastService;
import net.proselyte.springsecurityapp.service.UserService;
import net.proselyte.springsecurityapp.var.Command;
import net.proselyte.springsecurityapp.var.Position;
import net.proselyte.springsecurityapp.var.StaticVarStatus;

class NotInputAccountIdException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "Введите номер объекта.";
	}
}

@Controller
public class ObjectViewController {

	long MyidinfoStand = 0;
	Long MycitySetId = 0L;
	Long MystreetTypeSetId = 0L;
	Long MystreetSetId = 0L;

	@Autowired
	private ObjectViewService objectViewService;

	@Autowired
	private ObjectView2Service objectView2Service;

	@Autowired
	private AlarmNewService alarmnewService;

	@Autowired
	private EmployeesService employeesService;

	@Autowired
	private TypeNameService typeNameService;

	@Autowired
	private EventListService eventListService;

	@Autowired
	private StreetService streetService;

	@Autowired
	private UserService userService;

	@Autowired
	private DistrictService districtService;

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
	private CityService cityService;

	@Autowired
	private MaineqService maineqService;

	@Autowired
	private EventListVService eventlistVService;

	@Autowired
	private UpdateLastService updateLastService;

	private List<City> listCity;
	private String myKeyword;
	List<ObjectView> listObjectViewOchStatus;
	@Autowired
	private LogFileService logFileService;

	HashMap<String, Long> timeUpdatePreviousMaineqSession = new HashMap<>(); // Предыдущее время обновления maineq для
																				// данной сессии
	HashMap<String, Long> timeUpdatePreviousStandSession = new HashMap<>(); // Предыдущее время обновления стендов
																			// perifinfo для данной сессии
	HashMap<String, Long> timeUpdatePreviousEventlistSession = new HashMap<>();// Предыдущее время обновления стендов
																				// perifinfo для данной сессии
	HashMap<String, String> keyWordSession = new HashMap<>();// Парамет вводимый пользователем данной сессией для
																// пользователя. Необходим чтобы каждый пользователь
																// получал свои результаты поиска
	// perifinfo для данной сессии
	int flagUpdateSignal; // 1 обновляем 0-не обновляем. =1 при клике на новую тревогу или если в таблице

	public void saveException(String message) {
		LogFile logfile = new LogFile(message);
		logFileService.save(logfile);
	}

	List<Employees> listTechnic = new ArrayList<Employees>();// Список техников для постановки объекта в стенд

	@RequestMapping(value = "/home", method = RequestMethod.GET)

	public ModelAndView home(@RequestParam(required = false) String accountId,
			@RequestParam(required = false) String username, @RequestParam(required = false) Integer alarm_id,
			@RequestParam(required = false) String nameFunc, @RequestParam(required = false) Long idinfo,
			@RequestParam(required = false) Long city_id) {

		ModelAndView mav = new ModelAndView("welcome");

		String employeeFIO = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(userName);
		if (user == null) {
			mav = new ModelAndView("login");
			return mav;
		}

		Employees employees = user.getEmployees();
		if (employees != null)
			employeeFIO = employees.getFullName();
		employeeFIO = (null != employeeFIO) ? employeeFIO : userName;
		mav.addObject("employeeFIO", employeeFIO);

		// Size begin
		List<AlarmNew> alarmnew = alarmnewService.findAlarmNew();

		Integer alarmfullSize = 0;

		for (AlarmNew i : alarmnew) {
			if (i.getCount_alarm_new() != null)
				alarmfullSize += i.getCount_alarm_new().intValue();
		}
		mav.addObject("alarmfullSize", alarmfullSize);// прием сигналоы

		List<ObjectView> listObjectView;
		if (idinfo == null)
			idinfo = 0l;
		if (city_id == null || city_id == -1) {

			listObjectView = objectViewService.findAll();
		} else
			listObjectView = objectViewService.getAll(city_id);

		mav.addObject("listObjectView", listObjectView);

		Stream<ObjectView> listObjectViewStrim = listObjectView.stream()
				.filter((a) -> a.getStatusy().equals(StaticVarStatus.statusGroupOchrana)).map((a) -> a);

		List<ObjectView> listObjectViewOch = listObjectViewStrim.collect(Collectors.toList());

		listObjectViewOchStatus = listObjectViewOch;

		mav.addObject("listObjectViewOch", listObjectViewOch);

		listCity = cityService.getAll();

		List<Employees> listPosition = employeesService.findPosition();

		Stream<Employees> listPositionStrim = listPosition.stream()
				.filter((a) -> a.getPosition_id() == Position.technician || a.getPosition_id() == -1l).map((a) -> a);

		List<Employees> listTechnic = listPositionStrim.collect(Collectors.toList());

		Stream<Employees> listMasterStrim = listPosition.stream()
				.filter((a) -> a.getPosition_id() == Position.master || a.getPosition_id() == -1).map((a) -> a);

		List<Employees> listMaster = listMasterStrim.collect(Collectors.toList());

		Stream<Employees> listElectricianrStrim = listPosition.stream()
				.filter((a) -> a.getPosition_id() == Position.electrician || a.getPosition_id() == -1).map((a) -> a);

		List<Employees> listElectrician = listElectricianrStrim.collect(Collectors.toList());

		List<District> listDistrict = districtService.findAll();

		mav.addObject("listTechnic", listTechnic);
		mav.addObject("listMaster", listMaster);
		mav.addObject("listElectrician", listElectrician);
		mav.addObject("listDistrict", listDistrict);

		List<String> city = new ArrayList<String>();
		for (City c : listCity) {
			city.add(c.nameUa);
		}

		List<TypeName> listTypeName = typeNameService.findAll();
		Stream<TypeName> listTypeNameStrim = listTypeName.stream().filter((a) -> a.getTableName().equals("street"))
				.map((a) -> a);

		List<TypeName> listStreetType = listTypeNameStrim.collect(Collectors.toList());

		mav.addObject("listStreetType", listStreetType);

		mav.addObject("city", city);
		mav.addObject("listCity", listCity);

		// panelright
		List<Perifinfo> perifinfoBan1 = perifinfoService.findBan();
		mav.addObject("perifinfoBan", perifinfoBan1);
		if (idinfo != null) {

			Card listCard = cardService.findObject(idinfo);
			mav.addObject("listCard", listCard);
			System.out.println("yy");

		}

		System.out.println("--------------------homeGET--------------------" + idinfo);

		return mav;
	}

	public String welcome(Model model) {

		return "redirect:/home";
	}

	/**
	 * 
	 * @param accountId             Номер Объекта
	 * @param grouprel              номер группы
	 * @param paramnum              номер зоны
	 * @param idTechnician          ид техника
	 * @param nameFunc              название вызываемой функции
	 * @param appt_time             время постановки объекта в стенд
	 * @param alarm_id              ид тревоги
	 * @param idinfo                ид выбранной группы на объекте
	 * @param idinfoStand           ид выбраной группы для постановки в стенд
	 * @param groups_id
	 * @param id_alarm_cancel       ид причины отмены тревоги выбранной
	 *                              пользователем
	 * @param id_groups_status_name статус группы реагирования
	 * @param keyword               параметры поиска вводимые пользователем
	 * @param city_id
	 * @param date                  дата и время для поиска события С
	 * @param date1                 дата и время для поиска события ПО
	 * @param constTime             флаг постоянные стенды
	 * @param sizeColumn            количество выбранных строк
	 * @param myclick               флаг клика по строке таблицы( 1 -да , 0-нет)
	 * @param streetTypeSetId
	 * @param streetSetId
	 * @param citySetId
	 * @param panelcurrent          название вкладки открытой с правой панели
	 * @return
	 * @throws CloneNotSupportedException
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public @ResponseBody ModelAndView home(@RequestParam(required = false) String accountId,
			@RequestParam(required = false) Integer grouprel, @RequestParam(required = false) Integer paramnum,
			@RequestParam(required = false) Long idTechnician, @RequestParam(required = false) String nameFunc,
			@RequestParam(required = false) String appt_time, @RequestParam(required = false) Integer alarm_id,
			@RequestParam(required = false) Long idinfo, @RequestParam(required = false) Long idinfoStand,
			@RequestParam(required = false) Integer groups_id, @RequestParam(required = false) Integer id_alarm_cancel,
			@RequestParam(required = false) Long id_groups_status_name, @RequestParam(required = false) String keyword,
			@RequestParam(required = false) Long city_id, @RequestParam(required = false) String date,
			@RequestParam(required = false) String date1, @RequestParam(required = false) String constTime,
			@RequestParam(required = false) Integer sizeColumn, @RequestParam(required = false) Integer myclick,
			@RequestParam(required = false) Long streetTypeSetId, @RequestParam(required = false) Long streetSetId,
			@RequestParam(required = false) Long citySetId, @RequestParam(required = false) String panelcurrent)
			throws CloneNotSupportedException, UnsupportedEncodingException, ParseException {

		Long idinfo_current = idinfo;// ид выделенного объекта
		if (idinfo != null && idinfo < 0)
			idinfo = -idinfo;// Если в объекте есть несколько групп то в таблице отображется общий объект с
								// ид<0 но для расчетов нужна его действит ид

		if (idinfoStand != null && idinfoStand != 0)
			MyidinfoStand = idinfoStand;// ид для поставки объекта в стенд
		ModelAndView mav = new ModelAndView("welcome");

		// Получаеи ид текущей сессии. Необходим поскольку может работать несколько
		// пользователей одновременно
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		String session_id = attr.getSessionId();
		String messageSuccess = " ";// строка сообщений выводимых пользователю
		mav.addObject("messageSuccess", messageSuccess);
		String employeeFIO = null;
		// Получаем параметры залогиневавшегося пользователя.
		// Если пользователь не залогинен отправляем его на страницу логирования
		// Иначе отображанм ФИО или логин на странице
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(userName);

		keyWordSession.put(attr.getSessionId(), keyword);
		if (user == null) {
			mav = new ModelAndView("login");
			return mav;
		}
		Long userId = user.getId();
		Employees employees = user.getEmployees();
		if (employees != null)
			employeeFIO = employees.getFullName();
		employeeFIO = (null != employeeFIO) ? employeeFIO : userName;
		mav.addObject("employeeFIO", employeeFIO);

		// ----------------------------------Расчитываем span для кнопок----
		// Size begin
		List<AlarmNew> alarmnew = alarmnewService.findAlarmNew();

		Integer alarmfullSize = 0;

		for (AlarmNew i : alarmnew) {
			if (i.getCount_alarm_new() != null)
				alarmfullSize += i.getCount_alarm_new().intValue();
		}
		mav.addObject("alarmfullSize", alarmfullSize);// прием сигналоы

		List<ObjectView> listObjectViewSconn = objectViewService.findSconnection();
		Integer objectViewSconnSize = listObjectViewSconn.size();
		if (objectViewSconnSize > 0)
			mav.addObject("objectViewSconnSize", objectViewSconnSize);// нет вовремя теста

		ArrayList<Defect> alDefect = new ArrayList<Defect>();
		List<Perifinfo> perifinfoDefect = perifinfoService.findSensorFull();
		for (Perifinfo p : perifinfoDefect) {
			List<Defect> ld = p.getDefect();
			for (Defect d : ld) {
				alDefect.add(d);
			}

		}
		Integer alDefectSize = alDefect.size();
		if (alDefectSize > 0)
			mav.addObject("alDefectSize", alDefectSize);// неисправности

		List<Perifinfo> perifinfoBanFull = perifinfoService.findBanFull();
		Integer perifinfoBanFullSize = perifinfoBanFull.size();
		if (perifinfoBanFullSize > 0)
			mav.addObject("perifinfoBanFullSize", perifinfoBanFullSize);// стенды

		// Size end
		// ---------------------------------------------------------------------------------------------------------

		String error = "";

		String nameFuncUtf = "";
		if (nameFunc != null) {
			nameFuncUtf = new String(nameFunc.getBytes(ISO_8859_1), UTF_8);
		}
		if (keyword != null && keyWordSession != null) {

			myKeyword = keyWordSession.get(session_id);

		}
		List<ObjectView> listObjectView;
		List<ObjectView2> listObjectView2 = null;
//По заданным параметром поиска пользователем находим нудные объекты в основной таблицы
		if (((myKeyword != null && myKeyword != "") && city_id != null && city_id != -1)) {
			myKeyword = '%' + myKeyword + '%';

			if (idinfo == null)
				idinfo = 0l;
			listObjectView2 = objectView2Service.findObject(myKeyword, city_id, idinfo);
			listObjectView = objectViewService.findObject(myKeyword, city_id);

		} else if ((myKeyword == null || myKeyword == "") && city_id != null && city_id != -1) {

			listObjectView = objectViewService.getAll(city_id);
			listObjectView2 = objectView2Service.getAll(city_id);

		} else if (keyWordSession.get(session_id) != null && (city_id == null || city_id == -1)) {

			myKeyword = '%' + keyWordSession.get(session_id) + '%';

			if (idinfo == null)
				idinfo = 0l;
			listObjectView2 = objectView2Service.findObject2(myKeyword, idinfo);

			listObjectView = objectViewService.findObject('%' + myKeyword + '%');

		} else {

			listObjectView = objectViewService.findAll();
			if (idinfo != null)
				listObjectView2 = objectView2Service.findAll(idinfo);

		}
		mav.addObject("listObjectView2", listObjectView2);

		Stream<ObjectView> listObjectViewStrim = listObjectView.stream()
				.filter((a) -> a.getStatusy().equals(StaticVarStatus.statusGroupOchrana)).map((a) -> a);

		List<ObjectView> listObjectViewOch = listObjectViewStrim.collect(Collectors.toList());
		listObjectViewOchStatus = listObjectViewOch;

		mav.addObject("listObjectViewOch", listObjectViewOch);

		mav.addObject("listObjectViewSconn", listObjectViewSconn);

		listCity = cityService.getAll();
		List<TypeName> listTypeName = typeNameService.findAll();
		Stream<TypeName> listTypeNameStrim = listTypeName.stream().filter((a) -> a.getTableName().equals("street"))
				.map((a) -> a);

		List<TypeName> listStreetType = listTypeNameStrim.collect(Collectors.toList());

		List<String> city = new ArrayList<String>();
		for (City c : listCity) {
			city.add(c.nameUa);
		}
		if (citySetId != null) {
			MycitySetId = citySetId;
		}
		if (streetTypeSetId != null) {
			MystreetTypeSetId = streetTypeSetId;
		}
		if (streetSetId != null) {

		}

		mav.addObject("city", city);
		mav.addObject("listCity", listCity);
		mav.addObject("listStreetType", listStreetType);

		Long idTechnic = null;
		if (idTechnician != null && idTechnician != 0) {
			idTechnic = idTechnician;
		}
		// ------------------------------------------Оптимизация БД------

		// (вычисляем времяпоследних обновлений таблиц которые пишутся в таблицу
		// update_last тригером

		// таблица alarm

		Integer flagUpdateClick = 0;
		List<UpdateLast> updatelast = updateLastService.getUpdateLast();// Получаем список последних обновлений
																		// таблиц
		Stream<UpdateLast> listUpdateLastAlarmStrim = updatelast.stream()
				.filter((a) -> a.getNameTable().equals("maineq")).map((a) -> a);
		List<UpdateLast> listUpdateLastAlarm = listUpdateLastAlarmStrim.collect(Collectors.toList());
		System.out.println("---------------------listUpdateLastAlarm---------" + listUpdateLastAlarm.size());
		Integer timeUpdateCurrentAlarm = listUpdateLastAlarm.get(0).getTime_update();// время последнего
																						// обновления
																						// таблицы
		// alarm (текущее значение)
		// добавляется в таблицу тригером
		// Integer timeUpdatePreviousAlarm =
		// listUpdateLastAlarm.get(0).getTime_update_previous();

		if (myclick != null && myclick == 1) {// если
												// база
			// обновилась с
			// последнего
			// опроса
			flagUpdateClick = 1;

			mav.addObject("listObjectView2", listObjectView2); // переменные обновления

		} else {
			flagUpdateClick = 0;

		}

		// таблица maineq
		Integer flagUpdateMaineq = 0;
		Stream<UpdateLast> listUpdateMaineqStrim = updatelast.stream().filter((a) -> a.getNameTable().equals("maineq"))
				.map((a) -> a);
		List<UpdateLast> listUpdateLastMaineq = listUpdateMaineqStrim.collect(Collectors.toList());

		Integer timeUpdateCurrentMaineq = listUpdateLastMaineq.get(0).getTime_update();

		// timeUpdatePreviousMaineq
		if (!timeUpdatePreviousMaineqSession.containsKey(session_id) || !String.valueOf(timeUpdateCurrentMaineq)
				.equals(String.valueOf(timeUpdatePreviousMaineqSession.get(session_id)))

		) {// если база обновилась с последнего опроса

			flagUpdateMaineq = 1;// флаг обновления тревог
			timeUpdatePreviousMaineqSession.put(session_id, (long) timeUpdateCurrentMaineq);

		} else {
			flagUpdateMaineq = 0;// флаг обновления тревог
		}
		// таблица stand
		Integer flagUpdateStand = 0;
		Stream<UpdateLast> listUpdateStandStrim = updatelast.stream().filter((a) -> a.getNameTable().equals("stand"))
				.map((a) -> a);
		List<UpdateLast> listUpdateLastStand = listUpdateStandStrim.collect(Collectors.toList());

		Integer timeUpdateCurrentStand = listUpdateLastStand.get(0).getTime_update();

		// timeUpdatePreviousMaineq
		if (!timeUpdatePreviousStandSession.containsKey(session_id) || !String.valueOf(timeUpdateCurrentStand)
				.equals(String.valueOf(timeUpdatePreviousStandSession.get(session_id)))) {// если
			// база обновилась с последнего опроса

			flagUpdateStand = 1;// флаг обновления тревог
			timeUpdatePreviousStandSession.put(session_id, (long) timeUpdateCurrentStand);

		} else {
			flagUpdateStand = 0;
		}

		// таблица event
		Integer flagUpdateEventlist = 0;
		Stream<UpdateLast> listUpdateEventlistStrim = updatelast.stream()
				.filter((a) -> a.getNameTable().equals("eventlist")).map((a) -> a);
		List<UpdateLast> listUpdateLastEventlist = listUpdateEventlistStrim.collect(Collectors.toList());

		Integer timeUpdateCurrentEventlist = listUpdateLastEventlist.get(0).getTime_update();

		// timeUpdatePreviousMaineq
		if (!String.valueOf(timeUpdateCurrentEventlist)
				.equals(String.valueOf(timeUpdatePreviousEventlistSession.get(session_id)))) {// если/ база
																								// обновилась с
																								// последнего опроса

			flagUpdateEventlist = 1;// флаг обновления тревог
			timeUpdatePreviousEventlistSession.put(session_id, (long) timeUpdateCurrentEventlist);

		} else {
			flagUpdateEventlist = 0;
		}
		mav.addObject("flagUpdateSignal", flagUpdateSignal); // переменные обновления

		mav.addObject("flagUpdateClickAll", flagUpdateClick); // переменные обновления
		mav.addObject("flagUpdateMaineqAll", flagUpdateMaineq);
		mav.addObject("flagUpdateStandAll", flagUpdateStand);
		mav.addObject("flagUpdateEventlistAll", flagUpdateEventlist);

		System.out.println("----------------flagUpdateMaineq " + flagUpdateMaineq);

		// ----------------------------------------------------------------------------------------------
		try {

			String evgrname = "";
			String evznname = "";
			switch (nameFuncUtf) {
			// Удалить со стенда объект
			// После удаления со стенда снова начнут идти тревоги
			case "removeStand":
				List<Perifinfo> perifinfoStandId = null;

				List<Perifinfo> perifinfoStandRes = new ArrayList<Perifinfo>();

				List<Perifinfo> perifinfoBanIdinfoFull = perifinfoService.findBanIdinfoFull(MyidinfoStand);
				for (Perifinfo pf : perifinfoBanIdinfoFull) {

					if (pf.getIdinfoStand() == MyidinfoStand) {
						paramnum = pf.getParamnumSensor();
						evznname = pf.getPerifname();

						if (pf.getGrouprel() != null) {
							grouprel = pf.getGrouprel().intValue();

						} else
							grouprel = null;
						accountId = pf.getMaineq().getAccountid();
						System.out.println(paramnum + " " + grouprel + " " + accountId);

					}
				}
				if (paramnum != null) {
					perifinfoStandId = perifinfoService.getIdStand(accountId, grouprel, paramnum);

				}

				else if (grouprel != null) {
					perifinfoStandId = perifinfoService.getIdStandGroup(accountId, grouprel);

				} else {
					perifinfoStandId = perifinfoService.getIdStandObject(accountId);

				}
				if (perifinfoStandId.size() > 0) {
					for (Perifinfo p : perifinfoStandId) {
						if (p.getTimeBan() != null && !p.getTimeBan().after(new Date())) {
							throw new NotRemoveStandException();
						}
						p.setIdTechnician(null);
						p.setTimeBan(null);
						p.setIsStand(null);
						p.setStandLevel(null);
						perifinfoStandRes.add(p);
					}

					Maineq maineqStand = maineqService.findMaineqByAccount(accountId);

					Perifinfo perifnameGroup = perifinfoService.getGroup(maineqStand.getIdeqval(), grouprel);
					if (perifnameGroup != null)
						evgrname = perifnameGroup.getPerifname();
					if (paramnum == null) {
						paramnum = 0;
						evznname = "";
					}
					if (grouprel == null)
						grouprel = 0;
					EventList addStand = new EventList().removeStand(accountId, userId, grouprel, evgrname, paramnum,
							evznname, maineqStand.getIdeqval(), maineqStand.getEqinfo());
					perifinfoService.save(perifinfoStandRes, addStand);
					messageSuccess = "Запрос отправлен.";
					mav.addObject("messageSuccess", messageSuccess);
				} else {
					error = "Данный объект не найден в базе данных";
					mav.addObject("error", error);
				}
				break;
///-----------------Добавить в стенд-------------
//При поставки в стенд тревоги не идут
			case "addStand":

				perifinfoStandRes = new ArrayList<Perifinfo>();
				perifinfoStandId = null;
				Integer standLevel = 0;// Уровень поставки в стенд 1-для зоны 2-для группы 3 для объекта
				// 3 группа вкл в себя все 2 группы и 2 вклюает 1
				if (grouprel == null && paramnum != null) {
					throw new NotInputGroupException();
				}

				if (paramnum != null) {
					perifinfoStandId = perifinfoService.getIdStand(accountId, grouprel, paramnum);
					standLevel = 1;
				}

				else if (grouprel != null) {
					perifinfoStandId = perifinfoService.getIdStandGroup(accountId, grouprel);
					standLevel = 2;
				} else {
					perifinfoStandId = perifinfoService.getIdStandObject(accountId);
					standLevel = 3;
				}

				if (perifinfoStandId.size() != 0) {

					for (Perifinfo p : perifinfoStandId) {
						evznname = p.getPerifname();
						LocalDateTime datetime = LocalDateTime.now().plusMinutes(MyTime.toSSfromMMSS(appt_time));

						if (p.getTimeBan() != null && p.getTimeBan().after(new Date())) {
							throw new NotAddStandException();
						}

						p.setIdTechnician(idTechnic);

						Date convertedDatetime = Date.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
						if (constTime.equals("1")) {
							// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date date11 = new Date();

							// Convert Date to Calendar
							Calendar c = Calendar.getInstance();
							c.setTime(date11);

							// Perform addition/subtraction
							c.add(Calendar.YEAR, 15);

							// Convert calendar back to Date
							convertedDatetime = c.getTime();
						}
						System.out.println("---------convertedDatetime-----------" + convertedDatetime);
						p.setTimeBan(convertedDatetime);
						p.setIsStand(1);
						p.setStandLevel(standLevel);
						p.setStandOperatorid(userId);
						perifinfoStandRes.add(p);

					}
					Maineq maineqStand = maineqService.findMaineqByAccount(accountId);

					Perifinfo perifnameGroup = perifinfoService.getGroup(maineqStand.getIdeqval(), grouprel);
					if (perifnameGroup != null)
						evgrname = perifnameGroup.getPerifname();
					if (paramnum == null) {
						paramnum = 0;
						evznname = "";
					}
					if (grouprel == null)
						grouprel = 0;
					EventList addStand = new EventList().addStand(accountId, userId, grouprel, evgrname, paramnum,
							evznname, maineqStand.getIdeqval(), maineqStand.getEqinfo());
					perifinfoService.save(perifinfoStandRes, addStand);
					messageSuccess = "Запрос отправлен.";
					mav.addObject("messageSuccess", messageSuccess);
				} else {
					error = "Данный объект не найден в базе данных";
					mav.addObject("error", error);
				}
				break;
//---------------------Создать тревогу
//Пишем в eventlist отгуда триггером передаем в alarm
			case "createAlarm":
				Perifinfo perifinfoAlarmId = perifinfoService.getIdAlarm(accountId, grouprel);
				if (perifinfoAlarmId != null) {
					perifinfoAlarmId.getObinfoid();
					EventList eventList = new EventList(perifinfoAlarmId.getObinfoid(), "emulated", "1130", "", "", "",
							grouprel, 0, 0, 0);

					eventListService.save(eventList);
					messageSuccess = "Запрос отправлен.";
					mav.addObject("messageSuccess", messageSuccess);
				} else {
					error = "Данный объект не найден в базе данных";
					mav.addObject("error", error);
				}
				break;
			}
		} catch (NotInputGroupException | NotAddStandException |

				NotRemoveStandException e) {

			mav.addObject("error", e);
			return mav;
		}

		try {
			Maineq maineqStatusPool;
			if (nameFunc != null) {
				switch (nameFunc) {
				// опросить состояние объекта
				// Делается для всего объекта путем записи в таблицу eventlist
				case "myModalStatusPool":

					maineqStatusPool = maineqService.findMaineqByAccount(accountId);
					if (accountId.equals("")) {
						throw new NotInputAccountIdException();
					}
					if (maineqStatusPool == null) {
						throw new NotFoundDBException();

					} else {
						System.out.println("myModalStatusPool");
						EventList eventListCommand = new EventList(maineqStatusPool.getIdeqval(),
								maineqStatusPool.getEqinfo(), "Get status, " + userName,
								"#" + maineqStatusPool.getAccountid() + "|360100000", Command.STATUS, 0, 1, 1,
								maineqStatusPool.getAccountid(), "3601", userId, paramnum, null, null);
						eventListService.save(eventListCommand);
						messageSuccess = "Запрос отправлен.";
						mav.addObject("messageSuccess", messageSuccess);
					}
					break;
				// Взять объект под охрану
				// Делается для всего объекта путем записи в таблицу eventlist
				case "myModalClose":
					maineqStatusPool = maineqService.findMaineqByAccount(accountId);
					if (accountId.equals("")) {
						throw new NotInputAccountIdException();
					}
					if (paramnum == null) {
						throw new NotInputParamnumException();
					}
					if (maineqStatusPool == null) {
						throw new NotFoundDBException();

					} else {
						System.out.println(2);
						EventList eventListCommand = new EventList(maineqStatusPool.getIdeqval(),
								maineqStatusPool.getEqinfo(), "Try close, " + userName,
								"#" + maineqStatusPool.getAccountid() + "|340200000", Command.CLOSEOBJ + paramnum, 0, 0,
								1, maineqStatusPool.getAccountid(), "3402", userId, paramnum, null, null);
						eventListService.save(eventListCommand);
						messageSuccess = "Запрос отправлен.";
						mav.addObject("messageSuccess", messageSuccess);
					}
					break;
				// Снять объект с охраны
				// Делается для всего объекта путем записи в таблицу eventlist
				case "myModalOpen":
					maineqStatusPool = maineqService.findMaineqByAccount(accountId);
					if (accountId.equals("")) {
						throw new NotInputAccountIdException();
					}
					if (paramnum == null) {
						throw new NotInputParamnumException();
					}
					if (maineqStatusPool == null) {
						throw new NotFoundDBException();

					} else {
						System.out.println(2);
						EventList eventListCommand = new EventList(maineqStatusPool.getIdeqval(),
								maineqStatusPool.getEqinfo(), "Try open, " + userName,
								"#" + maineqStatusPool.getAccountid() + "|140700000", Command.OPENOBJ + paramnum, 0, 0,
								1, maineqStatusPool.getAccountid(), "1407", userId, paramnum, null, null);
						eventListService.save(eventListCommand);
						messageSuccess = "Запрос отправлен.";
						mav.addObject("messageSuccess", messageSuccess);
					}
					break;
				// --------------------Обход зоны-------------------
				// Делается для всего объекта путем записи в таблицу eventlist
				case "myModalBypass":
					String strParamnum = "";
					String evzndata = "-";
					Long grouprel1 = null;
					maineqStatusPool = maineqService.findMaineqByAccount(accountId);
					if (accountId.equals("")) {
						throw new NotInputAccountIdException();
					}
					if (paramnum == null) {
						throw new NotInputParamnumException("Выберите зону на объекте");
					}
					if (maineqStatusPool == null) {
						throw new NotFoundDBException();

					} else {

						if (paramnum > 9)
							strParamnum = String.valueOf(paramnum);// Всего 8 зон
						else
							strParamnum = "0" + String.valueOf(paramnum);
						Perifinfo zoneBypass = perifinfoService.getPerifinfoForZone(accountId, new Long(paramnum));
						if (zoneBypass != null) {
							evzndata = zoneBypass.getPerifdata();// название зоны
							grouprel1 = zoneBypass.getGrouprel();
						}

						EventList eventListCommand = new EventList(maineqStatusPool.getIdeqval(),
								maineqStatusPool.getAccountid(), "Try zone bypass, " + userName,
								"#" + maineqStatusPool.getAccountid() + "|357000000", Command.BYPASSOBJ + strParamnum,
								0, 0, 1, maineqStatusPool.getAccountid(), "3570", userId, paramnum, evzndata,
								grouprel1);
						eventListService.save(eventListCommand);
						messageSuccess = "Запрос отправлен.";
						mav.addObject("messageSuccess", messageSuccess);

					}
					break;
				}

			}
		} catch (NotInputAccountIdException | NotFoundDBException |

				NotInputParamnumException e) {
			mav.addObject("error", e);
			return mav;
		}

		// panelright
		List<EventListV> eventlistV;
		List<Perifinfo> perifinfoBan1 = perifinfoService.findBan();
		mav.addObject("perifinfoBan", perifinfoBan1);
		// При клике на мышь выбираем данные в модальные окна
		if (myclick != null && myclick == 1 && idinfo != null) {

			// getAccountID()
			ObjectView accountSchema = new ObjectView();
			accountSchema = objectViewService.findId(idinfo);
			String strAccountSchema;
			if (accountSchema != null) {
				strAccountSchema = accountSchema.getAccountID();

				List<FileColumn> schemaList = Schema.getFileMaineq(strAccountSchema);

				mav.addObject("schemaList", schemaList);
			}
			Perifinfo perifinfoBanIdinfo = new Perifinfo();

			perifinfoBanIdinfo = perifinfoService.findObject(idinfo);
			mav.addObject("perifinfoBanIdinfo", perifinfoBanIdinfo);

			Perifinfo perifinfo = perifinfoService.findObject(idinfo);
			mav.addObject("perifinfo", perifinfo);

			ObjectView objectviewId = objectViewService.findId(idinfo);
			mav.addObject("objectviewId", objectviewId);

			List<Groups> groupsFree = groupsService.findObjectActive(idinfo);
			mav.addObject("groupsFree", groupsFree);

			List<Groups> groupsBusy = groupsService.findObjectNotActive(idinfo);
			mav.addObject("groupsBusy", groupsBusy);

			List<Groups> alarmGroups = groupsService.findGrouptByIdinfo(idinfo);
			mav.addObject("alarmGroups", alarmGroups);
		}

		// panelright end

		if (idinfo != null && idinfo != 0 && panelcurrent != null) {

			List<Perifinfo> perifinfoBanIdinfoFull = perifinfoService.findBanIdinfoFull(idinfo);

			mav.addObject("perifinfoBanIdinfoFull", perifinfoBanIdinfoFull);
			Perifinfo perifinfoBanIdinfo = perifinfoService.findObject(idinfo);
			mav.addObject("perifinfoBanIdinfo", perifinfoBanIdinfo);

			Integer perifinfoSensorSizeFull = perifinfoBanIdinfoFull.size();

			if (perifinfoSensorSizeFull > 0)
				mav.addObject("perifinfoSensorSizeFull", perifinfoSensorSizeFull);

			List<Perifinfo> groupForAccount = perifinfoService.getGroupbyIdinfo(idinfo);
			List<Perifinfo> zoneForAccount = perifinfoService.getZonebyIdinfo(idinfo);
			mav.addObject("groupForAccount", groupForAccount);
			mav.addObject("zoneForAccount", zoneForAccount);
			Maineq maineq = maineqService.findObject(idinfo);
			mav.addObject("maineq", maineq);

			switch (panelcurrent) {
			case "Events":

				if (date == null || date.equals(""))
					date = dateMin;
				if (date1 == null || date1.equals(""))
					date1 = dateMax;
				if (date != null && date1 != null) {
					eventlistV = eventlistVService.findObjectData(idinfo, date.replace("T", " "),
							date1.replace("T", " "));
				} else {
					eventlistV = eventlistVService.findObject(idinfo);

				}
				if (sizeColumn == null)
					sizeColumn = 50;
				int fromList = sizeColumn;
				int toList = eventlistV.size();
				if (fromList < toList)
					eventlistV.subList(fromList, toList).clear();

				mav.addObject("eventlistV", eventlistV);

				break;
			case "Plume":
				List<Perifinfo> perifinfoSensor = perifinfoService.findSensor(idinfo);
				mav.addObject("perifinfoSensor", perifinfoSensor);
				break;

			case "Kard":

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
			case "ResponsPeop":
				List<PerifinfoResponsible> perifinfoResponsible = perifinfoResponsibleService.findObject(idinfo);
				mav.addObject("perifinfoResponsible", perifinfoResponsible);
				break;
			case "Schema":
				ObjectView accountSchema = objectViewService.findId(idinfo);
				String strAccountSchema = accountSchema.getAccountID();
				List<FileColumn> schemaList = Schema.getFileMaineq(strAccountSchema);

				mav.addObject("schemaList", schemaList);
				break;

			}
			List<Employees> listPosition = employeesService.findPosition();

			Stream<Employees> listPositionStrim = listPosition.stream()
					.filter((a) -> a.getPosition_id() == Position.technician || a.getPosition_id() == -1l)
					.map((a) -> a);

			listTechnic = listPositionStrim.collect(Collectors.toList());
			mav.addObject("listTechnic", listTechnic);
			List<Groups> groupsFree = groupsService.findObjectActive(idinfo);
			mav.addObject("groupsFree", groupsFree);

			List<Groups> groupsBusy = groupsService.findObjectNotActive(idinfo);
			mav.addObject("groupsBusy", groupsBusy);

		}

		if (sizeColumn == null)
			sizeColumn = 30;
		int fromList = sizeColumn;
		int toList = 0;
		if (listObjectView2 != null)
			toList = listObjectView2.size();
		if (fromList < toList)
			listObjectView2.subList(fromList, toList).clear();

		mav.addObject("listObjectView2", listObjectView2);
		// Устанавливаем значение выбранного курсора (выделяем нужным цветом)
		if (listObjectView2 != null)
			for (ObjectView2 i : listObjectView2) {
				i.setCurrentIdinfo(idinfo);
			}

		System.out.println("idinfo " + idinfo);
		System.out.println("panelcurrent " + panelcurrent);

		return mav;

	}

}
