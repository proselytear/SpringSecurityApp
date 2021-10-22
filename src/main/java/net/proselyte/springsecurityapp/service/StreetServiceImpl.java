package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.StreetDao;
import net.proselyte.springsecurityapp.model.Street;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class StreetServiceImpl implements StreetService {

	@Autowired
	private StreetDao streetDao;

	@Override
	public List<Street> findAll(Long city_id) {
		// TODO Auto-generated method stub
		return streetDao.findAll(city_id);
	}

	@Override
	public Street findObj(String streetSetName, Long citySetId) {
		return streetDao.findObj(streetSetName, citySetId);
	}

}
