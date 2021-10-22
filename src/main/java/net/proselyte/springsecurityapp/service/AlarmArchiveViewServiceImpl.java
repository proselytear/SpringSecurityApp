package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.proselyte.springsecurityapp.dao.AlarmArchiveViewDao;
import net.proselyte.springsecurityapp.dao.GroupsDao;
import net.proselyte.springsecurityapp.model.AlarmArchiveView;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class AlarmArchiveViewServiceImpl implements AlarmArchiveViewService {

	@Autowired
	private AlarmArchiveViewDao alarmArchiveDao;

	@Autowired
	private GroupsDao groupsDao;

	@Autowired
	private GroupsService groupsService;

	@Autowired
	private AlarmCancelService alarmCancelService;

	@Override
	public void save(AlarmArchiveView alarm) {
		alarmArchiveDao.save(alarm);
	}

	@Override
	public void saveList(List<AlarmArchiveView> alarm) {
		alarmArchiveDao.save(alarm);
	}

	@Transactional
	public void saveCancelAlerm(AlarmArchiveView alarm, List<AlarmArchiveView> alarmList) {
		save(alarm);
		saveList(alarmList);
	}

	public Object clone() throws CloneNotSupportedException {

		return super.clone();
	}

	@Override
	public List<AlarmArchiveView> findAlarmArchive() {
		return alarmArchiveDao.findAlarmArchive();
	}

	@Override
	public List<AlarmArchiveView> findObjectData(String dataEvdata, String dataEvdata1) {
		return alarmArchiveDao.findObjectData(dataEvdata, dataEvdata1);
	}

	@Override
	// Непрочитанные тревоги оператором которые в архиве находятся
	public List<AlarmArchiveView> findAlarmNotRead() {
		return alarmArchiveDao.findAlarmNotRead();
	}

}
