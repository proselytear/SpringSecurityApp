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
@Table(name = "eventlist")
public class EventList {

	public EventList(long eqid, String original, String evcode, String selenaorig, String evgrname, String evznname,
			Integer evgrnum, Integer isviewed, Integer ispush, Integer iscommand) {
		super();

		this.eqid = eqid;
		this.original = original;
		this.evcode = evcode;
		this.selenaorig = selenaorig;
		this.evgrname = evgrname;
		this.evznname = evznname;
		this.evznnum = 0;
		this.evgrnum = evgrnum;
		this.isviewed = isviewed;
		this.ispush = ispush;
		this.iscommand = iscommand;
		this.evdata = getEvdataTime();
	}

	public EventList(long eqid, String equipinfo, String evinfo, String original, String selenaorig, Integer isviewed,
			Integer ispush, Integer iscommand, String evaccount, String evcode, Long operatorid, Integer evgrnum,
			String evzndata, Long grouprel) {

		this.eqid = eqid;
		this.equipinfo = equipinfo;
		this.evinfo = evinfo;
		this.original = original;
		this.selenaorig = selenaorig;
		this.isviewed = isviewed;
		this.ispush = ispush;
		this.iscommand = iscommand;
		this.evaccount = evaccount;
		this.evcode = evcode;
		this.operatorid = operatorid;
		this.evdata = getEvdataTime();
		this.evgrnum = grouprel.intValue();
		this.evznnum = evgrnum;

		this.evzndata = evzndata;
		// this.evznnum = evznnum;
		System.out.println("---------------iscomand" + iscommand);
	}

	public EventList() {
		// TODO Auto-generated constructor stub
	}

	public EventList addStand(String evaccount, Long operatorid, Integer grouprel, String evgrname, Integer paramnum,
			String evznname, long eqid, String equipinfo) {

		this.eqid = eqid;// ?

		this.equipinfo = equipinfo;// ?
		this.evinfo = "Stand on";

		this.evaccount = evaccount;
		this.evcode = "11";
		this.operatorid = operatorid;
		this.evdata = getEvdataTime();
		this.evgrnum = grouprel;
		this.evgrname = evgrname;
		this.evznnum = paramnum;
		this.evznname = evznname;
		this.selenaorig = "";
		this.original = "";
		this.isviewed = 0;
		this.ispush = 0;
		this.iscommand = 0;
		return this;
	}

	public EventList removeStand(String evaccount, Long operatorid, Integer grouprel, String evgrname, Integer paramnum,
			String evznname, long eqid, String equipinfo) {

		this.eqid = eqid;// ?

		this.equipinfo = equipinfo;// ?
		this.evinfo = "Stand off";

		this.evaccount = evaccount;
		this.evcode = "12";
		this.operatorid = operatorid;
		this.evdata = getEvdataTime();
		this.evgrnum = grouprel;
		this.evgrname = evgrname;
		this.evznnum = paramnum;
		this.evznname = evznname;
		this.selenaorig = "";
		this.original = "";
		this.isviewed = 0;
		this.ispush = 0;
		this.iscommand = 0;
		return this;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idev;

	@Column(name = "eqid")
	public Long eqid;

	@Column(name = "equipinfo")
	public String equipinfo;

	@Column(name = "evinfo")
	public String evinfo;

	@Column(name = "original")
	public String original;

	@Column(name = "evcode")
	public String evcode;

	@Column(name = "selenaorig")
	public String selenaorig;

	@Column(name = "isviewed")
	public Integer isviewed;

	@Column(name = "ispush")
	public Integer ispush;

	@Column(name = "iscommand")
	public Integer iscommand;

	@Column(name = "evaccount")
	public String evaccount;

	@Column(name = "evgrname")
	public String evgrname;

	@Column(name = "evgrnum")
	public Integer evgrnum;

	@Column(name = "evznnum")
	public Integer evznnum;

	@Column(name = "evzndata")
	public String evzndata;

	@Column(name = "evznname")
	public String evznname;

	@Column(name = "evdata")
	public Long evdata;

	@Column(name = "operatorid")
	public Long operatorid;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "eqid", insertable = false, updatable = false)
	private Maineq maineq;

	public Long getEvdataTime() {

		return System.currentTimeMillis() / 1000L;
	}

	public Long getIdev() {
		return idev;
	}

	public void setIdev(Long idev) {
		this.idev = idev;
	}

	public Long getEqid() {
		return eqid;
	}

	public void setEqid(Long eqid) {
		this.eqid = eqid;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getEvcode() {
		return evcode;
	}

	public void setEvcode(String evcode) {
		this.evcode = evcode;
	}

	public String getSelenaorig() {
		return selenaorig;
	}

	public void setSelenaorig(String selenaorig) {
		this.selenaorig = selenaorig;
	}

	public String getEvgrname() {
		return evgrname;
	}

	public void setEvgrname(String evgrname) {
		this.evgrname = evgrname;
	}

	public String getEvznname() {
		return evznname;
	}

	public void setEvznname(String evznname) {
		this.evznname = evznname;
	}

	public Long getEvdata() {
		return evdata;
	}

	public void setEvdata(Long evdata) {
		this.evdata = evdata;
	}

	public Integer getEvgrnum() {
		return evgrnum;
	}

	public void setEvgrnum(Integer evgrnum) {
		this.evgrnum = evgrnum;
	}

	public String getEquipinfo() {
		return equipinfo;
	}

	public void setEquipinfo(String equipinfo) {
		this.equipinfo = equipinfo;
	}

	public String getEvinfo() {
		return evinfo;
	}

	public void setEvinfo(String evinfo) {
		this.evinfo = evinfo;
	}

	public Integer getIsviewed() {
		return isviewed;
	}

	public void setIsviewed(Integer isviewed) {
		this.isviewed = isviewed;
	}

	public Integer getIspush() {
		return ispush;
	}

	public void setIspush(Integer ispush) {
		this.ispush = ispush;
	}

	public Integer getIscommand() {
		return iscommand;
	}

	public void setIscommand(Integer iscommand) {
		this.iscommand = iscommand;
	}

	public String getEvaccount() {
		return evaccount;
	}

	public void setEvaccount(String evaccount) {
		this.evaccount = evaccount;
	}

	public Long getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(Long operatorid) {
		this.operatorid = operatorid;
	}

	public Integer getEvznnum() {
		return evznnum;
	}

	public void setEvznnum(Integer evznnum) {
		this.evznnum = evznnum;
	}

	public Maineq getMaineq() {
		return maineq;
	}

	public void setMaineq(Maineq maineq) {
		this.maineq = maineq;
	}

	public String getEvzndata() {
		return evzndata;
	}

	public void setEvzndata(String evzndata) {
		this.evzndata = evzndata;
	}

}
