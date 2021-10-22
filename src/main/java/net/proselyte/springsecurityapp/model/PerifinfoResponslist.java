package net.proselyte.springsecurityapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Simple JavaBean domain object that represents a User.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Entity
@Table(name = "perifinforesponslist")
public class PerifinfoResponslist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long perifinfoResponsList_id;

	@Column(name = "perifinfoList_id")
	public Long perifinfoList_id;

	@Column(name = "responList_id")
	public Long responList_id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "perifinfoList_id", insertable = false, updatable = false)
	private Perifinfo perifinfo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "responList_id", insertable = false, updatable = false)
	private PerifinfoResponslist perifinfoResponslist;

	public Long getPerifinfoResponsList_id() {
		return perifinfoResponsList_id;
	}

	public void setPerifinfoResponsList_id(Long perifinfoResponsList_id) {
		this.perifinfoResponsList_id = perifinfoResponsList_id;
	}

	public Long getPerifinfoList_id() {
		return perifinfoList_id;
	}

	public void setPerifinfoList_id(Long perifinfoList_id) {
		this.perifinfoList_id = perifinfoList_id;
	}

	public Long getResponList_id() {
		return responList_id;
	}

	public void setResponList_id(Long responList_id) {
		this.responList_id = responList_id;
	}

}
