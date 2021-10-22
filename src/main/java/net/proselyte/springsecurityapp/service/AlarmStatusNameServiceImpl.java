package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.AlarmStatusNameDao;
import net.proselyte.springsecurityapp.model.AlarmStatusName;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class AlarmStatusNameServiceImpl implements AlarmStatusNameService {

	@Autowired
	private AlarmStatusNameDao alarmstatusNameDao;

	@Override
	public List<AlarmStatusName> findObjectStatus() {
		// TODO Auto-generated method stub
		return alarmstatusNameDao.findObjectStatus();
	}

}
