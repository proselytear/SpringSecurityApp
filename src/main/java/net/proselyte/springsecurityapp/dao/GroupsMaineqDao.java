package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.proselyte.springsecurityapp.model.GroupsMaineq;

public interface GroupsMaineqDao extends JpaRepository<GroupsMaineq, Long> {
	@Query(value = "SELECT * from groups_maineq", nativeQuery = true)
	public List<GroupsMaineq> findAll();
}
