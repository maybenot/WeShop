package com.may.tong.enetities.services;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.may.tong.enetities.basic.Account;
import com.may.tong.enetities.basic.Cart;
import com.may.tong.enetities.basic.Intersting;
import com.may.tong.enetities.basic.StarLevel;

@Cacheable
@Entity
@Table(name = "S_Buyer")
public class Buyer {

	private Integer buyerId;
	private String userName;
	private String nickName;
	private String password;

	private String phone;

	private Integer gender;

	// 对应多个收货地址
	private Set<Address> address;
	// 对应一个星级
	private StarLevel level;

	// 一对一对应一个购物车
	private Cart cart;

	// 一对一对应一个账户
	private Account account;

	// 对应多个兴趣
	private Set<Intersting> inters;
	
	//一个买家对应多个订单
	private Set<OrderMaster>  orderMasters;
	

	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH,mappedBy="buyer")
	public Set<OrderMaster> getOrderMasters() {
		return orderMasters;
	}

	public void setOrderMasters(Set<OrderMaster> orderMasters) {
		this.orderMasters = orderMasters;
	}

	@ManyToMany(  mappedBy = "buyers",cascade=CascadeType.REFRESH)
	public Set<Intersting> getInters() {
		return inters;
	}

	public void setInters(Set<Intersting> inters) {
		this.inters = inters;
	}

	@OneToOne(mappedBy = "buyer" ,cascade=CascadeType.REFRESH)
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@OneToOne(mappedBy = "mapBuyer", targetEntity = Cart.class,cascade=CascadeType.REFRESH)
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Id
	@GeneratedValue
	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	@JoinColumn(name = "level_no")
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.REFRESH)
	public StarLevel getLevel() {
		return level;
	}

	public void setLevel(StarLevel level) {
		this.level = level;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "buyer",cascade=CascadeType.REFRESH)
	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	//添加一个测试的tostring

	@Override
	public String toString() {
		return "Buyer [buyerId=" + buyerId + ", userName=" + userName
				+ ", nickName=" + nickName + ", password=" + password
				+ ", phone=" + phone + ", gender=" + gender + ", address="
				+ address + ", level=" + level + ", cart=" + cart
				+ ", account=" + account + ", inters=" + inters + "]";
	}
	

}
