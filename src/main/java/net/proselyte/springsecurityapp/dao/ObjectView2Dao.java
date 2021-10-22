package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.ObjectView2;

@Repository
public interface ObjectView2Dao extends JpaRepository<ObjectView2, Long> {

	@Query(value = "SELECT   o.*  from objectview2 o  where status_object in(0,1) OR (ideqval IN (SELECT ideqval FROM objectview2 WHERE idinfo=?1) and  status_object=2 )order by accountid", nativeQuery = true)
	List<ObjectView2> findAll(@Param("idinfo") long idinfo);

	@Query(value = "SELECT   o.* from objectview2 o where (UPPER(accountid) like upper(?1) OR UPPER(o.perifname) like upper(?1) OR  UPPER(o.fullname) like upper(?1) ) and city_id=?2  and (status_object in(0,1)) OR (ideqval IN (SELECT ideqval FROM objectview2 WHERE idinfo=?3) and  status_object=2) order by accountid", nativeQuery = true)
	public List<ObjectView2> findObject(@Param("keyword") String keyword, @Param("city_id") long city_id,
			@Param("idinfo") Long idinfo);

	@Query(value = "SELECT   o.* from objectview2 o where (UPPER(accountid) like upper(?1) OR UPPER(o.perifname) like upper(?1) OR  UPPER(o.fullname) like upper(?1) ) and (status_object in(0,1)) OR (ideqval IN (SELECT ideqval FROM objectview2 WHERE idinfo=?2) and  status_object=2) order by accountid ", nativeQuery = true)
	public List<ObjectView2> findObject2(@Param("keyword") String keyword, @Param("idinfo") Long idinfo);

	@Query(value = "SELECT   o.*  from objectview2 o where city_id=?1  order by accountid", nativeQuery = true)
	List<ObjectView2> getAll(@Param("citi_id") Long citi_id);

}
