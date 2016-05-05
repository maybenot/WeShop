package com.may.tong.enetities.services;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.may.tong.enetities.basic.DescribeDetail;
import com.may.tong.enetities.basic.ProductUserDefine;
import com.may.tong.enetities.basic.State;


//������Ʒ��
@Entity
@Table(name="S_ShopProduct")
public class ShopProduct {
	
	private Integer shopProductNo;
	private Integer clickCount;
	private String imagPath;
	
	private String shopProductName;
	private Double stock;
	
	
	//���������Ʒ��Ӧ���������ϸ
	private Set<DescribeDetail> describeDetails;
	
	//���������Ʒ��Ӧһ��״̬
	private State state;
	
	//��Ӧ�����Ʒ������ϸ
	private  Set<ProductManagerDetail>  productManagerDetails;
	
	//һ��������Ʒ��Ӧһ���û��Զ�����ϸ
	private ProductUserDefine productUserDefine;
	
	
	
	@OneToOne(mappedBy = "shopProduct" ,cascade=CascadeType.REFRESH)
	public ProductUserDefine getProductUserDefine() {
		return productUserDefine;
	}


	public void setProductUserDefine(ProductUserDefine productUserDefine) {
		this.productUserDefine = productUserDefine;
	}


	//��Ӧ�����ϸ
	private Set<OrderDetail>  orderDetails;
	
	//һ��������Ʒ��Ӧ������ﳵ��ϸ
	private Set<CartDetail>  cartDetails;
	
	//��Ӧ���SKU��ϸ
	private Set<SKU_AttributeValueDetail> sKU_Details;
	

	@ManyToMany(fetch=FetchType.EAGER,cascade={CascadeType.REFRESH})
    @JoinTable(name="S_sp_SKU",
            inverseJoinColumns=@JoinColumn(name="SKU_id"),
            joinColumns=@JoinColumn(name="sp_id"))
	public Set<SKU_AttributeValueDetail> getsKU_Details() {
		return sKU_Details;
	}


	public void setsKU_Details(Set<SKU_AttributeValueDetail> sKU_Details) {
		this.sKU_Details = sKU_Details;
	}


	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH,mappedBy="shopProduct")
	public Set<CartDetail> getCartDetails() {
		return cartDetails;
	}


	public void setCartDetails(Set<CartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}


	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH,mappedBy="shopProduct")
    public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}


	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}


	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH,mappedBy="shopProduct")
	public Set<ProductManagerDetail> getProductManagerDetails() {
		return productManagerDetails;
	}


	public void setProductManagerDetails(
			Set<ProductManagerDetail> productManagerDetails) {
		this.productManagerDetails = productManagerDetails;
	}


	@JoinColumn(name="s_NO")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}


	@Id
	@GeneratedValue
	public Integer getShopProductNo() {
		return shopProductNo;
	}


	public void setShopProductNo(Integer shopProductNo) {
		this.shopProductNo = shopProductNo;
	}


	public Integer getClickCount() {
		return clickCount;
	}


	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}


	public String getImagPath() {
		return imagPath;
	}


	public void setImagPath(String imagPath) {
		this.imagPath = imagPath;
	}


	public String getShopProductName() {
		return shopProductName;
	}


	public void setShopProductName(String shopProductName) {
		this.shopProductName = shopProductName;
	}


	public Double getStock() {
		return stock;
	}


	public void setStock(Double stock) {
		this.stock = stock;
	}


	
	@ManyToMany(fetch=FetchType.EAGER,cascade={CascadeType.REFRESH})
    @JoinTable(name="S_sp_dd",
            inverseJoinColumns=@JoinColumn(name="sp_id"),
            joinColumns=@JoinColumn(name="dd_id"))
 
	public Set<DescribeDetail> getDescribeDetails() {
		return describeDetails;
	}


	public void setDescribeDetails(Set<DescribeDetail> describeDetails) {
		this.describeDetails = describeDetails;
	}


	


	
	 
	
	

}
