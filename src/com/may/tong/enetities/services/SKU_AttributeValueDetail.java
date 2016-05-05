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
import javax.persistence.Table;

//SKU������ϸ
@Entity
@Table(name="B_SKU_AttributeValueDetail")
public class SKU_AttributeValueDetail {
	
	private Integer id;
	
	//��Ӧһ��ͬ��
	private SimilarClass similarClass;
	
	//��Ӧһ��SKU����ֵ
	private SKU_AttributeValue SKUvalue;
	
	
	//��Ӧ���������Ʒ
	
	private Set<ShopProduct>  shopProducts;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@JoinColumn(name="SimilarClass_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public SimilarClass getSimilarClass() {
		return similarClass;
	}


	public void setSimilarClass(SimilarClass similarClass) {
		this.similarClass = similarClass;
	}


	@JoinColumn(name="SKUvalue_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public SKU_AttributeValue getSKUvalue() {
		return SKUvalue;
	}

	
	public void setSKUvalue(SKU_AttributeValue sKUvalue) {
		SKUvalue = sKUvalue;
	}

	@ManyToMany(mappedBy = "sKU_Details",cascade=CascadeType.REFRESH)
	public Set<ShopProduct> getShopProducts() {
		return shopProducts;
	}


	public void setShopProducts(Set<ShopProduct> shopProducts) {
		this.shopProducts = shopProducts;
	}
	
	
	
	

}
