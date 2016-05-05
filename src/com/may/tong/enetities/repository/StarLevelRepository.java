package com.may.tong.enetities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.may.tong.enetities.basic.StarLevel;

/**
 * 处理星级的一个dao层的一个持久类操作
 * */
public interface StarLevelRepository
    extends JpaRepository<StarLevel, Integer> {

}
