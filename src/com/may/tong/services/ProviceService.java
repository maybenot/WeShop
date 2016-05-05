package com.may.tong.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.may.tong.enetities.basic.Province;
import com.may.tong.enetities.repository.ProvinceRepository;

//处理省市的service层
@Service
public class ProviceService {

	@Autowired
	private ProvinceRepository repository;

	/**
	 * 获取所有的省
	 * 首先，因为使用了迫切链接，所以在查询省的时候，下面的市，县区
	 * 信息全部得到了
	 * 并且，这个方法使用了二级缓存，如果对一同一个请求，程序不会再访问数据库
	 * 这使得数据库的负担减轻
	 * ps:这个方法进过测试有效
	 * */
	public List<Province> getProvinces() {
		return repository.findAll();
	}
	
	/**
	 * 获取指定的省，用作测试，级联列表等
	 */
	public Province getProvince(Integer id){
		return repository.findOne(id);
	}

}
