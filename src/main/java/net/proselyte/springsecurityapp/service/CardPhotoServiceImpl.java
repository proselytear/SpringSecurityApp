package net.proselyte.springsecurityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.proselyte.springsecurityapp.dao.CardPhotoDao;
import net.proselyte.springsecurityapp.model.CardPhoto;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class CardPhotoServiceImpl implements CardPhotoService {

	@Autowired
	private CardPhotoDao cardPhotoDao;

	public CardPhoto findObject() {
		return cardPhotoDao.findObject();
	}

}
