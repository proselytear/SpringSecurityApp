package net.proselyte.springsecurityapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "typename")
public class TypeName {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long typename_id;

	@Column(name = "NAME_RU")
	public String nameRu;

	@Column(name = "SMALLNAME_RU")
	public String smallnameRu;

	@Column(name = "TABLENAME")
	public String tableName;

	public Long getTypename_id() {
		return typename_id;
	}

	public void setTypename_id(Long typename_id) {
		this.typename_id = typename_id;
	}

	public String getNameRu() {
		return nameRu;
	}

	public void setNameRu(String nameRu) {
		this.nameRu = nameRu;
	}

	public String getSmallnameRu() {
		return smallnameRu;
	}

	public void setSmallnameRu(String smallnameRu) {
		this.smallnameRu = smallnameRu;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
