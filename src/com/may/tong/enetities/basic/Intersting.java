package com.may.tong.enetities.basic;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;



import javax.persistence.Table;

import com.may.tong.enetities.services.Buyer;

//关于兴趣的类
@Cacheable
@Table(name="interests")
@Entity
public class Intersting {
	
	private Integer id;
	private String name;
	
	
	private Set<Buyer> buyers;
	
	
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@ManyToMany(cascade={CascadeType.REFRESH})
    @JoinTable(name="S_buyer_intersting",
            inverseJoinColumns=@JoinColumn(name="buyer_id"),
            joinColumns=@JoinColumn(name="inte_id"))
	public Set<Buyer> getBuyers() {
		return buyers;
	}
	public void setBuyers(Set<Buyer> buyers) {
		this.buyers = buyers;
	}
	@Override
	public String toString() {
		return "Intersting [id=" + id + ", name=" + name + "]";
	}
	 
	
	

}
