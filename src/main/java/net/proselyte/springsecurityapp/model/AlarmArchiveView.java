package net.proselyte.springsecurityapp.model;

import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.proselyte.springsecurityapp.Icon.Icon;
import net.proselyte.springsecurityapp.var.StaticVarStatus;

/**
 * Simple JavaBean domain object that represents a User.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */
@Entity
@Table(name = "alarm_archiveview")
public class AlarmArchiveView {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long alarm_id;

	@Column(name = "id_idinfo")
	public Long idIdinfo;

	@Column(name = "status_alarm_id")
	public Integer statusAlarmId;

	@Column(name = "note")
	private String note;

	@Column(name = "id_operator")
	private Long idOperator;

	@Column(name = "id_group")
	private Integer idGroup;

	@Column(name = "isactive")
	private Integer isactive;

	@Column(name = "evcode")
	private Integer evcode;

	@Column(name = "aldata")
	private Long aldata;

	@Column(name = "id_eventlist")
	private Long idEventList;

	@Column(name = "evzndata")
	private String evzndata;

	@Column(name = "evznnum")
	private Long evznnum;

	@Column(name = "ACCOUNTID")
	private String accountid;

	@Column(name = "current_alarm_id")
	private Long currentIdinfo;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "name_alarm_cancel")
	private String nameAlarmCancel;

	@Column(name = "status_name")
	private String statusName;

	@Column(name = "paramnum")
	private String paramNum;

	@Column(name = "perifname")
	private String perifName;

	@Column(name = "group_name")
	private String group_name;

	public Long getAlarm_id() {
		return alarm_id;
	}

	public void setAlarm_id(Long alarm_id) {
		this.alarm_id = alarm_id;
	}

	public Long getIdIdinfo() {
		return idIdinfo;
	}

	public void setIdIdinfo(Long idIdinfo) {
		this.idIdinfo = idIdinfo;
	}

	public Integer getStatusAlarmId() {
		return statusAlarmId;
	}

	public void setStatusAlarmId(Integer statusAlarmId) {
		this.statusAlarmId = statusAlarmId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getIdOperator() {
		return idOperator;
	}

	public void setIdOperator(long l) {
		this.idOperator = l;
	}

	public Integer getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}

	public Integer getIsactive() {
		return isactive;
	}

	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}

	public Integer getEvcode() {
		return evcode;
	}

	public void setEvcode(Integer evcode) {
		this.evcode = evcode;
	}

	public Long getIdEventList() {
		return idEventList;
	}

	public void setIdEventList(Long long1) {
		this.idEventList = long1;
	}

	public Long getAldata() {
		return aldata;
	}

	public void setCurrentAldata() {
		this.aldata = System.currentTimeMillis() / 1000L;
	}

	public Long getAldataTime() {

		return aldata;
	}

	public void setIdOperator(Long idOperator) {
		this.idOperator = idOperator;
	}

	public String getEvzndata() {
		return evzndata;
	}

	public void setEvzndata(String evzndata) {
		this.evzndata = evzndata;
	}

	public Long getEvznnum() {
		return evznnum;
	}

	public void setEvznnum(Long evznnum) {
		this.evznnum = evznnum;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public Long getCurrentIdinfo() {
		return currentIdinfo;
	}

	public void setCurrentIdinfo(Long currentIdinfo) {
		this.currentIdinfo = currentIdinfo;
	}

	public String getColor() {

		if (getCurrentIdinfo() != null && getCurrentIdinfo().intValue() == getAlarm_id()) {
			return "<tr class='rowlink' bgcolor='#FFFF00'>";
		} else
			return "<tr class='rowlink'  bgcolor='#DCD4FB'>";
	}

	public String getTab() {

		String s = "";

		s = getColor();
		String adress = "";
		if (fullname != null)
			adress = fullname;
		String employeeName = "";
		if (getEmployeeName() != null)
			employeeName = getEmployeeName();
		String nameAlarmCancel = "";
		if (getNameAlarmCancel() != null)
			nameAlarmCancel = getNameAlarmCancel();
		s += "<td style='display: none'><input type=\"hidden\" name=\"Alarm_id\" class=\"item\"\n" + " value=\'"
				+ getAlarm_id() + "'>" + getAlarm_id() + "</td>" + "<td>" + getStatusAlarmIdImg() + "</td>" + "<td>"
				+ accountid + "</td>" + "<td>" + paramNum + "</td>" + "<td>" + perifName + "</td>" + "<td>" + adress
				+ "</td>" + "<td>" + getStatusyFull() + "</td>" + "<td>" + getNoteTab() + "</td>" + "<td>"
				+ getAldataDate() + "</td>" + "<td>" + getAldataTime() + "</td>" + "<td>" + getEvcode() + "</td>"
				+ "<td>" + employeeName + "</td>" + "<td>" + nameAlarmCancel + "</td>" + "</tr>";

		return s;

	}

	public String getStatusyFull() {
		String group_name = "";
		if (getGroup_name() != null)
			group_name = " (" + getGroup_name() + ")";
		return getStatusName() + group_name;
	}

	public String getAldataDate() {
		if (aldata != null) {
			SimpleDateFormat format1 = new SimpleDateFormat("dd:MM:yyyy");
			java.util.Date time = new java.util.Date((long) getAldata() * 1000);
			String date1 = format1.format(time);
			return date1;
		} else
			return "";
	}

	public String getStatusAlarmIdImg() {
		if (statusAlarmId == StaticVarStatus.stGroupLeft)
			return Icon.car;
		else if (statusAlarmId == StaticVarStatus.stGroupArrived)
			return Icon.check + Icon.car;
		else if (statusAlarmId == StaticVarStatus.stGroupCancel)
			return Icon.cross + Icon.car;
		else
			return "";
	}

	public String getNoteTab() {
		if (note == null)
			return "";
		return note;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getNameAlarmCancel() {
		return nameAlarmCancel;
	}

	public void setNameAlarmCancel(String nameAlarmCancel) {
		this.nameAlarmCancel = nameAlarmCancel;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setAldata(Long aldata) {
		this.aldata = aldata;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

}
