package net.proselyte.springsecurityapp.model;

public class Defect {
	public Defect(Long idinfo, String maineq, Integer grouprel, Integer datchic, String perifnameGroup, String state) {
		super();
		this.idinfo = idinfo;
		this.maineq = maineq;
		this.grouprel = grouprel;
		this.datchic = datchic;
		this.perifnameGroup = perifnameGroup;
		this.state = state;

	}

	public Defect() {

	}

	private Long idinfo;
	private String maineq;
	private Integer grouprel;
	private Integer datchic;
	private String perifnameGroup;
	private String state;

	public String getMaineq() {
		return maineq;
	}

	public void setMaineq(String maineq) {
		this.maineq = maineq;
	}

	public Integer getGrouprel() {
		return grouprel;
	}

	public void setGrouprel(Integer grouprel) {
		this.grouprel = grouprel;
	}

	public Integer getDatchic() {
		return datchic;
	}

	public void setDatchic(Integer datchic) {
		this.datchic = datchic;
	}

	public String getPerifnameGroup() {
		return perifnameGroup;
	}

	public void setPerifnameGroup(String perifnameGroup) {
		this.perifnameGroup = perifnameGroup;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getIdinfo() {
		return idinfo;
	}

	public void setIdinfo(Long idinfo) {
		this.idinfo = idinfo;
	}

	private Long currentidinfo;

	public void setCurrentIdinfo(Long idinfo2) {
		currentidinfo = idinfo2;
	}

	public Long getCurrentIdinfo() {
		return currentidinfo;
	}

	public String getColor() {

		if (getCurrentIdinfo() != null && idinfo != null && getCurrentIdinfo().intValue() == idinfo.intValue()) {

			return "<tr class='rowlink' bgcolor='#FFFF00'>";
		} else
			return "<tr class='rowlink'  bgcolor='#FAFACD'>";

	}

	public String getTab() {
		String s = "";
		s = getColor();
		s += "<td><input type=\"hidden\" name=\"idinfo\" class=\"item\"\n" + "	 value=\'" + getIdinfo() + "'>"
				+ "</td>" + "<td>" + getMaineq() + "</td>" + "<td>" + getGrouprel() + "</td>" + "<td>" + getDatchic()
				+ "</td>" + "<td>" + getPerifnameGroup() + "</td>" + "<td>" + getState() + "</td>" + "</tr>";

		return s;
	}

}
