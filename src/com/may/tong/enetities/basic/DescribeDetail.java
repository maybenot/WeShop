package com.may.tong.enetities.basic;

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

import com.may.tong.enetities.services.ShopProduct;

//������ϸ
@Entity
@Table(name="B_DescribeDetail")
public class DescribeDetail {
	
	
	private Integer describeDetailNo;
	
	
	//��Ӧ���������Ʒ
	private Set<ShopProduct> shopProducts;
	 
	
	//���������ϸ��Ӧһ������ֵ��
	private AttributeValue attributeValue;
	
	
	//�����Ӧһ��С��
	private ExtendClass extendClass;
	
	@JoinColumn(name="ExtendClass_No")
	@ManyToOne(cascade=CascadeType.REFRESH)
	public ExtendClass getExtendClass() {
		return extendClass;
	}


	public void setExtendClass(ExtendClass extendClass) {
		this.extendClass = extendClass;
	}


	@JoinColumn(name="AttributeValue_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public AttributeValue getAttributeValue() {
		return attributeValue;
	}


	public void setAttributeValue(AttributeValue attributeValue) {
		this.attributeValue = attributeValue;
	}


	@Id
	@GeneratedValue
	public Integer getDescribeDetailNo() {
		return describeDetailNo;
	}


	public void setDescribeDetailNo(Integer describeDetailNo) {
		this.describeDetailNo = describeDetailNo;
	}

	
	
	@ManyToMany(mappedBy = "describeDetails",cascade=CascadeType.REFRESH)
	public Set<ShopProduct> getShopProducts() {
		return shopProducts;
	}


	public void setShopProducts(Set<ShopProduct> shopProducts) {
		this.shopProducts = shopProducts;
	}

}
