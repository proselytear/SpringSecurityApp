package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.EmployeesDao;
import net.proselyte.springsecurityapp.model.Employees;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class EmployeesServiceImpl implements EmployeesService {

	@Autowired
	private EmployeesDao employeesDao;

	@Override
	public List<Employees> findPosition() {
		// TODO Auto-generated method stub
		return employeesDao.findPosition();

	}

}
