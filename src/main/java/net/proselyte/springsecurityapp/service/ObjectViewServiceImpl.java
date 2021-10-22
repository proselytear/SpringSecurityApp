package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.proselyte.springsecurityapp.dao.ObjectViewDao;
import net.proselyte.springsecurityapp.model.ObjectView;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class ObjectViewServiceImpl implements ObjectViewService {

	@Autowired
	private ObjectViewDao objectViewDao;

	@Override
	@Transactional(readOnly = true)
	public List<ObjectView> getAll(Long citi_id) {
		// TODO Auto-generated method stub
		return objectViewDao.getAll(citi_id);
	}

	public List<ObjectView> findObject(String keyword, long city_id) {
		return objectViewDao.findObject(keyword, city_id);
	}

	public List<ObjectView> findObject(String keyword) {
		return objectViewDao.findObject(keyword);
	}

	public List<ObjectView> findTechnic(@Param("idinfo") long idinfo) {
		return objectViewDao.findTechnic(idinfo);
	}

	public List<ObjectView> findSconnection() {
		return objectViewDao.findSconnection();
	}

	public ObjectView findId(@Param("idinfo") Long idinfo) {
		return objectViewDao.findId(idinfo);
	}

	public List<ObjectView> findAll() {
		return objectViewDao.findAll();
	}

	public List<ObjectView> getObject() {

		return objectViewDao.getObject();
	}

}
