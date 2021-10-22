package net.proselyte.springsecurityapp.model;

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
@Table(name = "alarm")
public class Alarm implements Cloneable {

	public Alarm(Long idIdinfo, Integer statusAlarmId, String note, Integer isactive, Integer evcode,
			Long idEventList) {
		super();

		this.idIdinfo = idIdinfo;
		this.statusAlarmId = statusAlarmId;
		this.note = note;
		this.aldata = getAldataTime();
		this.isactive = isactive;
		this.evcode = evcode;
		this.idEventList = idEventList;

	}

	public Alarm() {
		super();
	}

	public Alarm AlarmCopy(Alarm alarm) {
		Alarm alarmNew = new Alarm();
		alarmNew.idIdinfo = alarm.idIdinfo;
		alarmNew.statusAlarmId = alarm.statusAlarmId;
		alarmNew.note = alarm.note;
		alarmNew.idOperator = alarm.idOperator;
		alarmNew.idGroup = alarm.idGroup;
		setCurrentAldata();
		alarmNew.isactive = alarm.isactive;
		alarmNew.evcode = alarm.evcode;
		alarmNew.idEventList = alarm.idEventList;
		return alarmNew;
	}

	public Object clone() throws CloneNotSupportedException {

		return super.clone();
	}

	public Alarm(Long idIdinfo, Integer statusAlarmId, String note, Long idOperator, Integer idGroup, Integer isactive,
			Integer evcode, Long idEventList) {
		super();

		this.idIdinfo = idIdinfo;
		this.statusAlarmId = statusAlarmId;
		this.note = note;
		this.idOperator = idOperator;
		this.idGroup = idGroup;
		setCurrentAldata();
		this.isactive = isactive;
		this.evcode = evcode;
		this.idEventList = idEventList;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long alarm_id;

	@Column(name = "id_idinfo")
	public Long idIdinfo;

	@Column(name = "status_alarm_id")
	public Integer statusAlarmId;

	@Column(name = "note")
	private String note;

	@Column(name = "id_Operator")
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

	@Column(name = "alarm_cancel_id")
	private Integer alarmCancelId;

	@Column(name = "isread")
	private Integer isread;

	@Column(name = "evzndata")
	private String evzndata;

	@Column(name = "evznnum")
	private Long evznnum;

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

	public Integer getAlarmCancelId() {
		return alarmCancelId;
	}

	public void setAlarmCancelId(Integer alarmCancelId) {
		this.alarmCancelId = alarmCancelId;
	}

	public Integer getIsread() {
		return isread;
	}

	public void setIsread(Integer isread) {
		this.isread = isread;
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

}
