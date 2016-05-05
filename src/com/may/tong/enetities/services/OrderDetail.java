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
//������ϸ��
@Entity
@Table(name="S_OrderDetail")
public class OrderDetail {
    
	private Integer orderDetailNo;
	private Double  orderQuantity;
	private Date  createTime;
	
	//��Ӧһ��������Ʒ
	private ShopProduct shopProduct;
	
	//��Ӧһ����������
	private OrderMaster orderMaster;
	
	//һ��������ϸ��Ӧһ�����ﳵ��ϸ
	private CartDetail  cartDetail;
	
	@JoinColumn(name="Cart_No",unique=true,nullable=true)
	@OneToOne
	public CartDetail getCartDetail() {
		return cartDetail;
	}

	public void setCartDetail(CartDetail cartDetail) {
		this.cartDetail = cartDetail;
	}

	@JoinColumn(name="OrderMaster_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH) 
	public OrderMaster getOrderMaster() {
		return orderMaster;
	}

	public void setOrderMaster(OrderMaster orderMaster) {
		this.orderMaster = orderMaster;
	}

	@Id
	@GeneratedValue
	public Integer getOrderDetailNo() {
		return orderDetailNo;
	}

	public void setOrderDetailNo(Integer orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
	}

	public Double getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Double orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	@JoinColumn(name="ShopProduct_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public ShopProduct getShopProduct() {
		return shopProduct;
	}

	public void setShopProduct(ShopProduct shopProduct) {
		this.shopProduct = shopProduct;
	}
	
	
	
	
	
}
