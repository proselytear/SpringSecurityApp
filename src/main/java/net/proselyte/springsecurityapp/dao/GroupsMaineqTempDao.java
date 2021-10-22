package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.proselyte.springsecurityapp.model.GroupsMaineqTemp;

public interface GroupsMaineqTempDao extends JpaRepository<GroupsMaineqTemp, Long> {
	@Query(value = "SELECT *\r\n" + "FROM groups_maineq_temp g\r\n" + "WHERE g.maineq_id=?1", nativeQuery = true)
	public List<GroupsMaineqTemp> findMaineq(@Param("maineq_id") long maineq_id);

}
