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
@Table(name = "alarm_cancel_name")
public class AlarmCancelName {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_alarm_cancel;

	@Column(name = "name_alarm_cancel")
	public String nameAlarmCancel;

	public Long getId_alarm_cancel() {
		return id_alarm_cancel;
	}

	public void setId_alarm_cancel(Long id_alarm_cancel) {
		this.id_alarm_cancel = id_alarm_cancel;
	}

	public String getNameAlarmCancel() {
		return nameAlarmCancel;
	}

	public void setNameAlarmCancel(String nameAlarmCancel) {
		this.nameAlarmCancel = nameAlarmCancel;
	}

}
