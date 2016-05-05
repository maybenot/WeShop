package com.may.tong.enetities.basic;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="B_BigClass")
//大类
public class BigClass implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer bigClassNo;
	private String bigClassName;


	//对应多个中类
	private Set<MiddleClass> middleClasses;
	


	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH,mappedBy="bigClass")
	public Set<MiddleClass> getMiddleClasses() {
		return middleClasses;
	}


	public void setMiddleClasses(Set<MiddleClass> middleClasses) {
		this.middleClasses = middleClasses;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Id
	@GeneratedValue
	public Integer getBigClassNo() {
		return bigClassNo;
	}


	public void setBigClassNo(Integer bigClassNo) {
		this.bigClassNo = bigClassNo;
	}


	public String getBigClassName() {
		return bigClassName;
	}


	public void setBigClassName(String bigClassName) {
		this.bigClassName = bigClassName;
	}

	
}
