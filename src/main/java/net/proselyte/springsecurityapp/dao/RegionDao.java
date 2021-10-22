package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.Region;

@Repository
public interface RegionDao extends JpaRepository<Region, Long> {

	@Query(value = "SELECT * from region  ORDER BY name_ru", nativeQuery = true)
	List<Region> findAll();

	@Query(value = "SELECT * from region r WHERE r.NAME_RU=?1", nativeQuery = true)
	Region findObj(@Param("regionSetName") String regionSetName);

}
