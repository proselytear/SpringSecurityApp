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
@Table(name = "city")
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long city_id;

	@Column(name = "name_ua")
	public String nameUa;

	@Column(name = "name_ru")
	public String nameRu;

	@Column(name = "sort")
	public Long sort;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Typename_id", insertable = false, updatable = false)
	private TypeName typename;

	public String getTypeName() {
		if (typename != null && typename.getTypename_id() != 6)
			return "(" + typename.getNameRu() + ")";
		else
			return "";
	}

	public Long getCity_id() {
		return city_id;
	}

	public void setCity_id(Long city_id) {
		this.city_id = city_id;
	}

	public String getNameUa() {
		return nameUa;
	}

	public void setNameUa(String nameUa) {
		this.nameUa = nameUa;
	}

	public String getNameRu() {
		return nameRu;
	}

	public void setNameRu(String nameRu) {
		this.nameRu = nameRu;
	}

	public TypeName getTypename() {
		return typename;
	}

	public void setTypename(TypeName typename) {
		this.typename = typename;
	}

}
