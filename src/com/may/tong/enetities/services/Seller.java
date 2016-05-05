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
import javax.persistence.Table;

import com.may.tong.enetities.basic.StarLevel;

@Entity
@Table(name = "S_Seller")
// 关于卖家的一个实体类
public class Seller {

	private Integer sellerNo;
	private String shopName;
	private String email;
	private String shopOwer;
	private Date createTime;
	private String password;
	private String tel;

	private StarLevel starLevel;

	// 一个卖家对应多个配送单
	private Set<Delivery> deliveries;

	// 一个卖家对应多个评价回复
	private Set<CommentReply> commentreplies;

	// 一个卖家对应多个退货单
	private Set<ReturnTable> returnTables;

	// 一个卖家对应多个商品管理日期
	private Set<ProductDateManager> productDateManagers;

	// 对应多个skuAttr
	private Set<SKU_Attribute> attributes;
	
	//对应多个同类商品
	private Set<SimilarClass> similarClasses;
	
	

	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH,mappedBy="seller")
	public Set<SimilarClass> getSimilarClasses() {
		return similarClasses;
	}

	public void setSimilarClasses(Set<SimilarClass> similarClasses) {
		this.similarClasses = similarClasses;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH,mappedBy="seller")
	public Set<SKU_Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<SKU_Attribute> attributes) {
		this.attributes = attributes;
	}

	@Id
	@GeneratedValue
	public Integer getSellerNo() {
		return sellerNo;
	}

	public void setSellerNo(Integer sellerNo) {
		this.sellerNo = sellerNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopOwer() {
		return shopOwer;
	}

	public void setShopOwer(String shopOwer) {
		this.shopOwer = shopOwer;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	// 多个个卖家对应一个星级
	@JoinColumn(name = "level_no")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	public StarLevel getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(StarLevel starLevel) {
		this.starLevel = starLevel;
	}

	// 一个卖家对应多个配送单
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "seller", cascade = CascadeType.REFRESH)
	public Set<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(Set<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	// 一个卖家对应多个评价回复
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "seller", cascade = CascadeType.REFRESH)
	public Set<CommentReply> getCommentreplies() {
		return commentreplies;
	}

	public void setCommentreplies(Set<CommentReply> commentreplies) {
		this.commentreplies = commentreplies;
	}

	// 一个卖家对应多个退货单
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, mappedBy = "seller")
	public Set<ReturnTable> getReturnTables() {
		return returnTables;
	}

	public void setReturnTables(Set<ReturnTable> returnTables) {
		this.returnTables = returnTables;
	}

	// 一个卖家对应多个商品管理日期
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, mappedBy = "seller")
	public Set<ProductDateManager> getProductDateManagers() {
		return productDateManagers;
	}

	public void setProductDateManagers(
			Set<ProductDateManager> productDateManagers) {
		this.productDateManagers = productDateManagers;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Seller(String shopName, String email, String shopOwer,
			String password, String tel) {
		super();
		this.shopName = shopName;
		this.email = email;
		this.shopOwer = shopOwer;
		this.password = password;
		this.tel = tel;
	}

	public Seller() {
		super();
	}

}
