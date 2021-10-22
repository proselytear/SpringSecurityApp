package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.DistrictDao;
import net.proselyte.springsecurityapp.model.District;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private DistrictDao employeesDao;

	@Override
	public List<District> findAll() {
		// TODO Auto-generated method stub
		return employeesDao.findAll();
	}

}
