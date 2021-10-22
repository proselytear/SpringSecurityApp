package net.proselyte.springsecurityapp.service;

import java.util.List;

import net.proselyte.springsecurityapp.model.Groups;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

public interface GroupsService {

	public List<Groups> findObject(long idinfo);

	public List<Groups> findObjectActive(long idinfo);

	public Groups findObjectById(Integer groups_id);

	public List<Groups> findGrouptByIdinfo(long idinfo);

	public List<Groups> findGrouptSendIdinfo(long idinfo);

	public List<Groups> findcanCancelGroup(long idinfo);

	public void save(Groups groups);

	public void saveList(List<Groups> groups);

	public List<Groups> getGroupsStatus();

	public List<Groups> findObjectNotActive(long idinfo);

	public List<Groups> isGroupFree(long groups_id);

	public List<Groups> canCancelGroup(long idinfo, long groups_id);

	public List<Groups> findAllGroup();
}
