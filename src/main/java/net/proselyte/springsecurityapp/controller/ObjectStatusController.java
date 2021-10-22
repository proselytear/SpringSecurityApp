package net.proselyte.springsecurityapp.controller;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import static net.proselyte.springsecurityapp.var.StaticVarDefault.dateMax;
import static net.proselyte.springsecurityapp.var.StaticVarDefault.dateMin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.proselyte.springsecurityapp.calculation.function.Schema;
import net.proselyte.springsecurityapp.model.AlarmFull;
import net.proselyte.springsecurityapp.model.AlarmNew;
import net.proselyte.springsecurityapp.model.Card;
import net.proselyte.springsecurityapp.model.CardExchange;
import net.proselyte.springsecurityapp.model.CardNotation;
import net.proselyte.springsecurityapp.model.Defect;
import net.proselyte.springsecurityapp.model.Employees;
import net.proselyte.springsecurityapp.model.EventListV;
import net.proselyte.springsecurityapp.model.FileColumn;
import net.proselyte.springsecurityapp.model.Groups;
import net.proselyte.springsecurityapp.model.Maineq;
import net.proselyte.springsecurityapp.model.ObjectView;
import net.proselyte.springsecurityapp.model.Perifinfo;
import net.proselyte.springsecurityapp.model.PerifinfoResponsible;
import net.proselyte.springsecurityapp.model.User;
import net.proselyte.springsecurityapp.service.AlarmFullService;
import net.proselyte.springsecurityapp.service.AlarmNewService;
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
public class ObjectStatusController {
	@Autowired
	private AlarmFullService alarmfullService;

	@Autowired
	private ObjectViewService objectViewService;

	@Autowired
	private AlarmNewService alarmnewService;

	@Autowired
	private PerifinfoService perifinfoService;

	@Autowired
	private CardService cardService;

	@Autowired
	private EmployeesService employeesService;

	@Autowired
	private CardNotationService cardNotationService;

	@Autowired
	private CardExchangeService cardExchangeService;

	@Autowired
	private PerifinfoResponsibleService perifinfoResponsibleService;

	@Autowired
	private GroupsService groupsService;

	@Autowired
	private MaineqService maineqService;

	@Autowired
	private EventListVService eventlistVService;

	@Autowired
	private UserService userService;

	ModelAndView mav;

	String statusOchFull;

	@RequestMapping(value = "/objectstatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")

	public ModelAndView objectstatus(@RequestParam(required = false) String statusOch,
			@RequestParam(required = false) Long idinfo, @RequestParam(required = false) Long city_id,
			@RequestParam(required = false) String date, @RequestParam(required = false) String date1,
			@RequestParam(required = false) Integer myclick, @RequestParam(required = false) Integer sizeColumn,
			@RequestParam(required = false) String panelcurrent) {
		System.out.println("-----------post status--------------" + statusOch);
		if (statusOch != null) {
			statusOchFull = new String(statusOch.getBytes(ISO_8859_1), UTF_8);
		}
		mav = new ModelAndView("objectstatus");

		String employeeFIO = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(userName);
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

		List<ObjectView> listObjectViewSconn = objectViewService.findSconnection();
		Integer objectViewSconnSize = listObjectViewSconn.size();
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
		mav.addObject("alDefectSize", alDefectSize);
		List<Perifinfo> perifinfoBan = perifinfoService.findBan();
		Integer perifinfoSensorSize = perifinfoBan.size();
		mav.addObject("perifinfoSensorSize", perifinfoSensorSize);

		List<Perifinfo> perifinfoBanFull = perifinfoService.findBanFull();
		Integer perifinfoBanFullSize = perifinfoBanFull.size();
		if (perifinfoBanFullSize > 0)
			mav.addObject("perifinfoBanFullSize", perifinfoBanFullSize);
		// Size end

		List<ObjectView> listObjectViewOchStatus = null;

		if (city_id == null || city_id == -1) {
			listObjectViewOchStatus = objectViewService.findAll();
		} else {
			listObjectViewOchStatus = objectViewService.getAll(city_id);
		}
		Stream<ObjectView> listObjectViewStrim = listObjectViewOchStatus.stream()
				.filter((a) -> a.getStatusy().equals(statusOchFull)).map((a) -> a);

		listObjectViewOchStatus = listObjectViewStrim.collect(Collectors.toList());

		for (ObjectView i : listObjectViewOchStatus) {
			i.setCurrentIdinfo(idinfo);
		}

		if (listObjectViewOchStatus != null)
			mav.addObject("listObjectViewOchStatus", listObjectViewOchStatus);
		mav.addObject("objectViewSconnSize", objectViewSconnSize);

		// panelright
		List<EventListV> eventlistV;
		List<Perifinfo> perifinfoBan1 = perifinfoService.findBan();
		mav.addObject("perifinfoBan", perifinfoBan1);
		if (myclick != null && myclick == 1) {

			// getAccountID()

			Perifinfo perifinfoBanIdinfo = perifinfoService.findObject(idinfo);
			mav.addObject("perifinfoBanIdinfo", perifinfoBanIdinfo);

			List<Perifinfo> groupForAccount = perifinfoService.getGroupbyIdinfo(idinfo);
			List<Perifinfo> zoneForAccount = perifinfoService.getZonebyIdinfo(idinfo);
			mav.addObject("groupForAccount", groupForAccount);
			mav.addObject("zoneForAccount", zoneForAccount);

			Perifinfo perifinfo = perifinfoService.findObject(idinfo);
			mav.addObject("perifinfo", perifinfo);

			List<PerifinfoResponsible> perifinfoResponsible = perifinfoResponsibleService.findObject(idinfo);
			mav.addObject("perifinfoResponsible", perifinfoResponsible);

			ObjectView objectviewId = objectViewService.findId(idinfo);
			mav.addObject("objectviewId", objectviewId);

			List<Groups> alarmGroups = groupsService.findGrouptByIdinfo(idinfo);
			mav.addObject("alarmGroups", alarmGroups);

			ObjectView accountSchema = objectViewService.findId(idinfo);
			String strAccountSchema = accountSchema.getAccountID();

			List<FileColumn> schemaList = Schema.getFileMaineq(strAccountSchema);

			mav.addObject("schemaList", schemaList);

		}

		if (idinfo != null && idinfo != 0 && panelcurrent != null) {
			List<Perifinfo> perifinfoBanIdinfoFull = perifinfoService.findBanIdinfoFull(idinfo);
			mav.addObject("perifinfoBanIdinfoFull", perifinfoBanIdinfoFull);

			Integer perifinfoSensorSizeFull = perifinfoBanIdinfoFull.size();
			System.out.println("perifinfoSensorSizeFull " + perifinfoSensorSizeFull);
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

		List<Employees> listTechnic = employeesService.findPosition();
		mav.addObject("listTechnic", listTechnic);

		// panelright end
		return mav;
	}

	@RequestMapping(value = "/objectstatus", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")

	public ModelAndView objectstatusget(@RequestParam(required = false) Long city_id) {
		mav = new ModelAndView("objectstatus");

		String employeeFIO = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(userName);
		Long userId = user.getId();
		Employees employees = user.getEmployees();
		if (employees != null)
			employeeFIO = employees.getFullName();
		employeeFIO = (null != employeeFIO) ? employeeFIO : userName;
		mav.addObject("employeeFIO", employeeFIO);

		// Size begin
		List<AlarmFull> alarmfull = alarmfullService.findAlarmNotRead();
		if (alarmfull != null) {
			mav.addObject("alarmfull", alarmfull);

			Integer alarmfullSize = alarmfull.size();
			mav.addObject("alarmfullSize", alarmfullSize);

		}

		List<ObjectView> listObjectViewSconn = objectViewService.findSconnection();
		Integer objectViewSconnSize = listObjectViewSconn.size();
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
		mav.addObject("alDefectSize", alDefectSize);
		List<Perifinfo> perifinfoBan = perifinfoService.findBan();
		Integer perifinfoSensorSize = perifinfoBan.size();
		mav.addObject("perifinfoSensorSize", perifinfoSensorSize);

		// Size end
		List<ObjectView> listObjectViewOchStatus = null;
		if (city_id == null || city_id == -1) {
			listObjectViewOchStatus = objectViewService.findAll();
		} else {
			listObjectViewOchStatus = objectViewService.getAll(city_id);
		}
		Stream<ObjectView> listObjectViewStrim = listObjectViewOchStatus.stream()
				.filter((a) -> a.getStatusy().equals(statusOchFull)).map((a) -> a);

		listObjectViewOchStatus = listObjectViewStrim.collect(Collectors.toList());

		if (listObjectViewOchStatus != null)
			mav.addObject("listObjectViewOchStatus", listObjectViewOchStatus);
		mav.addObject("objectViewSconnSize", objectViewSconnSize);
		System.out.println("-----------get status--------------");

		// panelright
		List<Perifinfo> perifinfoBan1 = perifinfoService.findBan();
		mav.addObject("perifinfoBan", perifinfoBan1);

		return mav;
	}

}
