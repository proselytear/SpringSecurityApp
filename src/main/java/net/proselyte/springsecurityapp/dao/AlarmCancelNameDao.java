package net.proselyte.springsecurityapp.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.AlarmCancelName;
@Repository
public interface AlarmCancelNameDao extends JpaRepository<AlarmCancelName, Long> {



	
	@Query(value =  "SELECT acn.id_alarm_cancel, acn.name_alarm_cancel\r\n" + 
			"FROM alarm_cancel_name acn" + 
			" " , nativeQuery = true)
	public List<AlarmCancelName> findObjectAll();
	

	

	
	
	
}

