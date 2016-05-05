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
 * 处理卖家相关service的一个类
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
	 * 用户登录的相关验证 原理与卖家登录类似
	 * */
	public void sellerLogin() {

		return;
	}

	/**
	 * 用户注册
	 * */
	@Transactional
	public void sellerRegist(Seller seller) {
		if (seller != null) {
			// 为卖家设置一些必要的属性
			// 设置用户的创建时间
			seller.setCreateTime(new Date());
			// 设置用户的星级
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

	// 准备小类号的一个方法
	public List<ExtendClass> prepredExtendsClass() {
		// 准备小类号
		List<ExtendClass> exts = extendsClassRepository.findAll();
		return exts;
	}

	/**
	 * 用户添加商品的一个方法
	 * 
	 * @param detailValues
	 *            detailValues 这个参数是每个商品都必须的一个属性
	 * @param valuesMap
	 *            这个参数是根据不定项
	 * @param skuDetailValues 
	 * @throws IOException
	 * 
	 */
	@Transactional
	public Map<ShopProduct, ProductManagerDetail> addShopProduct(
			Map<String, Object> valuesMap, List<String> detailValues, List<String> skuDetailValues)
			throws IOException {
		// 获取对应的基本的参数
		Integer sellerId = (Integer) valuesMap.get("sellerId");
		String productName = (String) valuesMap.get("productName");
		Double stock = (Double) valuesMap.get("stock");
		Double memberPrice = (Double) valuesMap.get("memberPrice");
		Double barginPrice = (Double) valuesMap.get("barginPrice");
		Integer state = (Integer) valuesMap.get("state");
		MultipartFile imag = (MultipartFile) valuesMap.get("imag");

		// 获取对应的卖家
		Seller seller = sellerRepository.findOne(sellerId);

		String fileName = null;
		try {
			fileName = uploadImg(imag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//遍历string集合
		Set<SKU_AttributeValueDetail> sKU_Details=
				new HashSet<SKU_AttributeValueDetail>();
		for(String str:skuDetailValues){
			//取出传入的值
			Integer id=Integer.parseInt(str);
			SKU_AttributeValueDetail  attrvaluedetail=
					sku_detailRepostitory.findOne(id); 
			
			sKU_Details.add(attrvaluedetail);
		}
		
		

		// 把基本的信息存入到对象，其中图片要做特殊的处理，先上传到服务器，然后才存入路径
		ShopProduct shopProduct = new ShopProduct();
		//设置sku明细和商品的一个管理关系
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
		// 如果该店铺没有，就新创建一个
		if (details == null) {
			details = new HashSet<ProductManagerDetail>();
			shopProduct.setProductManagerDetails(details);

			// 设置双向关联
		}
		ProductManagerDetail detail = new ProductManagerDetail();
		// 给商品设置会员价和促销价格
		detail.setMemberPrice(memberPrice);
		detail.setBarginPrice(barginPrice);

		// 设置双向关联,设置其关联的一个店铺商品
		detail.setShopProduct(shopProduct);

		// 把ProductManagerDetail添加到details中
		details.add(detail);

		// 单条线路已经设置完毕，所以，从另外一条路线出发；

		// 添加
		// 给商品设置管理日期（角色转换为卖家，即从卖家出发）

		Set<ProductDateManager> dateManagers = seller.getProductDateManagers();

		// 如果卖家是第一次添加，就创建一个这个
		if (dateManagers == null) {
			dateManagers = new HashSet<ProductDateManager>();
			seller.setProductDateManagers(dateManagers);
		}

		// 添加一个商品管理日期
		Date date = new Date();
		DateFormat df = DateFormat.getDateTimeInstance();

		ProductDateManager prodtm = new ProductDateManager();

		String dateIdentify = df.format(date);
		prodtm.setDateIdentify(dateIdentify);

		// 把商品管理日期添加到商品的manager
		dateManagers.add(prodtm);
		// 设置双向关联，把商品的日期设为卖家
		prodtm.setSeller(seller);

		Set<ProductManagerDetail> details2 = prodtm.getProductManagerDetails();

		// 如果为空那么就创建一个
		if (details2 == null) {
			details2 = new HashSet<ProductManagerDetail>();
			prodtm.setProductManagerDetails(details2);
		}

		// 把明细添加到管理的日期中
		details2.add(detail);
		// 设置双向关联,让明细里面也有日期
		detail.setProductDateManager(prodtm);

		// 到了这里，商品添加已经全部的完成了。

		// 进行保存操作

		// 更新卖家
		sellerRepository.flush();

		// 保存商品管理日期
		productDateManagerRepository.save(prodtm);

		// 保存店铺商品
		shopProductRepository.save(shopProduct);

		// 保存商品明细
		productManagerDetailRepository.save(detail);
		// 取出该商店所有的商品，然后返回

		// List用来存放商品
		Map<ShopProduct, ProductManagerDetail> products = getThisShopPrducts(seller
				.getSellerNo());
		/*
		 * 注意这里返回了一个新的Map，而对应的页面还没有改哦 *
		 */
		return products;
	}

	public Map<ShopProduct, ProductManagerDetail> getThisShopPrducts(
			Integer sellerId) {
		List<ShopProduct> products = new ArrayList<ShopProduct>();

		Map<ShopProduct, ProductManagerDetail> priceMap = new HashMap<ShopProduct, ProductManagerDetail>();

		Seller seller = sellerRepository.findOne(sellerId);
		Set<ProductDateManager> dateManagers = seller.getProductDateManagers();
		// 遍历managers，得到该店的商品集合,这里设定，只显示最新的商品
		for (ProductDateManager temp : dateManagers) {
			Set<ProductManagerDetail> det = temp.getProductManagerDetails();
			// 遍历其细节，
			for (ProductManagerDetail pmd : det) {
				// 先得到所有的商品，包括过去的
				products.add(pmd.getShopProduct());
			}

		}

		// 得到了所有的商品之后，开始筛选店铺商品，但是要保证这个店铺商品的价格是最新的
		for (ShopProduct sp : products) {
			Set<ProductManagerDetail> tempDetail = sp
					.getProductManagerDetails();

			// 如果有历史价格
			if (tempDetail != null && tempDetail.size() > 1) {

				Set<String> identities = new TreeSet<String>();
				// 取出每一个历史价格的管理日期
				for (ProductManagerDetail temp : tempDetail) {
					ProductDateManager prodm = temp.getProductDateManager();
					// 取出每一个时间的标识,然后添加到set中，treeset的天然排序是从小到大，意思是时间越靠前就越在后面
					// 所以我们要选择更大的时间，也就是最新的时间。
					String identity = prodm.getDateIdentify();
					identities.add(identity);
				}

				// 取出set的最后一个时间
				Object[] timeArray = identities.toArray();
				String timeString = (String) timeArray[timeArray.length - 1];

				// 再次循环，得到最新的价格
				for (ProductManagerDetail temp : tempDetail) {
					ProductDateManager prodm = temp.getProductDateManager();
					String identity = prodm.getDateIdentify();

					if (identity.equals(timeString)) {
						// 添加到最新的价格Map
						priceMap.put(sp, temp);
					}
				}

			} else {
				// 添加唯一的价格
				Iterator<ProductManagerDetail> iter = tempDetail.iterator();
				priceMap.put(sp, iter.next());
			}

		}

		return priceMap;
	}

	/**
	 * 返回商品状态的一个方法 ，在添加商品的时候要用
	 */

	public List<State> getProductStates() {
		// 准备商品的状态
		List<State> states_temp = stateRepostitory.findAll();

		// 遴选出商品的状态码，当初规定商品的标识码<100
		List<State> states = new ArrayList<State>();
		for (State temp : states_temp) {
			if (temp.getStateNo() < 100) {
				states.add(temp);
			}

		}

		// 返回
		return states;
	}

	/**
	 * 返回用户所拥有的sku属性
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
	 * 添加sku属性的方法
	 * */
	public void addSkUAttrValue(SKU_AttributeValue attributeValue,
			Integer attrNo) {
		// 获取value
		SKU_AttributeValue value = skuAttrValueRepository.findOne(attrNo);
		// 获取其关联的attr
		SKU_Attribute attribute = value.getAttribute();

		// 设置关联关系
		attributeValue.setAttribute(attribute);

		skuAttrValueRepository.save(attributeValue);
		// 判断是否是用户的第一次添加，如果是，去掉属性占位符
		System.out.println("***" + value.getValue());
		if (value.getValue().equals("属性值占位符")) {
			skuAttrValueRepository.delete(value);
		}

	}

	/**
	 * 添加sku属性的方法
	 * */
	public void addSkUAttrValue(SKU_AttributeValue attributeValue) {

		skuAttrValueRepository.save(attributeValue);

	}

	/**
	 * 根据小类的名字找到小类
	 * */

	public ExtendClass findExtByName(String extName) {
		List<ExtendClass> exts = extendsClassRepository.findAll();
		// 循环，找出目标的extName
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
	 * 保存自定义sku
	 * */

	public void saveDefineSKUAttr(SKU_Attribute attr) {
		sku_AttrRepository.save(attr);
	}

	/**
	 * 封装图片的上传操作
	 * 
	 * @throws Exception
	 * */
	public String uploadImg(MultipartFile imag)
			throws Exception {

		// 读取文件，上床到服务器，对于文件名，采取这样一种规范，一律取消用户的自己用户名，采用自己的
		// 用户名，文件的命名格式：卖家名+时间戳名字+图片的原始名字
		String fileName = Calendar.getInstance().getTimeInMillis()
				+ imag.getOriginalFilename();

		// 构造服务器的地址

		String path = request.getRealPath("pdt_image") + "/" + fileName;
		// 进行图片的上传操作
		OutputStream fos = new FileOutputStream(new File(path));
		InputStream fis = imag.getInputStream();

		int len = 0;
		byte[] b = new byte[1024];

		while ((len = fis.read(b)) != -1) {
			fos.write(b, 0, len);
		}
		// 关闭资源
		fos.close();
		fis.close();

		return path;

	}
	
	/**
	 * 传入小类名，获取其sku属性以及明细的一个方法
	 * */
	public Map<String, Map<Integer, String>> prepredSKUDetail(Map<String, Map<Integer, String>> map,
			 String extName,Integer sellerID ){
 
		List<ExtendClass> exts=extendsClassRepository.findAll();
		ExtendClass target1 = null;
		for (ExtendClass temp : exts) {
			if (temp.getExtendClassName().equals(extName)) {
				// 得到目标的一个extendsClass
				target1 = temp;
			}
		}
		Map<String, Map<Integer, String>> returnValues=null;
		if(map==null){
			// 定义返回Map的一个数据结构
			returnValues = new HashMap<String, Map<Integer, String>>();
		}else{
			//这个方法为添加商品的方法准备着
			returnValues=map;
			
		}
		

		// 1.第一个来源： 得到带信息的目标小类
		if (target1 != null) {
			
			extendsClassRepository.flush();
			ExtendClass target=extendsClassRepository.findOne(target1.getExtendClassNo());
			extendsClassRepository.flush();
			
			// 得到这种小类对应的一个sku属性
			Set<SKU_Attribute> sku_attr = target.getSku_Attributes();
			// 1. 遍历其描sku属性，得到它的sku值
			for (SKU_Attribute temp : sku_attr) {

				// 得到SKU_Attribute的名字和值
				String attrName = temp.getName();
				// 得到对应的sku值
				Set<SKU_AttributeValue> attrvalue = temp.getValues();
				Map<Integer, String> valueMap = new HashMap<Integer, String>();
				// 第二层循环
				for (SKU_AttributeValue attr_temp : attrvalue) {

					// 得到SKU_AttributeValue的id
					Integer id = attr_temp.getId();
					// 得到SKU_AttributeValue的名字和值
					String value = attr_temp.getValue();

					// 放入map
					valueMap.put(id, value);

				}
				// 循环出来之后，吧取值集合放入大Map
				returnValues.put(attrName, valueMap);
			}
		}

		// 2.第二个来源，来源于卖家自己定义类别，用循环遍历之；
		

		// 返回用户所拥有的sku属性
		Set<SKU_Attribute> sellerAttr = paraserSKUattr(sellerID);

		if (sellerAttr != null) {
			// 继续进入循环
			for (SKU_Attribute tempAttr : sellerAttr) {
				// 获取名字
				String attrName = tempAttr.getName();
				// 获取其对应的sku值
				Set<SKU_AttributeValue> attrValue = tempAttr.getValues();
				// 继续一头扎进循环
				// 判断，如果原来的map存在这个属性，也就是用户只是添加了子项目
				// 那么，直接取出添加进去就可以
				if (returnValues.containsKey(attrName)) {
					Map<Integer, String> tempMap = returnValues.get(attrValue);
					for (SKU_AttributeValue tempValue : attrValue) {
						tempMap.put(tempValue.getId(), tempValue.getValue());
					}
				} else {
					// 否则，就是新添加的suk属性，
					Map<Integer, String> tempMap = new HashMap<Integer, String>();
					for (SKU_AttributeValue tempValue : attrValue) {
						tempMap.put(tempValue.getId(), tempValue.getValue());
					}
					// 加入到大MAP
					returnValues.put(attrName, tempMap);
				}

			}

		}
		return returnValues;
	}
	
	/**
	 * 找到商品小类对应的所有的商品同类
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
	  * 用户添加商品时候，传入同类商品名称，把该同类商品名称下的sku找出来
	  * */
	 public Map<String,Map<Integer,String>> preparedAddShopSKU(String simclassName){
		 
		 //接下来，又是作死的循环，找出小类对应的sku的具体值
		 //首先，找出同类：
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
			 
			 //开始进入作死的循环
			 for(SKU_AttributeValueDetail valueDetail:details){
				 //获取属性值
				   SKU_AttributeValue  value =valueDetail.getSKUvalue();
				   //获取属性
				   SKU_Attribute  attr=value.getAttribute();
				   
				   //加入MAP
				   if(returnValues.containsKey(attr.getName())){
					   
					   //取出
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
