package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.ObjectView;

@Repository
public interface ObjectViewDao extends JpaRepository<ObjectView, Long> {

	@Query(value = "SELECT   o.* from objectview o where (UPPER(accountid) like upper(?1) OR UPPER(o.perifname) like upper(?1) OR  UPPER(o.fullname) like upper(?1) ) and city_id=?2  order by accountid", nativeQuery = true)
	public List<ObjectView> findObject(@Param("keyword") String keyword, @Param("city_id") long city_id);

	@Query(value = "SELECT   o.* from objectview o where (UPPER(accountid) like upper(?1) OR UPPER(o.perifname) like upper(?1) OR  UPPER(o.fullname) like upper(?1) )  order by accountid ", nativeQuery = true)
	public List<ObjectView> findObject(@Param("keyword") String keyword);

	@Query(value = "SELECT   o.*  from objectview o order by accountid", nativeQuery = true)
	List<ObjectView> findAll();

	@Query(value = "SELECT  o.* from objectview o where (UPPER(accountid) like %?1% OR UPPER(o.perifname) like %?1% OR  UPPER(o.fullname) like %?1% ) and city_id=?2  order by accountid", nativeQuery = true)
	public List<ObjectView> findObjectForm(@Param("keyword") String keyword, @Param("ideqval") long ideqval);

	@Query(value = "SELECT o.* \r\n" + "FROM objectview o\r\n" + "INNER JOIN card c ON c.OBJECT_ID=o.ideqval\r\n"
			+ "inner join perifinfo p on p.obinfoid=c.object_id\r\n" + "WHERE p.idinfo=?1", nativeQuery = true)
	public List<ObjectView> findTechnic(@Param("idinfo") long idinfo);

	@Query(value = "SELECT  o.* " + " FROM objectview o \r\n"
			+ " WHERE o.SCONNECTION=2 GROUP BY accountid order by accountid", nativeQuery = true)
	public List<ObjectView> findSconnection();

	@Query(value = "SELECT   o.* from objectview o where idinfo=?1", nativeQuery = true)
	public ObjectView findId(@Param("idinfo") Long idinfo);

	@Query(value = "SELECT   o.*  from objectview o where city_id=?1  order by accountid", nativeQuery = true)
	List<ObjectView> getAll(@Param("citi_id") Long citi_id);

	@Query(value = "SELECT o.*\r\n" + "	FROM objectview o  order by accountid ", nativeQuery = true)
	List<ObjectView> getObject();

}
