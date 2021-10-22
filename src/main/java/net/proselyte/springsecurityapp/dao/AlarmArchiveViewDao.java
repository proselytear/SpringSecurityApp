package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.AlarmArchiveView;

@Repository
public interface AlarmArchiveViewDao extends JpaRepository<AlarmArchiveView, Long> {
	// Все тревоги в архиве
	@Query(value = "SELECT af.* " + " FROM alarm_archiveview af \r\n" + " ORDER BY aldata desc \r\n"
			+ " ", nativeQuery = true)
	public List<AlarmArchiveView> findAlarmArchive();

//Тревоги в архиве по заданному диапазону времени
	@Query(value = "SELECT a.* "
			+ " FROM alarm_archiveview a where (FROM_UNIXTIME(aldata)>?1 and FROM_UNIXTIME(aldata)<?2) order by a.aldata desc", nativeQuery = true)
	public List<AlarmArchiveView> findObjectData(@Param("dataEvdata") String dataEvdata,
			@Param("dataEvdata") String dataEvdata1);

//Непрочитанные тревоги оператором которые в архиве находятся
	@Query(value = "SELECT af.* \r\n" + " from alarm_archiveview af where isread!=1 \r\n" + " ", nativeQuery = true)
	public List<AlarmArchiveView> findAlarmNotRead();

}
