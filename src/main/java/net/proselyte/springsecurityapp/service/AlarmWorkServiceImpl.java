package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.AlarmWorkDao;
import net.proselyte.springsecurityapp.model.AlarmWork;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class AlarmWorkServiceImpl implements AlarmWorkService {

	@Autowired
	private AlarmWorkDao alarmWorkDao;

	@Override
	public List<AlarmWork> findAll() {
		// TODO Auto-generated method stub
		return alarmWorkDao.findAll();
	}

	public List<AlarmWork> findIdinfo(Long idinfo) {
		// TODO Auto-generated method stub
		return alarmWorkDao.findIdinfo(idinfo);
	}

}
