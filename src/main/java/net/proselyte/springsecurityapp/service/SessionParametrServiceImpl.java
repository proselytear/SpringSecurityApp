package net.proselyte.springsecurityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.SessionParametrDao;
import net.proselyte.springsecurityapp.model.SessionParametr;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class SessionParametrServiceImpl implements SessionParametrService {

	@Autowired
	private SessionParametrDao sessionParametrDao;

	@Override
	public SessionParametr findOne(String session_id) {
		// TODO Auto-generated method stub
		return sessionParametrDao.findOne(session_id);
	}

	@Override
	public void save(SessionParametr sessionParametr) {
		// TODO Auto-generated method stub
		sessionParametrDao.save(sessionParametr);

	}

}
