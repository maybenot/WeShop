package com.may.tong.enetities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.may.tong.enetities.services.SKU_AttributeValueDetail;

public interface SKU_DetailRepostitory extends
		JpaRepository<SKU_AttributeValueDetail, Integer> {

}
