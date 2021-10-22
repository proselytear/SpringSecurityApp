package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.Employees;

@Repository
public interface EmployeesDao extends JpaRepository<Employees, Long> {
	@Query(value = "SELECT e.ID, e.LAST_NAME, e.FIRST_NAME, e.PATRONYMIC, e.position_id \r\n"
			+ "FROM employee e  union all select 0 , ' ', ' ', ' ', -1 from dual order by 1 ;", nativeQuery = true)
	List<Employees> findPosition();

}
