package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.AlarmDao;
import net.proselyte.springsecurityapp.dao.GroupsDao;
import net.proselyte.springsecurityapp.model.AlarmCancelName;
import net.proselyte.springsecurityapp.model.Groups;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekaterina Nosenko
 * @version 1.0
 */
@Service
public class AlarmCancelServiceImpl implements AlarmCancelService {

	@Autowired
	private AlarmDao alarmDao;

	@Autowired
	private AlarmDao alarmArchiveDao;

	@Autowired
	private GroupsDao groupsDao;

	@Override
	public List<AlarmCancelName> findObjectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveList(List<Groups> groupsList) {
		groupsDao.save(groupsList);

	}

}
