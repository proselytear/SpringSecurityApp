package net.proselyte.springsecurityapp.service;

import java.util.List;

import net.proselyte.springsecurityapp.model.AlarmCancelName;
import net.proselyte.springsecurityapp.model.Groups;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekaterina Nosenko
 * @version 1.0
 */

public interface AlarmCancelService {

	public List<AlarmCancelName> findObjectAll();

	public void saveList(List<Groups> groupsList);

}
