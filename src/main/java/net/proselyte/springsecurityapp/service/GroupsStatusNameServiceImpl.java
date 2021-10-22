package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.GroupsStatusNameDao;
import net.proselyte.springsecurityapp.model.GroupsStatusName;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class GroupsStatusNameServiceImpl implements GroupsStatusNameService {

	@Autowired
	private GroupsStatusNameDao groupsStatusNameDao;

	@Override
	public List<GroupsStatusName> findAll() {
		// TODO Auto-generated method stub
		return groupsStatusNameDao.findAll();
	}

}
