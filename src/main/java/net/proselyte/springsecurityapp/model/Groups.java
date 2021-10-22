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

@Entity
@Table(name = "groups")
public class Groups {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long groups_id;

	@Column(name = "name")
	public String name;

	@Column(name = "info")
	public String info;

	@Column(name = "phone")
	public String phone;

	@Column(name = "imei")
	public String imei;

	@Column(name = "groups_status_id")
	public Long groups_status_id;

	@Column(name = "id_idinfo")
	public Long id_idinfo;

	@Column(name = "currentidinfo")
	private Long currentidinfo;

	public void setCurrentIdinfo(Long idinfo2) {
		currentidinfo = idinfo2;
	}

	public Long getCurrentIdinfo() {
		return currentidinfo;
	}

	public String getColor() {

		if (getCurrentIdinfo() != null && getGroups_id() != null
				&& getCurrentIdinfo().intValue() == getGroups_id().intValue()) {
			System.out.println("COLOR" + getGroups_id() + " " + getCurrentIdinfo());
			return "<tr class='rowlink' bgcolor='#FFFF00'>";
		} else
			return "<tr class='rowlink'  bgcolor='#C6CFF5'>";
	}

	public String getTab() {
		String s = "";
		s = getColor();
		String accauntID = "";
		if (getObjectview() != null)
			accauntID = getObjectview().getAccountID();
		s += "<td style='display: none'><input type=\"hidden\" name=\"groups_id\" class=\"item\"\n" + "	 value=\'"
				+ getGroups_id() + "'>" + getGroups_id() + "</td>" + "<td>" + getName() + "</td>" + "<td>"
				+ getGroupsStatusName().getStatusName() + "</td>" + "<td>" + accauntID + "</td>" + "</tr>";
		System.out.println(s);
		return s;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "groups_status_id", insertable = false, updatable = false)
	private GroupsStatusName groupsStatusName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_idinfo", insertable = false, updatable = false)
	private ObjectView objectview;

	public Long getGroups_id() {
		return groups_id;
	}

	public void setGroups_id(Long groups_id) {
		this.groups_id = groups_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Long getGroups_status_id() {
		return groups_status_id;
	}

	public void setGroups_status_id(Long id_groups_status_name) {
		this.groups_status_id = id_groups_status_name;
	}

	public Long getId_idinfo() {
		return id_idinfo;
	}

	public void setId_idinfo(Long id_idinfo) {
		this.id_idinfo = id_idinfo;
	}

	public GroupsStatusName getGroupsStatusName() {
		return groupsStatusName;
	}

	public void setGroupsStatusName(GroupsStatusName groupsStatusName) {
		this.groupsStatusName = groupsStatusName;
	}

	public ObjectView getObjectview() {
		return objectview;
	}

	public void setObjectview(ObjectView objectview) {
		this.objectview = objectview;
	}

	public String toString() {
		return "groups " + groups_id;
	}

	public String getMessageStatus() {
		if (getGroups_status_id() == 5l)
			return "Внимание! Вы собираетесь изменить состояние группы находящейся на выезде или прибывшей  на объект. Подтвердите действие!";
		else
			return null;
	}

}
