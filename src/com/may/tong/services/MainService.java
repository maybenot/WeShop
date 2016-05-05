package com.may.tong.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.may.tong.enetities.basic.ProductUserDefine;
import com.may.tong.enetities.repository.ProductUserDefineRepository;
import com.may.tong.enetities.repository.ShopProductRepository;
import com.may.tong.enetities.services.ProductManagerDetail;
import com.may.tong.enetities.services.ShopProduct;

@Service
public class MainService {

	@Autowired
	private ShopProductRepository shopProductRepository;
	
	// 注入一个用户自定义富文本编辑器
	@Autowired
	private ProductUserDefineRepository productUserDefineRepository;

	/**
	 * 这个方法用来返回分页的结果 pageNo 请求第几页 ，原理上面是从第0页开始 pageSize 分页的页数
	 * */

	public Map<String, Object> getPage(Integer pageNo, Integer pageSize) {

		PageRequest request = new PageRequest(pageNo - 1, pageSize);

		Page<ShopProduct> temp = shopProductRepository.findAll(request);

		List<ShopProduct> shopProducts = new ArrayList<ShopProduct>();

		// 把分页的结果放入到list中
		for (ShopProduct sp : temp) {
			Set<ProductManagerDetail> pdtmds = sp.getProductManagerDetails();

			// 由于jpa是设置了双向的关联，所以，在提取的时候，要置空这些关联，否则会造成无限循环，从而栈溢出！
			for (ProductManagerDetail pmd : pdtmds) {
				pmd.setShopProduct(null);
				pmd.getProductDateManager().setProductManagerDetails(null);
				pmd.getProductDateManager().getSeller()
						.setProductDateManagers(null);
			}
			shopProducts.add(sp);
		}
		// System.out.println(shopProducts.size()+"****");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		// 添加总的结果数
		returnMap.put("totalElement", temp.getTotalElements());
		// 添加总的页数
		returnMap.put("totalPage", temp.getTotalPages());

		// 添加结果的一个list
		returnMap.put("list", shopProducts);

		return returnMap;
	}
	
	//保存用户富文本编辑器的一个方法
	public void saveContent(ProductUserDefine pud){
		productUserDefineRepository .save(pud);
		
	}

}
