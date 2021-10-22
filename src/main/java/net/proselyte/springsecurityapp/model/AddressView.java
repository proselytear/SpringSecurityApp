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
@Table(name = "addressview")
public class AddressView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long address_id;

    @Column(name = "city_name")
    public String cityName;

    @Column(name = "district_name")
    private String districtName;
    
    @Column(name = "fullname")
    private String fullname;
    
    @Column(name = "region_ru")
    private String regionRu;



	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}

	public String getRegionRu() {
		return regionRu;
	}

	public void setRegionRu(String regionRu) {
		this.regionRu = regionRu;
	}



}
