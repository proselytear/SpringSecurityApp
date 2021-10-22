package net.proselyte.springsecurityapp.service;

import java.util.List;

import net.proselyte.springsecurityapp.model.AlarmWork;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekaterina Nosenko
 * @version 1.0
 */

public interface AlarmWorkService {

	public List<AlarmWork> findAll();

	public List<AlarmWork> findIdinfo(Long idinfo);

}
