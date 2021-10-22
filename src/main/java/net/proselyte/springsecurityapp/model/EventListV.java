package net.proselyte.springsecurityapp.model;

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

@Entity
@Table(name = "eventlistv")
public class EventListV {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idev;

	@Column(name = "idinfo")
	public int idinfo;

	@Column(name = "eqid")
	public int eqid;

	@Column(name = "evcode")
	public String evcode;

	@Column(name = "namecode")
	public String namecode;

	@Column(name = "evzndata")
	private String evzndata;

	@Column(name = "evznnum")
	private Long evznnum;

	@Column(name = "evdata")
	public Long evdata;

	@Column(name = "evgrnum")
	public int evgrnum;

	@Column(name = "perifname")
	public String perifname;

	@Column(name = "events")
	public String events;

	@Column(name = "accountid")
	public String accountid;

	@Column(name = "nameEvrang")
	public String nameEvrang;

	@Column(name = "evrang")
	public Integer evrang;

	@Column(name = "operatorid")
	public Integer operatorid;

	@Column(name = "evznName")
	public String evznName;

	@Column(name = "last_name")
	public String lastName;

	@Column(name = "first_name")
	public String firstName;

	@Column(name = "username")
	public String username;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idinfo", insertable = false, updatable = false)
	private Perifinfo perifinfo;

	@Column(name = "currentidinfo")
	private Long currentidinfo;

	public void setCurrentIdinfo(Long idinfo2) {
		currentidinfo = idinfo2;
	}

	public Long getCurrentIdinfo() {
		return currentidinfo;
	}

	public String getColor() {
		String result = "";
		if (getCurrentIdinfo() != null && getIdev() != null && getCurrentIdinfo().intValue() == getIdev().intValue()) {

			result = "<tr class='row_current' bgcolor='#000080'>";
		} else {
			switch (evcode) {

			case "3401":
			case "3400":
				result = "<tr class='rowlink'  bgcolor='#BCF5D2'>";
				break;
			case "1401":
			case "1400":
				result = "<tr class='rowlink'  bgcolor='#C6CFF5'>";
				break;

			default:
				result = "<tr class='rowlink'  bgcolor='#f4f4fb'>";
			}
			switch (evrang) {
			case 5:
			case 3:
				result = "<tr class='rowlink'  bgcolor='#f9f373'>";
				break;
			case 7:
				result = "<tr class='rowlink'  bgcolor='#fbc6a8'>";
				break;
			case 1:
				result = "<tr class='rowlink'  bgcolor='#EE9CA4'>";
				break;
			}
		}
		return result;
	}

	public String getTab() {
		String s = "";
		s = getColor();
		s += "<td style='display: none'><input type=\"hidden\" name=\"idinfo\" class=\"item\"\n" + "	 value=\'"
				+ getIdev() + "'>" + getIdev() + "</td>" + "<td>" + getAccountid() + "</td>" + "<td>" + getEvcode()
				+ "</td>" + "<td>" + getNamecode() + "</td>" + "<td>" + getNameEvrang() + "</td>" + "<td>"
				+ getEvdataDate() + "</td>" + "<td>" + getEvdataTime() + "</td>" + "<td>" + getEvgrnumS() + "</td>"
				+ "<td>" + getPerifname() + "</td>" + "<td>" + getEvznnumVal() + "</td>" + "<td>" + getEvzndataVal()
				+ "</td>" + "<td>" + getEvents() + "</td>" + "</tr>";

		return s;
	}

	public String getTabPanel() {
		String s = "";
		s = getColor();
		s += "<td>" + getEvcode() + "</td>" + "<td>" + getNamecode() + "</td>" + "<td>" + getEvdataDate() + "</td>"
				+ "<td>" + getEvdataTime() + "</td>" + "<td>" + getEvgrnumS() + "</td>" + "<td>" + getPerifname()
				+ "</td>" + "<td>" + getEvznnumVal() + "</td>" + "<td>" + getEvzndataVal() + "</td>" + "<td>"
				+ getEvents() + "</td>" + "</tr>";

		return s;
	}

	public Long getIdev() {
		return idev;
	}

	public int getEqid() {
		return eqid;
	}

	public String getEvcode() {
		return evcode;
	}

	public String getNamecode() {
		if (namecode == null)
			return "";
		return namecode;
	}

	public Long getEvdata() {
		return evdata;
	}

	public int getEvgrnum() {
		return evgrnum;
	}

	public String getEvgrnumS() {
		if (evgrnum == 0)
			return "";
		else
			return String.valueOf(evgrnum);
	}

	public String getPerifnameS() {
		if (evgrnum == 0)
			return "";
		else
			return perifname;
	}

	public String getPerifname() {
		return perifname;
	}

	public String getEvents() {
		String result = events;
		if (evcode.equals("1130"))
			return result + " " + evznName;

		if (operatorid != null) {
			result += ".Оператор ";
			if (lastName != null)
				result += firstName + " " + lastName;
			else
				result += username;
		}
		return result;
	}

	public int getIdinfo() {
		return idinfo;
	}

	public String toString() {
		return "" + getEvdataDate();
	}

	public String getEvdataDate() {
		SimpleDateFormat format1 = new SimpleDateFormat("dd:MM:yyyy");
		java.util.Date time = new java.util.Date((long) evdata * 1000);
		String date1 = format1.format(time);
		return date1;
	}

	public String getEvdataTime() {
		SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
		java.util.Date time = new java.util.Date((long) evdata * 1000);
		String date1 = format1.format(time);
		return date1;
	}

	public void setIdev(Long idev) {
		this.idev = idev;
	}

	public String getNameEvrang() {
		return nameEvrang;
	}

	public void setNameEvrang(String nameEvrang) {

		this.nameEvrang = nameEvrang;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public Integer getEvrang() {
		return evrang;
	}

	public void setEvrang(Integer evrang) {
		this.evrang = evrang;
	}

	public Perifinfo getPerifinfo() {
		return perifinfo;
	}

	public void setPerifinfo(Perifinfo perifinfo) {
		this.perifinfo = perifinfo;
	}

	public String getEvznName() {
		return evznName;
	}

	public void setEvznName(String evznName) {
		this.evznName = evznName;
	}

	public String getEvzndata() {
		return evzndata;
	}

	public String getEvzndataVal() {
		if (evzndata == null) {
			if (evznName == null)

				return "";
			else
				return getEvznName();
		} else
			return evzndata;
	}

	public void setEvzndata(String evzndata) {
		this.evzndata = evzndata;
	}

	public Long getEvznnum() {
		return evznnum;
	}

	public String getEvznnumVal() {
		if (evznnum == null || evznnum == 0)
			return "";
		return String.valueOf(evznnum);
	}

	public void setEvznnum(Long evznnum) {
		this.evznnum = evznnum;
	}

	public Integer getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(Integer operatorid) {
		this.operatorid = operatorid;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
