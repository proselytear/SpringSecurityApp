package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.CardNotationDao;
import net.proselyte.springsecurityapp.model.CardNotation;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class CardNotationServiceImpl implements CardNotationService {

	@Autowired
	private CardNotationDao cardNotationDao;

	public List<CardNotation> findObject(long idinfo) {
		return cardNotationDao.findObject(idinfo);
	}

	@Override
	public void saveCardNotation(CardNotation cardNotation) {
		// TODO Auto-generated method stub
		cardNotationDao.save(cardNotation);

	}

}
