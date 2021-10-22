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
@Table(name = "perifinforesponsible")
public class PerifinfoResponsible {

	public PerifinfoResponsible(String firstName, String lastName, String patronymic, String note, String address,
			String phone) {
		super();

		this.firstName = firstName;
		this.lastName = lastName;
		this.patronymic = patronymic;

		this.note = note;
		this.address = address;
		this.phone = phone;

	}

	public PerifinfoResponsible() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long perifinfoResponsible_id;

	@Column(name = "first_name")
	public String firstName;

	@Column(name = "last_name")
	public String lastName;

	@Column(name = "patronymic")
	public String patronymic;

	@Column(name = "note")
	public String note;

	@Column(name = "address")
	public String address;

	@Column(name = "phone")
	public String phone;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getFullName() {
		return lastName + " " + firstName + "  " + patronymic;
	}

	public Long getPerifinfoResponsible_id() {
		return perifinfoResponsible_id;
	}

	public void setPerifinfoResponsible_id(Long perifinfoResponsible_id) {
		this.perifinfoResponsible_id = perifinfoResponsible_id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
