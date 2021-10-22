package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import net.proselyte.springsecurityapp.model.City;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekaterina Nosenko
 * @version 1.0
 */

public interface CityService {

	List<City> getAll();

	City getByName(String city_name);

	List<City> getAllRegion(@Param("regionSetId") Long regionSetId);

}
