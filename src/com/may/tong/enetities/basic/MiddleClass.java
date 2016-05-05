package com.may.tong.enetities.basic;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//中类
@Entity
@Table(name="B_MiddleClass")
public class MiddleClass {
	
	private Integer id;
	
	private String name;
	
	//对应一个大类
	private BigClass bigClass;
	
	//对应多个小类
	
	private Set<ExtendClass> extendClasses;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@JoinColumn(name="BigClass_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public BigClass getBigClass() {
		return bigClass;
	}

	public void setBigClass(BigClass bigClass) {
		this.bigClass = bigClass;
	}

	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH,mappedBy="middleClass")
	public Set<ExtendClass> getExtendClasses() {
		return extendClasses;
	}

	public void setExtendClasses(Set<ExtendClass> extendClasses) {
		this.extendClasses = extendClasses;
	}
	
	
	
	
	

}
