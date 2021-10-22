package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.PerifinfoResponslist;

@Repository
public interface PerifinfoResponslistDao extends JpaRepository<PerifinfoResponslist, Long> {

	@Query(value = "SELECT *\r\n" + "	FROM perifinforesponsible pf \r\n"
			+ "	INNER JOIN  perifinforesponslist p ON(p.responList_id=pf.perifinfoResponsible_id)\r\n"
			+ "	WHERE perifinfoList_id=544", nativeQuery = true)
	public List<PerifinfoResponslist> getForPerifinfo(@Param("perifinfoList_id") Long perifinfoList_id);

}
