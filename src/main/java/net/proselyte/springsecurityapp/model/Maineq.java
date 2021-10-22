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

import net.proselyte.springsecurityapp.calculation.Status;
import net.proselyte.springsecurityapp.var.StaticVarStatus;

@Entity
@Table(name = "maineq")
public class Maineq {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ideqval;

	@Column(name = "accountid")
	public String accountid;

	@Column(name = "eqinfo")
	public String eqinfo;

	@Column(name = "wifiname")
	public String wifiname;

	@Column(name = "eqphone")
	public String eqphone;

	@Column(name = "eqphone2")
	public String eqphone2;

	@Column(name = "code_name_id")
	public Long code_name_id;

	@Column(name = "FIRSTCON")
	public Long firstcon;

	@Column(name = "LASTCON")
	private Long lastcon;

	@Column(name = "TESTPER")
	public Long testper;

	@Column(name = "instruction_response")
	public String instructionResponse;

	@Column(name = "statusy")
	public String statusy;

	@Column(name = "GEOPOINT")
	public String geopoint;

	@Column(name = "LOSERNORD")
	public String losernord;

	/*
	 * @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch =
	 * FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "obinfoid") private List<Perifinfo> perifinfo;
	 */
	public Long getIdeqval() {
		return ideqval;

	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "code_name_id", insertable = false, updatable = false)
	private CodeName codeName;

	public void setIdeqval(Long ideqval) {
		this.ideqval = ideqval;

	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	/*
	 * public List<Perifinfo> getPerifinfo() { return perifinfo; }
	 * 
	 * public void setPerifinfo(List<Perifinfo> perifinfo) { this.perifinfo =
	 * perifinfo; }
	 */
	public String getWifiname() {
		return wifiname;
	}

	public void setWifiname(String wifiname) {
		this.wifiname = wifiname;
	}

	public String getEqphone() {
		return eqphone;
	}

	public void setEqphone(String eqphone) {
		this.eqphone = eqphone;
	}

	public String getEqphone2() {
		if (eqphone2 == null)
			return "не указан";
		else
			return eqphone2;
	}

	public void setEqphone2(String eqphone2) {
		this.eqphone2 = eqphone2;
	}

	public Long getCode_name_id() {
		return code_name_id;
	}

	public void setCode_name_id(Long code_name_id) {
		this.code_name_id = code_name_id;
	}

	public String getFirstcon() {
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date time = new java.util.Date((long) firstcon * 1000);
		String date1 = format1.format(time);
		return date1;

	}

	public void setFirstcon(Long firstcon) {
		this.firstcon = firstcon;
	}

	public Long getTestper() {
		return testper;
	}

	public void setTestper(Long testper) {
		this.testper = testper;
	}

	public CodeName getCodeName() {
		return codeName;
	}

	public void setCodeName(CodeName codeName) {
		this.codeName = codeName;
	}

	public String getInstructionResponse() {
		return instructionResponse;
	}

	public void setInstructionResponse(String instructionResponse) {
		this.instructionResponse = instructionResponse;
	}

	public String getStatusy() {
		return statusy;
	}

	public void setStatusy(String statusy) {
		this.statusy = statusy;
	}

	public String getLastcon() {
		SimpleDateFormat format1 = new SimpleDateFormat("dd:MM:yyyy  HH:mm:ss");
		java.util.Date time = new java.util.Date((long) lastcon * 1000);
		String date1 = format1.format(time);
		return date1;
	}

	public String getFixedMaineq() {
		String sumEqphone = "";

		if (eqphone2 == null && eqphone != null) {
			sumEqphone = "(" + eqphone + ")";
		} else if (eqphone == null && eqphone2 != null) {
			sumEqphone = "(" + eqphone2 + ")";
		} else if (eqphone != null && eqphone != null) {
			sumEqphone = "(" + eqphone + ", " + eqphone2 + ")";
		}

		return accountid + sumEqphone;
	}

	public String getStatusPowerRu() {
		Status status = new Status(statusy, 1);
		return status.getsStatusPowerRu();
	}

	public String getStatusPowerRuImg() {
		if (getStatusPowerRu() == StaticVarStatus.statusNorma)
			return "&#9989 " + getStatusPowerRu();
		else
			return "<svg width=\"17px\" height=\"12px\"><circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"red\" /></svg> "
					+ getStatusPowerRu();
	}

	public String getStatusBattary() {
		Status status = new Status(statusy, 1);
		return status.getsStatusBatteryRu();
	}

	public String getStatusBattaryRuImg() {
		if (getStatusBattary() == StaticVarStatus.statusNorma)
			return "&#9989 " + getStatusBattary();
		else
			return "<svg width=\"17px\" height=\"12px\"><circle cx=\"6\" cy=\"6\" r=\"6\" fill=\"red  \" /></svg> "
					+ getStatusBattary();
	}

	public void setLastcon(Long lastcon) {
		this.lastcon = lastcon;
	}

	public String getEqinfo() {
		return eqinfo;
	}

	public void setEqinfo(String eqinfo) {
		this.eqinfo = eqinfo;
	}

	public String getGeopoint() {
		return geopoint;
	}

	public void setGeopoint(String geopoint) {
		this.geopoint = geopoint;
	}

	public String getLosernord() {
		return losernord;
	}

	public void setLosernord(String losernord) {
		this.losernord = losernord;
	}

}
