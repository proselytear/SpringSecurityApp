package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.AlarmWork;

@Repository
public interface AlarmWorkDao extends JpaRepository<AlarmWork, Long> {

	@Query(value = "SELECT aw.* from alarm_work aw order by alarm_id desc   \r\n" + " ", nativeQuery = true)
	public List<AlarmWork> findAll();

	@Query(value = "SELECT aw.* from alarm_work aw  WHERE aw.IDINFO=? order by alarm_id desc \r\n"
			+ " ", nativeQuery = true)
	public List<AlarmWork> findIdinfo(@Param("idinfo") Long idinfo);

}
