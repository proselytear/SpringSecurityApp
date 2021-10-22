package net.proselyte.springsecurityapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.proselyte.springsecurityapp.model.TypeName;

@Repository
public interface TypeNameDao extends JpaRepository<TypeName, Long> {

	@Query(value = "  SELECT t.typename_id, t.NAME_RU, t.SMALLNAME_RU, t.TABLENAME FROM typename t  union ALL  SELECT -1, '','', 'street' FROM DUAL  ORDER BY 1", nativeQuery = true)
	List<TypeName> findAll();

}
