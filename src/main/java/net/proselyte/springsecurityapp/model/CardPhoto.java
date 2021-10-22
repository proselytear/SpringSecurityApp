package net.proselyte.springsecurityapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Simple JavaBean domain object that represents a User.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Entity
@Table(name = "cardphoto")
public class CardPhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cardphoto_id")
	private Long cardphoto_id;

	@Column(name = "name")
	public String name;

	@Column(name = "photo")
	private byte[] photo;

	@Column(name = "card_id")
	private Integer card_id;

	public Long getCardphoto_id() {
		return cardphoto_id;
	}

	public void setCardphoto_id(Long cardphoto_id) {
		this.cardphoto_id = cardphoto_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Integer getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

}
