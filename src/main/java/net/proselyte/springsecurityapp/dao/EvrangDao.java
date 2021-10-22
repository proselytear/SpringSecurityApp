package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.Evrang;

@Repository
public interface EvrangDao extends JpaRepository<Evrang, Long> {
	@Query(value = "SELECT e.evrangid, e.nameevrang\r\n" + "FROM evrang e\r\n" + " order by 2 ", nativeQuery = true)
	List<Evrang> getEvrangFull();

}
