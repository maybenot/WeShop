package com.may.tong.enetities.services;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.may.tong.enetities.basic.State;

//订单主表
@Entity
@Table(name="S_OrderMaster")
public class OrderMaster {
	
	private Integer orderNo;
	private Double orderSum;
	private Date  commitDate;
	private Date receiveDate;
	
	//对应一个state
	private State orderState;
	
	//多个对应一个收货地址
	private Address address;

	//一个对应一个配送单
	private Delivery delivery;
	
	
	//多个订单对应一个买家
	private Buyer buyer;
	
	//对应多个订单明细
	private Set<OrderDetail>  orderDetails;
	

	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH,mappedBy="orderMaster")
	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}


	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}


	@Id
	@GeneratedValue
	public Integer getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}


	public Double getOrderSum() {
		return orderSum;
	}


	public void setOrderSum(Double orderSum) {
		this.orderSum = orderSum;
	}


	public Date getCommitDate() {
		return commitDate;
	}


	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}


	public Date getReceiveDate() {
		return receiveDate;
	}


	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}


	@JoinColumn(name="OrderState_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public State getOrderState() {
		return orderState;
	}


	public void setOrderState(State orderState) {
		this.orderState = orderState;
	}

    
	@JoinColumn(name="Address_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}

     @OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	public Delivery getDelivery() {
		return delivery;
	}


	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}


	
	@JoinColumn(name="Buyer_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public Buyer getBuyer() {
		return buyer;
	}


	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	
	
	
	

}
