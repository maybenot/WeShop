package com.may.tong.enetities.basic;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Cacheable
@Entity
@Table(name="B_Country")
public class Country {

	private Integer countryNo;
	private String  countryName;
	
	
	//对应一个市区
	private City city;


	@Id
	@GeneratedValue
	public Integer getCountryNo() {
		return countryNo;
	}


	public void setCountryNo(Integer countryNo) {
	
		this.countryNo = countryNo;
	}


	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@JoinColumn(name="City_NO")
   @ManyToOne 
	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}
	
	
}
