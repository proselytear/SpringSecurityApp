package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.Perifinfo;

@Repository
public interface PerifinfoDao extends JpaRepository<Perifinfo, Long> {

	List<Perifinfo> findAll();

	@Query(value = " select  p.* from perifinfo p where infotype=0 and idinfo=?1", nativeQuery = true)
	public Perifinfo findObject(@Param("idinfo") long idinfo);

	@Query(value = " SELECT  p.* " + "FROM perifinfo p\r\n" + "INNER JOIN maineq m ON (m.IDEQVAL=p.OBINFOID)\r\n"
			+ "WHERE  infotype IN (0,1) AND obinfoid IN(\r\n" + "SELECT obinfoid\r\n" + "FROM perifinfo\r\n"
			+ "WHERE idinfo=?1) AND p.grouprel=1\r\n" + "ORDER BY  grouprel, paramnum, infotype", nativeQuery = true)
	public List<Perifinfo> findSensor(@Param("idinfo") long idinfo);

	@Query(value = " select  p.* from perifinfo p  " + " INNER JOIN maineq m ON (m.IDEQVAL=p.OBINFOID)\r\n"
			+ "WHERE  infotype IN (3) AND obinfoid IN(\r\n" + "SELECT obinfoid\r\n" + "FROM perifinfo\r\n"
			+ "WHERE idinfo=?1)\r\n", nativeQuery = true)
	public List<Perifinfo> findKey(@Param("idinfo") long idinfo);

	@Query(value = " SELECT  p.*  \r\n" + " FROM perifinfo p \r\n"
			+ " INNER JOIN maineq m ON (m.IDEQVAL=p.OBINFOID)\r\n"
			+ " LEFT JOIN employee e ON (p.id_technician=e.ID)\r\n"
			+ "WHERE  infotype IN (1) AND p.isstand=1 AND p.time_ban >SYSDATE()  " + "", nativeQuery = true)
	public List<Perifinfo> findBan();

	@Query(value = " SELECT  p.*  \r\n" + " FROM perifinfo p \r\n"
			+ " INNER JOIN maineq m ON (m.IDEQVAL=p.OBINFOID)\r\n"
			+ " left JOIN employee e ON (p.id_technician=e.ID)\r\n" + "WHERE  infotype IN (1) AND obinfoid IN(\r\n"
			+ "SELECT obinfoid\r\n" + "FROM perifinfo  where IDINFO=?1\r\n" + ") \r\n" + "limit 1"
			+ "", nativeQuery = true)
	public Perifinfo findBanIdinfo(@Param("idinfo") long idinfo);

	@Query(value = " SELECT  p.*  \r\n" + " FROM perifinfo p \r\n"
			+ " INNER JOIN maineq m ON (m.IDEQVAL=p.OBINFOID)\r\n"
			+ " left JOIN employee e ON (p.id_technician=e.ID)\r\n" + "WHERE  infotype IN (0,1) AND obinfoid IN(\r\n"
			+ "SELECT obinfoid\r\n" + "FROM perifinfo  where IDINFO=?1\r\n" + ") AND IDINFO=?1\r\n" + "limit 1"
			+ "", nativeQuery = true)
	public Perifinfo findBanIdinfoStand(@Param("idinfo") long idinfo);

	@Query(value = " SELECT  p.*  FROM perifinfo p INNER JOIN maineq m ON (m.IDEQVAL=p.OBINFOID)\r\n"
			+ "left JOIN employee e ON (p.id_technician=e.ID) WHERE   obinfoid IN\r\n"
			+ "(SELECT obinfoid FROM perifinfo  where IDINFO=?1) AND\r\n"
			+ " ((infotype IN (0)AND standLevel=2) OR(infotype IN (1)AND standLevel=1 ) )AND p.time_ban>SYSDATE()\r\n"
			+ " UNION all\r\n" + " SELECT *\r\n" + " FROM perifinfo pf\r\n" + " WHERE pf.IDINFO IN(\r\n"
			+ " SELECT  max(p.IDINFO)  FROM perifinfo p INNER JOIN maineq m ON (m.IDEQVAL=p.OBINFOID)\r\n"
			+ "left JOIN employee e ON (p.id_technician=e.ID) WHERE   obinfoid IN\r\n"
			+ "(SELECT obinfoid FROM perifinfo  where IDINFO=?1) AND\r\n"
			+ " ((infotype IN (0)AND standLevel=3) )AND p.time_ban>SYSDATE() \r\n" + " )", nativeQuery = true)
	public List<Perifinfo> findBanIdinfoFull(@Param("idinfo") long idinfo);

	@Query(value = "SELECT  p.*  FROM perifinfo p INNER JOIN maineq m ON (m.IDEQVAL=p.OBINFOID)\r\n"
			+ "			left JOIN employee e ON (p.id_technician=e.ID) WHERE  \r\n"
			+ "	((infotype IN (0)AND standLevel=2) OR(infotype IN (1)AND standLevel=1 ) )AND p.time_ban>SYSDATE()\r\n"
			+ "		 UNION ALL (SELECT pf.* FROM perifinfo pf  WHERE pf.IDINFO IN(\r\n"
			+ "		 SELECT  p.IDINFO  FROM perifinfo p INNER JOIN maineq m ON (m.IDEQVAL=p.OBINFOID)\r\n"
			+ "		left JOIN employee e ON (p.id_technician=e.ID) WHERE  \r\n"
			+ "		((infotype IN (0)AND standLevel=3) ))AND pf.time_ban>SYSDATE() )", nativeQuery = true)
	public List<Perifinfo> findBanFull();

	@Query(value = " SELECT  p.*  FROM perifinfo p INNER JOIN maineq m ON (m.IDEQVAL=p.OBINFOID)\r\n"
			+ "left JOIN employee e ON (p.id_technician=e.ID) WHERE   obinfoid IN\r\n"
			+ "(SELECT obinfoid FROM perifinfo  where IDINFO=?1)AND p.time_ban>SYSDATE() \r\n", nativeQuery = true)
	public List<Perifinfo> findBanIdinfoFullObj(@Param("idinfo") long idinfo);

	@Query(value = " SELECT  p.* \r\n" + "FROM perifinfo p\r\n" + "INNER JOIN maineq m ON (m.IDEQVAL=p.OBINFOID)\r\n"
			+ "WHERE  infotype IN (0" + ") " + ""
			+ " GROUP BY obinfoid ORDER BY  m.accountid, grouprel, paramnum, infotype ", nativeQuery = true)
	public List<Perifinfo> findSensorFull();

	@Query(value = " SELECT p.* \r\n" + " FROM perifinfo p \r\n" + " INNER JOIN maineq m ON (m.IDEQVAL=p.OBINFOID)\r\n"
			+ " LEFT JOIN employee e ON (p.id_technician=e.ID)\r\n" + " WHERE  infotype IN (1) \r\n"
			+ " AND m.ACCOUNTID=?1 AND grouprel=?2 AND paramnum=?3 limit 1", nativeQuery = true)
	public List<Perifinfo> getIdStand(@Param("accountid") String accountid, @Param("grouprel") Integer grouprel,
			@Param("paramnum") Integer paramnum);

	@Query(value = " SELECT  p.* \r\n" + " FROM perifinfo p \r\n" + " INNER JOIN maineq m ON (m.IDEQVAL=p.OBINFOID)\r\n"
			+ " LEFT JOIN employee e ON (p.id_technician=e.ID)\r\n" + " WHERE  infotype IN (0,1) \r\n"
			+ " AND m.ACCOUNTID=?1 AND grouprel=?2 ", nativeQuery = true)
	public List<Perifinfo> getIdStandGroup(@Param("accountid") String accountid, @Param("grouprel") Integer grouprel);

	@Query(value = " SELECT  p.* \r\n" + " FROM perifinfo p \r\n" + " INNER JOIN maineq m ON (m.IDEQVAL=p.OBINFOID)\r\n"
			+ " LEFT JOIN employee e ON (p.id_technician=e.ID)\r\n" + " WHERE  infotype IN (0,1) \r\n"
			+ " AND m.ACCOUNTID=?1 ", nativeQuery = true)
	public List<Perifinfo> getIdStandObject(@Param("accountid") String accountid);

	@Query(value = " SELECT  p.*" + " FROM perifinfo p \r\n" + " INNER JOIN maineq m ON (m.IDEQVAL=p.OBINFOID)\r\n"
			+ " LEFT JOIN employee e ON (p.id_technician=e.ID)\r\n" + " WHERE  infotype IN (0) \r\n"
			+ " AND m.ACCOUNTID=?1 AND grouprel=?2  limit 1", nativeQuery = true)
	public Perifinfo getIdAlarm(@Param("accountid") String accountid, @Param("grouprel") Integer grouprel);

	@Query(value = "SELECT pf.*\r\n" + "FROM perifinfo pf WHERE pf.OBINFOID IN (\r\n" + "SELECT obinfoid\r\n"
			+ "FROM perifinfo p\r\n" + "WHERE p.IDINFO=?1) AND infotype=0", nativeQuery = true)
	public List<Perifinfo> getGroupbyIdinfo(@Param("idinfo") Long idinfo);

	@Query(value = "SELECT p.*\r\n" + "FROM perifinfo p\r\n" + "WHERE obinfoid IN (\r\n"
			+ "SELECT obinfoid FROM `perifinfo` WHERE idinfo=?1)\r\n" + "AND infotype=1", nativeQuery = true)
	public List<Perifinfo> getZonebyIdinfo(@Param("idinfo") Long idinfo);

	@Query(value = "SELECT *\r\n" + "	FROM perifinfo p\r\n"
			+ "	WHERE p.OBINFOID=?1 AND infotype=0 AND grouprel=?2 limit 1", nativeQuery = true)
	public Perifinfo getGroup(@Param("obinfoid") Long obinfoid, @Param("grouprel") Integer grouprel);

	@Query(value = "SELECT *\r\n" + "FROM perifinfo p\r\n" + "INNER JOIN maineq m ON (p.OBINFOID=m.IDEQVAL)\r\n"
			+ "WHERE m.ACCOUNTID=?1 AND INFOTYPE=1 AND grouprel=?2 AND (perifdata is not NULL OR perifdata!=\"\") order by paramnum", nativeQuery = true)
	public List<Perifinfo> getParamnum(@Param("accountid") String accountid, @Param("grouprel") Integer grouprel);

	@Query(value = "SELECT p.*\r\n" + "FROM perifinfo p\r\n" + "INNER JOIN maineq m ON (p.OBINFOID=m.IDEQVAL)\r\n"
			+ "WHERE accountid=?1 AND infotype=1 AND grouprel=?2 AND paramnum=?3 limit 1", nativeQuery = true)
	public Perifinfo getParamnumOne(@Param("accountid") String accountid, @Param("grouprel") Integer grouprel,
			@Param("paramnum") Integer paramnum);

	// *Находим ид perifinfo для карточки
	@Query(value = "SELECT *\r\n" + "FROM perifinfo p INNER JOIN  card c  ON c.OBJECT_ID=p.OBINFOID\r\n"
			+ "WHERE infotype=0 AND grouprel=1 AND paramnum=?2 AND accountid_card=?1 LIMIT 1", nativeQuery = true)
	public Perifinfo getPerifinfoForCard(@Param("accountid_card") String accountid_card,
			@Param("paramnum") Long paramnum);

	@Query(value = "SELECT MAX(a.paramnum) FROM(\r\n" + "\r\n" + "SELECT  paramnum FROM perifinfo p\r\n"
			+ "INNER JOIN maineq m ON(m.IDEQVAL=p.OBINFOID)\r\n" + "WHERE infotype=3 AND grouprel=?2\r\n"
			+ "AND m.ACCOUNTID=?1\r\n" + " UNION ALL SELECT 0 FROM DUAL) AS a", nativeQuery = true)
	public int getUserLast(@Param("accountid_card") String accaountid_card, @Param("groupumun") Long groupumun);

	// Находим данные шлейва но номеру группы объекта и по номеру шлейфа
	@Query(value = "	SELECT *\r\n" + "FROM perifinfo WHERE obinfoid IN(\r\n" + "SELECT m.IDEQVAL\r\n"
			+ "FROM maineq m\r\n" + "WHERE m.ACCOUNTID=?1)\r\n" + "AND infotype=1 AND paramnum=?2", nativeQuery = true)
	public Perifinfo getPerifinfoForZone(@Param("accountid") String accountid, @Param("paramnum") Long paramnum);

}
