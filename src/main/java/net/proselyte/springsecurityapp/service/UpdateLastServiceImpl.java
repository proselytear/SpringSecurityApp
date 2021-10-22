package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.UpdateLastDao;
import net.proselyte.springsecurityapp.model.UpdateLast;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class UpdateLastServiceImpl implements UpdateLastService {

	@Autowired
	private UpdateLastDao updateLastDao;

	@Override
	public List<UpdateLast> getUpdateLast() {
		// TODO Auto-generated method stub
		return updateLastDao.getUpdateLast();
	}

	@Override
	public void save(UpdateLast updateLast) {
		// TODO Auto-generated method stub
		updateLastDao.save(updateLast);
	}

}
