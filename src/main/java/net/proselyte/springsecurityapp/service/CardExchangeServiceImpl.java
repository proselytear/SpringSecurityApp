package net.proselyte.springsecurityapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.CardExchangeDao;
import net.proselyte.springsecurityapp.model.CardExchange;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class CardExchangeServiceImpl implements CardExchangeService {

	@Autowired
	private CardExchangeDao cardExchangeDao;

	public List<CardExchange> findObject(long idinfo) {
		return cardExchangeDao.findObject(idinfo);
	}

}
