package com.may.tong.enetities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.may.tong.enetities.basic.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
