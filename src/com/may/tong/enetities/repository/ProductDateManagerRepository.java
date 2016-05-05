package com.may.tong.enetities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.may.tong.enetities.services.ProductDateManager;

public interface ProductDateManagerRepository 
       extends JpaRepository<ProductDateManager, Integer> {

}
