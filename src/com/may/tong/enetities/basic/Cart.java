package com.may.tong.enetities.basic;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.may.tong.enetities.services.Buyer;
import com.may.tong.enetities.services.CartDetail;

/**
 * 购物车实体类
 * */
@Cacheable
@Entity
@Table(name = "cart")
public class Cart {

	private Integer cartNo;

	private Buyer mapBuyer;
	
	//一个购物车对应多个购物车明细
	private Set<CartDetail>  cartDetails;
	

	@OneToMany(cascade=CascadeType.REFRESH,mappedBy="cart")
	public Set<CartDetail> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(Set<CartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}

	// 每个购物车对应一个买家
	// optional =false 指明buyer不能为空
	@JoinColumn(name = "buyer_id", unique = true)
	@OneToOne(cascade = CascadeType.ALL)
	public Buyer getMapBuyer() {
		return mapBuyer;
	}

	public void setMapBuyer(Buyer mapBuyer) {
		this.mapBuyer = mapBuyer;
	}

	@Id
	@GeneratedValue
	public Integer getCartNo() {
		return cartNo;
	}

	public void setCartNo(Integer cartNo) {
		this.cartNo = cartNo;
	}

	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + "]";
	}
   
}
