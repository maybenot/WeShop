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

//�����ڵ�ͬ����Ʒ
@Entity
@Table(name="B_SimilarClass")
public class SimilarClass {
	//ͬ����Ʒ��id
	private Integer similarClassId;
	private String  similarClassName;
	
	//ͬ����Ʒ��ͼ۸�
	private Double lowestPrice;
	
	//ͬ����Ʒ��Ӧһ���ܵ�����ͼƬ
	private String imgPath;
	
	//��Ӧ���SKU��ϸ
	private Set<SKU_AttributeValueDetail>  SKU_details;
	
	//��Ӧһ������
	private Seller seller;
	
	
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@JoinColumn(name="Seller_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	@Id
	@GeneratedValue
	public Integer getSimilarClassId() {
		return similarClassId;
	}

	public void setSimilarClassId(Integer similarClassId) {
		this.similarClassId = similarClassId;
	}

	public String getSimilarClassName() {
		return similarClassName;
	}

	public void setSimilarClassName(String similarClassName) {
		this.similarClassName = similarClassName;
	}

	public Double getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(Double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH,mappedBy="similarClass")
	public Set<SKU_AttributeValueDetail> getSKU_details() {
		return SKU_details;
	}

	public void setSKU_details(Set<SKU_AttributeValueDetail> sKU_details) {
		SKU_details = sKU_details;
	}
	
	

}
