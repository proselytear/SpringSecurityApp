package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import net.proselyte.springsecurityapp.model.ObjectView2;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekaterina Nosenko
 * @version 1.0
 */

public interface ObjectView2Service {

	List<ObjectView2> findAll(@Param("idinfo") Long idinfo);

	public List<ObjectView2> findObject(String keyword, long city_id, Long idinfo);

	List<ObjectView2> getAll(long citi_id);

	public List<ObjectView2> findObject2(String keyword, Long idinfo);

}
