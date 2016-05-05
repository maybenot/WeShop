package com.may.tong.enetities.basic;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.may.tong.enetities.services.SKU_Attribute;

//商品小类
@Entity
@Table(name="B_ExtendClass")
public class ExtendClass {
	
	private Integer  extendClassNo;
	private String extendClassName;
	
	
	
	private  Set<DescribeDetail>  describeDetails;
	
	//对应一个中类
	private MiddleClass middleClass;
	


	
	//对应多个sku
	private Set<SKU_Attribute> sku_Attributes;
	
	
	@ManyToMany(fetch=FetchType.EAGER,cascade={CascadeType.REFRESH})
    @JoinTable(name="S_e_SKUattr",
            inverseJoinColumns=@JoinColumn(name="e_id"),
            joinColumns=@JoinColumn(name="attr_id"))
    public Set<SKU_Attribute> getSku_Attributes() {
		return sku_Attributes;
	}

	public void setSku_Attributes(Set<SKU_Attribute> sku_Attributes) {
		this.sku_Attributes = sku_Attributes;
	}

	
	@JoinColumn(name="MiddleClass_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public MiddleClass getMiddleClass() {
		return middleClass;
	}

	public void setMiddleClass(MiddleClass middleClass) {
		this.middleClass = middleClass;
	}
 
	@Id
	@GeneratedValue
	public Integer getExtendClassNo() {
		return extendClassNo;
	}

	

	public void setExtendClassNo(Integer extendClassNo) {
		this.extendClassNo = extendClassNo;
	}

	public String getExtendClassName() {
		return extendClassName;
	}

	public void setExtendClassName(String extendClassName) {
		this.extendClassName = extendClassName;
	}

	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH,mappedBy="extendClass")
	public Set<DescribeDetail> getDescribeDetails() {
		return describeDetails;
	}

	public void setDescribeDetails(Set<DescribeDetail> describeDetails) {
		this.describeDetails = describeDetails;
	}
	
	
	

}
