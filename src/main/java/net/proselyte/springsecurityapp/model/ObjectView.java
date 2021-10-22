package net.proselyte.springsecurityapp.model;

import java.sql.Time;

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
@Table(name = "objectview")

public class ObjectView {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@Column(name = "hastechnic")
	private Integer hastechnic;

	@Column(name = "hasrepairs")
	private Integer hasrepairs;

	@Column(name = "technicfio")
	private String technicfio;

	@Column(name = "sconnection")
	private String sconnection;

	@Column(name = "geopoint")
	private String geopoint;

	@Column(name = "currentidinfo")
	private Long currentidinfo;

	public void setCurrentIdinfo(Long idinfo2) {
		currentidinfo = idinfo2;
	}

	public Long getCurrentIdinfo() {
		return currentidinfo;
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

	public String getColor() {
		Status status = new Status(statusy, paramNum);

		if (getCurrentIdinfo() != null && idinfo != null && getCurrentIdinfo().intValue() == idinfo.intValue()) {

			return "<tr class='rowlink' bgcolor='#FFFF00'>";
		}
		// if (getStatusyManual()!=null && getStatusyManual()==1) return
		// "&#128308"+"ТРЕВОГА";
		else if (status.getS_status_och_group().equals("БЕЗ ОХРАНЫ")) {
			return "<tr class='rowlink' bgcolor='#BCF5D2'>";
		} else if (status.getS_status_och_group().equals("ПОД ОХРАНОЙ")) {
			return "<tr class='rowlink' bgcolor='#C6CFF5'>";
		} else if (status.getS_status_och_group().equals("ЧАСТИЧНАЯ ОХРАНА")) {
			return "<tr class='rowlink' bgcolor='#008000'>";
		} else if (status.getS_status_och_group().equals("ТРЕВОГА")) {

			return "<tr class='rowlink' bgcolor='#EE9CA4'>";
		} else
			return "<tr class='rowlink'>";
	}

	public String getTab() {
		String s = "";
		s = getColor();
		s += "<td ><input type=\"hidden\" name=\"idinfo\" class=\"item\"\n" + "	 value=\'" + idinfo + "'>"
				+ getHasrepairsImg() + "</td>" + "<td style='display: none'>" + idinfo + "</td>" + "<td>" + accountID
				+ "</td>" + "<td>" + paramNum + "</td>" + "<td>" + perifName + "</td>" + "<td>" + getFullname()
				+ "</td>" + "<td>" + timeOpenClose + "</td>" + "<td>" + getStatusyImg() + "</td>" +
				// "<td>" + timeFrom + "</td>" + "<td>"+ timeTo + "</td>" +
				"</tr>";
		return s;
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
		if (fullname == null)
			fullname = "";
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ideqval", insertable = false, updatable = false)
	private Maineq Maineq;

	public String getStatusy() {
		Status status = new Status(statusy, paramNum);

		String result = status.getS_status_och_group();
		// if (getStatusyManual()!=null && getStatusyManual()==1) result="ТРЕВОГА";
		return result;

	}

	public String getZonaAlarmAll() {
		Status status = new Status(statusy, paramNum);

		String result = status.zonaAlarmAll;
		// if (getStatusyManual()!=null && getStatusyManual()==1) result="ТРЕВОГА";
		return result;

	}

	public String getStatusyOchrana() {
		Status status = new Status(statusy, paramNum);

		String result = status.getS_status_och_group();
		if (result == StaticVarStatus.statusGroupOchrana) {

			return result;
		} else
			return null;

	}

	public String getStatusyImg() {
		Status status = new Status(statusy, paramNum);
		// if (getStatusyManual()!=null && getStatusyManual()==1) return
		// "&#128308"+"ТРЕВОГА";
		if (status.getS_status_och_group().equals("БЕЗ ОХРАНЫ")) {
			return "<svg width=\"17px\" height=\"12px\"><circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"green\" /></svg>"
					+ status.getS_status_och_group();
		} else if (status.getS_status_och_group().equals("ПОД ОХРАНОЙ"))
			return "<svg width=\"17px\" height=\"12px\"><circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"blue \" /></svg>"
					+ status.getS_status_och_group();
		else if (status.getS_status_och_group().equals("ЧАСТИЧНАЯ ОХРАНА"))
			return "<svg width=\"17px\" height=\"12px\"><circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"yellow  \" /></svg>"
					+ status.getS_status_och_group();
		else if (status.getS_status_och_group().equals("ТРЕВОГА"))
			return "<svg width=\"17px\" height=\"12px\"><circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"red\" /></svg>"
					+ status.getS_status_och_group();

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

	public Long getIdeqval() {
		return ideqval;
	}

	public void setIdeqval(Long ideqval) {
		this.ideqval = ideqval;
	}

	public String getHastechnicImg() {
		if (hastechnic != null)
			return Icon.hammer;
		else
			return "";
	}

	public String getHasrepairsImg() {
		if (hasrepairs != null)
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

	public String getSconnection() {
		return sconnection;
	}

	public void setSconnection(String sconnection) {
		this.sconnection = sconnection;
	}

	public String getGeopoint() {
		return geopoint;
	}

	public void setGeopoint(String geopoint) {
		this.geopoint = geopoint;
	}

	public String getXpoint() {
		String res = "";
		if (geopoint != null && !geopoint.equals("")) {
			int beginIndex = geopoint.indexOf("X");
			int endIndex = geopoint.indexOf("Y");
			res = geopoint.substring(beginIndex + 1, endIndex);
		}
		return res;
	}

	public String getYpoint() {
		String res = "";
		if (geopoint != null) {
			int beginIndex = geopoint.indexOf("Y");
			res = geopoint.substring(beginIndex + 1);

		}
		return res;
	}

	public String getXYpoint() {
		return getXpoint() + ',' + getYpoint();
	}

	public Integer getHasrepairs() {
		return hasrepairs;
	}

	public void setHasrepairs(Integer hasrepairs) {
		this.hasrepairs = hasrepairs;
	}

}
