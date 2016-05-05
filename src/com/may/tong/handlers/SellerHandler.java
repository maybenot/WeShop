package com.may.tong.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.may.tong.enetities.basic.AttributeValue;
import com.may.tong.enetities.basic.DescribeAttribute;
import com.may.tong.enetities.basic.DescribeDetail;
import com.may.tong.enetities.basic.ExtendClass;
import com.may.tong.enetities.basic.State;
import com.may.tong.enetities.repository.ExtendsClassRepository;
import com.may.tong.enetities.repository.SKUAttrValueRepository;
import com.may.tong.enetities.repository.SKU_DetailRepostitory;
import com.may.tong.enetities.repository.SellerRepository;
import com.may.tong.enetities.services.ProductManagerDetail;
import com.may.tong.enetities.services.SKU_Attribute;
import com.may.tong.enetities.services.SKU_AttributeValue;
import com.may.tong.enetities.services.SKU_AttributeValueDetail;
import com.may.tong.enetities.services.Seller;
import com.may.tong.enetities.services.ShopProduct;
import com.may.tong.enetities.services.SimilarClass;
import com.may.tong.enetities.services.SimilarClassRepostitory;
import com.may.tong.message.ResultMessage;
import com.may.tong.services.MainService;
import com.may.tong.services.SellerService;

/**
 * 处理关于卖家相关任务的一个handler
 * */

@Controller
public class SellerHandler {

	@Autowired
	private SellerService sellerService;

	@Autowired
	private MainService mainService;
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private SKU_DetailRepostitory sku_DetailRepostitory;
	
	@Autowired
	private SKUAttrValueRepository  skuAttrValueRepository;
	
	@Autowired
	private SimilarClassRepostitory similarClassRepostitory;
	
	@Autowired
	private ExtendsClassRepository extendsClassRepository;

	// 静态数据
	private static List<ExtendClass> exts = null;

	/**
	 * 关于用户注册的一个方法
	 * */
	@RequestMapping("/regist")
	public String regist(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "shopName", required = true) String shopName,
			@RequestParam(value = "shopOwer", required = true) String shopOwer,
			@RequestParam(value = "tel", required = true) String tel,
			HttpSession session) {
		// 装配好必要的属性
		Seller seller = new Seller(shopName, email, shopOwer, password, tel);
		sellerService.sellerRegist(seller);
		// 把用户的信息存入session
		session.setAttribute("USER_IDENTITY", seller);
		// 卖家的id存入session
		session.setAttribute("USER_ID", seller.getSellerNo());

		// 获取商品的状态
		List<State> states = sellerService.getProductStates();

		// 把这个商品的状态放入session
		session.setAttribute("productStates", states);

		// 取出这个店家自定义的商品同类,放入请求
		Set<SimilarClass> similarClasses = seller.getSimilarClasses();
		session.setAttribute("similarClasses", similarClasses);

		// 准备小类
		if (exts == null) {
			exts = sellerService.prepredExtendsClass();
		}
		session.setAttribute("exts", exts);
		return "redirect:seller/s_main.jsp";
	}

	/**
	 * 用户登录的一个方法
	 * 
	 * */
	@RequestMapping("/s_login")
	public String login(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			HttpSession session, Map<String, Object> map) {
		Seller seller = sellerService.login(email);
		if (seller != null) {
			if (seller.getPassword().equals(password)) {
				session.setAttribute("USER_IDENTITY", seller);

				// 卖家的id存入session
				session.setAttribute("USER_ID", seller.getSellerNo());

				// 准备小类
				if (exts == null) {
					exts = sellerService.prepredExtendsClass();
				}
				session.setAttribute("exts", exts);

				// 获取商品的状态
				List<State> states = sellerService.getProductStates();

				// 把这个商品的状态放入session
				session.setAttribute("productStates", states);

				// 取出这个店铺的商品，把这个店铺的商品放入session中
				Map<ShopProduct, ProductManagerDetail> products = sellerService
						.getThisShopPrducts(seller.getSellerNo());

				// 把这个店铺的商品放入session
				session.setAttribute("products", products);

				// 取出这个店家自定义的商品同类,放入请求
				Set<SimilarClass> similarClasses = seller.getSimilarClasses();
				session.setAttribute("similarClasses", similarClasses);

				return "redirect:/seller/s_main.jsp";
			}
			ResultMessage message = new ResultMessage(true, "密码错误");
			map.put("message", message);
			return "/seller/login.jsp";
		} else {
			ResultMessage message = new ResultMessage(true, "用户名不存在！");
			map.put("message", message);
			return "/seller/login.jsp";
		}

	}

	/**
	 * 用户添加商品的一个方法，用于ajax请求，返回一个商品的一个描述相关属性
	 * */

	@RequestMapping(value = "/returnDescri")
	@ResponseBody
	public  Map<String,Map<String, Map<Integer, String>>> test(HttpServletRequest request,HttpSession session,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// 获取用户选择的小类名称
		String extName = request.getParameter("eca");
		extName = new String(extName.getBytes("ISO-8859-1"), "UTF-8");
		// 取出该小类名称对应的 描述属性：如：颜色，全新，等等
		ExtendClass target1 = null;
		for (ExtendClass temp : exts) {
			if (temp.getExtendClassName().equals(extName)) {
				// 得到目标的一个extendsClass
				target1 = temp;
			}
		}
		
		Map<String,Map<String, Map<Integer, String>>> realReturn=new HashMap<String, Map<String,Map<Integer, String>>>();
		
		// 定义返回Map的一个数据结构
		Map<String, Map<Integer, String>> returnValues = new HashMap<String, Map<Integer, String>>(); 
		
		 
		
		ExtendClass target=null;
		// 得到带信息的目标小类
		if (target1 != null) {
			// 得到它对应的一个描述属性
			//这里应该是重新获取一下小类，要不然有缓存
			extendsClassRepository.flush();
			 target=extendsClassRepository.findOne(target1.getExtendClassNo());
			extendsClassRepository.flush();
			
			
			Set<DescribeDetail> describeDetails = target.getDescribeDetails();
			// 遍历其描述属性，得到它的一个描述属性
			for (DescribeDetail temp : describeDetails) {
				// 得到它的描述属性号
				// 得到它的一个属性值集
				AttributeValue attrvalue = temp.getAttributeValue();
				// 得到它的描述属性
				DescribeAttribute descriAttr = attrvalue.getDescribeAttribute();
				// 得到它对应的属性名字
				String attrName = descriAttr.getDescribeAttributeName();

				// 获取细节的描述号
				Integer describeNo = temp.getDescribeDetailNo();
				// 获取文字的一个描述属性值
				String valueName = attrvalue.getAttributeValueName();

				// 判断，返回值集合中是否已经存在这个属性
				if (returnValues.containsKey(attrName)) {
					// 如果存在，取出集合的map
					Map<Integer, String> valueMap = returnValues.get(attrName);
					valueMap.put(describeNo, valueName);
				} else {
					// 如果不存在，新添加
					Map<Integer, String> values = new HashMap<Integer, String>();
					values.put(describeNo, valueName);
					// 加入到对应的map中
					returnValues.put(attrName, values);
				}

			}
		}

		// 测试：完美的符合情况
		// System.out.println(returnValues);
		
		
		 
		
		//获取用户的id
	    Integer sellerID= (Integer) session.getAttribute("USER_ID");

          //把对应的sku属性放入到map中
	   //  Map<String ,Map<Integer,String>> skuAttr=sellerService.prepredSKUDetail(null, extName, sellerID);
	     //找到商品小类对应的所有同类
	    Map<Integer ,String>  similarClass=sellerService.preperdSimilarClass(target);
	    realReturn.put("descAttr", returnValues);
	    
	    Map<String ,Map<Integer,String>> similar=new HashMap<String, Map<Integer,String>>();
	    similar.put("simi", similarClass);
	    
	    realReturn.put("similarClass",  similar);
		// 返回这个结果集
		return realReturn;
	}

	/**
	 * 用户添加SKU的一个方法，这个方法与用户添加描述属性类似，但是不同的是，这个来源有很多
	 * 
	 * */

	@RequestMapping(value = "/SKU_Descri")
	@ResponseBody
	public Map<String, Map<Integer, String>> return_sku(
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// 获取用户选择的小类名称
		String extName = request.getParameter("skuEClass");
		extName = new String(extName.getBytes("ISO-8859-1"), "UTF-8");

		// 获取用户的id
	    Integer sellerID = (Integer) session.getAttribute("USER_ID");
	    
	     //调用方法
	    Map<String, Map<Integer, String>> returnValues=  
	    		sellerService.prepredSKUDetail(null,extName, sellerID);

		// 这个方法还没有测试！也还没有写ajax！
		// 测试，是否得到了这个属性值经过测试，是可以的
		// System.out.println("********"+returnValues);
		// 返回这个结果集
		return returnValues;
	}
	/**
	 * 用户添加商品时候说使用的返回desc
	 * */
	@RequestMapping(value = "/addShopProductSku")
	@ResponseBody
	public Map<String, Map<Integer, String>> return_addShopProsku(
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// 获取用户选择的小类名称
		String simclass = request.getParameter("simclass");
		simclass = new String(simclass.getBytes("ISO-8859-1"), "UTF-8");
		
		 
		//调用方法
		Map<String, Map<Integer, String>> returnValues=  
				sellerService.preparedAddShopSKU(simclass);
		return returnValues;
	}

	/**
	 * 自定义SKU的一个方法，这个方法与用户添加描述属性类似，但是不同的是，这个来源有很多
	 * 
	 * */

	@RequestMapping(value = "/SKU_UserDefine")
	@ResponseBody
	public Map<String, Map<Integer, String>> return_UserDefinesku(
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// 获取用户选择的小类名称
		String extName = request.getParameter("skuEClass");
		extName = new String(extName.getBytes("ISO-8859-1"), "UTF-8");

		ExtendClass targetTemp = null;
		for (ExtendClass temp : exts) {
			if (temp.getExtendClassName().equals(extName)) {
				// 得到目标的一个extendsClass
				targetTemp = temp;
			}
		}
		// 定义返回Map的一个数据结构
		Map<String, Map<Integer, String>> returnValues = new HashMap<String, Map<Integer, String>>();

		// 1.第一个来源： 得到带信息的目标小类
		if (targetTemp != null) {
			
			extendsClassRepository.flush();
		    ExtendClass	target=extendsClassRepository.findOne(targetTemp.getExtendClassNo());
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
		// 获取用户的id
		// 获取用户的id
		Integer sellerID = (Integer) session.getAttribute("USER_ID");

		// 返回用户所拥有的sku属性
		Set<SKU_Attribute> sellerAttr = sellerService.paraserSKUattr(sellerID);
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

		// 这个方法还没有测试！也还没有写ajax！
		// 测试，是否得到了这个属性值经过测试，是可以的
		// System.out.println("********"+returnValues);
		// 返回这个结果集
		return returnValues;
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addShopProduct(@RequestParam("sellerId") Integer sellerId,
			@RequestParam("productName") String productName,
			@RequestParam("stock") Double stock,
			@RequestParam("state") Integer state,
			@RequestParam("imag") MultipartFile imag,
			@RequestParam("memberPrice") Double memberPrice,
			@RequestParam("barginPrice") Double barginPrice,
			HttpServletRequest request, HttpSession session) throws IOException {

		List<String> areadyNames = new ArrayList<String>(Arrays.asList(
				"sellerId", "productName", "stock", "state", "imag",
				"memberPrice", "barginPrice","skuvalus"));
         
		 
		
		// 把参数的一个Map，添加到map
		Map<String, Object> valuesMap = new HashMap<String, Object>();
		valuesMap.put("sellerId", sellerId);
		valuesMap.put("productName", productName);
		valuesMap.put("stock", stock);
		valuesMap.put("state", state);
		valuesMap.put("imag", imag);
		valuesMap.put("memberPrice", memberPrice);
		valuesMap.put("barginPrice", barginPrice);

		// 这个List用来存商品的描述明细
		List<String> detailValues = new ArrayList<String>();
		//这个list用来存储sku的描述明细
		List<String> skuDetailValues=new ArrayList<String>();

		// 得到请求参数的map
		Map<String, String[]> params = request.getParameterMap();
		// 遍历请求参数
		for (String key : params.keySet()) {
			String value = params.get(key)[0];
			// 剔除已经获取的参数，需要得到不确定个数的参数
			//这里，当初没有考虑好，现在的情况是现在传入了多种类型的参数，所以要对参数进行筛选，分类:
			if (areadyNames.contains(key)) {
				continue;
			}
			//进行判断
			if(key.startsWith("skuValue")){
				skuDetailValues.add(value);
			}else{
				detailValues.add(value);
			}

		}
		// 检查参数是否获取正确 ,经过检查，这些参数都完美的显示
		//System.out.println("skuDetailValues:"+skuDetailValues);
		//System.out.println("detailValues:"+detailValues);

		// 为了节省查询，就在此处，把这个店家的商品全部查出，暂且不考虑效率的问题，查出后放入map中；
		// 调用servcie，把商品的相关信息加入到数据库,并且获取该店铺所有的店铺商品
		Map<ShopProduct, ProductManagerDetail> products = sellerService
				.addShopProduct(valuesMap, detailValues,skuDetailValues);
		
		

		// 存入session
		session.setAttribute("products", products);

		// 注意这里要用重定向到目标页面，以免出现商品重复添加的问题
		return "redirect:seller/s_main.jsp";
	}

	/* 添加用户自定义SUKvalue方法 */
	@RequestMapping(value = "/addDefineSUKValue", method = RequestMethod.POST)
	public String addUserDefineSUKValue(HttpServletRequest request) {
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {

			String paramName = (String) paramNames.nextElement();

			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					System.out.println("参数：" + paramName + "=" + paramValue);
					 
					// 添加数据库中
					if (paramName == null || paramValue == null) {
						//
					} else {
						// 添加
						SKU_AttributeValue attributeValue = new SKU_AttributeValue();

						attributeValue.setValue(paramValue);
						// 把这个添加到数据库
						sellerService.addSkUAttrValue(attributeValue,
								Integer.parseInt(paramName));
					}
				}
			} else {
				// 代表卖家同时添加了两个以上的值
				List<String> valueString = new ArrayList<String>();
				for (String str : paramValues) {
					valueString.add(str);
					// 添加
					SKU_AttributeValue attributeValue = new SKU_AttributeValue();

					attributeValue.setValue(str);
					// 把这个添加到数据库
					sellerService.addSkUAttrValue(attributeValue,
							Integer.parseInt(paramName));
					System.out.println("参数：" + paramName + "=" + str);
				}
			}

		}

		return "redirect:seller/s_main.jsp";
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/addDefineSUK", method = RequestMethod.POST)
	public String addUserDefineSUK(HttpServletRequest request,HttpSession session) {
		// 获取两个重要的参数

		String[] tempExtName = request.getParameterValues("ExtName");

		String[] tempSKUAttr = request.getParameterValues("SKUAttr");
		
		//取出当前用户的名字
		 Seller seller= (Seller) session.getAttribute("USER_IDENTITY");

		// 根据小类的名字找出小类的id
		ExtendClass extendClass = sellerService.findExtByName(tempExtName[0]);
		
		Set<ExtendClass> classes=new HashSet<ExtendClass>();
		classes.add(extendClass);

		for (String str : tempSKUAttr) {
			//创建新的attr
			SKU_Attribute attr=new SKU_Attribute();
			attr.setName(str);
			//建立与小类的管理关系
			attr.setExtendClasses(classes);
			//建一个伪值，让属性至少有一个值，等用户添加之后将其删除
			SKU_AttributeValue value=new SKU_AttributeValue();
			value.setValue("属性值占位符");
			value.setAttribute(attr);
			
			
			//建立与seller的关联关系
			Seller sellertihs=sellerRepository.findOne(seller.getSellerNo());
			attr.setSeller(sellertihs);
			
			
			//保存
			sellerService.saveDefineSKUAttr(attr);
			
			sellerService.addSkUAttrValue(value);
			 
			 
		}

		// SKUAttr ExtName
		return "a.jsp";
	}
	
	/* 
	 * 添加同类商品的一种方法
	 */
	@RequestMapping(value="/addSimilarClass" ,method=RequestMethod.POST)
	public String addSimilarClass(HttpServletRequest request,HttpSession  session ,
			@RequestParam("simImage") MultipartFile imag){
		String[] parms=request.getParameterValues("skuValue");
		String similarClassName=request.getParameter("simClassName");
		String lowestPrice=request.getParameter("lowestPrice");
		
		//获取图片,存入服务器
		String imagPath=null;
		try {
			imagPath=sellerService.uploadImg(imag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//System.out.println("simClassName :"+similarClassName);
		//这里本来要对用户名进行检查，但是现在没有时间了
		//sku_DetailRepostitory
		 SimilarClass similarClass=new SimilarClass();
		 //设置同类名字
		 similarClass.setSimilarClassName(similarClassName);
		 //设置最低价格
		 similarClass.setLowestPrice(Double.parseDouble(lowestPrice));
		 
		 //设置总的图片路径
		 similarClass.setImgPath(imagPath);
		 
		 Set<SKU_AttributeValueDetail> details=new HashSet<SKU_AttributeValueDetail>();
		 
		
		for(String str:parms){
			//测试通过
			//System.out.println(str);
			//添加明细
			 SKU_AttributeValueDetail detail=new SKU_AttributeValueDetail();
			 //取出对应的值
			 SKU_AttributeValue sKUvalue= skuAttrValueRepository.findOne(Integer.parseInt(str));
			 
			 //加入明细
			 detail.setSKUvalue(sKUvalue);
			 detail.setSimilarClass(similarClass);
			 //把明细加入集合
			 details.add(detail);
		}
		
		Seller seller=(Seller) session.getAttribute("USER_IDENTITY") ;
		//取出卖家，跟卖家发生联系
		similarClass.setSeller(seller);
		
		//把明细加入同类
		similarClass.setSKU_details(details);
		//保存
		similarClassRepostitory.save(similarClass);
		
		//保存明细
		sku_DetailRepostitory.save(details);
		
		
		
		//重定向到登录，为的是刷新到session中
		String email=seller.getEmail();
		String password=seller.getPassword();

	   return "redirect:/s_login?email="+email+"&"+"password="+password;
	}
	
	
	
	
	
	
}
