package net.proselyte.springsecurityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.proselyte.springsecurityapp.calculation.JsonConvert.Convert;
import net.proselyte.springsecurityapp.calculation.MyException.AddGroupCardException;
import net.proselyte.springsecurityapp.calculation.MyException.AddKardException;
import net.proselyte.springsecurityapp.dao.CardDao;
import net.proselyte.springsecurityapp.dao.CardExchangeDao;
import net.proselyte.springsecurityapp.dao.CardNotationDao;
import net.proselyte.springsecurityapp.model.Card;
import net.proselyte.springsecurityapp.model.CardExchange;
import net.proselyte.springsecurityapp.model.CardNotation;
import net.proselyte.springsecurityapp.model.Maineq;
import net.sf.json.JSONObject;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardDao cardDao;
	@Autowired
	private CardNotationDao cardNotationDao;
	@Autowired
	private CardExchangeDao cardExchangeDao;
	@Autowired
	private MaineqService maineqService;

	public Card findObject(long idinfo) {
		return cardDao.findObject(idinfo);
	}

	@Override
	public Card getObjectId(long objectid) {
		// TODO Auto-generated method stub
		return cardDao.getObjectId(objectid);
	}

	@Transactional
	public void saveCard(Card card) {
		cardDao.save(card);

	}

	@Override
	public Long getMeineqForObjectId(long card_id) {
		// TODO Auto-generated method stub
		return cardDao.getMeineqForObjectId(card_id);
	}

	@Override
	public Long getCardforObject_id(long object_id) {
		// TODO Auto-generated method stub
		return cardDao.getCardforObject_id(object_id);
	}

	/**
	 * Присваиваем значения карты вводимые пользователем
	 * 
	 * @param jsonObject поля вводимые пользователем
	 * @return
	 * @throws AddKardException
	 */
	public Card kardSet(JSONObject jsonObject) throws AddKardException {
		Card cardAddSet = null;
		Long ideqval = null;
//Получаем данные с функции 'Добавить объект'

		String nameOrganizationSet = jsonObject.get("nameOrganizationSet").toString();
		String phoneKardSet = jsonObject.get("phoneKardSet").toString();
		String directorKardSet = jsonObject.get("directorKardSet").toString();
		String kardInfoSet = jsonObject.get("kardInfoSet").toString();
		String kardAdditionallySet = jsonObject.get("kardAdditionallySet").toString();
		String equipmentSet = jsonObject.get("equipmentSet").toString();
		String addressFullSet = jsonObject.get("addressFullSet").toString();
		String accountKardSet = jsonObject.get("accountKardSet").toString().toUpperCase().trim();
		String idManagerSetStr = Convert.JsonToString("idManagerSet", jsonObject);

//Поскольку перед создания прибора в карточке вначале обязательно создать этот прибор в настройках ппк(таблица maineq) проверяем наличие этого прибора в таблице maineq
		Maineq maineqKard = maineqService.findMaineqByAccount(accountKardSet);
		if (maineqKard == null) {

			throw new AddKardException("Вначале создайте объект в настройках ППК.");
		} else {
			ideqval = maineqKard.getIdeqval();// получаем ид прибора (дальше для связки таблиц maineq и card)
		}
		// Проверяем были ли создана карточка для данного прибора
		if (ideqval != null)
			cardAddSet = this.getObjectId(ideqval);
		if (cardAddSet != null) {

			throw new AddKardException("Карточка уже была создана раньше");

		}
		// Создаем новую карточку
		cardAddSet = new Card();
		// Запролняем поля карты
		Long idManagerSet = null;
		if (idManagerSetStr != null && !idManagerSetStr.equals(""))
			idManagerSet = Long.parseLong(idManagerSetStr);

		if (idManagerSet == 0)
			cardAddSet.setManager_id(null);
		else
			cardAddSet.setManager_id(idManagerSet);
		cardAddSet.setName(nameOrganizationSet);
		cardAddSet.setPhone(phoneKardSet);
		cardAddSet.setDirector(directorKardSet);
		cardAddSet.setResponsible(kardInfoSet);
		cardAddSet.setAdditionally(kardAdditionallySet);
		cardAddSet.setEquipmentRent(equipmentSet);
		cardAddSet.setAccountid(accountKardSet);
		cardAddSet.setObject_id(ideqval);
		// По умолчанию карточка создается для первой группы
		cardAddSet.setGroupnum(1);
		cardAddSet.setAddressFull(addressFullSet);
		return cardAddSet;
	}

	public Long getmaineqId(JSONObject jsonObject) throws AddGroupCardException {
		Long maineqIdSet = null;
		String accountKardSet = jsonObject.get("accountKardSet").toString().toUpperCase();
		if (accountKardSet == null) {
			throw new AddGroupCardException("Введите номер Объекта");

		} else {

			Card card = this.getCardforObjectName(accountKardSet, 1l);// для 1 группы
			if (card == null) {
				System.out.println("Вначале сохраните объект" + accountKardSet);
				throw new AddGroupCardException("Вначале сохраните объект" + accountKardSet);
			}
			maineqIdSet = card.getObject_id();
		}
		return maineqIdSet;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cardSaveFull(Card cardAddSet, CardNotation cardNotationSet, CardExchange cardExchangeSet) {

		this.saveCard(cardAddSet);
		long card_idSet = cardAddSet.getCard_id();

		if (cardNotationSet != null) {
			cardNotationSet.setCardId(card_idSet);

			cardNotationDao.save(cardNotationSet);
		}
		if (cardExchangeSet != null) {
			cardExchangeSet.setCardId(card_idSet);

			cardExchangeDao.save(cardExchangeSet);
		}
	}

	@Override
	public Card getCardforObjectName(String accountid_card, long groupnum) {

		return cardDao.getCardforObjectName(accountid_card, groupnum);

	}

}
