package com.may.tong.enetities.basic;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.may.tong.enetities.services.Buyer;

//账户类
@Entity
@Cacheable
public class Account {
	
	
	private Integer id;
	private double balance;
	
	
	//与用户是一对一关系
	private Buyer buyer;


	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}

    @JoinColumn(name="buyer_id",unique=true)
	@OneToOne 
	public Buyer getBuyer() {
		return buyer;
	}


	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	
	
	

}
