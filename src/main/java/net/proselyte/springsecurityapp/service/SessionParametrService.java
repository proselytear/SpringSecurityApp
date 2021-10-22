package net.proselyte.springsecurityapp.service;

import net.proselyte.springsecurityapp.model.SessionParametr;

/**
 *
 * 
 * @author Ekateryna Nosenko
 * @version 1.0
 */

public interface SessionParametrService {

	SessionParametr findOne(String session_id);

	public void save(SessionParametr sessionParametr);

}
