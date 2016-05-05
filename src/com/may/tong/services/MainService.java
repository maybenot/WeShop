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
	
	// ע��һ���û��Զ��帻�ı��༭��
	@Autowired
	private ProductUserDefineRepository productUserDefineRepository;

	/**
	 * ��������������ط�ҳ�Ľ�� pageNo ����ڼ�ҳ ��ԭ�������Ǵӵ�0ҳ��ʼ pageSize ��ҳ��ҳ��
	 * */

	public Map<String, Object> getPage(Integer pageNo, Integer pageSize) {

		PageRequest request = new PageRequest(pageNo - 1, pageSize);

		Page<ShopProduct> temp = shopProductRepository.findAll(request);

		List<ShopProduct> shopProducts = new ArrayList<ShopProduct>();

		// �ѷ�ҳ�Ľ�����뵽list��
		for (ShopProduct sp : temp) {
			Set<ProductManagerDetail> pdtmds = sp.getProductManagerDetails();

			// ����jpa��������˫��Ĺ��������ԣ�����ȡ��ʱ��Ҫ�ÿ���Щ������������������ѭ�����Ӷ�ջ�����
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
		// ����ܵĽ����
		returnMap.put("totalElement", temp.getTotalElements());
		// ����ܵ�ҳ��
		returnMap.put("totalPage", temp.getTotalPages());

		// ��ӽ����һ��list
		returnMap.put("list", shopProducts);

		return returnMap;
	}
	
	//�����û����ı��༭����һ������
	public void saveContent(ProductUserDefine pud){
		productUserDefineRepository .save(pud);
		
	}

}
