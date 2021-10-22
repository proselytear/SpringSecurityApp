package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import net.proselyte.springsecurityapp.model.ObjectView;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekaterina Nosenko
 * @version 1.0
 */

public interface ObjectViewService {

	List<ObjectView> getAll(Long citi_id);

	public List<ObjectView> findObject(String keyword, long city_id);

	public List<ObjectView> findObject(String keyword);

	public List<ObjectView> findTechnic(@Param("idinfo") long idinfo);

	public List<ObjectView> findSconnection();

	public ObjectView findId(@Param("idinfo") Long idinfo);

	List<ObjectView> findAll();

	public List<ObjectView> getObject();

}
