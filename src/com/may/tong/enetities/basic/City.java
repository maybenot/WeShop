package com.may.tong.enetities.basic;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Cacheable
@Entity
@Table(name = "B_City")
public class City {

	private Integer cityNo;
	private String cityName;

	// 对应一个省
	private Province province;

	// 一个市有很多的县区
	private Set<Country> countries;

	@Id
	@GeneratedValue
	public Integer getCityNo() {
		return cityNo;
	}

	public void setCityNo(Integer cityNo) {
		this.cityNo = cityNo;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@JoinColumn(name = "Province_NO")
	@ManyToOne
	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "city"
			,cascade=CascadeType.REMOVE)
	public Set<Country> getCountries() {
		return countries;
	}

	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}

}
