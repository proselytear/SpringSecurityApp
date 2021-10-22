package net.proselyte.springsecurityapp.controller;

import static java.nio.charset.StandardCharsets.UTF_8;
import static net.proselyte.springsecurityapp.var.StaticVarDefault.dateMax;
import static net.proselyte.springsecurityapp.var.StaticVarDefault.dateMin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import net.proselyte.springsecurityapp.calculation.function.Schema;
import net.proselyte.springsecurityapp.model.Alarm;
import net.proselyte.springsecurityapp.model.AlarmCancelName;
import net.proselyte.springsecurityapp.model.AlarmNew;
import net.proselyte.springsecurityapp.model.AlarmWork;
import net.proselyte.springsecurityapp.model.Card;
import net.proselyte.springsecurityapp.model.CardExchange;
import net.proselyte.springsecurityapp.model.CardNotation;
import net.proselyte.springsecurityapp.model.Defect;
import net.proselyte.springsecurityapp.model.Employees;
import net.proselyte.springsecurityapp.model.EventListV;
import net.proselyte.springsecurityapp.model.FileColumn;
import net.proselyte.springsecurityapp.model.Groups;
import net.proselyte.springsecurityapp.model.LogFile;
import net.proselyte.springsecurityapp.model.Maineq;
import net.proselyte.springsecurityapp.model.ObjectView;
import net.proselyte.springsecurityapp.model.Perifinfo;
import net.proselyte.springsecurityapp.model.PerifinfoResponsible;
import net.proselyte.springsecurityapp.model.SessionParametr;
import net.proselyte.springsecurityapp.model.UpdateLast;
import net.proselyte.springsecurityapp.model.User;
import net.proselyte.springsecurityapp.service.AlarmCancelNameService;
import net.proselyte.springsecurityapp.service.AlarmNewService;
import net.proselyte.springsecurityapp.service.AlarmService;
import net.proselyte.springsecurityapp.service.AlarmWorkService;
import net.proselyte.springsecurityapp.service.CardExchangeService;
import net.proselyte.springsecurityapp.service.CardNotationService;
import net.proselyte.springsecurityapp.service.CardService;
import net.proselyte.springsecurityapp.service.EmployeesService;
import net.proselyte.springsecurityapp.service.EventListVService;
import net.proselyte.springsecurityapp.service.GroupsService;
import net.proselyte.springsecurityapp.service.LogFileService;
import net.proselyte.springsecurityapp.service.MaineqService;
import net.proselyte.springsecurityapp.service.ObjectViewService;
import net.proselyte.springsecurityapp.service.PerifinfoResponsibleService;
import net.proselyte.springsecurityapp.service.PerifinfoService;
import net.proselyte.springsecurityapp.service.SessionParametrService;
import net.proselyte.springsecurityapp.service.UpdateLastService;
import net.proselyte.springsecurityapp.service.UserService;
import net.proselyte.springsecurityapp.var.Position;

/**
 * Controller for {@link net.proselyte.springsecurityapp.model.User}'s pages.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 * @param <TimeUpdateLastService>
 */

@Controller
public class SignalController<TimeUpdateLastService> {
	long Myidinfo = 0;

	@Autowired
	private AlarmNewService alarmnewService;

	@Autowired
	private UpdateLastService updateLastService;

	@Autowired
	private AlarmWorkService alarmWorkService;

	@Autowired
	private SessionParametrService sessionParametrService;

	@Autowired
	private UserService userService;

	@Autowired
	private EventListVService eventlistVService;

	@Autowired
	private PerifinfoResponsibleService perifinfoResponsibleService;

	@Autowired
	private ObjectViewService objectViewService;

	@Autowired
	private PerifinfoService perifinfoService;

	@Autowired
	private AlarmCancelNameService alarmCancelNameService;

	@Autowired
	private GroupsService groupsService;

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private EmployeesService employeesService;

	@Autowired
	private MaineqService maineqService;

	@Autowired
	private CardService cardService;

	@Autowired
	private CardNotationService cardNotationService;

	@Autowired
	private CardExchangeService cardExchangeService;
//Предедущее последнее время обновления таблиц
	Integer timeUpdatePreviousAlarm = 0;
	Integer timeUpdatePreviousMaineq = 0;
	Integer timeUpdatePreviousStand = 0;
	Integer timeUpdatePreviousEventlist = 0;

	ModelAndView mav;

	String statusOchFull;

	@Autowired
	private LogFileService logFileService;

	List<AlarmWork> alarmnotnew = null;// Список обрабатываемых тревог пользователем (напр группа прибыла) в нижней
	AlarmNew alarmnewCurrentOne = new AlarmNew();

	// части окна
	UpdateLast updatelastAlarmNotNew = null;
	Long idinfoPrevious = 0l;// значение предыдущее

	public void saveException(String message) {
		LogFile logfile = new LogFile(message);
		logFileService.save(logfile);
	}

	HashMap<String, Long> timeUpdatePreviousAlarmSession = new HashMap<>();
	HashMap<String, Long> timeUpdatePreviousMaineqSession = new HashMap<>();
	HashMap<String, Long> timeUpdatePreviousStandSession = new HashMap<>();
	HashMap<String, Long> timeUpdatePreviousEventlistSession = new HashMap<>();
	List<AlarmNew> alarmnew = null;
	List<Employees> listTechnic = new ArrayList<Employees>();// Список техников для почтановки объекта в стенд

	int flagUpdateSignal; // 1 обновляем 0-не обновляем. =1 при клике на новую тревогу или если в таблице
	// alarm были изменения

	/**
	 * 
	 * @param idinfo          ид группы на объекте из таблицы perifinfo
	 * @param nameFunc        название вызываемой пользователем функции
	 * @param sizeColumn      количество выводимых строк таблицы (выбирается
	 *                        пользователем из выпадающего списка)
	 * @param groups_id       ид группы реагирования
	 * @param id_alarm_cancel ид причины отмены тревоги
	 * @param accountId       номер объекта
	 * @param grouprel        значение для группы из таблицы
	 * @param date            время поиска событий на вкладке события
	 * @param date1
	 * 
	 * 
	 * @param panelcurrent    название выбранной панели справа(напр Kard для
	 *                        карточки, Maineq для оборудования)
	 * @return
	 * @throws CloneNotSupportedException
	 */
	@RequestMapping(value = "/objectsignal", method = RequestMethod.POST)

	public ModelAndView signal(@RequestParam(required = false) Long idinfo,
			@RequestParam(required = false) String nameFunc, @RequestParam(required = false) Integer sizeColumn,
			@RequestParam(required = false) Integer groups_id, @RequestParam(required = false) Integer id_alarm_cancel,
			@RequestParam(required = false) String accountId, @RequestParam(required = false) Integer grouprel,
			@RequestParam(required = false) String date, @RequestParam(required = false) String date1,

			@RequestParam(required = false) String panelcurrent, @RequestParam(required = false) Integer myclick,
			@RequestParam(required = false) Long idinfo_group) throws CloneNotSupportedException {

		ModelAndView mav = new ModelAndView("objectsignal");
		// Получаеи ид текущей сессии. Необходим поскольку может работать несколько
		// пользователей одновременно
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		String session_id = attr.getSessionId();

		Long alarm_id = null;// ид тревоги
		int status_alarm_new = 0;// статус новой тревоги 1 принята 0 не принята оператором
		String employeeFIO = null;// фио операторв залогиневшегося в системе
		Integer alarmfullSize = 0;// количество новых не принятых сигналов(служит для отображения количества
									// сигналов на кнопке 'Прием сигналов'

		String nameFuncUtf = "";// название вызываемой пользователем функции в формате UTf
		String error = ""; // Описание ошибки выводимое пользователю
		String messageSuccess;// Сообщение для пользователя

		List<EventListV> eventlistV;// Получить все значения событий
		if (nameFunc != null) {
			nameFuncUtf = new String(nameFunc.getBytes(UTF_8));
		} else {
			nameFunc = "";
		}

//Проверка на аутифекацию залогиневшегося пользователя
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(userName);
		if (user == null) {
			mav = new ModelAndView("login");// для незалогиневшегося пользователя возвращаем модель логин
			return mav;
		}

		// Находим ФИО для залогтневшегося пользователя для отображения на странице если
		// фио не заведенено то показываем логин
		Long userId = user.getId();
		Employees employees = user.getEmployees();
		if (employees != null)
			employeeFIO = employees.getFullName();
		employeeFIO = (null != employeeFIO) ? employeeFIO : userName;
		mav.addObject("employeeFIO", employeeFIO);

		List<Perifinfo> perifinfoBanFull = perifinfoService.findBanFull();
		Integer perifinfoBanFullSize = perifinfoBanFull.size();
		if (perifinfoBanFullSize > 0)
			mav.addObject("perifinfoBanFullSize", perifinfoBanFullSize);

		if (myclick != null && myclick == 1 && idinfo != null) {

			SessionParametr sessionParametr = sessionParametrService.findOne(session_id);
			Long idinfo_alarm = sessionParametr.getIdinfo_alarm();

			if (idinfo_alarm == -1) {
				sessionParametr = new SessionParametr();

			}
			sessionParametr.setSession_id(session_id);
			sessionParametr.setIdinfo_alarm(idinfo);
			try {
				sessionParametrService.save(sessionParametr);
			} catch (Exception e) {

			}

		}
		Myidinfo = sessionParametrService.findOne(session_id).getIdinfo_alarm();

		// Обновление правой панели. Обновляется только по выбранному из меню окна
		// название которого передается в панеле panelcurrent
		if (idinfo != null && idinfo != 0 && panelcurrent != null) {
			List<Perifinfo> perifinfoBanIdinfoFull = perifinfoService.findBanIdinfoFull(idinfo);
			mav.addObject("perifinfoBanIdinfoFull", perifinfoBanIdinfoFull);

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
			List<Groups> groupsFree = groupsService.findObjectActive(idinfo);
			mav.addObject("groupsFree", groupsFree);

			List<Groups> groupsBusy = groupsService.findObjectNotActive(idinfo);
			mav.addObject("groupsBusy", groupsBusy);

		}
		if (myclick != null && myclick == 1 && idinfo != null) {

			// getAccountID()

			ObjectView accountSchema = objectViewService.findId(idinfo);
			String strAccountSchema;
			if (accountSchema != null) {
				strAccountSchema = accountSchema.getAccountID();

				List<FileColumn> schemaList = Schema.getFileMaineq(strAccountSchema);

				mav.addObject("schemaList", schemaList);
			}

			Perifinfo perifinfoBanIdinfo = perifinfoService.findObject(idinfo);
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

		// ------------------------------------------Оптимизация БД------

		// (вычисляем времяпоследних обновлений таблиц которые пишутся в таблицу
		// update_last тригером

		// таблица alarm

		Integer flagUpdateClick = 0;
		List<UpdateLast> updatelast = updateLastService.getUpdateLast();// Получаем список последних обновлений таблиц
		Stream<UpdateLast> listUpdateLastAlarmStrim = updatelast.stream()
				.filter((a) -> a.getNameTable().equals("alarm")).map((a) -> a);
		List<UpdateLast> listUpdateLastAlarm = listUpdateLastAlarmStrim.collect(Collectors.toList());
		System.out.println("---------------------listUpdateLastAlarm---------" + listUpdateLastAlarm.size());
		Integer timeUpdateCurrentAlarm = listUpdateLastAlarm.get(0).getTime_update();// время последнего
																						// обновления
																						// таблицы
		// alarm (текущее значение)
		// добавляется в таблицу тригером

		if ((myclick != null && myclick == 1)) {// если
												// база
			// обновилась с
			// последнего
			// опроса
			flagUpdateClick = 1;

			alarmnotnew = alarmWorkService.findIdinfo(idinfo);
			mav.addObject("alarmnotnew", alarmnotnew);
			Integer alarmnotnewSize = alarmnotnew.size();
			mav.addObject("alarmnotnewSize", alarmnotnewSize);

		} else {
			flagUpdateClick = 0;
			alarmnotnew = alarmWorkService.findIdinfo(idinfo);
			mav.addObject("alarmnotnew", alarmnotnew);
		}

		if (!timeUpdatePreviousAlarmSession.containsKey(session_id) || !String.valueOf(timeUpdateCurrentAlarm)
				.equals(String.valueOf(timeUpdatePreviousAlarmSession.get(session_id)))) {// если
			// база
			// обновилась с
			// последнего
			// опроса
			flagUpdateSignal = 1;// флаг обновления тревог

			timeUpdatePreviousAlarmSession.put(session_id, (long) timeUpdateCurrentAlarm);
			alarmnotnew = alarmWorkService.findIdinfo(idinfo);
			mav.addObject("alarmnotnew", alarmnotnew);
			Integer alarmnotnewSize = alarmnotnew.size();
			mav.addObject("alarmnotnewSize", alarmnotnewSize);

		} else {
			alarmnotnew = alarmWorkService.findIdinfo(idinfo);
			flagUpdateSignal = 0;
			mav.addObject("alarmnotnew", alarmnotnew);

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
				.equals(String.valueOf(timeUpdatePreviousEventlistSession.get(session_id)))) {// если/ база обновилась с
																								// последнего опроса

			flagUpdateEventlist = 1;// флаг обновления тревог
			timeUpdatePreviousEventlistSession.put(session_id, (long) timeUpdateCurrentEventlist);

		} else {
			flagUpdateEventlist = 0;
		}
		mav.addObject("flagUpdateSignal", flagUpdateSignal); // переменные обновления

		mav.addObject("flagUpdateClick", flagUpdateClick); // переменные обновления
		mav.addObject("flagUpdateMaineq", flagUpdateMaineq);
		mav.addObject("flagUpdateStand", flagUpdateStand);
		mav.addObject("flagUpdateEventlist", flagUpdateEventlist);

		System.out.println("----------------flagUpdateMaineq " + flagUpdateMaineq);

		// ----------------------------------------------------------------------------------------------

		List<AlarmCancelName> alarmcancelname = alarmCancelNameService.findObjectAll();
		mav.addObject("alarmcancelname", alarmcancelname);

		List<Employees> listPosition = employeesService.findPosition();

		Stream<Employees> listPositionStrim = listPosition.stream()
				.filter((a) -> a.getPosition_id() == Position.technician || a.getPosition_id() == -1l).map((a) -> a);

		listTechnic = listPositionStrim.collect(Collectors.toList());
		mav.addObject("listTechnic", listTechnic);

		switch (nameFunc) {
		case "takeProcessing":
			// Функция взять в обработку
			if (idinfo != null && idinfo > 0) {
				alarmService.takeProcessing(idinfo, userId);
			}
			break;
		case "GroupLeft":
			if (alarm_id != null && alarm_id != 0) {
				if (status_alarm_new > 0) {
					error = "Возьмите тревогу в обработку";
					mav.addObject("error", error);
				} else if (groups_id != null && groupsService.isGroupFree(groups_id) != null
						&& groupsService.isGroupFree(groups_id).size() == 0) {
					error = "Выберите другую группу. Данная группа занята";
					mav.addObject("error", error);
				} else {
					alarmService.GroupLeft(groups_id, userId, alarm_id, idinfo);
					messageSuccess = "Запрос отправлен.";
					mav.addObject("messageSuccess", messageSuccess);
				}
			}
			break;
		case "myModalCancelGroups":
			// Функция отмены группы
			if (status_alarm_new > 0) {
				error = "Возьмите тревогу в обработку";
				mav.addObject("error", error);
			} else if (idinfo != null && groups_id != null) {
				if (groupsService.canCancelGroup(idinfo, groups_id) != null
						&& groupsService.canCancelGroup(idinfo, groups_id).size() == 0) {
					error = "Данную группа не вызванна на данносм объекте.";
					mav.addObject("error", error);
				} else {
					alarmService.CancelGroups(groups_id, userId, alarm_id, idinfo);
					messageSuccess = "Запрос отправлен.";
					mav.addObject("messageSuccess", messageSuccess);
				}
			}
			break;
		case "myModalCancelAlarm":
			// Функция отмены тревоги(завершение обработки)
			if (idinfo != null && idinfo != 0 && id_alarm_cancel != null) {
				if (alarmService.newAlarmIdinfo(idinfo).size() > 0) {
					error = "Обработайте новую тревогу по данному объекту(взять в обработку).";
					mav.addObject("error", error);
				} else {
					alarmService.CancelAlarm(groups_id, userId, alarm_id, idinfo, id_alarm_cancel);

					messageSuccess = "Запрос отправлен.";
					mav.addObject("messageSuccess", messageSuccess);
				}
			}

			break;
		case "myModalGroupArrived":
			// Функция пребытия группы на объект
			if (idinfo != null && groups_id != null) {
				if (alarmService.canArrivedGroup(idinfo, groups_id).size() == 0) {
					error = "Данная группа уже отмечена как прибывшая на объект.";
					mav.addObject("error", error);
				} else {
					alarmService.GroupArrived(idinfo, groups_id, userId);
					messageSuccess = "Запрос отправлен.";
					mav.addObject("messageSuccess", messageSuccess);
				}
			}
			break;

		case "myModalResponseNotAlarm":
			System.out.println("*****************myModalResponseNotAlarm" + idinfo + groups_id);
			// Функци выслать группу реагирования (на панели)
			if (groups_id != null && idinfo_group != null) {
				alarmService.GroupResponse(idinfo_group, groups_id);
				Alarm alarmNew = new Alarm();
				alarmNew.setIdIdinfo(idinfo_group);
				alarmNew.setIdGroup(groups_id);
				alarmNew.setIsactive(0);
				alarmNew.setStatusAlarmId(5);
				alarmService.save(alarmNew);
			}
		}
		Myidinfo = sessionParametrService.findOne(session_id).getIdinfo_alarm();
		if (Myidinfo != 0) {
			ObjectView objectviewId = objectViewService.findId(Myidinfo);
			mav.addObject("objectviewId", objectviewId);

			List<Groups> groupsFree = groupsService.findObjectActive(Myidinfo);
			mav.addObject("groupsFree", groupsFree);

			List<Groups> groupsBusy = groupsService.findObjectNotActive(Myidinfo);
			mav.addObject("groupsBusy", groupsBusy);

			List<Groups> alarmGroups = groupsService.findGrouptSendIdinfo(Myidinfo);
			mav.addObject("alarmGroups", alarmGroups);

			List<Groups> alarmGroupsForCancel = groupsService.findcanCancelGroup(Myidinfo);
			mav.addObject("alarmGroupsForCancel", alarmGroupsForCancel);

			Perifinfo perifinfoBanIdinfo = perifinfoService.findObject(Myidinfo);
			mav.addObject("perifinfoBanIdinfo", perifinfoBanIdinfo);

			List<Perifinfo> groupForAccount = perifinfoService.getGroupbyIdinfo(Myidinfo);
			List<Perifinfo> zoneForAccount = perifinfoService.getZonebyIdinfo(Myidinfo);
			mav.addObject("groupForAccount", groupForAccount);
			mav.addObject("zoneForAccount", zoneForAccount);

		}

		if (idinfo != null && alarm_id != null) {

			// Myidinfo = idinfo;
			Maineq maineq = maineqService.findObject(idinfo);

			mav.addObject("maineq", maineq);

		}
		// Получаем все данные для новых и принятых сигналов (вверху страницы)

		alarmnew = alarmnewService.findAlarmNew();

		if (idinfo != null) {
			final Long IDINFO = idinfo;
			// Получаем выбранную строку тревоги пользователя
			Stream<AlarmNew> listTypeNameStrim = alarmnew.stream()
					.filter((a) -> a.getIdinfo().toString().equals(IDINFO.toString())).map((a) -> a);
			List<AlarmNew> alarmnewCurrent = listTypeNameStrim.collect(Collectors.toList());
			if (alarmnewCurrent.size() > 0) {
				alarmnewCurrentOne = alarmnewCurrent.get(0);
				alarm_id = alarmnewCurrentOne.getAlarm_id();

				if (alarmnewCurrentOne.getCount_alarm_new() != null
						&& alarmnewCurrentOne.getCount_alarm_accepted() == null)
					status_alarm_new = 1;// Присваиваем статус 1 для тревог соописанием события Новое
				// alarmnewCurrentOne.setCurrentAlarmId(idinfo);// записываем значение курсора
				// таблицы
			}
			alarmnewCurrentOne.setCurrentAlarmId(idinfo);// записываем значение курсора таблицы
		}
		mav.addObject("alarmnew", alarmnew);
		// суммируем количество всех непринятых тревох для проигрывание музыки и для
		// отобоажение на кнопке Прием сигналов если количество тревог>0
		for (AlarmNew i : alarmnew) {
			if (i.getCount_alarm_new() != null)
				alarmfullSize += i.getCount_alarm_new().intValue();
		}

		mav.addObject("alarmfullSize", alarmfullSize);
		System.out.println("____________________________" + Myidinfo + "__" + session_id);

		idinfoPrevious = idinfo;
		return mav;
	}

	@RequestMapping(value = "/objectsignal", method = RequestMethod.GET)
	public ModelAndView signal(ModelAndView mav) {
		System.out.println("-------------------signal-get--------------------");

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

		mav.addObject("alarmnew", alarmnew);

		// Считаем количество сигналов
		Integer alarmfullSize = null;

		for (AlarmNew i : alarmnew) {

			if (i.getCount_alarm_new() != null)
				alarmfullSize += i.getCount_alarm_new().intValue();

		}

		mav.addObject("alarmfullSize", alarmfullSize);

		List<Perifinfo> perifinfoBanFull = perifinfoService.findBanFull();
		Integer perifinfoBanFullSize = perifinfoBanFull.size();
		if (perifinfoBanFullSize > 0)
			mav.addObject("perifinfoBanFullSize", perifinfoBanFullSize);

		//

		List<ObjectView> listObjectViewSconn = objectViewService.findSconnection();
		Integer objectViewSconnSize = listObjectViewSconn.size();
		if (objectViewSconnSize > 0)
			mav.addObject("objectViewSconnSize", objectViewSconnSize);

		mav.addObject("listObjectViewSconn", listObjectViewSconn);

		ArrayList<Defect> alDefect = new ArrayList<Defect>();
		List<Perifinfo> perifinfoDefect = perifinfoService.findSensorFull();
		for (Perifinfo p : perifinfoDefect) {
			List<Defect> ld = p.getDefect();
			for (Defect d : ld) {
				alDefect.add(d);
			}

		}

		mav.addObject("alDefect", alDefect);
		Integer alDefectSize = alDefect.size();
		if (alDefectSize > 0)
			mav.addObject("alDefectSize", alDefectSize);

		List<Perifinfo> perifinfoBan = perifinfoService.findBan();
		Integer perifinfoSensorSize = perifinfoBan.size();
		if (perifinfoSensorSize > 0)
			mav.addObject("perifinfoSensorSize", perifinfoSensorSize);

		// Size end
		List<Employees> listTechnic = employeesService.findPosition();
		mav.addObject("listTechnic", listTechnic);

		// List<AlarmWork> alarmnotnew = alarmWorkService.findAll();
		// mav.addObject("alarmnotnew", alarmnotnew);

		return mav;
	}

}
