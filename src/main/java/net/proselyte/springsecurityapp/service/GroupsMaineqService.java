package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import net.proselyte.springsecurityapp.model.GroupsMaineq;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

public interface GroupsMaineqService {

	public List<GroupsMaineq> findAll();

	@Transactional
	public void save(GroupsMaineq groupsMaineq);

	@Transactional
	public void saveList(List<GroupsMaineq> groups);
}
