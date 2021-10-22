package net.proselyte.springsecurityapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Ekateryna Nosenko
 *
 */
@Entity
@Table(name = "update_last")
public class UpdateLast {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_update_last;

	@Column(name = "name_table")
	public String nameTable;

	@Column(name = "time_update")
	public Integer time_update;

	@Column(name = "time_update_previous")
	public Integer time_update_previous;

	public Long getId_update_last() {
		return id_update_last;
	}

	public void setId_update_last(Long id_update_last) {
		this.id_update_last = id_update_last;
	}

	public String getNameTable() {
		return nameTable;
	}

	public void setNameTable(String nameTable) {
		this.nameTable = nameTable;
	}

	public Integer getTime_update() {
		return time_update;
	}

	public void setTime_update(Integer time_update) {
		this.time_update = time_update;
	}

	public Integer getTime_update_previous() {
		return time_update_previous;
	}

	public void setTime_update_previous(Integer time_update_previous) {
		this.time_update_previous = time_update_previous;
	}

}
