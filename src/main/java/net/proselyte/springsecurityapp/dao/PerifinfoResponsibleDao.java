package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.PerifinfoResponsible;

@Repository
public interface PerifinfoResponsibleDao extends JpaRepository<PerifinfoResponsible, Long> {

	@Query(value = " SELECT *\r\n" + " FROM perifinforesponsible ", nativeQuery = true)
	public List<PerifinfoResponsible> findAll();

	@Query(value = " SELECT pk.perifinfoResponsible_id, pk.first_name, pk.last_name, pk.patronymic,  pk.address, pk.phone, pk.note FROM perifinforesponsible pk\r\n"
			+ "INNER JOIN perifinforesponslist pl ON (pk.perifinfoResponsible_id=pl.responList_id) INNER JOIN perifinfo p ON(p.IDINFO=pl.perifinfoList_id)\r\n"
			+ " where p.idinfo=?1", nativeQuery = true)
	public List<PerifinfoResponsible> findObject(@Param("idinfo") long idinfo);

	@Query(value = " SELECT *  FROM perifinforesponsible p\r\n"
			+ "WHERE p.perifinfoResponsible_id=?1", nativeQuery = true)
	public List<PerifinfoResponsible> findId(@Param("perifinfoResponsible_id") long perifinfoResponsible_id);

	@Query(value = " SELECT *  FROM perifinforesponsible p\r\n" + "WHERE p.phone=?1 limit 1", nativeQuery = true)
	public PerifinfoResponsible findPhone(@Param("phone") String phone);

	@Query(value = "	SELECT *\r\n" + "	FROM perifinforesponsible pf \r\n"
			+ "	INNER JOIN  perifinforesponslist p ON(p.responList_id=pf.perifinfoResponsible_id)\r\n"
			+ "	INNER JOIN perifinfo po ON(po.IDINFO=p.perifinfoList_id)\r\n"
			+ "	INNER JOIN card c ON(c.OBJECT_ID=po.OBINFOID)\r\n"
			+ "	WHERE c.ACCOUNTID_CARD=?1 AND groupnum=?2", nativeQuery = true)
	public List<PerifinfoResponsible> getListForAccount(@Param("account_card") String account_card,
			@Param("groupnum") Long groupnum);

}
