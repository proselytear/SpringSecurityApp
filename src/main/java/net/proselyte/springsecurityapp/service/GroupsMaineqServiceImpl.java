package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.GroupsMaineqDao;
import net.proselyte.springsecurityapp.model.GroupsMaineq;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class GroupsMaineqServiceImpl implements GroupsMaineqService {

	@Autowired
	private GroupsMaineqDao groupsMaineqDao;

	@Override
	public List<GroupsMaineq> findAll() {
		// TODO Auto-generated method stub
		return groupsMaineqDao.findAll();
	}

	@Override
	public void save(GroupsMaineq groupsMaineq) {
		// TODO Auto-generated method stub
		groupsMaineqDao.save(groupsMaineq);

	}

	@Override
	public void saveList(List<GroupsMaineq> groupsMaineq) {
		// TODO Auto-generated method stub
		groupsMaineqDao.save(groupsMaineq);

	}

}
