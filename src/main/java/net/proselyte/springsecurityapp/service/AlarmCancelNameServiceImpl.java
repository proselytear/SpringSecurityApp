package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.AlarmCancelNameDao;
import net.proselyte.springsecurityapp.model.AlarmCancelName;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class AlarmCancelNameServiceImpl implements AlarmCancelNameService {

	@Autowired
	private AlarmCancelNameDao alarmCancelNameDao;

	@Override
	public List<AlarmCancelName> findObjectAll() {
		// TODO Auto-generated method stub
		return alarmCancelNameDao.findObjectAll();
	}

}
