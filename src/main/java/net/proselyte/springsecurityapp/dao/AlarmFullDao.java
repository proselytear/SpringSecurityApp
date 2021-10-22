package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.AlarmFull;

@Repository
public interface AlarmFullDao extends JpaRepository<AlarmFull, Long> {

	@Query(value = "SELECT *\r\n" + "from alarm_full alf\r\n" + "WHERE alf.alarm_id IN (select MAX(alarm_id)\r\n"
			+ "from alarm_full af \r\n" + "where status_alarm_id IN (3,4,6) AND isactive=1 and isread=1 \r\n"
			+ "GROUP BY af.GROUPS_ID)\r\n" + "UNION ALL\r\n" + "SELECT *\r\n" + "from alarm_full af \r\n"
			+ "where status_alarm_id not IN (3,4,6) AND isactive=1  and isread=1 order by accountid, aldata desc\r\n"
			+ " ", nativeQuery = true)
	public List<AlarmFull> findAlarmNotNew();

	// Непрочитанные тревоги оператором которые в архиве находятся
	@Query(value = "SELECT af.* \r\n" + " from alarm_archive af where isread!=1 \r\n" + " ", nativeQuery = true)
	public List<AlarmFull> findAlarmNotRead();

}
