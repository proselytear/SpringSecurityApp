package net.proselyte.springsecurityapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.proselyte.springsecurityapp.dao.AlarmDao;
import net.proselyte.springsecurityapp.dao.GroupsDao;
import net.proselyte.springsecurityapp.model.Alarm;
import net.proselyte.springsecurityapp.model.Groups;
import net.proselyte.springsecurityapp.var.StaticVarStatus;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	private AlarmDao alarmDao;

	@Autowired
	private GroupsDao groupsDao;

	@Autowired
	private GroupsService groupsService;

	@Autowired
	private AlarmArchiveViewService alarmArchiveService;

	@Autowired
	private AlarmCancelService alarmCancelService;

	@Override
	public void save(Alarm alarm) {
		alarmDao.save(alarm);
	}

	@Override
	public void saveList(List<Alarm> alarm) {
		alarmDao.save(alarm);
	}

	public Alarm findObject(long alarm_id) {
		return alarmDao.findObject(alarm_id);
	}

	public Alarm findObjectIdinfo(long idinfo) {
		return alarmDao.findObjectIdinfo(idinfo);
	}

	public List<Alarm> findObjectIdinfoAll(long idinfo) {
		return alarmDao.findObjectIdinfoAll(idinfo);
	}

	public List<Alarm> findGroupsIdinfo(@Param("idinfo") long idinfo) {
		return alarmDao.findGroupsIdinfo(idinfo);
	}

	public List<Alarm> findFullNew() {
		return alarmDao.findFullNew();
	}

	@Transactional
	public void saveGroupLeft(Alarm alarmTakeProcesNew, Groups groups) {
		groupsDao.save(groups);
		alarmDao.save(alarmTakeProcesNew);

	}

	public List<Alarm> canArrivedGroup(long idinfo, long group_id) {
		return alarmDao.canArrivedGroup(idinfo, group_id);
	}

	public void saveCancelGroup(Groups groups, Alarm alarmarrivednew) {
		groupsDao.save(groups);
		alarmDao.save(alarmarrivednew);
	}

	public List<Alarm> newAlarmIdinfo(long idinfo) {
		return alarmDao.newAlarmIdinfo(idinfo);
	}

	@Override
	public List<Alarm> findIdnfo(Long idinfo) {
		// TODO Auto-generated method stub
		return alarmDao.findIdnfo(idinfo);
	}

	/**
	 * Взять объект в обработку userId ид залогин. пользователя idinfo ид выбранной
	 * группы на объекте
	 */
	@Override
	public void takeProcessing(Long idinfo, Long userId) throws CloneNotSupportedException {
		/*
		 * Когда пользователь берет в обработку текущая строка стает прочитанной со
		 * временем срабатывания тревоги и создается еще одна строка со статусом принята
		 * со временем принятия оператором сигнала
		 */
		List<Alarm> alarmTakeProcessingFull = this.findIdnfo(idinfo);
		List<Alarm> alarmTakeProcesFull = new ArrayList<Alarm>();
		for (Alarm alarmTakeProcessing : alarmTakeProcessingFull) {
			alarmTakeProcessing.setIsread(1);// Присваиваем свойство прочитанное

			Alarm alarmTakeProcesNew = (Alarm) alarmTakeProcessing.clone();
			alarmTakeProcesNew.setAlarm_id(null);
			alarmTakeProcesNew.setCurrentAldata();
			alarmTakeProcesNew.setIdOperator(userId);
			alarmTakeProcesNew.setStatusAlarmId(StaticVarStatus.stAccepted);// Принятая тревога тревога
			alarmTakeProcesFull.add(alarmTakeProcesNew);
			alarmTakeProcesFull.add(alarmTakeProcessing);
		}
		this.saveList(alarmTakeProcesFull);
	}

	/**
	 * Вызвать группу реагирования
	 * 
	 * @param groups_id ид группы реагирования
	 * @param userId    ид залогиневавшегося пользователя
	 * @param alarm_id  ид тревоги
	 * @param idinfo    ид группы на объекте
	 * @throws CloneNotSupportedException
	 */
	public void GroupLeft(Integer groups_id, Long userId, Long alarm_id, Long idinfo)
			throws CloneNotSupportedException {
		Alarm alarmTakeProcessing = this.findObject(alarm_id);
//создаем новую строку с параметрами вызова группы и временем когда оператор вызвал группу
		Alarm alarmTakeProcesNew = (Alarm) alarmTakeProcessing.clone();
		alarmTakeProcesNew.setAlarm_id(null);
		alarmTakeProcesNew.setIdOperator(userId);
		alarmTakeProcesNew.setStatusAlarmId(StaticVarStatus.stGroupLeft);
		alarmTakeProcesNew.setCurrentAldata();
		alarmTakeProcesNew.setIdGroup(groups_id);
//когда мы отправляем группу на объект во вкладке группы также признак что группа на осмотре объекта и пишем номер группы на осматриваемом объекте
		Groups groupleft = groupsService.findObjectById(groups_id);
		if (groupleft != null)
			groupleft.setId_idinfo(idinfo);
		groupleft.setGroups_status_id((StaticVarStatus.groupInObject));

		this.saveList(Arrays.asList(alarmTakeProcessing, alarmTakeProcesNew));
		this.saveGroupLeft(alarmTakeProcesNew, groupleft);

		this.saveList(Arrays.asList(alarmTakeProcessing, alarmTakeProcesNew));
	}

	/**
	 * Отмана группы реагирования
	 * 
	 * @param groups_id ид группы реагирования
	 * @param userId    ид залогиневавшегося пользователя
	 * @param alarm_id  ид тревоги
	 * @param idinfo    ид группы на объекте
	 * @throws CloneNotSupportedException
	 */
	public void CancelGroups(Integer groups_id, Long userId, Long alarm_id, Long idinfo) {
		Alarm alarmLast = this.findObjectIdinfo(idinfo);
		// создаем новую строку с параметрами отмены группы и временем когда оператор
		// отменил группу
		Alarm alarmarrivednew = new Alarm(idinfo, StaticVarStatus.stGroupCancel, alarmLast.getNote(), userId, groups_id,
				1, alarmLast.getEvcode(), alarmLast.getIdEventList());
		alarmarrivednew.setCurrentAldata();
		alarmarrivednew.setIsread(1);
		Groups groups = groupsService.findObjectById(groups_id);
		groups.setId_idinfo(null);
		groups.setGroups_status_id(StaticVarStatus.groupFree);
		this.saveCancelGroup(groups, alarmarrivednew);
	}

	/**
	 * Отмана тревоги
	 * 
	 * @param groups_id       ид группы реагирования
	 * @param userId          ид залогиневавшегося пользователя
	 * @param alarm_id        ид тревоги
	 * @param idinfo          ид группы на объекте
	 * @param id_alarm_cancel причина отмены
	 * @throws CloneNotSupportedException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void CancelAlarm(Integer groups_id, Long userId, Long alarm_id, Long idinfo, Integer id_alarm_cancel)
			throws CloneNotSupportedException {
		List<Alarm> alarmGroups = this.findGroupsIdinfo(idinfo);
//Присваиваем статус групп реагирования на данном объекте СВОБОДНА
		List<Groups> groupsList = new ArrayList<Groups>();
		for (Alarm ag : alarmGroups) {
			Groups gr = groupsService.findObjectById(ag.getIdGroup());
			gr.setGroups_status_id(StaticVarStatus.groupFree);
			gr.setId_idinfo(null);
			groupsList.add(gr);

		}
		alarmCancelService.saveList(groupsList);
		Alarm alarmLast = this.findObjectIdinfo(idinfo);
		if (alarmLast != null) {
			// создаем новую строку со статусом обработано для данного объекта
			Alarm alarmcancelnew = new Alarm(idinfo, StaticVarStatus.stProcessed, alarmLast.getNote(), userId, null, 0,
					alarmLast.getEvcode(), alarmLast.getIdEventList());
			alarmcancelnew.setAlarmCancelId(id_alarm_cancel);

			List<Alarm> alarmCancelAll = this.findObjectIdinfoAll(idinfo);
			for (Alarm al : alarmCancelAll) {
				al.setIsactive(0);// Все события по данной тревоге делаем неактивными
			}
			//

			// Удаляем тревоги по данному объекту
			this.delete(alarmcancelnew);
			this.delete(alarmCancelAll);
			// Триггером добавляем удаленные строки в таблицу архив
		}
	}

	@Override
	@Transactional
	public void delete(Alarm alarm) {
		alarmDao.delete(alarm);
	}

	@Override
	@Transactional
	public void delete(List<Alarm> alarm) {
		alarmDao.delete(alarm);
	}

	/**
	 * Отмана группы реагирования
	 * 
	 * @param groups_id       ид группы реагирования
	 * @param userId          ид залогиневавшегося пользователя
	 * @param alarm_id        ид тревоги
	 * @param idinfo          ид группы на объекте
	 * @param id_alarm_cancel причина отмены
	 * @throws CloneNotSupportedException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void GroupArrived(Long idinfo, Integer groups_id, Long userId) {
		Alarm alarmLast = this.findObjectIdinfo(idinfo);
		// создаем новую строку с параметрами отмены группы реагирования и временем
		// когда оператор
		// отменил группу реагирования
		Alarm alarmarrivednew = new Alarm(idinfo, StaticVarStatus.stGroupArrived, alarmLast.getNote(), userId,
				groups_id, 1, alarmLast.getEvcode(), alarmLast.getIdEventList());
		alarmarrivednew.setIsread(1);
		Groups groups = groupsService.findObjectById(groups_id);
		groups.setId_idinfo(idinfo);
		this.save(alarmarrivednew);
		groupsService.save(groups);
	}

	/**
	 * 
	 * @param idinfo    ид группы на объекте
	 * @param groups_id ид группы реагирования
	 */
	public void GroupResponse(Long idinfo, Integer groups_id) {
		// Присваиваем выбранно группе что она на объекте а также записываем номер
		// объекта
		Groups groups = groupsService.findObjectById(groups_id);
		groups.setGroups_status_id(StaticVarStatus.groupInObject);
		groups.setId_idinfo(idinfo);

		groupsService.save(groups);
	}

}
