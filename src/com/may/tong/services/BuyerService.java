package com.may.tong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.may.tong.enetities.basic.BigClass;
import com.may.tong.enetities.repository.BigClassRepository;
import com.may.tong.enetities.repository.BuyerRepository;
import com.may.tong.enetities.services.Buyer;

//处理买家业务的业务层
@Service
public class BuyerService {

	@Autowired
	private BuyerRepository repository;

	@Autowired
	private BigClassRepository bigClassRepository;

	/**
	 * 查询单个买家的方法 可能用于查询买家是否存在以及获取买家的基本信息
	 * */
	@Transactional(readOnly = true)
	public Buyer getBuyer(String userName) {
		return repository.getUserByUserName(userName);
	}

	/**
	 * 新增买家的方法 可能用于买家的注册 添加事物保证原子性 但是在用户完善信息之前，用户实例里面的数据只有email和passwd
	 * 但是有一些基本的东西可以先为其附上值
	 * */

	@Transactional
	public void addBuyer(Buyer buyer) {
		repository.save(buyer);
	}

	/**
	 * 更新买家的方法 先删除旧的买家的信息，然后添加新的买家的信息 这是效率最高的，但是考虑到外键的关联性，所以不允许删除被作为外键的信息
	 * 所以还是采用更新的方法 ps：这个方法也还没有测试
	 * */
	@Transactional
	public void updateBuyer(Buyer newBuyer) {
		if (newBuyer != null) {
			repository.saveAndFlush(newBuyer);
		}
	}

	/**
	 * 判断用户是否存在的方法
	 * */
	@Transactional(readOnly = true)
	public boolean nameIsExist(String userName) {

		Buyer buyer = null;
		if (userName != null) {
			buyer = repository.getUserByUserName(userName);
		}
		if (buyer != null) {
			return true;
		}
		return false;

	}

	// 装备大类的一个方法
	public List<BigClass> prepredBigClass() {
		// 准备大类号
		List<BigClass> bgc = bigClassRepository.findAll();

		return bgc;
	}

}
