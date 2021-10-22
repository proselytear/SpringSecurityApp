package net.proselyte.springsecurityapp.controller;

import static net.proselyte.springsecurityapp.var.StaticVarDefault.dateMax;
import static net.proselyte.springsecurityapp.var.StaticVarDefault.dateMin;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.proselyte.springsecurityapp.model.Alarm;
import net.proselyte.springsecurityapp.model.AlarmArchiveView;
import net.proselyte.springsecurityapp.model.AlarmNew;
import net.proselyte.springsecurityapp.model.AlarmStatusName;
import net.proselyte.springsecurityapp.model.Card;
import net.proselyte.springsecurityapp.model.CardExchange;
import net.proselyte.springsecurityapp.model.CardNotation;
import net.proselyte.springsecurityapp.model.Employees;
import net.proselyte.springsecurityapp.model.EventListV;
import net.proselyte.springsecurityapp.model.Groups;
import net.proselyte.springsecurityapp.model.Maineq;
import net.proselyte.springsecurityapp.model.ObjectView;
import net.proselyte.springsecurityapp.model.Perifinfo;
import net.proselyte.springsecurityapp.model.PerifinfoResponsible;
import net.proselyte.springsecurityapp.model.User;
import net.proselyte.springsecurityapp.service.AlarmArchiveViewService;
import net.proselyte.springsecurityapp.service.AlarmFullService;
import net.proselyte.springsecurityapp.service.AlarmNewService;
import net.proselyte.springsecurityapp.service.AlarmService;
import net.proselyte.springsecurityapp.service.AlarmStatusNameService;
import net.proselyte.springsecurityapp.service.CardExchangeService;
import net.proselyte.springsecurityapp.service.CardNotationService;
import net.proselyte.springsecurityapp.service.CardService;
import net.proselyte.springsecurityapp.service.EmployeesService;
import net.proselyte.springsecurityapp.service.EventListVService;
import net.proselyte.springsecurityapp.service.GroupsService;
import net.proselyte.springsecurityapp.service.MaineqService;
import net.proselyte.springsecurityapp.service.ObjectViewService;
import net.proselyte.springsecurityapp.service.PerifinfoResponsibleService;
import net.proselyte.springsecurityapp.service.PerifinfoService;
import net.proselyte.springsecurityapp.service.UserService;

/**
 * Controller for {@link net.proselyte.springsecurityapp.model.User}'s pages.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Controller
public class ArchivController {
	@Autowired
	private AlarmFullService alarmfullService;

	@Autowired
	private AlarmArchiveViewService alarmarchiveviewService;

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private AlarmNewService alarmnewService;

	long Myidinfo = 0;

	@Autowired
	private ObjectViewService objectViewService;

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

	@Autowired
	private EventListVService eventlistVService;

	@Autowired
	private EmployeesService employeesService;

	@Autowired
	private AlarmStatusNameService alarmstatusnameService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/archiv", method = RequestMethod.POST)

	public @ResponseBody ModelAndView archiv(@RequestParam(required = false) String date,
			@RequestParam(required = false) String date1, @RequestParam(required = false) String keyword,
			@RequestParam(required = false) Long statusnameid, @RequestParam(required = false) Integer sizeColumnArch,
			@RequestParam(required = false) Integer alarm_id, @RequestParam(required = false) String nameFunc)
			throws Exception {
		ModelAndView mav = new ModelAndView("objectarchiv");

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
		List<AlarmArchiveView> alarmarchive;
		Stream<AlarmArchiveView> alarmFullStrim;
		Long idinfo = null;
		System.out.println("-------archive post---------------");
		List<EventListV> eventlistV;
		if (alarm_id != null) {
			Alarm alarm = alarmService.findObject(alarm_id);
			if (alarm != null)
				idinfo = alarm.getIdIdinfo();

		}
		if (idinfo != null) {

			Card listCard = cardService.findObject(idinfo);
			mav.addObject("listCard", listCard);
			// getAccountID()
			List<Perifinfo> perifinfoBan1 = perifinfoService.findBan();
			mav.addObject("perifinfoBan", perifinfoBan1);

			Perifinfo perifinfoBanIdinfo = perifinfoService.findBanIdinfo(idinfo);
			mav.addObject("perifinfoBanIdinfo", perifinfoBanIdinfo);
			System.out.println("------------------perifinfoBanIdinfo=" + perifinfoBanIdinfo.getMaineq().getAccountid());

			Maineq maineq = maineqService.findObject(idinfo);
			mav.addObject("maineq", maineq);

			Perifinfo perifinfo = perifinfoService.findObject(idinfo);
			mav.addObject("perifinfo", perifinfo);

			List<Groups> groups = groupsService.findObject(idinfo);
			mav.addObject("groups", groups);

			List<Perifinfo> perifinfoSensor = perifinfoService.findSensor(idinfo);
			mav.addObject("perifinfoSensor", perifinfoSensor);

			List<Perifinfo> perifinfoKey = perifinfoService.findKey(idinfo);
			mav.addObject("perifinfoKey", perifinfoKey);

			List<PerifinfoResponsible> perifinfoResponsible = perifinfoResponsibleService.findObject(idinfo);
			mav.addObject("perifinfoResponsible", perifinfoResponsible);

			;
			Myidinfo = idinfo;

			eventlistV = eventlistVService.findObject(idinfo);

			mav.addObject("eventlistV", eventlistV);

			List<CardNotation> listCardNotation = cardNotationService.findObject(idinfo);
			mav.addObject("listCardNotation", listCardNotation);
			List<CardExchange> listCardExchange = cardExchangeService.findObject(idinfo);
			mav.addObject("listCardExchange", listCardExchange);

			ObjectView objectviewId = objectViewService.findId(idinfo);
			mav.addObject("objectviewId", objectviewId);

			List<Groups> groupsFree = groupsService.findObjectActive(idinfo);
			mav.addObject("groupsFree", groupsFree);

			List<Groups> groupsBusy = groupsService.findObjectNotActive(idinfo);
			mav.addObject("groupsBusy", groupsBusy);
			for (Groups g : groupsBusy) {
				System.out.println(g.getName());
			}
			System.out.println("---------------------groupsBusy" + groupsBusy);

			List<Groups> alarmGroups = groupsService.findGrouptByIdinfo(idinfo);
			mav.addObject("alarmGroups", alarmGroups);

			List<Perifinfo> groupForAccount = perifinfoService.getGroupbyIdinfo(idinfo);
			List<Perifinfo> zoneForAccount = perifinfoService.getZonebyIdinfo(idinfo);
			mav.addObject("groupForAccount", groupForAccount);
			mav.addObject("zoneForAccount", zoneForAccount);

		}

		if (date == null || date.equals(""))
			date = dateMin;
		if (date1 == null || date1.equals(""))
			date1 = dateMax;
		if (date != dateMin || date1 != dateMax) {
			alarmarchive = alarmarchiveviewService.findObjectData(date.replace("T", " "), date1.replace("T", " "));

		} else {
			alarmarchive = alarmarchiveviewService.findAlarmArchive();
		}
		if (keyword != null && !keyword.equals("")) {
			alarmFullStrim = alarmarchive.stream()
					.filter((a) -> a.getAccountid().toUpperCase().indexOf(keyword.toUpperCase()) > -1).map((a) -> a);

			alarmarchive = alarmFullStrim.collect(Collectors.toList());
		}
		if (statusnameid != null && statusnameid > -1) {

			alarmFullStrim = alarmarchive.stream()
					.filter((a) -> a.getStatusAlarmId().toString().equals(statusnameid.toString())).map((a) -> a);

			alarmarchive = alarmFullStrim.collect(Collectors.toList());
		}
		if (sizeColumnArch == null)
			sizeColumnArch = 50;
		int fromList = sizeColumnArch;
		int toList = alarmarchive.size();
		if (fromList < toList)
			alarmarchive.subList(fromList, toList).clear();

		List<Employees> listTechnic = employeesService.findPosition();

		mav.addObject("listTechnic", listTechnic);
		// Size begin
		List<AlarmNew> alarmnew = alarmnewService.findAlarmNew();

		Integer alarmfullSize = 0;

		for (AlarmNew i : alarmnew) {
			if (i.getCount_alarm_new() != null)
				alarmfullSize += i.getCount_alarm_new().intValue();
		}

		mav.addObject("alarmfullSize", alarmfullSize);

		List<Perifinfo> perifinfoBanFull = perifinfoService.findBanFull();
		Integer perifinfoBanFullSize = perifinfoBanFull.size();
		if (perifinfoBanFullSize > 0)
			mav.addObject("perifinfoBanFullSize", perifinfoBanFullSize);

//Size end
		List<AlarmStatusName> alarmstatusname = alarmstatusnameService.findObjectStatus();
		mav.addObject("alarmstatusname", alarmstatusname);
		System.out.println("------------size-----------" + alarmstatusname.size());

		for (AlarmArchiveView i : alarmarchive) {
			if (alarm_id != null)
				i.setCurrentIdinfo(new Long(alarm_id));

		}
		mav.addObject("alarmarchive", alarmarchive);
		System.out.println("alarmfullSize " + alarmfullSize);
		return mav;
	}

	@RequestMapping(value = "/archiv", method = RequestMethod.GET)

	public ModelAndView archiv(ModelAndView mv) {
		ModelAndView mav = new ModelAndView("objectarchiv");
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
		List<AlarmArchiveView> alarmarchive = alarmarchiveviewService.findAlarmArchive();
		mav.addObject("alarmarchive", alarmarchive);
		System.out.println("-------archive get---------------");
		// Size begin
		List<AlarmArchiveView> alarmfull = alarmarchiveviewService.findAlarmNotRead();

		Integer alarmfullSize = alarmfull.size();
		mav.addObject("alarmfullSize", alarmfullSize);
//Size end

		return mav;
	}
}
