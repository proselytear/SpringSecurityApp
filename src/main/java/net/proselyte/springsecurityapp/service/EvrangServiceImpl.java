package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.EvrangDao;
import net.proselyte.springsecurityapp.model.Evrang;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class EvrangServiceImpl implements EvrangService {

	@Autowired
	private EvrangDao evrangDao;

	@Override
	public List<Evrang> getEvrangFull() {
		// TODO Auto-generated method stub
		return evrangDao.getEvrangFull();

	}

}
