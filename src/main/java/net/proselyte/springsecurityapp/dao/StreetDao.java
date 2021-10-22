package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.Street;

@Repository
public interface StreetDao extends JpaRepository<Street, Long> {

	@Query(value = "SELECT s.street_id, s.NAME_RU, s.NAME_RU_OLD, s.typename_id, s.city_id FROM street s\r\n"
			+ "WHERE city_id=?1\r\n" + "\r\n" + "UNION ALL  SELECT -1, '', '',null,null\r\n" + "\r\n"
			+ "FROM DUAL  ORDER BY 2", nativeQuery = true)
	List<Street> findAll(Long city_id);

	@Query(value = "SELECT * " + "FROM street s\r\n" + "INNER JOIN typename t ON(s.typename_id=t.typename_id)\r\n"
			+ "WHERE city_id=?2 and  concat(s.NAME_RU,\" \",t.SMALLNAME_RU)=?1 ", nativeQuery = true)
	Street findObj(@Param("streetSetName") String streetSetName, @Param("citySetId") Long citySetId);

}
