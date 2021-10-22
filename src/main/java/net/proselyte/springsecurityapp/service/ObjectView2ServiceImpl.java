package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.ObjectView2Dao;
import net.proselyte.springsecurityapp.model.ObjectView2;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */
@Service
public class ObjectView2ServiceImpl implements ObjectView2Service {
	@Autowired
	private ObjectView2Dao objectView2Dao;

	@Override
	public List<ObjectView2> findAll(Long idinfo) {
		// TODO Auto-generated method stub
		return objectView2Dao.findAll(idinfo);
	}

	@Override
	public List<ObjectView2> getAll(long citi_id) {
		// TODO Auto-generated method stub
		return objectView2Dao.getAll(citi_id);
	}

	@Override
	public List<ObjectView2> findObject(String keyword, long city_id, Long idinfo) {
		// TODO Auto-generated method stub
		return objectView2Dao.findObject(keyword, city_id, idinfo);
	}

	@Override
	public List<ObjectView2> findObject2(String keyword, Long idinfo) {
		return objectView2Dao.findObject2(keyword, idinfo);
	}

}
