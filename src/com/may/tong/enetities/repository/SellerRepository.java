package com.may.tong.enetities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.may.tong.enetities.services.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

	/**
	 * 返回用户名的一个个数，一般用于做用户名是否存在的验证
	 * */
	@Query("from Seller s where s.email=?1")
	Seller getSellerByName(String email);

}
