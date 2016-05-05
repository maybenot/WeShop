package com.may.tong.enetities.basic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


//ÉÌÆ·×´Ì¬Àà
@Entity
@Table(name="S_State")
public class State {
	
	private Integer stateNo;
	private String stateName;
	
	
	@Id
	@GeneratedValue
	public Integer getStateNo() {
		return stateNo;
	}

	public void setStateNo(Integer stateNo) {
		this.stateNo = stateNo;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
