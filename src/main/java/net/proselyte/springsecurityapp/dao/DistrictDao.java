package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.District;

@Repository
public interface DistrictDao extends JpaRepository<District, Long> {

	@Query(value = "SELECT d.district_id, d.NAME_RU, d.NAME_UA, d.NAME_EN\r\n" + " FROM district d\r\n"
			+ " UNION all\r\n" + "  SELECT 0, '','',''  FROM DUAL  ORDER BY 1", nativeQuery = true)
	List<District> findAll();

}
