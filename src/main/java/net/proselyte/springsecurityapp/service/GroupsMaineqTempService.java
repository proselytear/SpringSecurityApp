package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import net.proselyte.springsecurityapp.model.GroupsMaineqTemp;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

public interface GroupsMaineqTempService {

	public List<GroupsMaineqTemp> findMaineq(Long maineq_id);

	@Transactional
	public void save(GroupsMaineqTemp groupsMaineqTemp);
}
