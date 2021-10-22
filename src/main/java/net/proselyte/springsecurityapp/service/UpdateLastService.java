package net.proselyte.springsecurityapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;

import net.proselyte.springsecurityapp.model.UpdateLast;

/**
 *
 * 
 * @author Ekateryna Nosenko
 * @version 1.0
 */

public interface UpdateLastService {

	List<UpdateLast> getUpdateLast();

	@Transactional
	@Modifying
	public void save(UpdateLast updateLast);

}
