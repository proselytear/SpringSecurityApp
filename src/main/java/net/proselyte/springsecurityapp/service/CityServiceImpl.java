package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.proselyte.springsecurityapp.dao.CityDao;
import net.proselyte.springsecurityapp.model.City;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;

	@Override
	@Transactional(readOnly = true)
	public List<City> getAll() {
		// TODO Auto-generated method stub
		return cityDao.findAll();
	}

	@Override
	public City getByName(String city_name) {
		// TODO Auto-generated method stub
		return cityDao.getByName(city_name);
	}

	@Override
	public List<City> getAllRegion(Long regionSetId) {
		// TODO Auto-generated method stub
		return cityDao.getAllRegion(regionSetId);
	}

}
