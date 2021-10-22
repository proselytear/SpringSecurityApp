package net.proselyte.springsecurityapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author user
 *
 */
@Entity
@Table(name = "session_parametr")
public class SessionParametr {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_parametr;

	@Column(name = "session_id")
	private String session_id;

	@Column(name = "idinfo_alarm")
	private Long idinfo_alarm;

	public Long getId_parametr() {
		return id_parametr;
	}

	public void setId_parametr(Long id_parametr) {
		this.id_parametr = id_parametr;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public Long getIdinfo_alarm() {
		return idinfo_alarm;
	}

	public void setIdinfo_alarm(Long idinfo_alarm) {
		this.idinfo_alarm = idinfo_alarm;
	}
}
