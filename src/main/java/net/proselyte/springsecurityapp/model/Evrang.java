package net.proselyte.springsecurityapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "evrang")
public class Evrang {

	@Id
	@Column(name = "nameevrang")
	public String nameevrang;

	@Column(name = "evrangid")
	public Long evrangid;

	public String getNameevrang() {
		return nameevrang;
	}

	public void setNameevrang(String nameevrang) {
		this.nameevrang = nameevrang;
	}

	public Long getEvrangid() {
		return evrangid;
	}

	public void setEvrangid(Long evrangid) {
		this.evrangid = evrangid;
	}

}
