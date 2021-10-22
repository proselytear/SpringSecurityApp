package net.proselyte.springsecurityapp.controller;

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

import net.proselyte.springsecurityapp.calculation.MyException.NotFoundDBException;
import net.proselyte.springsecurityapp.model.Alarm;
import net.proselyte.springsecurityapp.model.AlarmNew;
import net.proselyte.springsecurityapp.model.Employees;
import net.proselyte.springsecurityapp.model.Groups;
import net.proselyte.springsecurityapp.model.GroupsStatusName;
import net.proselyte.springsecurityapp.model.ObjectView;
import net.proselyte.springsecurityapp.model.Perifinfo;
import net.proselyte.springsecurityapp.model.User;
import net.proselyte.springsecurityapp.service.AlarmFullService;
import net.proselyte.springsecurityapp.service.AlarmNewService;
import net.proselyte.springsecurityapp.service.AlarmService;
import net.proselyte.springsecurityapp.service.GroupsService;
import net.proselyte.springsecurityapp.service.GroupsStatusNameService;
import net.proselyte.springsecurityapp.service.ObjectViewService;
import net.proselyte.springsecurityapp.service.PerifinfoService;
import net.proselyte.springsecurityapp.service.UserService;
import net.proselyte.springsecurityapp.var.StaticVarStatus;

/**
 * Controller for {@link net.proselyte.springsecurityapp.model.User}'s pages.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Controller

public class GroupsController {
	@Autowired
	private AlarmFullService alarmfullService;

	@Autowired
	private ObjectViewService objectViewService;

	@Autowired
	private PerifinfoService perifinfoService;

	@Autowired
	private GroupsService groupsService;

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private GroupsStatusNameService groupsStatusNameService;

	@Autowired
	private AlarmNewService alarmnewService;

	@Autowired
	private UserService userService;

	ModelAndView mav;

	String statusOchFull;

	@RequestMapping(value = "/objectgroupsstatus", method = RequestMethod.GET)

	public ModelAndView groups() {
		System.out.println("--------------------groups get----------------------------");
		ModelAndView mav = new ModelAndView("objectgroupsstatus");

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
		List<Perifinfo> perifinfoBanFull = perifinfoService.findBanFull();
		Integer perifinfoBanFullSize = perifinfoBanFull.size();
		if (perifinfoBanFullSize > 0)
			mav.addObject("perifinfoBanFullSize", perifinfoBanFullSize);
//Size end

		List<Groups> groupsStatus = groupsService.getGroupsStatus();
		mav.addObject("groupsStatus", groupsStatus);
		List<GroupsStatusName> groupsStatusName = groupsStatusNameService.findAll();
		mav.addObject("groupsStatusName", groupsStatusName);
		return mav;

	}

	@RequestMapping(value = "/objectgroupsstatus", method = RequestMethod.POST)

	public ModelAndView groups(@RequestParam(required = false) String nameFunc,
			@RequestParam(required = false) Long id_groups_status_name,
			@RequestParam(required = false) Integer groups_id, @RequestParam(required = false) String accountId) {

		ModelAndView mav = new ModelAndView("objectgroupsstatus");
		String strAccountId = null;
		if (accountId != null) {
			strAccountId = accountId.replace(",", "");
		}
		System.out.println("--------------------groups post----------------------------");
		System.out.println(nameFunc);
		System.out.println(id_groups_status_name);
		System.out.println(groups_id);
		System.out.println(accountId);
		System.out.println(nameFunc);
		String messageSuccess;

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

		List<GroupsStatusName> groupsStatusName = groupsStatusNameService.findAll();
		mav.addObject("groupsStatusName", groupsStatusName);

		if (groups_id != null) {
			System.out.println("----set-----");
			Groups groupsStOne = groupsService.findObjectById(groups_id);
			mav.addObject("groupsStOne", groupsStOne);

		}
		System.out.println(199);
		List<Groups> groupsStatus = groupsService.getGroupsStatus();
		System.out.println(299);
		if (groups_id != null)
			for (Groups i : groupsStatus) {

				i.setCurrentIdinfo(new Long(groups_id));
			}
		mav.addObject("groupsStatus", groupsStatus);
		final String fStrAccontId = strAccountId;
		try {
			if (nameFunc != null && nameFunc.equals("myModalSetGroupStatus") && groups_id != null
					&& id_groups_status_name != null) {
				System.out.println("------------Set Group Status accountId" + accountId);

				Groups groupsStOne = groupsService.findObjectById(groups_id);
				List<ObjectView> groupsObject = objectViewService.getObject();
				List<ObjectView> setGroupsAccount = new ArrayList<ObjectView>();
				if (fStrAccontId != null) {
					Stream<ObjectView> groupsAccount = groupsObject.stream()
							.filter((a) -> fStrAccontId.equals(a.getAccountID()));
					setGroupsAccount = groupsAccount.collect(Collectors.toList());
				}
				Long idinfoold = groupsStOne.getId_idinfo();
				Long idinfonew = null;
				if (accountId.contentEquals("") && id_groups_status_name == 4l)
					throw new NotInputAccountIdException();
				if (setGroupsAccount.size() > 0)
					idinfonew = setGroupsAccount.get(0).getIdinfo();
				else if (id_groups_status_name == 4l && setGroupsAccount.size() == 0)
					throw new NotFoundDBException();

				Groups groups;

				if (idinfoold != null && groupsStOne.getGroups_status_id() == 5l) {
					Alarm alarmLast = alarmService.findObjectIdinfo(idinfoold);
					Alarm alarmarrivednew = new Alarm(idinfoold, StaticVarStatus.stGroupCancel, alarmLast.getNote(),
							userId, groups_id, 1, alarmLast.getEvcode(), alarmLast.getIdEventList());
					alarmarrivednew.setCurrentAldata();
					alarmarrivednew.setIsread(1);
					groups = groupsService.findObjectById(groups_id);
					groups.setId_idinfo(idinfonew);
					groups.setGroups_status_id(id_groups_status_name);
					alarmService.saveCancelGroup(groups, alarmarrivednew);

				} else {
					groupsStOne.setGroups_status_id(id_groups_status_name);
					groupsStOne.setId_idinfo(idinfonew);
					groupsService.save(groupsStOne);

				}
				messageSuccess = "Запрос отправлен.";
				mav.addObject("messageSuccess", messageSuccess);

			}
		} catch (NotInputAccountIdException e) {
			System.out.println("-------------NotInputAccountIdException");
			mav.addObject("error", e);
			return mav;
		} catch (NotFoundDBException e) {
			System.out.println("-------------NotFoundDBException");
			mav.addObject("error", e);
			return mav;
		}

		return mav;
	}

}
