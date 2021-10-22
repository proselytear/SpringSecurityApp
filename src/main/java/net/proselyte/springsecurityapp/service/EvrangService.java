package net.proselyte.springsecurityapp.service;

import java.util.List;

import net.proselyte.springsecurityapp.model.Evrang;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekaterina Nosenko
 * @version 1.0
 */

public interface EvrangService {

	List<Evrang> getEvrangFull();

}
