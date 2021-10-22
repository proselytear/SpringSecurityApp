package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.AlarmNew;

@Repository
public interface AlarmNewDao extends JpaRepository<AlarmNew, Long> {

	@Query(value = "SELECT af.* \r\n" + " from alarm_new af  \r\n" + " ", nativeQuery = true)
	public List<AlarmNew> findAlarmNew();

}
