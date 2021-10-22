package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.UpdateLast;

@Repository
public interface UpdateLastDao extends JpaRepository<UpdateLast, Long> {

	// Получить последнее время обновления таблицы по имени
	@Query(value = "SELECT *\r\n" + "FROM update_last u\r\n", nativeQuery = true)
	List<UpdateLast> getUpdateLast();

}
