package com.may.tong.enetities.services;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.may.tong.enetities.basic.ExtendClass;

//商品的SKU属性值（shop keep unit ） 即，商品的销售属性
@Table(name="S_SKU_Attribute")
@Entity
public class SKU_Attribute {
	
	private Integer id;
	private String name;
	
	//对应多个属性值
	private Set<SKU_AttributeValue> values;
	
	//对应多个小类
	private Set<ExtendClass>  extendClasses;
	
	//对应一个卖家,系统给定的可以为空
	private Seller seller;
	
	
	@JoinColumn(name="sller_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

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

	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH,mappedBy="attribute")
	public Set<SKU_AttributeValue> getValues() {
		return values;
	}

	public void setValues(Set<SKU_AttributeValue> values) {
		this.values = values;
	}

	
	@ManyToMany(mappedBy = "sku_Attributes",cascade=CascadeType.REFRESH)
	public Set<ExtendClass> getExtendClasses() {
		return extendClasses;
	}

	public void setExtendClasses(Set<ExtendClass> extendClasses) {
		this.extendClasses = extendClasses;
	}
	
	//对应一个卖家，但是没有必要，只需要在卖家端添加就可以
	
	
	
	

}
