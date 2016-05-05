package com.may.tong.enetities.services;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="S_ReturnTable")
//�˻���
public class ReturnTable {
	
	private Integer returnNo;
	private Double quality;
	private String reasion;
	
	
	//���һ
	private Seller seller;

	@Id
	@GeneratedValue
	public Integer getReturnNo() {
		return returnNo;
	}


	public void setReturnNo(Integer returnNo) {
		this.returnNo = returnNo;
	}


	public Double getQuality() {
		return quality;
	}


	public void setQuality(Double quality) {
		this.quality = quality;
	}


	public String getReasion() {
		return reasion;
	}


	public void setReasion(String reasion) {
		this.reasion = reasion;
	}

	@JoinColumn(name="Seller_No")
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public Seller getSeller() {
		return seller;
	}


	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	

}
