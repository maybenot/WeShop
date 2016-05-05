package com.may.tong.enetities.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.may.tong.enetities.services.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Serializable> {

	/**
	 * �����û�����һ��������һ���������û����Ƿ���ڵ���֤
	 * */
	@Query("from Buyer b where b.userName=?1")
	Buyer getUserByUserName( String userName);
}
