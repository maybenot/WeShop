package com.may.tong.enetities.services;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//商品管理明细
@Entity
@Table(name="S_ProductManagerDetail")
public class ProductManagerDetail {

	 private Integer productManagerDetailNo;
	 private  Double memberPrice;
	 private Double barginPrice;
	 
	 
	 //对应一个店铺商品
	 private ShopProduct shopProduct;
	 
	 //多个对应一个商品管理日期
	 private ProductDateManager productDateManager;

	 
	@Id
	@GeneratedValue
	public Integer getProductManagerDetailNo() {
		return productManagerDetailNo;
	}

	public void setProductManagerDetailNo(Integer productManagerDetailNo) {
		this.productManagerDetailNo = productManagerDetailNo;
	}

	public Double getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(Double memberPrice) {
		this.memberPrice = memberPrice;
	}

	public Double getBarginPrice() {
		return barginPrice;
	}

	public void setBarginPrice(Double barginPrice) {
		this.barginPrice = barginPrice;
	}

	
	@JoinColumn(name="ShopProduct_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public ShopProduct getShopProduct() {
		return shopProduct;
	}

	public void setShopProduct(ShopProduct shopProduct) {
		this.shopProduct = shopProduct;
	}

	
	@JoinColumn(name="ProductDateManager_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public ProductDateManager getProductDateManager() {
		return productDateManager;
	}

	public void setProductDateManager(ProductDateManager productDateManager) {
		this.productDateManager = productDateManager;
	}
	 
	 
	 
	 
	 
}
