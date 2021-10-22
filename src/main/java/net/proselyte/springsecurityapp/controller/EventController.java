package net.proselyte.springsecurityapp.controller;

import static net.proselyte.springsecurityapp.var.StaticVarDefault.dateMax;
import static net.proselyte.springsecurityapp.var.StaticVarDefault.dateMin;

import java.util.Arrays;
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

import net.proselyte.springsecurityapp.calculation.MyException.NotFoundFileExportException;
import net.proselyte.springsecurityapp.calculation.function.ExportFile;
import net.proselyte.springsecurityapp.model.AlarmNew;
import net.proselyte.springsecurityapp.model.Employees;
import net.proselyte.springsecurityapp.model.EventFound;
import net.proselyte.springsecurityapp.model.EventListV;
import net.proselyte.springsecurityapp.model.Evrang;
import net.proselyte.springsecurityapp.model.Groups;
import net.proselyte.springsecurityapp.model.ObjectView;
import net.proselyte.springsecurityapp.model.Perifinfo;
import net.proselyte.springsecurityapp.model.UpdateLast;
import net.proselyte.springsecurityapp.model.User;
import net.proselyte.springsecurityapp.service.AlarmNewService;
import net.proselyte.springsecurityapp.service.EmployeesService;
import net.proselyte.springsecurityapp.service.EventListVService;
import net.proselyte.springsecurityapp.service.EvrangService;
import net.proselyte.springsecurityapp.service.GroupsService;
import net.proselyte.springsecurityapp.service.ObjectViewService;
import net.proselyte.springsecurityapp.service.PerifinfoService;
import net.proselyte.springsecurityapp.service.UpdateLastService;
import net.proselyte.springsecurityapp.service.UserService;

/**
 * Controller for {@link net.proselyte.springsecurityapp.model.User}'s pages.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Controller

public class EventController {
	@Autowired
	private EventListVService eventlistVService;

	@Autowired
	private UpdateLastService updateLastService;

	@Autowired
	private UserService userService;

	@Autowired
	private EvrangService evrangService;

	@Autowired
	private EmployeesService employeesService;

	@Autowired
	private EventListVService eventlistvService;

	@Autowired
	private PerifinfoService perifinfoService;

	@Autowired
	private ObjectViewService objectViewService;

	@Autowired
	private GroupsService groupsService;

	@Autowired
	private AlarmNewService alarmnewService;

	HashMap<String, Long> timeUpdatePreviousEventlistSession = new HashMap<>();// Предыдущее время обновления стендов
	// perifinfo для данной сессии
	HashMap<String, EventFound> eventFoundSession = new HashMap<>();// параметры поиска вводимые пользователем текущей
																	// сессии
	ModelAndView mav;

	String statusOchFull;
	Long MyidinfoEvent;

	private String myKeyword;
	private String myKeyword2;
	private String myDate;
	private String myDate1;
	private String myEvrangidStr;
	private Integer mySizeColumn = 50;

	@RequestMapping(value = "/objectevent", method = RequestMethod.GET)

	public ModelAndView objectevent() {
		System.out.println("--------------------groups get----------------------------");
		ModelAndView mav = new ModelAndView("objectevent");

		String employeeFIO = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(userName);
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

		// Size begin
		List<AlarmNew> alarmnew = alarmnewService.findAlarmNew();

		Integer alarmfullSize = 0;

		for (AlarmNew i : alarmnew) {
			if (i.getCount_alarm_new() != null)
				alarmfullSize += i.getCount_alarm_new().intValue();
		}

		mav.addObject("alarmfullSize", alarmfullSize);
//Size end

		List<Perifinfo> perifinfoBanFull = perifinfoService.findBanFull();
		mav.addObject("perifinfoBanFull", perifinfoBanFull);
		System.out.println("---get event---");

		return mav;

	}

	@RequestMapping(value = "/objectevent", method = RequestMethod.POST)
	public ModelAndView objectevent(@RequestParam(required = false) Long idinfoEvent,
			@RequestParam(required = false) String date, @RequestParam(required = false) String date1,
			@RequestParam(required = false) String evrangidStr, @RequestParam(required = false) String keyword,
			@RequestParam(required = false) String keyword2, @RequestParam(required = false) Integer sizeColumn,
			@RequestParam(required = false) Integer idEvent, @RequestParam(required = false) String nameFunc,
			@RequestParam(required = false) String fileName, @RequestParam(required = false) Integer myclick)
			throws Exception {

		ModelAndView mav = new ModelAndView("objectevent");
		String employeeFIO = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(userName);
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

		// Получаеи ид текущей сессии. Необходим поскольку может работать несколько
		// пользователей одновременно
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		String session_id = attr.getSessionId();

		eventFoundSession.put(session_id, new EventFound(date, date1, evrangidStr, keyword, keyword2, sizeColumn));

		if (MyidinfoEvent != null) {
			Perifinfo perifinfoBanIdinfo = perifinfoService.findBanIdinfoStand(idinfoEvent);
			mav.addObject("perifinfoBanIdinfo", perifinfoBanIdinfo);

		}

		EventListV eventlistObjIdev;
		if (myclick != null && myclick == 1 && idEvent != null && idEvent > 0) {

			eventlistObjIdev = eventlistvService.findObjectIdev(idEvent);
			Perifinfo perifinfoBanIdinfo = perifinfoService.findObject(eventlistObjIdev.getIdinfo());
			mav.addObject("perifinfoBanIdinfo", perifinfoBanIdinfo);

			List<Perifinfo> groupForAccount = perifinfoService.getGroupbyIdinfo(new Long(eventlistObjIdev.getIdinfo()));
			List<Perifinfo> zoneForAccount = perifinfoService.getZonebyIdinfo(new Long(eventlistObjIdev.getIdinfo()));
			mav.addObject("groupForAccount", groupForAccount);
			mav.addObject("zoneForAccount", zoneForAccount);

			ObjectView objectviewId = objectViewService.findId(Long.valueOf(eventlistObjIdev.getIdinfo()));
			mav.addObject("objectviewId", objectviewId);

			List<Groups> groupsFree = groupsService.findObjectActive(Long.valueOf(eventlistObjIdev.getIdinfo()));
			mav.addObject("groupsFree", groupsFree);

			List<Groups> groupsBusy = groupsService.findObjectNotActive(Long.valueOf(eventlistObjIdev.getIdinfo()));
			mav.addObject("groupsBusy", groupsBusy);

			List<Employees> listTechnic = employeesService.findPosition();

			mav.addObject("listTechnic", listTechnic);

		}

		List<Perifinfo> perifinfoBanFull = perifinfoService.findBanFull();
		mav.addObject("perifinfoBanFull", perifinfoBanFull);

		Integer perifinfoBanFullSize = perifinfoBanFull.size();
		if (perifinfoBanFullSize > 0)
			mav.addObject("perifinfoBanFullSize", perifinfoBanFullSize);
		List<AlarmNew> alarmnew = alarmnewService.findAlarmNew();

		Integer alarmfullSize = 0;

		for (AlarmNew i : alarmnew) {
			if (i.getCount_alarm_new() != null)
				alarmfullSize += i.getCount_alarm_new().intValue();
		}

		mav.addObject("alarmfullSize", alarmfullSize);

		List<EventListV> eventlistObjFull;

		if (eventFoundSession.get(attr.getSessionId()) != null) {
			myDate = eventFoundSession.get(attr.getSessionId()).getDate();
		}
		if (eventFoundSession.get(attr.getSessionId()) != null) {
			myDate1 = eventFoundSession.get(attr.getSessionId()).getDate1();
		}
		if (myDate == null || myDate.equals("")) {
			myDate = dateMin;
			System.out.println("---4---");
		}
		if (myDate1 == null || myDate1.equals(""))
			myDate1 = dateMax;
		if (myDate1 != dateMax || myDate != dateMin) {

			eventlistObjFull = eventlistvService.findObjectData(myDate.replace("T", " "), myDate1.replace("T", " "));

		} else {
			eventlistObjFull = eventlistvService.findObjectFull();

		}
		Stream<EventListV> listObjectViewStrim;
		/*
		 * if (evrangid != null && evrangid[0].intValue() > 0) { listObjectViewStrim =
		 * eventlistObjFull.stream().filter((a) -> a.getEvrang() == evrangid[0]).map((a)
		 * -> a);
		 * 
		 * eventlistObjFull = listObjectViewStrim.collect(Collectors.toList()); }
		 */
		if (eventFoundSession.get(attr.getSessionId()) != null) {
			myEvrangidStr = eventFoundSession.get(attr.getSessionId()).getEvrangidStr();
		}
		if (myEvrangidStr != null && !myEvrangidStr.equals("")) {

			List<String> evrangid = Arrays.asList(myEvrangidStr.split(","));

			if (evrangid.size() > 0 && !evrangid.contains("-1")) {

				listObjectViewStrim = eventlistObjFull.stream()
						.filter((a) -> evrangid.contains(String.valueOf(a.getEvrang()))).map((a) -> a);
				eventlistObjFull = listObjectViewStrim.collect(Collectors.toList());
			}

		}
		if (eventFoundSession.get(attr.getSessionId()) != null) {

			myKeyword2 = eventFoundSession.get(attr.getSessionId()).getKeyword2();

		}
		if (keyword != null) {

			myKeyword = eventFoundSession.get(attr.getSessionId()).getKeyword();

		}
		if (myKeyword2 != null && !myKeyword2.equals("")) {
			listObjectViewStrim = eventlistObjFull.stream()
					.filter((a) -> a.getAccountid().toUpperCase().indexOf(myKeyword2.toUpperCase()) > -1).map((a) -> a);

			eventlistObjFull = listObjectViewStrim.collect(Collectors.toList());
		}
		if (myKeyword != null && !myKeyword.equals("")) {
			listObjectViewStrim = eventlistObjFull.stream()
					.filter((a) -> a.getEvents().toUpperCase().indexOf(myKeyword.toUpperCase()) > -1).map((a) -> a);

			eventlistObjFull = listObjectViewStrim.collect(Collectors.toList());
		}
		mav.addObject("eventlistObjFull", eventlistObjFull);
		if (eventFoundSession.get(attr.getSessionId()) != null) {
			mySizeColumn = eventFoundSession.get(attr.getSessionId()).getSizeColumn();
		}
		if (mySizeColumn == null)
			mySizeColumn = 50;
		int fromList = mySizeColumn;
		int toList = eventlistObjFull.size();
		if (fromList < toList)
			eventlistObjFull.subList(fromList, toList).clear();

		List<Evrang> evrangFull = evrangService.getEvrangFull();
		mav.addObject("evrangFull", evrangFull);

		System.out.println("size " + eventlistObjFull.size());
		if (eventlistObjFull.size() > 0 && nameFunc != null && nameFunc.equals("ExportFile")) {
			try {
				if (fileName.equals("")) {
					throw new NotFoundFileExportException();
				} else {
					int column = 9;
					System.out.println("size " + eventlistObjFull.size());
					EventListV[] array = new EventListV[eventlistObjFull.size()];
					array = eventlistObjFull.toArray(array);
					String[][] array2 = new String[eventlistObjFull.size() + 1][column];
					array2[0] = new String[] { "Объект", "Код", "Тип кода", "Ранк", "Дата", "Время", "Гр.", "Название",
							"Шл.", "Название", "Описание события" };
					for (int i = 0; i < eventlistObjFull.size(); i++) {
						array2[i + 1] = new String[] { String.valueOf(array[i].getAccountid()),
								String.valueOf(array[i].getEvcode()), array[i].getNamecode(), array[i].getNameEvrang(),
								array[i].getEvdataDate(), array[i].getEvdataTime(), array[i].getEvgrnumS(),
								array[i].getPerifname(), array[i].getEvznnumVal(), array[i].getEvzndataVal(),
								array[i].getEvents()

						};
					}
					Runnable fileHandlingThread = new Runnable() {
						public void run() {
							// open the file
							// write to the file
							// flush the file
							try {
								ExportFile.writeToXls(fileName, array2);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								// e.printStackTrace();
								System.out.println("-----------error");
								// mav.addObject("error",
								// "Данный файл уже создан и открыт. Закройте его или введите новое имя");

							}
						}
					};

					new Thread(fileHandlingThread).start();

					System.out.println("----ExportFile-----" + eventlistObjFull.size());
				}
			} catch (NotFoundFileExportException e) {
				mav.addObject("error", e);

			}
		}
		// ------------------------------------------Оптимизация БД------

		// (вычисляем времяпоследних обновлений таблиц которые пишутся в таблицу
		// update_last тригером

		Integer flagUpdateClick = 0;
		List<UpdateLast> updatelast = updateLastService.getUpdateLast();// Получаем список последних обновлений таблиц

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

		} else {
			flagUpdateClick = 0;

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

		mav.addObject("flagUpdateClickEvent", flagUpdateClick); // переменные обновления

		mav.addObject("flagUpdateEventlistEvent", flagUpdateEventlist);

		// ----------------------------------------------------------------------------------------------
		if (idEvent != null)
			for (EventListV i : eventlistObjFull) {
				i.setCurrentIdinfo(new Long(idEvent));

			}
		// mav.addObject("eventlistObjFull", eventlistObjFull);

		System.out.println("--------------------event-post---------------------------" + MyidinfoEvent);
		System.out.println("size " + eventlistObjFull.size());
		System.out.println(date);
		System.out.println(date1);

		System.out.println("evrangstr" + evrangidStr);
		System.out.println(keyword);
		System.out.println("idEvent" + idEvent);
		System.out.println("myclick " + myclick);
		return mav;
	}

}
