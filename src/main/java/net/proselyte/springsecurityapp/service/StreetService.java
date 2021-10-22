package net.proselyte.springsecurityapp.service;

import java.util.List;

import net.proselyte.springsecurityapp.model.Street;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekaterina Nosenko
 * @version 1.0
 */

public interface StreetService {

	public List<Street> findAll(Long city_id);

	Street findObj(String streetSetName, Long citySetId);

}
