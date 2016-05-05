package com.may.tong.enetities.services;

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

//商品的SKU属性值（shop keep unit ） 即，商品的销售属性

@Entity
@Table(name="B_SKU_AttributeValue")
public class SKU_AttributeValue {
	
	private Integer id;
	private String value;
	
	//对应多个明细
	private Set<SKU_AttributeValueDetail> details;
	
	//对应yi个sku属性
	private SKU_Attribute attribute;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
 
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH,mappedBy="SKUvalue")
	public Set<SKU_AttributeValueDetail> getDetails() {
		return details;
	}

	public void setDetails(Set<SKU_AttributeValueDetail> details) {
		this.details = details;
	}

	
	@JoinColumn(name="Attribute_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public SKU_Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(SKU_Attribute attribute) {
		this.attribute = attribute;
	}
	
	
	
	
	
	
	
	
	

}
