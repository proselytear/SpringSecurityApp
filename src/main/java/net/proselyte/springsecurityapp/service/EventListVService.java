package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import net.proselyte.springsecurityapp.model.EventListV;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

public interface EventListVService {

	public List<EventListV> findObject(long idinfo);

	List<EventListV> findObjectData(@Param("idinfo") long idinfo, String dataEvdata, String dataEvdata1);

	public List<EventListV> findObjectFull();

	public List<EventListV> findObjectData(@Param("dataEvdata") String dataEvdata,
			@Param("dataEvdata") String dataEvdata1);

	public List<EventListV> findObjectKey(@Param("key") String key);

	public EventListV findObjectIdev(Integer idev);

}
