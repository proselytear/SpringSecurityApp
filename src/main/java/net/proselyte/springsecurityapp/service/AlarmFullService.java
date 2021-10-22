package net.proselyte.springsecurityapp.service;

import java.util.List;

import net.proselyte.springsecurityapp.model.AlarmFull;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekaterina Nosenko
 * @version 1.0
 */

public interface AlarmFullService {

	public List<AlarmFull> findAlarmNotRead();

	public List<AlarmFull> findAlarmNotNew();

}
