package net.proselyte.springsecurityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.EventListDao;
import net.proselyte.springsecurityapp.model.EventList;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class EventListServiceImpl implements EventListService {

	@Autowired
	private EventListDao eventListDao;

	@Override
	public void save(EventList eventlist) {
		// TODO Auto-generated method stub
		eventListDao.save(eventlist);

	}

}
