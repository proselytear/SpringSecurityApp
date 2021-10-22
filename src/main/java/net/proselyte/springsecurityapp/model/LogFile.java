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
@Table(name = "logfile")
public class LogFile {
	public LogFile() {
		super();
	}

	public LogFile(String message) {
		this.message = message;
		this.software = "pultsoftware";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long IDLog;

	@Column(name = "message")
	public String message;

	@Column(name = "software")
	private String software;

	public Long getIDLog() {
		return IDLog;
	}

	public void setIDLog(Long iDLog) {
		IDLog = iDLog;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSoftware() {
		return software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

}
