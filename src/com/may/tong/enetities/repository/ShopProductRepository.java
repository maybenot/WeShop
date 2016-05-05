package com.may.tong.enetities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.may.tong.enetities.services.ShopProduct;

public interface ShopProductRepository extends
		JpaRepository<ShopProduct, Integer> {

}
