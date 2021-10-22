package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.TypeNameDao;
import net.proselyte.springsecurityapp.model.TypeName;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class TypeNameServiceImpl implements TypeNameService {

	@Autowired
	private TypeNameDao typeNameDao;

	@Override
	public List<TypeName> findAll() {
		// TODO Auto-generated method stub
		return typeNameDao.findAll();
	}

}
