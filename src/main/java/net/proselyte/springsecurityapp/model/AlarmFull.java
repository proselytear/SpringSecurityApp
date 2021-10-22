package net.proselyte.springsecurityapp.model;

import java.sql.Time;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "alarm_full")
public class AlarmFull {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long alarm_id;

	@Column(name = "idinfo")
	private Long idinfo;

	@Column(name = "ideqval")
	public Long ideqval;

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

	@Column(name = "timefrom")
	public Time timeFrom;

	@Column(name = "timeTo")
	private Time timeTo;

	@Column(name = "timeopenclose")
	private Time timeOpenClose;

	@Column(name = "city_id")
	private Long cityId;

	@Column(name = "region_ru")
	private String region_ru;

	@Column(name = "hastechnic")
	private Integer hastechnic;

	@Column(name = "technicfio")
	private String technicfio;

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

	@Column(name = "isactive")
	private Integer isactive;

	@Column(name = "aldata")
	private Long aldata;

	@Column(name = "evcode")
	private Integer evcode;

	@Column(name = "status_alarm_id")
	private Long statusAlarmId;

	@Column(name = "isread")
	private Integer isread;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "name_alarm_cancel")
	private String nameAlarmCancel;

	@Column(name = "id_status")
	private Long id_status;

	private static Long currentidinfo = null;

	public void setCurrentIdinfo(Long idinfo2) {
		currentidinfo = idinfo2;
	}

	public Long getCurrentIdinfo() {
		return currentidinfo;
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
		s += "<td><input type=\"hidden\" name=\"Alarm_id\" class=\"item\"\n" + "	 value=\'" + getAlarm_id() + "'>"
				+ getAlarm_id() + "</td>" + "<td>" + getStatusAlarmIdImg() + "</td>" + "<td>" + accountID + "</td>"
				+ "<td>" + paramNum + "</td>" + "<td>" + perifName + "</td>" + "<td>" + adress + "</td>" + "<td>"
				+ getStatusyFull() + "</td>" + "<td>" + getNoteTab() + "</td>" + "<td>" + getAldataDate() + "</td>"
				+ "<td>" + getAldataTime() + "</td>" + "<td>" + getEvcode() + "</td>" + "<td>" + employeeName + "</td>"
				+ "<td>" + nameAlarmCancel + "</td>" + "</tr>";
		return s;
	}

	private static Long currentAlarmId2 = null;

	public void setCurrentAlarmId2(Long idinfo2) {
		currentAlarmId2 = idinfo2;
	}

	public Long getCurrentAlarmId2() {
		return currentAlarmId2;
	}

	public String getColorAlarm2() {

		if (getCurrentAlarmId2() != null && getCurrentAlarmId2().intValue() == getAlarm_id().intValue()) {
			return "<tr class='rowlink' bgcolor='#FFFF00'>";
		} else
			return "<tr class='rowlink'  bgcolor='#DCD4FB'>";
	}

	public String getTabAlarm2() {
		String s = "";
		s = getColorAlarm2();
		String adress = "";
		if (fullname != null)
			adress = fullname;
		String employeeName = "";
		if (getEmployeeName() != null)
			employeeName = getEmployeeName();

		s += "<td><input type=\"hidden\" class='anotherclass' name=\"Alarm_id\" class=\"item\"\n" + "	 value=\'"
				+ getAlarm_id() + "'>" + getStatusAlarmIdImg() + "</td>" + "<td>" + accountID + "</td>" + "<td>"
				+ paramNum + "</td>" + "<td>" + perifName + "</td>" + "<td>" + getEvznnumVal() + "</td>" + "<td>"
				+ getEvzndataVal() + "</td>" + "<td>" + adress + "</td>"
				+ "<td><svg width=\"17px\" height=\"12px\"><circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"red\" /></svg> ТРЕВОГА</td>"
				+ "<td>" + getStatusyFull() + "</td>" + "<td>" + getNote() + "</td>" + "<td>" + getAldataDate()
				+ "</td>" + "<td>" + getAldataTime() + "</td>" + "<td>" + getEvcode() + "</td>" + "<td>" + employeeName
				+ "</td>" + "</tr>";
		return s;
	}

	private static Long currentAlarmId3 = null;

	public void setCurrentAlarmId3(Long idinfo2) {
		currentAlarmId3 = idinfo2;
	}

	public Long getCurrentAlarmId3() {
		return currentAlarmId3;
	}

	public String getColorAlarm3() {

		if (getCurrentAlarmId3() != null && getCurrentAlarmId3().intValue() == getAlarm_id().intValue()) {
			return "<tr class='rowlink' bgcolor='#FFFF00'>";
		} else
			return "<tr class='rowlink'  bgcolor='#DCD4FB'>";
	}

	public String getTabAlarm3() {
		String s = "";
		s = getColorAlarm3();
		String adress = "";
		if (fullname != null)
			adress = fullname;

		if (getEmployeeName() != null)
			employeeName = getEmployeeName();

		s += "<td><input type=\"hidden\" name=\"Alarm_id\" class=\"item\"\n" + "	 value=\'" + getAlarm_id() + "'>"
				+ getStatusAlarmIdImg() + "</td>" + "<td>" + accountID + "</td>" + "<td>" + paramNum + "</td>" + "<td>"
				+ perifName + "</td>" + "<td>" + getEvznnumVal() + "</td>" + "<td>" + getEvzndataVal() + "</td>"
				+ "<td>" + adress + "</td>"
				+ "<td><svg width=\"17px\" height=\"12px\"><circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"red\" /></svg> ТРЕВОГА</td>"
				+ "<td>" + getStatusyFull() + "</td>" + "<td>" + getNoteTab() + "</td>" + "<td>" + getAldataDate()
				+ "</td>" + "<td>" + getAldataTime() + "</td>" + "<td>" + getEvcode() + "</td>" + "</tr>";
		return s;
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

	public Long getIdinfo() {
		return idinfo;
	}

	public void setIdinfo(Long idinfo) {
		this.idinfo = idinfo;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ideqval", insertable = false, updatable = false)
	private Maineq Maineq;

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

	public Time getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Time timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Time getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(Time timeTo) {
		this.timeTo = timeTo;
	}

	public Time getTimeOpenClose() {
		return timeOpenClose;
	}

	public void setTimeOpenClose(Time timeOpenClose) {
		this.timeOpenClose = timeOpenClose;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getIdeqval() {
		return ideqval;
	}

	public void setIdeqval(Long ideqval) {
		this.ideqval = ideqval;
	}

	public String getRegion_ru() {
		return region_ru;
	}

	public void setRegion_ru(String region_ru) {
		this.region_ru = region_ru;
	}

	public String getHastechnicImg() {
		if (hastechnic != null)
			return Icon.hammer;
		else
			return "";
	}

	public Integer getHastechnic() {
		return hastechnic;
	}

	public String getHastechnicStr() {
		if (hastechnic != null)
			return Icon.hammer + " Группа " + paramNum + " РАБОТАЕТ ТЕХНИК: " + technicfio;
		else
			return "";
	}

	public String getTechnicfio() {
		return technicfio;
	}

	public void setTechnicfio(String technicfio) {
		this.technicfio = technicfio;
	}

	public Maineq getMaineq() {
		return Maineq;
	}

	public void setMaineq(Maineq maineq) {
		Maineq = maineq;
	}

	public void setHastechnic(Integer hastechnic) {
		this.hastechnic = hastechnic;
	}

	public Long getAlarm_id() {
		return alarm_id;
	}

	public void setAlarm_id(Long alarm_id) {
		this.alarm_id = alarm_id;
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

	public String getNoteTab() {
		if (note == null)
			return "";
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

	public Integer getIsactive() {
		return isactive;
	}

	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
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

	public Long getStatusAlarmId() {
		return statusAlarmId;
	}

	public Integer getIsread() {
		return isread;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getNameAlarmCancel() {
		return nameAlarmCancel;
	}

	public void setNameAlarmCancel(String nameAlarmCancel) {
		this.nameAlarmCancel = nameAlarmCancel;
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

}
