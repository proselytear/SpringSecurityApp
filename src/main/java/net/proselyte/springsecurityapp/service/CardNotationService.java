package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import net.proselyte.springsecurityapp.model.CardNotation;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

public interface CardNotationService {

	public List<CardNotation> findObject(long idinfo);

	@Transactional
	public void saveCardNotation(CardNotation cardNotation);

}
