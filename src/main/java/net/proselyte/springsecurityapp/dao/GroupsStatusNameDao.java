package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.GroupsStatusName;

@Repository
public interface GroupsStatusNameDao extends JpaRepository<GroupsStatusName, Long> {

	@Query(value = "SELECT g.*\r\n" + " FROM groups_status_name g where id_groups_status_name !=5", nativeQuery = true)
	public List<GroupsStatusName> findAll();

}
