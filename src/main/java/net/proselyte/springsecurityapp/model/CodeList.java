package net.proselyte.springsecurityapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "codelist")
public class CodeList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idcodelist;

	@Column(name = "kod")
	public String kod;

	@Column(name = "events")
	public String events;

	public Long getIdcodelist() {
		return idcodelist;
	}

	public void setIdcodelist(Long idcodelist) {
		this.idcodelist = idcodelist;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

}
