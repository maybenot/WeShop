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
 * ���ﳵʵ����
 * */
@Cacheable
@Entity
@Table(name = "cart")
public class Cart {

	private Integer cartNo;

	private Buyer mapBuyer;
	
	//һ�����ﳵ��Ӧ������ﳵ��ϸ
	private Set<CartDetail>  cartDetails;
	

	@OneToMany(cascade=CascadeType.REFRESH,mappedBy="cart")
	public Set<CartDetail> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(Set<CartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}

	// ÿ�����ﳵ��Ӧһ�����
	// optional =false ָ��buyer����Ϊ��
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
