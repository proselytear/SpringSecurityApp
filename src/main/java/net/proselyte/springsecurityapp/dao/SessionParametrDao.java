package net.proselyte.springsecurityapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.SessionParametr;

@Repository
public interface SessionParametrDao extends JpaRepository<SessionParametr, Long> {

	@Query(value = "SELECT -1 AS id_parametr, '' AS session_id,-1 AS idinfo_alarm\r\n" + "FROM DUAL\r\n" + "union\r\n"
			+ "SELECT s.id_parametr, s.session_id, s.idinfo_alarm\r\n" + "FROM session_parametr s\r\n"
			+ "WHERE session_id=? order by 1 desc limit 1", nativeQuery = true)
	SessionParametr findOne(String session_id);

}
