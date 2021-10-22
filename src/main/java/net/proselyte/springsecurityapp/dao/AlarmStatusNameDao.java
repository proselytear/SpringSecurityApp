package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.AlarmStatusName;

@Repository
public interface AlarmStatusNameDao extends JpaRepository<AlarmStatusName, Long> {

	@Query(value = "SELECT asn.* from alarm_status_name asn union all select -1, ' ' from dual order by 1"
			+ " ", nativeQuery = true)
	public List<AlarmStatusName> findObjectStatus();

}
