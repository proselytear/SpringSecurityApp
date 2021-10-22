package net.proselyte.springsecurityapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.proselyte.springsecurityapp.model.AlarmFull;
import net.proselyte.springsecurityapp.model.AlarmNew;
import net.proselyte.springsecurityapp.model.Employees;
import net.proselyte.springsecurityapp.model.Perifinfo;
import net.proselyte.springsecurityapp.model.User;
import net.proselyte.springsecurityapp.service.AlarmFullService;
import net.proselyte.springsecurityapp.service.AlarmNewService;
import net.proselyte.springsecurityapp.service.EmployeesService;
import net.proselyte.springsecurityapp.service.PerifinfoService;
import net.proselyte.springsecurityapp.service.UserService;

/**
 * Controller for {@link net.proselyte.springsecurityapp.model.User}'s pages.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Controller

public class StandController {
	@Autowired
	private AlarmFullService alarmfullService;

	@Autowired
	private PerifinfoService perifinfoService;

	@Autowired
	private EmployeesService employeesService;

	@Autowired
	private AlarmNewService alarmnewService;

	@Autowired
	private UserService userService;

	ModelAndView mav;

	String statusOchFull;

	@RequestMapping(value = "/objectStand", method = RequestMethod.GET)

	public ModelAndView objectstand() {
		System.out.println("--------------------groups get----------------------------");
		ModelAndView mav = new ModelAndView("objectstand");
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
		List<AlarmFull> alarmfull = alarmfullService.findAlarmNotRead();
		if (alarmfull != null) {
			mav.addObject("alarmfull", alarmfull);

			Integer alarmfullSize = alarmfull.size();

			mav.addObject("alarmfullSize", alarmfullSize);

		}
//Size end

		List<Perifinfo> perifinfoBanFull = perifinfoService.findBanFull();
		mav.addObject("perifinfoBanFull", perifinfoBanFull);
		System.out.println("---get stand---");
		return mav;

	}

	@RequestMapping(value = "/objectstand", method = RequestMethod.POST)

	public ModelAndView objectstand(@RequestParam(required = false) String nameFunc,
			@RequestParam(required = false) Long id_groups_status_name,
			@RequestParam(required = false) Integer groups_id, @RequestParam(required = false) String accountId,
			@RequestParam(required = false) Long idinfoStand) {

		ModelAndView mav = new ModelAndView("objectstand");
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
		System.out.println("idinfoStand" + idinfoStand);

		if (idinfoStand != null) {

			Perifinfo perifinfoBanIdinfo = perifinfoService.findBanIdinfoStand(Math.abs(idinfoStand));
			System.out.println(idinfoStand);
			System.out.println("----------------------perifinfoBanIdinfo-" + perifinfoBanIdinfo);
			mav.addObject("perifinfoBanIdinfo", perifinfoBanIdinfo);

			List<Perifinfo> groupForAccount = perifinfoService.getGroupbyIdinfo(Math.abs(idinfoStand));
			List<Perifinfo> zoneForAccount = perifinfoService.getZonebyIdinfo(Math.abs(idinfoStand));
			mav.addObject("groupForAccount", groupForAccount);
			mav.addObject("zoneForAccount", zoneForAccount);

		}
		List<Employees> listTechnic = employeesService.findPosition();

		mav.addObject("listTechnic", listTechnic);
		List<Perifinfo> perifinfoBanFull = perifinfoService.findBanFull();

		for (Perifinfo i : perifinfoBanFull) {
			i.setCurrentIdinfo(idinfoStand);
			System.out.println(idinfoStand);
		}
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

		System.out.println("--------------------stand-post---------------------------" + idinfoStand);
		return mav;
	}

}
