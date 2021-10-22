package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.EventListV;

@Repository
public interface EventListVDao extends JpaRepository<EventListV, Long> {

	@Query(value = "SELECT e.* " + "FROM eventlist_v e where idinfo=?1  order by e.idev desc ", nativeQuery = true)
	public List<EventListV> findObject(@Param("idinfo") long idinfo);

	@Query(value = "SELECT e.* "
			+ "FROM eventlist_v e where (idinfo=?1 )  and (FROM_UNIXTIME(evdata)>?2 and FROM_UNIXTIME(evdata)<?3) order by e.idev desc ", nativeQuery = true)
	public List<EventListV> findObjectData(@Param("idinfo") long idinfo, @Param("dataEvdata") String dataEvdata,
			@Param("dataEvdata") String dataEvdata1);

	@Query(value = "SELECT e.* " + "FROM eventlist_v e ", nativeQuery = true)
	public List<EventListV> findObjectFull();

	@Query(value = "SELECT e.* "
			+ " FROM eventlist_v e where (FROM_UNIXTIME(evdata)>?1 and FROM_UNIXTIME(evdata)<?2) order by e.idev desc ", nativeQuery = true)
	public List<EventListV> findObjectData(@Param("dataEvdata") String dataEvdata,
			@Param("dataEvdata") String dataEvdata1);

	@Query(value = "SELECT e.* FROM   eventlist_v e WHERE e.`events` LIKE %?1%  ORDER by e.idev desc", nativeQuery = true)
	public List<EventListV> findObjectKey(String key);

	@Query(value = "SELECT e.* FROM   eventlist_v e \r\n" + "	WHERE idev=?1 limit 1", nativeQuery = true)
	public EventListV findObjectIdev(Integer idev);

}
