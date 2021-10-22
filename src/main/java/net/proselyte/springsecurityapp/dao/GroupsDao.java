package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.Groups;

@Repository
public interface GroupsDao extends JpaRepository<Groups, Long> {

	@Query(value = "SELECT g.*\r\n"
			+ " FROM groups g INNER JOIN groups_maineq gm on (gm.groups_id=g.groups_id) inner join maineq m on(m.ideqval=gm.maineq_id) inner join perifinfo p on (p.obinfoid=m.ideqval) where p.idinfo=?1 order by gm.priority", nativeQuery = true)
	public List<Groups> findObject(@Param("idinfo") long idinfo);

	@Query(value = "SELECT g.*\r\n"
			+ " FROM groups g INNER JOIN groups_maineq gm on (gm.groups_id=g.groups_id) inner join maineq m on(m.ideqval=gm.maineq_id) inner join perifinfo p on (p.obinfoid=m.ideqval) where p.idinfo=?1 and groups_status_id=1 order by gm.priority", nativeQuery = true)
	public List<Groups> findObjectActive(@Param("idinfo") long idinfo);

	@Query(value = "SELECT g.*\r\n"
			+ " FROM groups g INNER JOIN groups_maineq gm on (gm.groups_id=g.groups_id) inner join maineq m on(m.ideqval=gm.maineq_id) inner join perifinfo p on (p.obinfoid=m.ideqval) where p.idinfo=?1 and groups_status_id!=1 order by gm.priority", nativeQuery = true)
	public List<Groups> findObjectNotActive(@Param("idinfo") long idinfo);

	@Query(value = "SELECT g.*\r\n" + " FROM groups g where g.groups_id=?1", nativeQuery = true)
	public Groups findObjectById(@Param("groups_id") Integer groups_id);

	@Query(value = "SELECT distinct g.*\r\n" + "From groups g INNER JOIN alarm a ON (g.GROUPS_ID=a.id_group)\r\n"
			+ "WHERE g.groups_status_id=5\r\n" + "and id_group IS NOT  null and a.id_idinfo=?1", nativeQuery = true)
	public List<Groups> findGrouptByIdinfo(@Param("idinfo") long idinfo);

	@Query(value = "SELECT *\r\n" + "from groups g\r\n" + "WHERE g.GROUPS_ID IN(\r\n" + "SELECT id_group\r\n"
			+ "FROM alarm\r\n" + "WHERE aldata IN(\r\n" + "SELECT MAX(aldata)\r\n" + "from alarm al\r\n"
			+ "WHERE al.alarm_id IN (\r\n" + "SELECT MAX(alarm_id)\r\n" + "FROM alarm a \r\n"
			+ "WHERE id_idinfo=?1 AND isactive=1\r\n" + "AND status_alarm_id IN (3,4,6)\r\n"
			+ "GROUP BY a.id_group, status_alarm_id)\r\n" + "GROUP BY id_group)\r\n"
			+ "and id_idinfo=?1 AND isactive=1\r\n" + "AND status_alarm_id IN (3))", nativeQuery = true)
	public List<Groups> findGrouptSendIdinfo(@Param("idinfo") long idinfo);

	@Query(value = " Select * FROM groups g\r\n"
			+ "LEFT JOIN groups_status_name gsm ON gsm.id_groups_status_name=g.groups_status_id\r\n"
			+ "LEFT JOIN objectview o ON o.IDINFO=g.id_idinfo", nativeQuery = true)
	public List<Groups> getGroupsStatus();

	@Query(value = " SELECT g.*\r\n" + "FROM groups g\r\n"
			+ "WHERE groups_status_id=1 and g.groups_id=?1", nativeQuery = true)
	public List<Groups> isGroupFree(@Param("groups_id") long groups_id);

	@Query(value = "	SELECT *\r\n" + "	FROM groups g\r\n" + "	WHERE g.GROUPS_ID IN (\r\n"
			+ "	SELECT al.id_group\r\n" + "	FROM alarm al\r\n" + "	WHERE al.alarm_id IN(\r\n" + "\r\n"
			+ "	SELECT alarm_id\r\n" + "	FROM alarm \r\n" + "	WHERE alarm_id IN(\r\n"
			+ "	SELECT MAX(alarm_id)\r\n" + "	FROM alarm a \r\n"
			+ "	WHERE id_idinfo=?1 AND isactive=1 AND status_alarm_id IN(3,4,6)\r\n" + "	GROUP BY a.id_group))\r\n"
			+ "	AND status_alarm_id IN(3,4)\r\n" + "	);" +

			" ", nativeQuery = true)
	public List<Groups> findcanCancelGroup(@Param("idinfo") long idinfo);

	@Query(value = "	SELECT *\r\n" + "	FROM groups g\r\n" + "	WHERE g.GROUPS_ID IN (\r\n"
			+ "	SELECT al.id_group\r\n" + "	FROM alarm al\r\n" + "	WHERE al.alarm_id IN(\r\n" + "\r\n"
			+ "	SELECT alarm_id\r\n" + "	FROM alarm \r\n" + "	WHERE alarm_id IN(\r\n"
			+ "	SELECT MAX(alarm_id)\r\n" + "	FROM alarm a \r\n"
			+ "	WHERE id_idinfo=?1 AND isactive=1 AND status_alarm_id IN(3,4,6)\r\n" + "	GROUP BY a.id_group))\r\n"
			+ "	AND status_alarm_id IN(3,4) and groups_id=?2\r\n" + "	);" +

			" ", nativeQuery = true)
	public List<Groups> canCancelGroup(@Param("idinfo") long idinfo, @Param("groups_id") long groups_id);

	@Query(value = "	SELECT * from groups " +

			" ", nativeQuery = true)
	public List<Groups> findAllGroup();

}
