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
@Table(name = "objectview2")

public class ObjectView2 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idinfo;

	@Column(name = "ideqval")
	public Long ideqval;

	@Column(name = "accountid")
	public String accountID;

	@Column(name = "paramnum")
	private int paramNum;

	@Column(name = "count_ideqval")
	private int count_ideqval;

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

	@Column(name = "status_object")
	private Long status_object;

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

			return "<tr class='rowlink' style=\"background-color:navy;color:white;\">";
		}
		// if (getStatusyManual()!=null && getStatusyManual()==1) return
		// "&#128308"+"ТРЕВОГА";
		else if (status.getS_status_och_group().equals("БЕЗ ОХРАНЫ")) {
			return "<tr class='rowlink' bgcolor='#BCF5D2'>";
		} else if (status.getS_status_och_group().equals("ПОД ОХРАНОЙ")) {
			return "<tr class='rowlink' bgcolor='#C6CFF5'>";
		} else if (status.getS_status_och_group().equals("ЧАСТИЧНАЯ ОХРАНА")) {
			return "<tr class='rowlink' bgcolor='#FFA500'>";
		} else if (status.getS_status_och_group().equals("ТРЕВОГА")) {

			return "<tr class='rowlink' bgcolor='#EE9CA4'>";
		} else
			return "<tr class='rowlink' style=\\\"background-color:navy;color:white;\\\">";
	}

	public String getColorSet() {
		Status status = new Status(statusy, paramNum);

		if (getCurrentIdinfo() != null && idinfo != null && getCurrentIdinfo().intValue() == idinfo.intValue()) {

			return "<tr class='row_current' style=\"background-color:navy;color:white;\">";
		}

		else
			return "<tr class='rowlink' bgcolor='#E0E0E0'>";
	}

	public String getColorInner() {
		Status status = new Status(statusy, paramNum);

		if (getCurrentIdinfo() != null && idinfo != null && getCurrentIdinfo().intValue() == idinfo.intValue()) {

			return "<tr class='rowlink' style=\"background-color:navy;color:white;\">";
		}
		// if (getStatusyManual()!=null && getStatusyManual()==1) return
		// "&#128308"+"ТРЕВОГА";
		else if (status.getS_status_och_group().equals("БЕЗ ОХРАНЫ")) {
			return "<tr class='rowlink' bgcolor='#e7fdf0'>";
		} else if (status.getS_status_och_group().equals("ПОД ОХРАНОЙ")) {
			return "<tr class='rowlink' bgcolor='#dadff7'>";
		} else if (status.getS_status_och_group().equals("ЧАСТИЧНАЯ ОХРАНА")) {
			return "<tr class='rowlink' bgcolor='#f7dcab'>";
		} else if (status.getS_status_och_group().equals("ТРЕВОГА")) {

			return "<tr class='rowlink' bgcolor='#f7d2d6'>";
		} else
			return "<tr class='rowlink'>";
	}

	public String getColorInnerSet() {
		Status status = new Status(statusy, paramNum);

		if (getCurrentIdinfo() != null && idinfo != null && getCurrentIdinfo().intValue() == idinfo.intValue()) {

			return "<tr class='row_current' style=\"background-color:navy;color:white;\">";
		}

		return "<tr class='rowlink' bgcolor='#FFFFFF'>";
	}

	public String getColorObj() {
		Status status = new Status(statusy);

		if (getCurrentIdinfo() != null && idinfo != null && getCurrentIdinfo().intValue() == idinfo.intValue()) {

			return "<tr class='rowlink' style=\"background-color:navy;color:white;\">";
		}
		// if (getStatusyManual()!=null && getStatusyManual()==1) return
		// "&#128308"+"ТРЕВОГА";
		else if (status.getS_status_och_object().equals("БЕЗ ОХРАНЫ")) {
			return "<tr class='rowlink' bgcolor='#BCF5D2'>";
		} else if (status.getS_status_och_object().equals("ПОД ОХРАНОЙ")) {
			return "<tr class='rowlink' bgcolor='#C6CFF5'>";
		} else if (status.getS_status_och_object().equals("ЧАСТИЧНАЯ ОХРАНА")) {
			return "<tr class='rowlink' bgcolor='#FFA500'>";
		} else if (status.getS_status_och_object().equals("ТРЕВОГА")) {

			return "<tr class='rowlink' bgcolor='#EE9CA4'>";
		} else
			return "<tr class='rowlink'>";
	}

	public String getTab() {
		String s = "";
		s = getColor();
		String accounIDTab = accountID;
		String statusy = "";
		if (status_object == 0) {
			statusy = getStatusyObjImg();
			s = getColorObj();
		} else if (status_object == 2) {
			statusy = getStatusyImg();
			s = getColorInner();
		} else
			statusy = getStatusyImg();
		if (status_object == 0)
			accounIDTab += "(" + count_ideqval + ")";
		s += "<td><input type=\"hidden\" name=\"idinfo\" class=\"item\"\n" + "	 value=\'" + idinfo + "'>"
				+ getHasrepairsImg() + "</td>" + "<td style='display: none'>" + idinfo + "</td>" + "<td>" + accounIDTab
				+ "</td>" + "<td>" + getParamNumTab() + "</td>" + "<td>" + perifName + "</td>" + "<td>" + getFullname()
				+ "</td>" + "<td>" + getTimeOpenCloseTab() + "</td>" + "<td>" + statusy + "</td>"
				// + "<td>" + getTimeFromTab() + "</td>"
				// + "<td>" + getTimeToTab() + "</td>"
				+ "</tr>";
		return s;
	}

	public String getTabSet() {
		String s = "";
		s = getColorSet();
		String accounIDTab = accountID;
		String statusy = "";
		if (status_object == 0) {
			statusy = getStatusyObjImg();

		} else if (status_object == 2) {
			statusy = getStatusyImg();
			s = getColorInnerSet();
		} else
			statusy = getStatusyImg();
		if (status_object == 0)
			accounIDTab += "(" + count_ideqval + ")";
		s += "<td><input type=\"hidden\" name=\"idinfo\" class=\"item\"\n" + "	 value=\'" + idinfo + "'>"
				+ getHasrepairsImg() + "</td>" + "<td>" + idinfo + "</td>" + "<td>" + accounIDTab + "</td>" + "<td>"
				+ getParamNumTab() + "</td>" + "<td>" + perifName + "</td>" + "<td>" + getFullname() + "</td>" + "<td>"
				+ getTimeOpenCloseTab() + "</td>" + "<td>" + getTimeFromTab() + "</td>" + "<td>" + getTimeToTab()
				+ "</td>" + "</tr>";
		return s;
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
			return "<svg width=\"17px\" height=\"12px\">\n" + "  <circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"green\" />\n"
					+ "</svg>" + status.getS_status_och_group();
		} else if (status.getS_status_och_group().equals("ПОД ОХРАНОЙ"))
			return "<svg width=\"17px\" height=\"12px\">\n" + "  <circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"blue\" />\n"
					+ "</svg>" + status.getS_status_och_group();
		else if (status.getS_status_och_group().equals("ЧАСТИЧНАЯ ОХРАНА"))
			return "<svg width=\"17px\" height=\"12px\">\n"
					+ "  <circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"yellow \" />\n" + "</svg>"
					+ status.getS_status_och_group();
		else if (status.getS_status_och_group().equals("ТРЕВОГА"))
			return "<svg width=\"17px\" height=\"12px\">\n" + "  <circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"red \" />\n"
					+ "</svg>" + status.getS_status_och_group();

		return null;

	}

	public String getStatusyObjImg() {
		Status status = new Status(statusy);
		// if (getStatusyManual()!=null && getStatusyManual()==1) return
		// "&#128308"+"ТРЕВОГА";
		if (status.getS_status_och_object().equals("БЕЗ ОХРАНЫ")) {
			return "<svg width=\"17px\" height=\"12px\">\n" + "  <circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"green\" />\n"
					+ "</svg>" + status.getS_status_och_object();
		} else if (status.getS_status_och_object().equals("ПОД ОХРАНОЙ"))
			return "<svg width=\"17px\" height=\"12px\">\n" + "  <circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"blue\" />\n"
					+ "</svg>" + status.getS_status_och_object();
		else if (status.getS_status_och_object().equals("ЧАСТИЧНАЯ ОХРАНА"))
			return "<svg width=\"17px\" height=\"12px\">\n" + "  <circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"yellow\" />\n"
					+ "</svg>" + status.getS_status_och_object();
		else if (status.getS_status_och_object().equals("ТРЕВОГА"))
			return "<svg width=\"17px\" height=\"12px\">\n" + "  <circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"red\" />\n"
					+ "</svg>" + status.getS_status_och_object();

		return null;

	}

	public void setStatusy(String statusy) {
		this.statusy = statusy;
	}

	public Time getTimeFrom() {
		return timeFrom;
	}

	public String getTimeFromTab() {
		if (timeFrom == null)
			return "";
		return timeFrom.toString();
	}

	public void setTimeFrom(Time timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Time getTimeTo() {
		return timeTo;
	}

	public String getTimeToTab() {
		if (timeTo == null)
			return "";
		return timeTo.toString();
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

	public String getTimeOpenCloseTab() {
		if (timeOpenClose == null)
			return "";
		return timeOpenClose.toString();
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
		if (geopoint != null) {
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

	public Long getCurrentidinfo() {
		return currentidinfo;
	}

	public void setCurrentidinfo(Long currentidinfo) {
		this.currentidinfo = currentidinfo;
	}

	public Long getStatus_object() {
		return status_object;
	}

	public void setStatus_object(Long status_object) {
		this.status_object = status_object;
	}

	public int getCount_ideqval() {
		return count_ideqval;
	}

	public void setCount_ideqval(int count_ideqval) {
		this.count_ideqval = count_ideqval;
	}

}
