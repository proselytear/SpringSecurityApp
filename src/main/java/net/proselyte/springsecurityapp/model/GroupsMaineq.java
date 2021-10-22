package net.proselyte.springsecurityapp.model;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.proselyte.springsecurityapp.calculation.JsonConvert.Convert;
import net.sf.json.JSONObject;

@Entity
@Table(name = "groups_maineq")
public class GroupsMaineq {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long groups_maineq_id;

	@Column(name = "groups_id")
	public long groups_id;

	@Column(name = "maineq_id")
	public long maineq_id;

	@Column(name = "priority")
	public int priority;

	@ManyToOne
	@JoinColumn(name = "groups_id", insertable = false, updatable = false)
	Groups groups;

	@ManyToOne
	@JoinColumn(name = "maineq_id", insertable = false, updatable = false)
	Maineq maineq;

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

	public Groups getGroups() {
		return groups;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}

	public Maineq getMaineq() {
		return maineq;
	}

	public void setMaineq(Maineq maineq) {
		this.maineq = maineq;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public ArrayList<GroupsMaineq> getGroupList(JSONObject jsonObject, Long maineqIdSet) {

		String idGroupSet = jsonObject.get("idGroupSet").toString();

		// Преобразуем данные в коллекцию
		Set<String> idGroupHash = Convert.toArrayStringUnique(idGroupSet);
		ArrayList<GroupsMaineq> groupsMaineqList = new ArrayList<GroupsMaineq>();

		if (maineqIdSet != null && maineqIdSet != 0 && idGroupHash.size() > 0) {
			for (String i : idGroupHash) {
				GroupsMaineq groupsMaineq = new GroupsMaineq();
				groupsMaineq.setMaineq_id(maineqIdSet);
				groupsMaineq.setGroups_id(Integer.parseInt(i));
				groupsMaineqList.add(groupsMaineq);

			}

		}
		return groupsMaineqList;

	}

}
