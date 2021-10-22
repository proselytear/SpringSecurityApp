package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.GroupsDao;
import net.proselyte.springsecurityapp.model.Groups;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class GroupsServiceImpl implements GroupsService {

	@Autowired
	private GroupsDao groupsDao;

	public List<Groups> findObject(long idinfo) {
		return groupsDao.findObject(idinfo);
	}

	public List<Groups> findObjectActive(@Param("idinfo") long idinfo) {
		return groupsDao.findObjectActive(idinfo);
	}

	public List<Groups> findObjectNotActive(@Param("idinfo") long idinfo) {
		return groupsDao.findObjectNotActive(idinfo);
	}

	public Groups findObjectById(Integer groups_id) {
		return groupsDao.findObjectById(groups_id);
	}

	@Override
	public void save(Groups groups) {
		groupsDao.save(groups);
	}

	@Override
	public void saveList(List<Groups> groups) {
		groupsDao.save(groups);
	}

	public List<Groups> findGrouptByIdinfo(long idinfo) {
		return groupsDao.findGrouptByIdinfo(idinfo);
	}

	public List<Groups> findGrouptSendIdinfo(long idinfo) {
		return groupsDao.findGrouptSendIdinfo(idinfo);
	}

	public List<Groups> getGroupsStatus() {
		return groupsDao.getGroupsStatus();
	}

	public List<Groups> isGroupFree(long groups_id) {
		return groupsDao.isGroupFree(groups_id);
	}

	public List<Groups> findcanCancelGroup(long idinfo) {
		return groupsDao.findcanCancelGroup(idinfo);
	}

	public List<Groups> canCancelGroup(long idinfo, long groups_id) {
		return groupsDao.canCancelGroup(idinfo, groups_id);
	}

	@Override
	public List<Groups> findAllGroup() {
		// TODO Auto-generated method stub
		return groupsDao.findAllGroup();
	}

}
