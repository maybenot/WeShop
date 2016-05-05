package com.may.tong.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.may.tong.enetities.basic.ExtendClass;
import com.may.tong.enetities.basic.StarLevel;
import com.may.tong.enetities.basic.State;
import com.may.tong.enetities.repository.ExtendsClassRepository;
import com.may.tong.enetities.repository.ProductDateManagerRepository;
import com.may.tong.enetities.repository.ProductManagerDetailRepository;
import com.may.tong.enetities.repository.SKUAttrValueRepository;
import com.may.tong.enetities.repository.SKU_AttrRepository;
import com.may.tong.enetities.repository.SKU_DetailRepostitory;
import com.may.tong.enetities.repository.SellerRepository;
import com.may.tong.enetities.repository.ShopProductRepository;
import com.may.tong.enetities.repository.StarLevelRepository;
import com.may.tong.enetities.repository.StateRepostitory;
import com.may.tong.enetities.services.ProductDateManager;
import com.may.tong.enetities.services.ProductManagerDetail;
import com.may.tong.enetities.services.SKU_Attribute;
import com.may.tong.enetities.services.SKU_AttributeValue;
import com.may.tong.enetities.services.SKU_AttributeValueDetail;
import com.may.tong.enetities.services.Seller;
import com.may.tong.enetities.services.ShopProduct;
import com.may.tong.enetities.services.SimilarClass;
import com.may.tong.enetities.services.SimilarClassRepostitory;

/**
 * �����������service��һ����
 * */
@Service
public class SellerService {

	@Autowired
	private SellerRepository sellerRepository;

	@Autowired
	private StarLevelRepository starLevelRepository;

	@Autowired
	private ExtendsClassRepository extendsClassRepository;

	@Autowired
	private StateRepostitory stateRepostitory;

	@Autowired
	private ShopProductRepository shopProductRepository;

	@Autowired
	private SimilarClassRepostitory similarClassRepostitory;

	@Autowired
	private SKUAttrValueRepository skuAttrValueRepository;
    
	@Autowired
	private SKU_DetailRepostitory sku_detailRepostitory;
	
	@Autowired
	private SKU_AttrRepository sku_AttrRepository;

	@Autowired
	private ServletRequest request;

	@Autowired
	private ProductManagerDetailRepository productManagerDetailRepository;

	@Autowired
	private ProductDateManagerRepository productDateManagerRepository;

	/**
	 * �û���¼�������֤ ԭ�������ҵ�¼����
	 * */
	public void sellerLogin() {

		return;
	}

	/**
	 * �û�ע��
	 * */
	@Transactional
	public void sellerRegist(Seller seller) {
		if (seller != null) {
			// Ϊ��������һЩ��Ҫ������
			// �����û��Ĵ���ʱ��
			seller.setCreateTime(new Date());
			// �����û����Ǽ�
			StarLevel starLevel = starLevelRepository.findOne(1);
			seller.setStarLevel(starLevel);

			sellerRepository.save(seller);
		}

		return;
	}

	@Transactional(readOnly = true)
	public Seller login(String email) {
		Seller seller = sellerRepository.getSellerByName(email);
		return seller;
	}

	// ׼��С��ŵ�һ������
	public List<ExtendClass> prepredExtendsClass() {
		// ׼��С���
		List<ExtendClass> exts = extendsClassRepository.findAll();
		return exts;
	}

	/**
	 * �û������Ʒ��һ������
	 * 
	 * @param detailValues
	 *            detailValues ���������ÿ����Ʒ�������һ������
	 * @param valuesMap
	 *            ��������Ǹ��ݲ�����
	 * @param skuDetailValues 
	 * @throws IOException
	 * 
	 */
	@Transactional
	public Map<ShopProduct, ProductManagerDetail> addShopProduct(
			Map<String, Object> valuesMap, List<String> detailValues, List<String> skuDetailValues)
			throws IOException {
		// ��ȡ��Ӧ�Ļ����Ĳ���
		Integer sellerId = (Integer) valuesMap.get("sellerId");
		String productName = (String) valuesMap.get("productName");
		Double stock = (Double) valuesMap.get("stock");
		Double memberPrice = (Double) valuesMap.get("memberPrice");
		Double barginPrice = (Double) valuesMap.get("barginPrice");
		Integer state = (Integer) valuesMap.get("state");
		MultipartFile imag = (MultipartFile) valuesMap.get("imag");

		// ��ȡ��Ӧ������
		Seller seller = sellerRepository.findOne(sellerId);

		String fileName = null;
		try {
			fileName = uploadImg(imag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����string����
		Set<SKU_AttributeValueDetail> sKU_Details=
				new HashSet<SKU_AttributeValueDetail>();
		for(String str:skuDetailValues){
			//ȡ�������ֵ
			Integer id=Integer.parseInt(str);
			SKU_AttributeValueDetail  attrvaluedetail=
					sku_detailRepostitory.findOne(id); 
			
			sKU_Details.add(attrvaluedetail);
		}
		
		

		// �ѻ�������Ϣ���뵽��������ͼƬҪ������Ĵ������ϴ�����������Ȼ��Ŵ���·��
		ShopProduct shopProduct = new ShopProduct();
		//����sku��ϸ����Ʒ��һ�������ϵ
		shopProduct.setsKU_Details(sKU_Details);
		
		shopProduct.setImagPath(fileName);
		shopProduct.setShopProductName(productName);
		shopProduct.setStock(stock);
		shopProduct.setClickCount(1);
		State sta = stateRepostitory.findOne(state);
		if (sta != null) {
			shopProduct.setState(sta);
		}

		Set<ProductManagerDetail> details = shopProduct
				.getProductManagerDetails();
		// ����õ���û�У����´���һ��
		if (details == null) {
			details = new HashSet<ProductManagerDetail>();
			shopProduct.setProductManagerDetails(details);

			// ����˫�����
		}
		ProductManagerDetail detail = new ProductManagerDetail();
		// ����Ʒ���û�Ա�ۺʹ����۸�
		detail.setMemberPrice(memberPrice);
		detail.setBarginPrice(barginPrice);

		// ����˫�����,�����������һ��������Ʒ
		detail.setShopProduct(shopProduct);

		// ��ProductManagerDetail��ӵ�details��
		details.add(detail);

		// ������·�Ѿ�������ϣ����ԣ�������һ��·�߳�����

		// ���
		// ����Ʒ���ù������ڣ���ɫת��Ϊ���ң��������ҳ�����

		Set<ProductDateManager> dateManagers = seller.getProductDateManagers();

		// ��������ǵ�һ����ӣ��ʹ���һ�����
		if (dateManagers == null) {
			dateManagers = new HashSet<ProductDateManager>();
			seller.setProductDateManagers(dateManagers);
		}

		// ���һ����Ʒ��������
		Date date = new Date();
		DateFormat df = DateFormat.getDateTimeInstance();

		ProductDateManager prodtm = new ProductDateManager();

		String dateIdentify = df.format(date);
		prodtm.setDateIdentify(dateIdentify);

		// ����Ʒ����������ӵ���Ʒ��manager
		dateManagers.add(prodtm);
		// ����˫�����������Ʒ��������Ϊ����
		prodtm.setSeller(seller);

		Set<ProductManagerDetail> details2 = prodtm.getProductManagerDetails();

		// ���Ϊ����ô�ʹ���һ��
		if (details2 == null) {
			details2 = new HashSet<ProductManagerDetail>();
			prodtm.setProductManagerDetails(details2);
		}

		// ����ϸ��ӵ������������
		details2.add(detail);
		// ����˫�����,����ϸ����Ҳ������
		detail.setProductDateManager(prodtm);

		// ���������Ʒ����Ѿ�ȫ��������ˡ�

		// ���б������

		// ��������
		sellerRepository.flush();

		// ������Ʒ��������
		productDateManagerRepository.save(prodtm);

		// ���������Ʒ
		shopProductRepository.save(shopProduct);

		// ������Ʒ��ϸ
		productManagerDetailRepository.save(detail);
		// ȡ�����̵����е���Ʒ��Ȼ�󷵻�

		// List���������Ʒ
		Map<ShopProduct, ProductManagerDetail> products = getThisShopPrducts(seller
				.getSellerNo());
		/*
		 * ע�����ﷵ����һ���µ�Map������Ӧ��ҳ�滹û�и�Ŷ *
		 */
		return products;
	}

	public Map<ShopProduct, ProductManagerDetail> getThisShopPrducts(
			Integer sellerId) {
		List<ShopProduct> products = new ArrayList<ShopProduct>();

		Map<ShopProduct, ProductManagerDetail> priceMap = new HashMap<ShopProduct, ProductManagerDetail>();

		Seller seller = sellerRepository.findOne(sellerId);
		Set<ProductDateManager> dateManagers = seller.getProductDateManagers();
		// ����managers���õ��õ����Ʒ����,�����趨��ֻ��ʾ���µ���Ʒ
		for (ProductDateManager temp : dateManagers) {
			Set<ProductManagerDetail> det = temp.getProductManagerDetails();
			// ������ϸ�ڣ�
			for (ProductManagerDetail pmd : det) {
				// �ȵõ����е���Ʒ��������ȥ��
				products.add(pmd.getShopProduct());
			}

		}

		// �õ������е���Ʒ֮�󣬿�ʼɸѡ������Ʒ������Ҫ��֤���������Ʒ�ļ۸������µ�
		for (ShopProduct sp : products) {
			Set<ProductManagerDetail> tempDetail = sp
					.getProductManagerDetails();

			// �������ʷ�۸�
			if (tempDetail != null && tempDetail.size() > 1) {

				Set<String> identities = new TreeSet<String>();
				// ȡ��ÿһ����ʷ�۸�Ĺ�������
				for (ProductManagerDetail temp : tempDetail) {
					ProductDateManager prodm = temp.getProductDateManager();
					// ȡ��ÿһ��ʱ��ı�ʶ,Ȼ����ӵ�set�У�treeset����Ȼ�����Ǵ�С������˼��ʱ��Խ��ǰ��Խ�ں���
					// ��������Ҫѡ������ʱ�䣬Ҳ�������µ�ʱ�䡣
					String identity = prodm.getDateIdentify();
					identities.add(identity);
				}

				// ȡ��set�����һ��ʱ��
				Object[] timeArray = identities.toArray();
				String timeString = (String) timeArray[timeArray.length - 1];

				// �ٴ�ѭ�����õ����µļ۸�
				for (ProductManagerDetail temp : tempDetail) {
					ProductDateManager prodm = temp.getProductDateManager();
					String identity = prodm.getDateIdentify();

					if (identity.equals(timeString)) {
						// ��ӵ����µļ۸�Map
						priceMap.put(sp, temp);
					}
				}

			} else {
				// ���Ψһ�ļ۸�
				Iterator<ProductManagerDetail> iter = tempDetail.iterator();
				priceMap.put(sp, iter.next());
			}

		}

		return priceMap;
	}

	/**
	 * ������Ʒ״̬��һ������ ���������Ʒ��ʱ��Ҫ��
	 */

	public List<State> getProductStates() {
		// ׼����Ʒ��״̬
		List<State> states_temp = stateRepostitory.findAll();

		// ��ѡ����Ʒ��״̬�룬�����涨��Ʒ�ı�ʶ��<100
		List<State> states = new ArrayList<State>();
		for (State temp : states_temp) {
			if (temp.getStateNo() < 100) {
				states.add(temp);
			}

		}

		// ����
		return states;
	}

	/**
	 * �����û���ӵ�е�sku����
	 * */
	@Transactional(readOnly = true)
	public Set<SKU_Attribute> paraserSKUattr(Integer id) {
		Seller seller = sellerRepository.findOne(id);
		sellerRepository.flush();
		if (seller != null) {
			Set<SKU_Attribute> attrs = seller.getAttributes();
			return attrs;
		}
		return null;
	}

	/**
	 * ���sku���Եķ���
	 * */
	public void addSkUAttrValue(SKU_AttributeValue attributeValue,
			Integer attrNo) {
		// ��ȡvalue
		SKU_AttributeValue value = skuAttrValueRepository.findOne(attrNo);
		// ��ȡ�������attr
		SKU_Attribute attribute = value.getAttribute();

		// ���ù�����ϵ
		attributeValue.setAttribute(attribute);

		skuAttrValueRepository.save(attributeValue);
		// �ж��Ƿ����û��ĵ�һ����ӣ�����ǣ�ȥ������ռλ��
		System.out.println("***" + value.getValue());
		if (value.getValue().equals("����ֵռλ��")) {
			skuAttrValueRepository.delete(value);
		}

	}

	/**
	 * ���sku���Եķ���
	 * */
	public void addSkUAttrValue(SKU_AttributeValue attributeValue) {

		skuAttrValueRepository.save(attributeValue);

	}

	/**
	 * ����С��������ҵ�С��
	 * */

	public ExtendClass findExtByName(String extName) {
		List<ExtendClass> exts = extendsClassRepository.findAll();
		// ѭ�����ҳ�Ŀ���extName
		String tempName = extName.trim();
		ExtendClass target = null;
		for (ExtendClass temp : exts) {
			if (temp.getExtendClassName().equals(tempName)) {
				target = temp;
			}

		}
		if (target != null) {

		}

		return null;
	}

	/**
	 * �����Զ���sku
	 * */

	public void saveDefineSKUAttr(SKU_Attribute attr) {
		sku_AttrRepository.save(attr);
	}

	/**
	 * ��װͼƬ���ϴ�����
	 * 
	 * @throws Exception
	 * */
	public String uploadImg(MultipartFile imag)
			throws Exception {

		// ��ȡ�ļ����ϴ����������������ļ�������ȡ����һ�ֹ淶��һ��ȡ���û����Լ��û����������Լ���
		// �û������ļ���������ʽ��������+ʱ�������+ͼƬ��ԭʼ����
		String fileName = Calendar.getInstance().getTimeInMillis()
				+ imag.getOriginalFilename();

		// ����������ĵ�ַ

		String path = request.getRealPath("pdt_image") + "/" + fileName;
		// ����ͼƬ���ϴ�����
		OutputStream fos = new FileOutputStream(new File(path));
		InputStream fis = imag.getInputStream();

		int len = 0;
		byte[] b = new byte[1024];

		while ((len = fis.read(b)) != -1) {
			fos.write(b, 0, len);
		}
		// �ر���Դ
		fos.close();
		fis.close();

		return path;

	}
	
	/**
	 * ����С��������ȡ��sku�����Լ���ϸ��һ������
	 * */
	public Map<String, Map<Integer, String>> prepredSKUDetail(Map<String, Map<Integer, String>> map,
			 String extName,Integer sellerID ){
 
		List<ExtendClass> exts=extendsClassRepository.findAll();
		ExtendClass target1 = null;
		for (ExtendClass temp : exts) {
			if (temp.getExtendClassName().equals(extName)) {
				// �õ�Ŀ���һ��extendsClass
				target1 = temp;
			}
		}
		Map<String, Map<Integer, String>> returnValues=null;
		if(map==null){
			// ���巵��Map��һ�����ݽṹ
			returnValues = new HashMap<String, Map<Integer, String>>();
		}else{
			//�������Ϊ�����Ʒ�ķ���׼����
			returnValues=map;
			
		}
		

		// 1.��һ����Դ�� �õ�����Ϣ��Ŀ��С��
		if (target1 != null) {
			
			extendsClassRepository.flush();
			ExtendClass target=extendsClassRepository.findOne(target1.getExtendClassNo());
			extendsClassRepository.flush();
			
			// �õ�����С���Ӧ��һ��sku����
			Set<SKU_Attribute> sku_attr = target.getSku_Attributes();
			// 1. ��������sku���ԣ��õ�����skuֵ
			for (SKU_Attribute temp : sku_attr) {

				// �õ�SKU_Attribute�����ֺ�ֵ
				String attrName = temp.getName();
				// �õ���Ӧ��skuֵ
				Set<SKU_AttributeValue> attrvalue = temp.getValues();
				Map<Integer, String> valueMap = new HashMap<Integer, String>();
				// �ڶ���ѭ��
				for (SKU_AttributeValue attr_temp : attrvalue) {

					// �õ�SKU_AttributeValue��id
					Integer id = attr_temp.getId();
					// �õ�SKU_AttributeValue�����ֺ�ֵ
					String value = attr_temp.getValue();

					// ����map
					valueMap.put(id, value);

				}
				// ѭ������֮�󣬰�ȡֵ���Ϸ����Map
				returnValues.put(attrName, valueMap);
			}
		}

		// 2.�ڶ�����Դ����Դ�������Լ����������ѭ������֮��
		

		// �����û���ӵ�е�sku����
		Set<SKU_Attribute> sellerAttr = paraserSKUattr(sellerID);

		if (sellerAttr != null) {
			// ��������ѭ��
			for (SKU_Attribute tempAttr : sellerAttr) {
				// ��ȡ����
				String attrName = tempAttr.getName();
				// ��ȡ���Ӧ��skuֵ
				Set<SKU_AttributeValue> attrValue = tempAttr.getValues();
				// ����һͷ����ѭ��
				// �жϣ����ԭ����map����������ԣ�Ҳ�����û�ֻ�����������Ŀ
				// ��ô��ֱ��ȡ����ӽ�ȥ�Ϳ���
				if (returnValues.containsKey(attrName)) {
					Map<Integer, String> tempMap = returnValues.get(attrValue);
					for (SKU_AttributeValue tempValue : attrValue) {
						tempMap.put(tempValue.getId(), tempValue.getValue());
					}
				} else {
					// ���򣬾�������ӵ�suk���ԣ�
					Map<Integer, String> tempMap = new HashMap<Integer, String>();
					for (SKU_AttributeValue tempValue : attrValue) {
						tempMap.put(tempValue.getId(), tempValue.getValue());
					}
					// ���뵽��MAP
					returnValues.put(attrName, tempMap);
				}

			}

		}
		return returnValues;
	}
	
	/**
	 * �ҵ���ƷС���Ӧ�����е���Ʒͬ��
	 */
	 public Map<Integer ,String> preperdSimilarClass(ExtendClass extendClass){
		 
		 Set<SKU_Attribute>  atrAttributes=extendClass.getSku_Attributes();
		 
		 Map<Integer ,String> similarClasses=new HashMap<Integer, String>();
		 
		  for(SKU_Attribute attr:atrAttributes){
			    Set<SKU_AttributeValue> values=attr.getValues();
			    for( SKU_AttributeValue value:values){
			    	Set<SKU_AttributeValueDetail> details=value.getDetails();
			    	for(SKU_AttributeValueDetail detail:details){
			    		SimilarClass similarClass=detail.getSimilarClass();
			    		similarClasses.put(similarClass.getSimilarClassId(), similarClass.getSimilarClassName());
			    	}
			    }
			     
			  
		  }
		  
		  return similarClasses;
		 
	 }
	 
	 /**
	  * �û������Ʒʱ�򣬴���ͬ����Ʒ���ƣ��Ѹ�ͬ����Ʒ�����µ�sku�ҳ���
	  * */
	 public Map<String,Map<Integer,String>> preparedAddShopSKU(String simclassName){
		 
		 //������������������ѭ�����ҳ�С���Ӧ��sku�ľ���ֵ
		 //���ȣ��ҳ�ͬ�ࣺ
		 List<SimilarClass> sices=similarClassRepostitory.findAll();
		 SimilarClass target=null;
		 
		 for(SimilarClass temp:sices){
			 if(temp.getSimilarClassId()==Integer.parseInt(simclassName)){
				 target=temp;
			 }
			 
		 }
		 
		 Map<String,Map<Integer,String>>  returnValues=new HashMap<String, Map<Integer,String>>();
		 if(target!=null){
			 
			 Set<SKU_AttributeValueDetail>  details=target.getSKU_details();
			 
			 //��ʼ����������ѭ��
			 for(SKU_AttributeValueDetail valueDetail:details){
				 //��ȡ����ֵ
				   SKU_AttributeValue  value =valueDetail.getSKUvalue();
				   //��ȡ����
				   SKU_Attribute  attr=value.getAttribute();
				   
				   //����MAP
				   if(returnValues.containsKey(attr.getName())){
					   
					   //ȡ��
					   Map<Integer,String> oldMap=returnValues.get(attr.getName());
					   oldMap.put(valueDetail.getId(), value.getValue());
					   
				   }else{
					   Map<Integer,String> newMap=new HashMap<Integer, String>();
					   newMap.put(valueDetail.getId(), value.getValue());
					   returnValues.put(attr.getName(), newMap);
				   }
			 }
		 }
		 return returnValues;
		 
	 }

}
