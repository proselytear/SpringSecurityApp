package net.proselyte.springsecurityapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "groups_maineq_temp")
public class GroupsMaineqTemp {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long groups_maineq_id;

	@Column(name = "groups_id")
	public long groups_id;

	@Column(name = "maineq_id")
	public long maineq_id;

	@Column(name = "priority")
	public int priority;

	public Long getGroups_maineq_id() {
		return groups_maineq_id;
	}

	public void setGroups_maineq_id(Long groups_maineq_id) {
		this.groups_maineq_id = groups_maineq_id;
	}

	public long getGroups_id() {
		return groups_id;
	}

	public void setGroups_id(long groups_id) {
		this.groups_id = groups_id;
	}

	public long getMaineq_id() {
		return maineq_id;
	}

	public void setMaineq_id(long maineq_id) {
		this.maineq_id = maineq_id;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
