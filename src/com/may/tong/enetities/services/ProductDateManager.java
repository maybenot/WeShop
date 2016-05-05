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

@Entity
@Table(name = "S_ProductDateManager")
// 商品管理日期
public class ProductDateManager {
	private Integer id;
	private String dateIdentify;

	// 对应一个seller
	private Seller seller;

	// 对应多个商品管理明细
	private Set<ProductManagerDetail> productManagerDetails;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, mappedBy = "productDateManager")
	public Set<ProductManagerDetail> getProductManagerDetails() {
		return productManagerDetails;
	}

	public void setProductManagerDetails(
			Set<ProductManagerDetail> productManagerDetails) {
		this.productManagerDetails = productManagerDetails;
	}

	@JoinColumn(name = "Seller_No")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDateIdentify() {
		return dateIdentify;
	}

	public void setDateIdentify(String dateIdentify) {
		this.dateIdentify = dateIdentify;
	}

}
