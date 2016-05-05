package com.may.tong.enetities.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.may.tong.enetities.services.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Serializable> {

	/**
	 * 返回用户名的一个个数，一般用于做用户名是否存在的验证
	 * */
	@Query("from Buyer b where b.userName=?1")
	Buyer getUserByUserName( String userName);
}
