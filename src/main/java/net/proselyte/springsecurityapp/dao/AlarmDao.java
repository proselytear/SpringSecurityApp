package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.Alarm;

@Repository
public interface AlarmDao extends JpaRepository<Alarm, Long> {

	@Query(value = "SELECT a.* " + " from alarm a  where a.alarm_id=?1 \r\n" + " ", nativeQuery = true)
	public Alarm findObject(@Param("alarm_id") long alarm_id);

	@Query(value = "SELECT a.* " + " from alarm a  where isread<>1 \r\n" + " ", nativeQuery = true)
	public List<Alarm> findFullNew();

	@Query(value = "SELECT a.* " + " from alarm a  where a.id_idinfo=?1  AND isactive=1 ORDER BY aldata DESC\r\n"
			+ "LIMIT 1 \r\n" + " ", nativeQuery = true)
	public Alarm findObjectIdinfo(@Param("idinfo") long idinfo);

	@Query(value = "SELECT a.*  " + " from alarm a  where a.id_idinfo=?1 AND isactive=1 \r\n" +

			" ", nativeQuery = true)
	public List<Alarm> findObjectIdinfoAll(@Param("idinfo") long idinfo);

	@Query(value = "SELECT a.* " + " FROM alarm a\r\n"
			+ "WHERE a.isactive=1 AND a.id_group is not null and a.id_idinfo=?1 \r\n" +

			" ", nativeQuery = true)
	public List<Alarm> findGroupsIdinfo(@Param("idinfo") long idinfo);

	@Query(value = "SELECT *\r\n" + "FROM alarm\r\n"
			+ "WHERE isread=0    AND isactive=1 and status_alarm_id=1 AND id_idinfo=?1 \r\n" +

			" ", nativeQuery = true)
	public List<Alarm> newAlarmIdinfo(@Param("idinfo") long idinfo);

	// проверяет можно ли поставить статус что машина прибыла

	@Query(value = "SELECT alm.*\r\n" + "FROM alarm alm\r\n" + "WHERE aldata IN(\r\n" + "SELECT MAX(aldata)\r\n"
			+ "from alarm al\r\n" + "WHERE al.alarm_id IN (\r\n" + "SELECT MAX(alarm_id)\r\n" + "FROM alarm a \r\n"
			+ "WHERE id_idinfo=?1 AND isactive=1\r\n" + "AND status_alarm_id IN (3,4)\r\n"
			+ "GROUP BY a.id_group, status_alarm_id)\r\n" + "GROUP BY id_group)\r\n"
			+ "and id_idinfo=?1 AND isactive=1\r\n" + "AND status_alarm_id IN (3) AND id_group=?2"
			+ "", nativeQuery = true)
	public List<Alarm> canArrivedGroup(@Param("idinfo") long idinfo, @Param("idinfo") long group_id);

	@Query(value = "SELECT aw.* from alarm aw\r\n"
			+ " WHERE aw.isactive=1 AND aw.status_alarm_id=1 AND aw.isread=0 AND aw.id_idinfo=?1", nativeQuery = true)
	public List<Alarm> findIdnfo(@Param("idinfo") Long idinfo);

}
