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
@Table(name = "cardnotation")
public class CardNotation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cardNotation_id;

	@Column(name = "card_id")
	private Long cardId;

	@Column(name = "notation")
	private String notation;

	@Column(name = "notation_date")
	private Timestamp notationDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "card_id", insertable = false, updatable = false)
	private Card card;

	public Long getCardNotation_id() {
		return cardNotation_id;
	}

	public void setCardNotation_id(Long cardNotation_id) {
		this.cardNotation_id = cardNotation_id;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getNotation() {
		return notation;
	}

	public void setNotation(String notation) {
		this.notation = notation;
	}

	public Timestamp getNotationDate() {
		return notationDate;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public void setNotationDate(Timestamp notationDate) {
		this.notationDate = notationDate;
	}

	public static CardNotation createCardNotation(JSONObject jsonObject) {
		String kardNoteSet = jsonObject.get("kardNoteSet").toString();
		CardNotation cardNotationSet = null;
		if (!kardNoteSet.equals("")) {
			cardNotationSet = new CardNotation();
			// cardNotationSet.setCardId(card_idSet);
			cardNotationSet.setNotation(kardNoteSet);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			cardNotationSet.setNotationDate(timestamp);

		}
		return cardNotationSet;
	}

}
