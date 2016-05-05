package com.may.tong.enetities.basic;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Cacheable
@Entity
@Table(name="B_Province")
public class Province {
	
	private Integer provinceNo;
	private String  proviceName;
	
	//一个省有很多市区
	private Set<City> cities;

	@Id
	@GeneratedValue
	public Integer getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(Integer provinceNo) {
		this.provinceNo = provinceNo;
	}

	 
	public String getProviceName() {
		return proviceName;
	}

	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}
	
    @OneToMany(mappedBy="province",
    		cascade=CascadeType.REMOVE) 
	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}
	
	
	

}
