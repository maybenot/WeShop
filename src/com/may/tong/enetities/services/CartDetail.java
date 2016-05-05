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

//���ﳵ��ϸ��
@Entity
@Table(name = "S_CartDetail")
public class CartDetail {

	private Integer cartDetailNo;
	private Double cartQuantity;
	private Date createTime;

	// һ�����ﳵ��ϸ��Ӧһ��������Ʒ
	private ShopProduct shopProduct;

	// ������ﳵ��ϸ��Ӧһ�����ﳵ
	private Cart cart;

	// һ�����ﳵ��ϸ��Ӧһ��������ϸ
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
