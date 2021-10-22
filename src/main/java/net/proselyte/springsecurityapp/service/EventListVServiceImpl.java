package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.EventListVDao;
import net.proselyte.springsecurityapp.model.EventListV;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class EventListVServiceImpl implements EventListVService {

	@Autowired
	private EventListVDao eventlistVDao;

	public List<EventListV> findObject(long idinfo) {
		return eventlistVDao.findObject(idinfo);
	}

	public List<EventListV> findObjectData(@Param("idinfo") long idinfo, String dataEvdata, String dataEvdata1) {
		return eventlistVDao.findObjectData(idinfo, dataEvdata, dataEvdata1);
	}

	public List<EventListV> findObjectFull() {
		return eventlistVDao.findObjectFull();
	}

	public List<EventListV> findObjectData(@Param("dataEvdata") String dataEvdata,
			@Param("dataEvdata") String dataEvdata1) {
		return eventlistVDao.findObjectData(dataEvdata, dataEvdata1);
	}

	public List<EventListV> findObjectKey(String key) {
		return eventlistVDao.findObjectKey(key);
	}

	public EventListV findObjectIdev(Integer idev) {
		return eventlistVDao.findObjectIdev(idev);
	}
}
