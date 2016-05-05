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
@Entity
@Table(name="S_Delivery")
//配送单
public class Delivery {
	
	private Integer deliveryNo;
	//快递单号
	private String trackingNo;
	//物流名称
	private String logisticsName;
	
	private Date createTime;
	
	//多个配送单对应一个卖家
	private Seller seller;
	
	//对应一个订单
	private OrderMaster orderMaster;
	
	
	
	@OneToOne(mappedBy = "delivery" ,cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	public OrderMaster getOrderMaster() {
		return orderMaster;
	}

	public void setOrderMaster(OrderMaster orderMaster) {
		this.orderMaster = orderMaster;
	}

	@Id
	@GeneratedValue
	public Integer getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(Integer deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JoinColumn(name="seller_No")
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.REFRESH)
	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	

}
