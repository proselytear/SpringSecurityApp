package net.proselyte.springsecurityapp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.sf.json.JSONObject;

/**
 * Simple JavaBean domain object that represents a User.
 *
 * @author Kateryna Nosenko
 * @version 1.0
 */

@Entity
@Table(name = "cardexchange")
public class CardExchange {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cardexchange_id;

	@Column(name = "card_id")
	private Long cardId;

	@Column(name = "exchange_description")
	private String exchangeDescription;

	@Column(name = "exchange_date")
	private Timestamp exchangeDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "card_id", insertable = false, updatable = false)
	private Card card;

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Long getCardExchange_id() {
		return cardexchange_id;
	}

	public void setCardExchange_id(Long cardExchange_id) {
		this.cardexchange_id = cardExchange_id;
	}

	public String getExchangeDescription() {
		return exchangeDescription;
	}

	public void setExchangeDescription(String exchangeDescription) {
		this.exchangeDescription = exchangeDescription;
	}

	public Timestamp getExchangeDate() {
		return exchangeDate;
	}

	public void setExchangeDate(Timestamp exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	public Long getCardexchange_id() {
		return cardexchange_id;
	}

	public void setCardexchange_id(Long cardexchange_id) {
		this.cardexchange_id = cardexchange_id;
	}

	/**
	 * Устанавливаеи параметры замены в функции "добавить объект" в панеле
	 * "карточка"
	 * 
	 * @param jsonObject параметры в функции "добавить объект" в панеле вводимые
	 *                   пользователем
	 * @return
	 */
	public static CardExchange createCardExchange(JSONObject jsonObject) {
		// Получаем строку замены
		String replacementSet = jsonObject.get("replacementSet").toString().trim();
		// Если пользователь не ввел параметры замены возвращаеи пустой объект
		CardExchange cardExhangeSet = null;
		//
		if (!replacementSet.equals("")) {
			cardExhangeSet = new CardExchange();

			cardExhangeSet.setExchangeDescription(replacementSet);
			// устанавливаем текущую дату вставки
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			cardExhangeSet.setExchangeDate(timestamp);

		}
		return cardExhangeSet;
	}

}
