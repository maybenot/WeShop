package com.may.tong.enetities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.may.tong.enetities.services.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

	/**
	 * �����û�����һ��������һ���������û����Ƿ���ڵ���֤
	 * */
	@Query("from Seller s where s.email=?1")
	Seller getSellerByName(String email);

}
