package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.AlarmFullDao;
import net.proselyte.springsecurityapp.model.AlarmFull;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class AlarmFullServiceImpl implements AlarmFullService {

	@Autowired
	private AlarmFullDao alarmFullDao;

	public List<AlarmFull> findAlarmNotRead() {
		// TODO Auto-generated method stub
		return alarmFullDao.findAlarmNotRead();
	}

	public List<AlarmFull> findAlarmNotNew() {
		// TODO Auto-generated method stub
		return alarmFullDao.findAlarmNotNew();
	}

}
