package net.proselyte.springsecurityapp.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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

/**
 * Simple JavaBean domain object that represents a User.
 *
 * @author Ekaterina Nosenko
 * @version 1.0
 */

@Entity
@Table(name = "perifinfo")
public class Perifinfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idinfo;

	@Column(name = "infotype")
	private Integer infotype;

	@Column(name = "paramnum")
	private int paramnum;

	@Column(name = "perifname")
	private String perifname;

	@Column(name = "perifdata")
	private String perifdata;

	@Column(name = "obinfoid")
	private long obinfoid;

	@Column(name = "grouprel")
	private Long grouprel;

	@Column(name = "isstand")
	private Integer isStand;

	@Column(name = "standLevel")
	private Integer standLevel;

	public Long getIdinfo() {
		return idinfo;
	}

	@Column(name = "timefrommonday")
	private Time timefrommonday;

	@Column(name = "timetomonday")
	private Time timetomonday;

	@Column(name = "timefromtuesday")
	private Time timefromtuesday;

	@Column(name = "timetotuesday")
	private Time timetotuesday;

	@Column(name = "timefromwednesday")
	private Time timefromwednesday;

	@Column(name = "timetowednesday")
	private Time timetowednesday;

	@Column(name = "timefromthursday")
	private Time timefromthursday;

	@Column(name = "timetothursday")
	private Time timetothursday;

	@Column(name = "timefromfriday")
	private Time timefromfriday;

	@Column(name = "timetofriday")
	private Time timetofriday;

	@Column(name = "timefromsaturday")
	private Time timefromsaturday;

	@Column(name = "timetosaturday")
	private Time timetosaturday;

	@Column(name = "timefromsunday")
	private Time timefromsunday;

	@Column(name = "timetosunday")
	private Time timetosunday;

	@Column(name = "sensor_type")
	private int sensorType;

	@Column(name = "id_technician")
	private Long idTechnician;

	@Column(name = "stand_operatorid")
	private Long standOperatorid;

	@Column(name = "time_Ban")
	private Date timeBan;

	private static Long currentidinfo = null;

	public void setCurrentIdinfo(Long idinfo2) {
		currentidinfo = idinfo2;
	}

	public Long getCurrentIdinfo() {
		return currentidinfo;
	}

	public String getColor() {

		if (getCurrentIdinfo() != null && getCurrentIdinfo().intValue() == getIdinfoStand()) {
			return "<tr class='rowlink' bgcolor='#FFFF00'>";
		} else
			return "<tr class='rowlink'  bgcolor='#DCD4FB'>";
	}

	public String getTab() {
		String s = "";
		s = getColor();
		String fullName = "";
		if (getEmployees() != null)
			fullName = getEmployees().getFullName();
		String fullNameUser = "";
		if (getUser().getEmployees() != null)
			fullNameUser = getUser().getEmployees().getFullName();
		String paramnum = "";
		if (getParamnumSensor() != null)
			paramnum = String.valueOf(getParamnumSensor());
		s += "<td style='display: none'><input type=\"hidden\" name=\"idinfo\" class=\"item\"\n" + "	 value=\'"
				+ getIdinfoStand() + "'>" + getIdinfoStand() + "</td>" + "<td>" + getMaineq().getAccountid() + "</td>"
				+ "<td>" + getGrouprelStand() + "</td>" + "<td>" + paramnum + "</td>" + "<td>" + getTimeBan() + "</td>"
				+ "<td>" + fullName + "</td>" + "<td>" + fullNameUser + "</td>" + "</tr>";
		return s;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "obinfoid", insertable = false, updatable = false)
	private Maineq maineq;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_technician", insertable = false, updatable = false)
	private Employees employees;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "stand_operatorid", insertable = false, updatable = false)
	private User user;

	public String getStatusy() {

		Status status = new Status(maineq.getStatusy(), paramnum);

		return status.getS_status_och_group();

	}

	public String getZonaAlarmAll() {

		Status status = new Status(maineq.getStatusy(), paramnum);

		return status.zonaAlarmAll;

	}

	public String getStatusyImg() {
		Status status = new Status(maineq.getStatusy(), paramnum);

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

	public ArrayList<Defect> getDefect() {
		Status status = new Status(maineq.getStatusy(), 1);

		LinkedHashMap<Integer, Integer> result = status.getZ_status_datch();

		ArrayList<Defect> alDefect = new ArrayList<Defect>();
		if (status.getZ_state_220().equals("")) {

		} else {
			alDefect.add(new Defect(getIdinfo(), maineq.getAccountid(), null, null, getPerifname(),
					status.getZ_state_220()));
		}
		if (status.getZ_state_battery().equals("")) {

		} else {
			alDefect.add(new Defect(getIdinfo(), maineq.getAccountid(), null, null, getPerifname(),
					status.getZ_state_battery()));

		}
		if (result != null) {
			Set<Map.Entry<Integer, Integer>> set = result.entrySet();
			for (Map.Entry<Integer, Integer> me : set) {
				if (me.getValue() == 1) {
					System.out.print(" datchic  " + me.getKey());
					System.out.print(" group " + me.getValue());
					System.out.print(" status=" + maineq.getAccountid());
					System.out.print(" getGrouprelSensor()=" + getGrouprelSensor());
					System.out.print(" getParamnumSensor()=" + getParamnumSensor());
					System.out.print("Потеря связи с дачиком");
					System.out.println(" getPerifname()" + getPerifname());
					alDefect.add(new Defect(getIdinfo(), maineq.getAccountid(), me.getValue(), me.getKey(),
							getPerifname(), " Потеря связи с дачиком"));
				}

			}
			if (result != null) {
				result = status.getZ_status_datch_battery();
				set = result.entrySet();
				for (Map.Entry<Integer, Integer> me : set) {
					if (me.getValue() == 1) {
						System.out.print(" datchic  " + me.getKey());
						System.out.print(" group " + me.getValue());
						System.out.print(" status=" + maineq.getAccountid());
						System.out.print(" Проблема с батареей датчика");
						System.out.println(" getPerifname()" + getPerifname());
						alDefect.add(new Defect(getIdinfo(), maineq.getAccountid(), me.getValue(), me.getKey(),
								getPerifname(), "Проблема с батареей датчика"));
					}

				}
				result = status.getZ_status_datch_tamper();
				set = result.entrySet();
				for (Map.Entry<Integer, Integer> me : set) {
					if (me.getValue() == 1) {
						System.out.print(" datchic  " + me.getKey());
						System.out.print(" group " + me.getValue());
						System.out.print(" status=" + maineq.getAccountid());
						System.out.print(" Проблема с тампером датчика");
						System.out.println(" getPerifname()" + getPerifname());
						alDefect.add(new Defect(getIdinfo(), maineq.getAccountid(), me.getValue(), me.getKey(),
								getPerifname(), "Проблема с тампером датчика"));
					}

				}
			}
		}
		return alDefect;

	}

	public void setIdinfo(Long idinfo) {
		this.idinfo = idinfo;
	}

	public int getInfotype() {
		return infotype;
	}

	public long getIdinfoStand() {
		if (getGrouprel() == null)
			return -idinfo;
		return idinfo;
	}

	public void setInfotype(int infotype) {
		this.infotype = infotype;
	}

	public String getPerifname() {
		return perifname;
	}

	public String getPerifnameVal() {
		if ((infotype == 1 || infotype == 0) && perifdata != null)
			return perifdata;
		else
			return perifname;
	}

	public void setPerifname(String perifname) {
		this.perifname = perifname;
	}

	public String getPerifnameKey() {
		return perifname;
	}

	public Maineq getMaineq() {
		return maineq;
	}

	public void setMaineq(Maineq maineq) {
		this.maineq = maineq;
	}

	public int getParamnum() {
		return paramnum;
	}

	public Integer getParamnumSensor() {
		if (infotype == 0)
			return null;
		else
			return paramnum;
	}

	public void setParamnum(int paramnum) {
		this.paramnum = paramnum;
	}

	public Time getTimefrommonday() {
		return timefrommonday;
	}

	public void setTimefrommonday(Time mondWithPC) {
		this.timefrommonday = mondWithPC;
	}

	public Time getTimetomonday() {
		return timetomonday;
	}

	public void setTimetomonday(Time timetomonday) {
		this.timetomonday = timetomonday;
	}

	public Time getTimefromtuesday() {
		return timefromtuesday;
	}

	public void setTimefromtuesday(Time timefromtuesday) {
		this.timefromtuesday = timefromtuesday;
	}

	public Time getTimetotuesday() {
		return timetotuesday;
	}

	public void setTimetotuesday(Time timetotuesday) {
		this.timetotuesday = timetotuesday;
	}

	public Time getTimefromwednesday() {
		return timefromwednesday;
	}

	public void setTimefromwednesday(Time timefromwednesday) {
		this.timefromwednesday = timefromwednesday;
	}

	public Time getTimetowednesday() {
		return timetowednesday;
	}

	public void setTimetowednesday(Time timetowednesday) {
		this.timetowednesday = timetowednesday;
	}

	public Time getTimefromthursday() {
		return timefromthursday;
	}

	public void setTimefromthursday(Time timefromthursday) {
		this.timefromthursday = timefromthursday;
	}

	public Time getTimetothursday() {
		return timetothursday;
	}

	public void setTimetothursday(Time timetothursday) {
		this.timetothursday = timetothursday;
	}

	public Time getTimefromfriday() {
		return timefromfriday;
	}

	public void setTimefromfriday(Time timefromfriday) {
		this.timefromfriday = timefromfriday;
	}

	public Time getTimetofriday() {
		return timetofriday;
	}

	public void setTimetofriday(Time timetofriday) {
		this.timetofriday = timetofriday;
	}

	public Time getTimefromsaturday() {
		return timefromsaturday;
	}

	public void setTimefromsaturday(Time timefromsaturday) {
		this.timefromsaturday = timefromsaturday;
	}

	public Time getTimetosaturday() {
		return timetosaturday;
	}

	public void setTimetosaturday(Time timetosaturday) {
		this.timetosaturday = timetosaturday;
	}

	public Time getTimefromsunday() {
		return timefromsunday;
	}

	public void setTimefromsunday(Time timefromsunday) {
		this.timefromsunday = timefromsunday;
	}

	public Time getTimetosunday() {
		return timetosunday;
	}

	public void setTimetosunday(Time timetosunday) {
		this.timetosunday = timetosunday;
	}

	public int getSensorType() {
		return sensorType;
	}

	public void setSensorType(int sensorType) {
		this.sensorType = sensorType;
	}

	public long getObinfoid() {
		return obinfoid;
	}

	public void setObinfoid(long obinfoid) {
		this.obinfoid = obinfoid;
	}

	public Long getGrouprel() {
		if (standLevel != null && standLevel == 3)
			return null;
		else
			return grouprel;

	}

	public String getGrouprelStand() {
		if (standLevel != null && standLevel == 3)
			return "";
		else
			return String.valueOf(grouprel);

	}

	public Long getGrouprelSensor() {
		if (infotype == 0) {
			return grouprel;
		} else
			return null;
	}

	public void setGrouprel(long grouprel) {

		this.grouprel = grouprel;

	}

	public String getPatrol() {
		if (sensorType == 2)
			return "&#10004";
		return null;
	}

	public String getAlarmButton() {
		if (sensorType == 1)
			return "&#10004";
		return null;
	}

	public void setInfotype(Integer infotype) {
		this.infotype = infotype;
	}

	public Long getIdTechnician() {
		return idTechnician;
	}

	public void setIdTechnician(Long idTechnician) {
		this.idTechnician = idTechnician;
	}

	public Date getTimeBan() {
		return timeBan;
	}

	public void setTimeBan(Date timeBan) {
		this.timeBan = timeBan;
	}

	public Employees getEmployees() {
		return employees;
	}

	public void setEmployees(Employees employees) {
		this.employees = employees;
	}

	public String toString() {
		return "perifinfo" + grouprel;
	}

	public Integer getIsStand() {
		return isStand;
	}

	public void setIsStand(Integer isStand) {
		this.isStand = isStand;
	}

	public Integer getStandLevel() {
		return standLevel;
	}

	public void setStandLevel(Integer standLevel) {
		this.standLevel = standLevel;
	}

	public Long getStandOperatorid() {
		return standOperatorid;
	}

	public void setStandOperatorid(Long standOperatorid) {
		this.standOperatorid = standOperatorid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPerifdata() {
		return perifdata;
	}

	public void setPerifdata(String perifdata) {
		this.perifdata = perifdata;
	}

}
