package com.may.tong.enetities.basic;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.may.tong.enetities.services.ShopProduct;

//用户自定义对商品的描述
@Entity
@Table(name="S_ProductUserDefine")
public class ProductUserDefine  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer productUserDefineId;
	private String  content;
	
	//一对一对应一个商品的描述
	private ShopProduct shopProduct;
	
	
	
	@JoinColumn(name="shopProductNo",unique=true)
	@OneToOne 
	public ShopProduct getShopProduct() {
		return shopProduct;
	}
	public void setShopProduct(ShopProduct shopProduct) {
		this.shopProduct = shopProduct;
	}
	
	@Id
	@GeneratedValue
	public Integer getProductUserDefineId() {
		return productUserDefineId;
	}
	public void setProductUserDefineId(Integer productUserDefineId) {
		this.productUserDefineId = productUserDefineId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
