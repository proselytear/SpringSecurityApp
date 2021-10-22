package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.Maineq;

@Repository
public interface MaineqDao extends JpaRepository<Maineq, Long> {
	public static final String FIND_PROJECTS = "SELECT m.* FROM maineq m where UPPER(accountid) like %?1%";

	@Query(value = FIND_PROJECTS, nativeQuery = true)
	public List<Maineq> findProjects(@Param("keyword") String keyword);

	List<Maineq> findAll();

	@Query(value = " SELECT m.* FROM maineq m WHERE ideqval IN ( SELECT obinfoid \r\n"
			+ " from perifinfo where idinfo=?1)", nativeQuery = true)
	public Maineq findObject(@Param("idinfo") long idinfo);

	@Query(value = "SELECT *\r\n" + "FROM maineq m WHERE m.ACCOUNTID=?1 limit 1", nativeQuery = true)
	public Maineq findMaineqByAccount(@Param("accountId") String accountId);

}
