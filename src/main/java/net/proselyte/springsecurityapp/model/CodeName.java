package net.proselyte.springsecurityapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "codename")
public class CodeName {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codename_id;

	@Column(name = "namecode")
	public String nameCode;

	public Long getCodename_id() {
		return codename_id;
	}

	public void setCodename_id(Long codename_id) {
		this.codename_id = codename_id;
	}

	public String getNameCode() {
		return nameCode;
	}

	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}

}
