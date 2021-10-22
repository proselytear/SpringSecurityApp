package net.proselyte.springsecurityapp.model;

import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.proselyte.springsecurityapp.Icon.Icon;
import net.proselyte.springsecurityapp.calculation.Status;
import net.proselyte.springsecurityapp.var.StaticVarStatus;

/**
 * Simple JavaBean domain object that represents a User.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */
@Entity
@Table(name = "alarm_work")
public class AlarmWork {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long alarm_id;

	@Column(name = "accountid")
	public String accountID;

	@Column(name = "paramnum")
	private int paramNum;

	@Column(name = "perifname")
	public String perifName;

	@Column(name = "fullName")
	private String fullname;

	@Column(name = "statusy")
	private String statusy;

	@Column(name = "status_name")
	private String status_name;

	@Column(name = "note")
	private String note;

	@Column(name = "evzndata")
	private String evzndata;

	@Column(name = "evznnum")
	private Long evznnum;

	@Column(name = "group_name")
	private String group_name;

	@Column(name = "aldata")
	private Long aldata;

	@Column(name = "evcode")
	private Integer evcode;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "id_status")
	private Long id_status;

	@Column(name = "current_idinfo")
	private Integer currentIdInfo;

	public String getColorAlarm() {
		String result = "";

		if (getCurrentIdInfo() != null && getCurrentIdInfo().equals(getAlarm_id())) {
			result = "<tr class='rowlink' bgcolor='#FFFF00'>";
		}
		return result;
	}

	public String getTabWork() {
		String s = "";
		s = getColorAlarm();
		String adress = "";
		if (fullname != null)
			adress = fullname;
		String employeeName = "";
		if (getEmployeeName() != null)
			employeeName = getEmployeeName();

		String checkBoxtable = "";

		String checkBoxtableStat = "";

		s += "<td style=\"display: none\"><input type=\"hidden\" class='anotherclass' name=\"Alarm_idStr\" class=\"item\"\n"
				+ "	 value=\'" + getAlarm_id() + "'>" + getAlarm_id() + "</td>" + "<td>" + checkBoxtable + "</td>"
				+ "<td>" + getStatusAlarmIdImg() + "</td>" + "<td>" + accountID + "</td>" + "<td>" + checkBoxtableStat
				+ "</td>" + "<td>" + getStatusyFull() + "</td>" + "<td>" + paramNum + "</td>" + "<td>" + perifName
				+ "</td>" + "<td>" + getEvznnumVal() + "</td>" + "<td>" + getEvzndataVal() + "</td>" + "<td>" + adress
				+ "</td>"
				+ "<td><svg width=\"17px\" height=\"12px\"><circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"red\" /></svg> ТРЕВОГА</td>"
				+ "<td>" + getNote() + "</td>" + "<td>" + getAldataDate() + "</td>" + "<td>" + getAldataTime() + "</td>"
				+ "<td>" + getEvcode() + "</td>" + "<td>" + employeeName + "</td>" + "</tr>";

		return s;
	}

	public String getStatusAlarmIdImg() {
		if (id_status == StaticVarStatus.stGroupLeft)
			return Icon.car;
		else if (id_status == StaticVarStatus.stGroupArrived)
			return Icon.check + Icon.car;
		else if (id_status == StaticVarStatus.stGroupCancel)
			return Icon.cross + Icon.car;
		else
			return "";
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public int getParamNum() {
		return paramNum;
	}

	public void setParamNum(int paramNum) {
		this.paramNum = paramNum;
	}

	public String getPerifName() {
		return perifName;
	}

	public void setPerifName(String perifName) {
		this.perifName = perifName;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	/*
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "ideqval", insertable = false, updatable = false) private
	 * Maineq Maineq;
	 */

	public String getStatusyOchrana() {
		Status status = new Status(statusy, paramNum);

		String result = status.getS_status_och_group();
		if (result == StaticVarStatus.statusGroupOchrana) {

			return result;
		} else
			return null;

	}

	public void setStatusy(String statusy) {
		this.statusy = statusy;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public Long getAldata() {
		return aldata;
	}

	public void setAldata(Long aldata) {
		this.aldata = aldata;
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

	public String getAldataTime() {
		if (aldata != null) {

			SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss   ");
			java.util.Date time = new java.util.Date((long) aldata * 1000);
			String date1 = format1.format(time);

			return date1;
		} else
			return "";

	}

	public Integer getEvcode() {
		return evcode;
	}

	public void setEvcode(Integer evcode) {
		this.evcode = evcode;
	}

	public String getStatusy() {
		return statusy;
	}

	public String getStatusyFull() {
		String group_name = "";
		if (getGroup_name() != null)
			group_name = " (" + getGroup_name() + ")";
		return getStatus_name() + group_name;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public Long getId_status() {
		return id_status;
	}

	public void setId_status(Long id_status) {
		this.id_status = id_status;
	}

	public String getEvzndata() {
		return evzndata;
	}

	public String getEvzndataVal() {
		if (evzndata == null)
			return "";
		else
			return evzndata;
	}

	public void setEvzndata(String evzndata) {
		this.evzndata = evzndata;
	}

	public Long getEvznnum() {
		return evznnum;
	}

	public String getEvznnumVal() {
		if (evznnum == null)
			return "";
		return String.valueOf(evznnum);
	}

	public void setEvznnum(Long evznnum) {
		this.evznnum = evznnum;
	}

	public Long getAlarm_id() {
		return alarm_id;
	}

	public void setAlarm_id(Long alarm_id) {
		this.alarm_id = alarm_id;
	}

	public Integer getCurrentIdInfo() {
		return currentIdInfo;
	}

	public void setCurrentIdInfo(Integer currentIdInfo) {
		this.currentIdInfo = currentIdInfo;
	}

}
