package net.proselyte.springsecurityapp.service;

import java.util.List;

import net.proselyte.springsecurityapp.model.Test;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

public interface TestService {

	List<Test> getAll();

}
