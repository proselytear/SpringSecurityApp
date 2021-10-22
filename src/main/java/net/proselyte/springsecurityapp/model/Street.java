package net.proselyte.springsecurityapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "street")
public class Street {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long street_id;

	@Column(name = "NAME_RU")
	public String nameRu;

	@Column(name = "NAME_RU_OLD")
	public String nameRuOld;

	@Column(name = "typename_id")
	public Long typename_id;

	@Column(name = "city_id")
	public Long city_id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "typename_id", insertable = false, updatable = false)
	private TypeName typename;

	public Long getStreet_id() {
		return street_id;
	}

	public void setStreet_id(Long street_id) {
		this.street_id = street_id;
	}

	public String getNameRu() {
		return nameRu;
	}

	public String getNameRuFull() {
		if (typename != null)
			return nameRu + " " + typename.getSmallnameRu();
		else
			return nameRu;
	}

	public void setNameRu(String nameRu) {
		this.nameRu = nameRu;
	}

	public Long getTypename_id() {
		if (typename_id == null)
			return 0L;
		return typename_id;
	}

	public void setTypename_id(Long typename_id) {
		this.typename_id = typename_id;
	}

	public Long getCity_id() {
		return city_id;
	}

	public void setCity_id(Long city_id) {
		this.city_id = city_id;
	}

	public TypeName getTypename() {
		return typename;
	}

	public void setTypename(TypeName typename) {
		this.typename = typename;
	}

	public String getNameRuOldFull() {
		if (typename != null && nameRuOld != null)
			return nameRuOld + " " + typename.getSmallnameRu();
		else
			return "";
	}

	public String getNameRuOld() {
		return nameRuOld;
	}

	public void setNameRuOld(String nameRuOld) {
		this.nameRuOld = nameRuOld;
	}

}
