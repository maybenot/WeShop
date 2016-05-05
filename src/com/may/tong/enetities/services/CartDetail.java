package com.may.tong.enetities.services;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.may.tong.enetities.basic.Cart;

//购物车明细类
@Entity
@Table(name = "S_CartDetail")
public class CartDetail {

	private Integer cartDetailNo;
	private Double cartQuantity;
	private Date createTime;

	// 一个购物车明细对应一个店铺商品
	private ShopProduct shopProduct;

	// 多个购物车明细对应一个购物车
	private Cart cart;

	// 一个购物车明细对应一个订单明细
	private OrderDetail orderDetail;

	@Id
	@GeneratedValue
	public Integer getCartDetailNo() {
		return cartDetailNo;
	}

	public void setCartDetailNo(Integer cartDetailNo) {
		this.cartDetailNo = cartDetailNo;
	}

	public Double getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(Double cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JoinColumn(name = "ShopProduct_No")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	public ShopProduct getShopProduct() {
		return shopProduct;
	}

	public void setShopProduct(ShopProduct shopProduct) {
		this.shopProduct = shopProduct;
	}

	@JoinColumn(name = "Cart_No")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@OneToOne(mappedBy = "cartDetail", cascade = CascadeType.REFRESH)
	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

}
