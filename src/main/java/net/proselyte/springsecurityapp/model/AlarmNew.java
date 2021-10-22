package net.proselyte.springsecurityapp.model;

import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Simple JavaBean domain object that represents a User.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */
@Entity
@Table(name = "alarm_new")
public class AlarmNew {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long alarm_id;

	@Column(name = "accountid")
	public String accountID;

	@Column(name = "fullName")
	private String fullname;

	@Column(name = "paramnum")
	private int paramNum;

	@Column(name = "perifname")
	public String perifName;

	@Column(name = "aldata")
	private Long aldata;

	@Column(name = "evcode")
	private Integer evcode;

	@Column(name = "idinfo")
	private Long idinfo;

	@Column(name = "note")
	private String note;

	@Column(name = "evznnum")
	private int evznnum;

	@Column(name = "evzndata")
	private String evzndata;

	@Column(name = "status_name")
	private String status_name;

	@Column(name = "count_alarm_accepted")
	private Integer count_alarm_accepted;

	@Column(name = "count_alarm_new")
	private Integer count_alarm_new;

	public String getColor() {
		if (getCurrentAlarmId() != null && getCurrentAlarmId().intValue() == getIdinfo().intValue()) {
			return "<tr class='rowlink' style='color: #fff; background: navy;' >";// выделяем выбранную строку другим
																					// цветом

		} else if (count_alarm_new != null && count_alarm_new > 0)
			return "<tr class='rowlink'  bgcolor='#EE9CA4'>";// выделяем новые тревогти другим цветом
		else
			return "<tr class='rowlink'  bgcolor='#DCD4FB'>";
	}

	public String getTab() {
		String s = getColor();

		String adress = "";
		if (fullname != null)
			adress = fullname;

		String signal_count = "";
		if (count_alarm_new != null) {
			signal_count = "<span  class=\"badge badge-danger\" >" + count_alarm_new + "</span>";
		}
		if (count_alarm_accepted != null) {
			signal_count += count_alarm_accepted;
		}

		s += "<td style=\"display: none\"><input type=\"hidden\" name=\"Alarm_id\" class=\"item\"\n" + "  value=\'"
				+ getIdinfo() + "'>" + getIdinfo() + "</td>" + "<td class=\"cent\">" + signal_count + "</td>" + "<td>"
				+ accountID + "</td>" + "<td class=\"cent\">" + getParamNumTab() + "</td>" + "<td>" + perifName
				+ "</td>" + "<td>" + getEvznnumTab() + "</td>" + "<td>" + evzndata + "</td>" + "<td>" + adress + "</td>"
				+ "<td>" + note + "</td>" + "<td>" + status_name + "</td>" + "<td>" + getAldataDate() + "</td>" + "<td>"
				+ getAldataTime() + "</td>" + "<td>" + getEvcode() + "</td>" + "</tr>";
		return s;
	}

	@Column(name = "current_alarm_id")
	private Long currentAlarmId;

	public void setCurrentAlarmId(Long idinfo) {
		currentAlarmId = idinfo;
	}

	public Long getCurrentAlarmId() {
		return currentAlarmId;
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

	public String getParamNumTab() {
		if (paramNum == 0)
			return "";
		else
			return String.valueOf(paramNum);
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

	public Long getAlarm_id() {
		return alarm_id;
	}

	public void setAlarm_id(Long alarm_id) {
		this.alarm_id = alarm_id;
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

	public Long getIdinfo() {
		return idinfo;
	}

	public void setIdinfo(Long idinfo) {
		this.idinfo = idinfo;
	}

	public Integer getCount_alarm_accepted() {
		return count_alarm_accepted;
	}

	/**
	 * 
	 * @param count_alarm_accepted количество принятых тревог на данном объекте
	 *                             оператором
	 */
	public void setCount_alarm_accepted(Integer count_alarm_accepted) {
		this.count_alarm_accepted = count_alarm_accepted;
	}

	public Integer getCount_alarm_new() {
		return count_alarm_new;
	}

	public void setCount_alarm_new(Integer count_alarm_new) {
		this.count_alarm_new = count_alarm_new;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getEvznnum() {
		return evznnum;
	}

	public String getEvznnumTab() {
		if (evznnum == 0)
			return "";

		return String.valueOf(evznnum);
	}

	public void setEvznnum(int evznnum) {
		this.evznnum = evznnum;
	}

}
