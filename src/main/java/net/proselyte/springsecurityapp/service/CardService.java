package net.proselyte.springsecurityapp.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.proselyte.springsecurityapp.calculation.MyException.AddGroupCardException;
import net.proselyte.springsecurityapp.calculation.MyException.AddKardException;
import net.proselyte.springsecurityapp.model.Card;
import net.proselyte.springsecurityapp.model.CardExchange;
import net.proselyte.springsecurityapp.model.CardNotation;
import net.sf.json.JSONObject;

/**
 * Service class for {@link net.proselyte.springsecurityapp.model.User}
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

public interface CardService {

	public Card findObject(long idinfo);

	public Card getObjectId(long objectid);

	public Long getMeineqForObjectId(long card_id);

	public Long getCardforObject_id(long object_id);

	@Transactional
	public void saveCard(Card card);

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cardSaveFull(Card cardAddSet, CardNotation cardNotationSet, CardExchange cardExchange);

	public Card kardSet(JSONObject jsonObject) throws AddKardException;

	public Card getCardforObjectName(String accountid_card, long groupnum);

	public Long getmaineqId(JSONObject jsonObject) throws AddGroupCardException;

}
