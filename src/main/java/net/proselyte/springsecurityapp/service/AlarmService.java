package net.proselyte.springsecurityapp.service;

import java.util.List;

import net.proselyte.springsecurityapp.model.Alarm;
import net.proselyte.springsecurityapp.model.Groups;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekaterina Nosenko
 * @version 1.0
 */

public interface AlarmService {

	public void save(Alarm alarm);

	public void saveList(List<Alarm> alarm);

	public Alarm findObject(long alarm_id);

	public List<Alarm> findFullNew();

	public Alarm findObjectIdinfo(long idinfo);

	public List<Alarm> findObjectIdinfoAll(long idinfo);

	public List<Alarm> findGroupsIdinfo(long idinfo);

	public void saveGroupLeft(Alarm alarmTakeProcesNew, Groups groups);

	public List<Alarm> canArrivedGroup(long idinfo, long group_id);

	public void saveCancelGroup(Groups groups, Alarm alarmarrivednew);

	public List<Alarm> newAlarmIdinfo(long idinfo);

	public List<Alarm> findIdnfo(Long idinfo);

	public void takeProcessing(Long idinfo, Long userId) throws CloneNotSupportedException;

	public void GroupLeft(Integer groups_id, Long userId, Long alarm_id, Long idinfo) throws CloneNotSupportedException;

	public void CancelGroups(Integer groups_id, Long userId, Long alarm_id, Long idinfo);

	public void CancelAlarm(Integer groups_id, Long userId, Long alarm_id, Long idinfo, Integer id_alarm_cancel)
			throws CloneNotSupportedException;

	public void GroupArrived(Long idinfo, Integer groups_id, Long userId);

	public void GroupResponse(Long idinfo, Integer groups_id);

	public void delete(Alarm alarm);

	public void delete(List<Alarm> alarm);
}
