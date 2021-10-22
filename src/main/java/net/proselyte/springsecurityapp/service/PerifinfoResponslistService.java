package net.proselyte.springsecurityapp.service;

import org.springframework.dao.DataIntegrityViolationException;

import net.proselyte.springsecurityapp.calculation.MyException.AddListResponsException;
import net.proselyte.springsecurityapp.model.PerifinfoResponslist;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekaterina Nosenko
 * @version 1.0
 */

public interface PerifinfoResponslistService {

	public void save(PerifinfoResponslist perifinfoResponslist) throws DataIntegrityViolationException;

	public void savePerifinfoRespons(Long idinfoCard, Long perifinfoResponsibleId) throws AddListResponsException;

}
