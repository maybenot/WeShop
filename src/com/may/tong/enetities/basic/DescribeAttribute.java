package com.may.tong.enetities.basic;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//��������
@Entity
@Table(name="B_DescribeAttribute")
public class DescribeAttribute {
	
	private Integer describeAttributeNo;
	
	private String describeAttributeName;
	
	//��Ӧ�������ֵ��
	private Set<AttributeValue>  attributeValues;

	
	@Id
	@GeneratedValue
	public Integer getDescribeAttributeNo() {
		return describeAttributeNo;
	}

	public void setDescribeAttributeNo(Integer describeAttributeNo) {
		this.describeAttributeNo = describeAttributeNo;
	}

	public String getDescribeAttributeName() {
		return describeAttributeName;
	}

	public void setDescribeAttributeName(String describeAttributeName) {
		this.describeAttributeName = describeAttributeName;
	}

	
	@OneToMany(cascade=CascadeType.REFRESH,mappedBy="describeAttribute")
	public Set<AttributeValue> getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(Set<AttributeValue> attributeValues) {
		this.attributeValues = attributeValues;
	}
	
	
	

}
