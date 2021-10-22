package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.AlarmNewDao;
import net.proselyte.springsecurityapp.model.AlarmNew;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class AlarmNewServiceImpl implements AlarmNewService {

	@Autowired
	private AlarmNewDao alarmNewDao;

	@Override
	public List<AlarmNew> findAlarmNew() {
		// TODO Auto-generated method stub
		return alarmNewDao.findAlarmNew();
	}

}
