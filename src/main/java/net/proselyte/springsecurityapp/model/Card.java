package net.proselyte.springsecurityapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Simple JavaBean domain object that represents a User.
 *
 * @author Ekateryna Nosenko
 * @version 1.0
 */

@Entity
@Table(name = "card")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long card_id;

	@Column(name = "OBJECT_ID")
	public Long object_id;

	@Column(name = "name")
	public String name;

	@Column(name = "GROUPNUM")
	public Integer groupnum;

	@Column(name = "ADDRESSFULL")
	public String addressFull;

	@Column(name = "phone")
	public String phone;

	@Column(name = "director")
	private String director;

	@Column(name = "responsible")
	private String responsible;

	@Column(name = "additionally")
	private String additionally;

	@Column(name = "equipment_rent")
	private String equipmentRent;

	@Column(name = "ACCOUNTID_CARD")
	private String accountid;

	@Column(name = "MANAGER_ID")
	private Long manager_id;

	/*
	 * @Column(name = "master_id") private int masterId;
	 * 
	 * @Column(name = "technician_id") private int technicianId;
	 * 
	 * @Column(name = "mounter_id") private int mounterId;
	 */

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "object_id", insertable = false, updatable = false)
	private Maineq maineq;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "technician_id", insertable = false, updatable = false)
	private Employees employeest;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mounter_id", insertable = false, updatable = false)
	private Employees employeesm;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "master_id", insertable = false, updatable = false)
	private Employees employees;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manager_id", insertable = false, updatable = false)
	private Employees employeesmanager;

	public Long getCard_id() {
		return card_id;
	}

	public void setCard_id(Long card_id) {
		this.card_id = card_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public Maineq getMaineq() {
		return maineq;
	}

	public void setMaineq(Maineq maineq) {
		this.maineq = maineq;
	}

	public String toString() {
		return "card_id=" + card_id + " phone=" + phone + " employeesm" + employeesm + " manager_id " + manager_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employees getEmployees() {
		return employees;
	}

	public void setEmployees(Employees employees) {
		this.employees = employees;
	}

	public Employees getEmployeesm() {
		return employeesm;
	}

	public void setEmployeesm(Employees employeesm) {
		this.employeesm = employeesm;
	}

	public Employees getEmployeest() {
		return employeest;
	}

	public void setEmployeest(Employees employeest) {
		this.employeest = employeest;
	}

	public String getAdditionally() {
		return additionally;
	}

	public void setAdditionally(String additionally) {
		this.additionally = additionally;
	}

	public String getEquipmentRent() {
		return equipmentRent;
	}

	public void setEquipmentRent(String equipmentRent) {
		this.equipmentRent = equipmentRent;
	}

	public Long getObject_id() {
		return object_id;
	}

	public void setObject_id(Long object_id) {
		this.object_id = object_id;
	}

	public List<String> getItems() {
		List<String> list = new ArrayList<String>();
		list.add("Thing1");
		list.add("Thing2");
		list.add("Thing3");
		return list;
	}

	public Long getManager_id() {
		return manager_id;
	}

	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}

	public Integer getGroupnum() {
		return groupnum;
	}

	public void setGroupnum(Integer groupnum) {
		this.groupnum = groupnum;
	}

	public String getAddressFull() {
		return addressFull;
	}

	public void setAddressFull(String addressFull) {
		this.addressFull = addressFull;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public Employees getEmployeesmanager() {
		return employeesmanager;
	}

	public void setEmployeesmanager(Employees employeesmanager) {
		this.employeesmanager = employeesmanager;
	}
}
