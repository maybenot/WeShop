package com.may.tong.enetities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.may.tong.enetities.basic.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
