package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.City;

@Repository
public interface CityDao extends JpaRepository<City, Long> {
	@Query(value = "SELECT c.CITY_ID, c.NAME_UA, c.NAME_RU, c.NAME_EN, c.Typename_id, c.sort\r\n" + " FROM city c \r\n"
			+ " UNION all\r\n" + "  SELECT -1, '','','', null, 0  FROM DUAL  ORDER BY sort", nativeQuery = true)
	List<City> findAll();

	@Query(value = "SELECT *\r\n" + "FROM city\r\n" + " WHERE name_ru=?1 limit 1", nativeQuery = true)
	City getByName(@Param("city_name") String city_name);

	@Query(value = "SELECT *\r\n" + "FROM city c\r\n" + "WHERE c.region_id=?1", nativeQuery = true)
	List<City> getAllRegion(@Param("regionSetId") Long regionSetId);

}
