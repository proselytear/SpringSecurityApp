package net.proselyte.springsecurityapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.proselyte.springsecurityapp.var.StaticVarStatus;

@Entity
@Table(name = "groups_status_name")
public class GroupsStatusName {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_groups_status_name;

	@Column(name = "status_name")
	public String statusName;

	public Long getId_groups_status_name() {
		return id_groups_status_name;
	}

	public void setId_groups_status_name(Long id_groups_status_name) {
		this.id_groups_status_name = id_groups_status_name;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getInput() {
		if (id_groups_status_name == StaticVarStatus.groupInObject
				|| id_groups_status_name == StaticVarStatus.groupRevision)

			return "<input id='idAccountSetStGr' name='accountId' type='text' size='10'>";
		else
			return "";

	}

}
