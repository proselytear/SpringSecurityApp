package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.RegionDao;
import net.proselyte.springsecurityapp.model.Region;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionDao regionDao;

	@Override
	public List<Region> findAll() {
		// TODO Auto-generated method stub
		return regionDao.findAll();
	}

	@Override
	public Region findObj(String regionSetName) {
		return regionDao.findObj(regionSetName);
	}

}
