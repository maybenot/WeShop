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


//����ֵ��
@Entity
@Table(name="B_AttributeValue")
public class AttributeValue {
	
	private Integer attributeValueNo;
	private String attributeValueName;
	
	
	//��Ӧ���������ϸ
	private Set<DescribeDetail> describeDetails;
	
	//�������ֵ���϶�Ӧһ����������
	private DescribeAttribute describeAttribute;
	


	
	 @JoinColumn(name="DescribeAttribute_No")
	@ManyToOne(cascade=CascadeType.REFRESH)
	public DescribeAttribute getDescribeAttribute() {
		return describeAttribute;
	}


	public void setDescribeAttribute(DescribeAttribute describeAttribute) {
		this.describeAttribute = describeAttribute;
	}


	@Id
	@GeneratedValue
	public Integer getAttributeValueNo() {
		return attributeValueNo;
	}


	public void setAttributeValueNo(Integer attributeValueNo) {
		this.attributeValueNo = attributeValueNo;
	}


	
	
	public String getAttributeValueName() {
		return attributeValueName;
	}


	public void setAttributeValueName(String attributeValueName) {
		this.attributeValueName = attributeValueName;
	}


	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH,mappedBy="attributeValue")
	public Set<DescribeDetail> getDescribeDetails() {
		return describeDetails;
	}


	public void setDescribeDetails(Set<DescribeDetail> describeDetails) {
		this.describeDetails = describeDetails;
	}
	
	

}
