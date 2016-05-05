package com.may.tong.enetities.basic;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Entity
@Table(name="B_StarLevel")
public class StarLevel {
	
	private Integer levelNo;
	private String levelName;
	
	@Id
	@GeneratedValue
	public Integer getLevelNo() {

		return levelNo;
	}
	public void setLevelNo(Integer levelNo) {
		this.levelNo = levelNo;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	

}
