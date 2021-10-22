package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.GroupsMaineqTempDao;
import net.proselyte.springsecurityapp.model.GroupsMaineqTemp;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class GroupsMaineqTempServiceImpl implements GroupsMaineqTempService {

	@Autowired
	private GroupsMaineqTempDao groupsMaineqTempDao;

	@Override
	public void save(GroupsMaineqTemp groupsMaineqTemp) {
		// TODO Auto-generated method stub
		groupsMaineqTempDao.save(groupsMaineqTemp);

	}

	@Override
	public List<GroupsMaineqTemp> findMaineq(Long maineq_id) {
		return groupsMaineqTempDao.findMaineq(maineq_id);
	}

}
