package net.proselyte.springsecurityapp.service;

/**
 * Service for Security.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

public interface SecurityService {

	String findLoggedInUsername();

	void autoLogin(String username, String password);
}
