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
 * �������������������һ��handler
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

	// ��̬����
	private static List<ExtendClass> exts = null;

	/**
	 * �����û�ע���һ������
	 * */
	@RequestMapping("/regist")
	public String regist(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "shopName", required = true) String shopName,
			@RequestParam(value = "shopOwer", required = true) String shopOwer,
			@RequestParam(value = "tel", required = true) String tel,
			HttpSession session) {
		// װ��ñ�Ҫ������
		Seller seller = new Seller(shopName, email, shopOwer, password, tel);
		sellerService.sellerRegist(seller);
		// ���û�����Ϣ����session
		session.setAttribute("USER_IDENTITY", seller);
		// ���ҵ�id����session
		session.setAttribute("USER_ID", seller.getSellerNo());

		// ��ȡ��Ʒ��״̬
		List<State> states = sellerService.getProductStates();

		// �������Ʒ��״̬����session
		session.setAttribute("productStates", states);

		// ȡ���������Զ������Ʒͬ��,��������
		Set<SimilarClass> similarClasses = seller.getSimilarClasses();
		session.setAttribute("similarClasses", similarClasses);

		// ׼��С��
		if (exts == null) {
			exts = sellerService.prepredExtendsClass();
		}
		session.setAttribute("exts", exts);
		return "redirect:seller/s_main.jsp";
	}

	/**
	 * �û���¼��һ������
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

				// ���ҵ�id����session
				session.setAttribute("USER_ID", seller.getSellerNo());

				// ׼��С��
				if (exts == null) {
					exts = sellerService.prepredExtendsClass();
				}
				session.setAttribute("exts", exts);

				// ��ȡ��Ʒ��״̬
				List<State> states = sellerService.getProductStates();

				// �������Ʒ��״̬����session
				session.setAttribute("productStates", states);

				// ȡ��������̵���Ʒ����������̵���Ʒ����session��
				Map<ShopProduct, ProductManagerDetail> products = sellerService
						.getThisShopPrducts(seller.getSellerNo());

				// ��������̵���Ʒ����session
				session.setAttribute("products", products);

				// ȡ���������Զ������Ʒͬ��,��������
				Set<SimilarClass> similarClasses = seller.getSimilarClasses();
				session.setAttribute("similarClasses", similarClasses);

				return "redirect:/seller/s_main.jsp";
			}
			ResultMessage message = new ResultMessage(true, "�������");
			map.put("message", message);
			return "/seller/login.jsp";
		} else {
			ResultMessage message = new ResultMessage(true, "�û��������ڣ�");
			map.put("message", message);
			return "/seller/login.jsp";
		}

	}

	/**
	 * �û������Ʒ��һ������������ajax���󣬷���һ����Ʒ��һ�������������
	 * */

	@RequestMapping(value = "/returnDescri")
	@ResponseBody
	public  Map<String,Map<String, Map<Integer, String>>> test(HttpServletRequest request,HttpSession session,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// ��ȡ�û�ѡ���С������
		String extName = request.getParameter("eca");
		extName = new String(extName.getBytes("ISO-8859-1"), "UTF-8");
		// ȡ����С�����ƶ�Ӧ�� �������ԣ��磺��ɫ��ȫ�£��ȵ�
		ExtendClass target1 = null;
		for (ExtendClass temp : exts) {
			if (temp.getExtendClassName().equals(extName)) {
				// �õ�Ŀ���һ��extendsClass
				target1 = temp;
			}
		}
		
		Map<String,Map<String, Map<Integer, String>>> realReturn=new HashMap<String, Map<String,Map<Integer, String>>>();
		
		// ���巵��Map��һ�����ݽṹ
		Map<String, Map<Integer, String>> returnValues = new HashMap<String, Map<Integer, String>>(); 
		
		 
		
		ExtendClass target=null;
		// �õ�����Ϣ��Ŀ��С��
		if (target1 != null) {
			// �õ�����Ӧ��һ����������
			//����Ӧ�������»�ȡһ��С�࣬Ҫ��Ȼ�л���
			extendsClassRepository.flush();
			 target=extendsClassRepository.findOne(target1.getExtendClassNo());
			extendsClassRepository.flush();
			
			
			Set<DescribeDetail> describeDetails = target.getDescribeDetails();
			// �������������ԣ��õ�����һ����������
			for (DescribeDetail temp : describeDetails) {
				// �õ������������Ժ�
				// �õ�����һ������ֵ��
				AttributeValue attrvalue = temp.getAttributeValue();
				// �õ�������������
				DescribeAttribute descriAttr = attrvalue.getDescribeAttribute();
				// �õ�����Ӧ����������
				String attrName = descriAttr.getDescribeAttributeName();

				// ��ȡϸ�ڵ�������
				Integer describeNo = temp.getDescribeDetailNo();
				// ��ȡ���ֵ�һ����������ֵ
				String valueName = attrvalue.getAttributeValueName();

				// �жϣ�����ֵ�������Ƿ��Ѿ������������
				if (returnValues.containsKey(attrName)) {
					// ������ڣ�ȡ�����ϵ�map
					Map<Integer, String> valueMap = returnValues.get(attrName);
					valueMap.put(describeNo, valueName);
				} else {
					// ��������ڣ������
					Map<Integer, String> values = new HashMap<Integer, String>();
					values.put(describeNo, valueName);
					// ���뵽��Ӧ��map��
					returnValues.put(attrName, values);
				}

			}
		}

		// ���ԣ������ķ������
		// System.out.println(returnValues);
		
		
		 
		
		//��ȡ�û���id
	    Integer sellerID= (Integer) session.getAttribute("USER_ID");

          //�Ѷ�Ӧ��sku���Է��뵽map��
	   //  Map<String ,Map<Integer,String>> skuAttr=sellerService.prepredSKUDetail(null, extName, sellerID);
	     //�ҵ���ƷС���Ӧ������ͬ��
	    Map<Integer ,String>  similarClass=sellerService.preperdSimilarClass(target);
	    realReturn.put("descAttr", returnValues);
	    
	    Map<String ,Map<Integer,String>> similar=new HashMap<String, Map<Integer,String>>();
	    similar.put("simi", similarClass);
	    
	    realReturn.put("similarClass",  similar);
		// ������������
		return realReturn;
	}

	/**
	 * �û����SKU��һ������������������û���������������ƣ����ǲ�ͬ���ǣ������Դ�кܶ�
	 * 
	 * */

	@RequestMapping(value = "/SKU_Descri")
	@ResponseBody
	public Map<String, Map<Integer, String>> return_sku(
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// ��ȡ�û�ѡ���С������
		String extName = request.getParameter("skuEClass");
		extName = new String(extName.getBytes("ISO-8859-1"), "UTF-8");

		// ��ȡ�û���id
	    Integer sellerID = (Integer) session.getAttribute("USER_ID");
	    
	     //���÷���
	    Map<String, Map<Integer, String>> returnValues=  
	    		sellerService.prepredSKUDetail(null,extName, sellerID);

		// ���������û�в��ԣ�Ҳ��û��дajax��
		// ���ԣ��Ƿ�õ����������ֵ�������ԣ��ǿ��Ե�
		// System.out.println("********"+returnValues);
		// ������������
		return returnValues;
	}
	/**
	 * �û������Ʒʱ��˵ʹ�õķ���desc
	 * */
	@RequestMapping(value = "/addShopProductSku")
	@ResponseBody
	public Map<String, Map<Integer, String>> return_addShopProsku(
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// ��ȡ�û�ѡ���С������
		String simclass = request.getParameter("simclass");
		simclass = new String(simclass.getBytes("ISO-8859-1"), "UTF-8");
		
		 
		//���÷���
		Map<String, Map<Integer, String>> returnValues=  
				sellerService.preparedAddShopSKU(simclass);
		return returnValues;
	}

	/**
	 * �Զ���SKU��һ������������������û���������������ƣ����ǲ�ͬ���ǣ������Դ�кܶ�
	 * 
	 * */

	@RequestMapping(value = "/SKU_UserDefine")
	@ResponseBody
	public Map<String, Map<Integer, String>> return_UserDefinesku(
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// ��ȡ�û�ѡ���С������
		String extName = request.getParameter("skuEClass");
		extName = new String(extName.getBytes("ISO-8859-1"), "UTF-8");

		ExtendClass targetTemp = null;
		for (ExtendClass temp : exts) {
			if (temp.getExtendClassName().equals(extName)) {
				// �õ�Ŀ���һ��extendsClass
				targetTemp = temp;
			}
		}
		// ���巵��Map��һ�����ݽṹ
		Map<String, Map<Integer, String>> returnValues = new HashMap<String, Map<Integer, String>>();

		// 1.��һ����Դ�� �õ�����Ϣ��Ŀ��С��
		if (targetTemp != null) {
			
			extendsClassRepository.flush();
		    ExtendClass	target=extendsClassRepository.findOne(targetTemp.getExtendClassNo());
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
		// ��ȡ�û���id
		// ��ȡ�û���id
		Integer sellerID = (Integer) session.getAttribute("USER_ID");

		// �����û���ӵ�е�sku����
		Set<SKU_Attribute> sellerAttr = sellerService.paraserSKUattr(sellerID);
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

		// ���������û�в��ԣ�Ҳ��û��дajax��
		// ���ԣ��Ƿ�õ����������ֵ�������ԣ��ǿ��Ե�
		// System.out.println("********"+returnValues);
		// ������������
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
         
		 
		
		// �Ѳ�����һ��Map����ӵ�map
		Map<String, Object> valuesMap = new HashMap<String, Object>();
		valuesMap.put("sellerId", sellerId);
		valuesMap.put("productName", productName);
		valuesMap.put("stock", stock);
		valuesMap.put("state", state);
		valuesMap.put("imag", imag);
		valuesMap.put("memberPrice", memberPrice);
		valuesMap.put("barginPrice", barginPrice);

		// ���List��������Ʒ��������ϸ
		List<String> detailValues = new ArrayList<String>();
		//���list�����洢sku��������ϸ
		List<String> skuDetailValues=new ArrayList<String>();

		// �õ����������map
		Map<String, String[]> params = request.getParameterMap();
		// �����������
		for (String key : params.keySet()) {
			String value = params.get(key)[0];
			// �޳��Ѿ���ȡ�Ĳ�������Ҫ�õ���ȷ�������Ĳ���
			//�������û�п��Ǻã����ڵ���������ڴ����˶������͵Ĳ���������Ҫ�Բ�������ɸѡ������:
			if (areadyNames.contains(key)) {
				continue;
			}
			//�����ж�
			if(key.startsWith("skuValue")){
				skuDetailValues.add(value);
			}else{
				detailValues.add(value);
			}

		}
		// �������Ƿ��ȡ��ȷ ,������飬��Щ��������������ʾ
		//System.out.println("skuDetailValues:"+skuDetailValues);
		//System.out.println("detailValues:"+detailValues);

		// Ϊ�˽�ʡ��ѯ�����ڴ˴����������ҵ���Ʒȫ����������Ҳ�����Ч�ʵ����⣬��������map�У�
		// ����servcie������Ʒ�������Ϣ���뵽���ݿ�,���һ�ȡ�õ������еĵ�����Ʒ
		Map<ShopProduct, ProductManagerDetail> products = sellerService
				.addShopProduct(valuesMap, detailValues,skuDetailValues);
		
		

		// ����session
		session.setAttribute("products", products);

		// ע������Ҫ���ض���Ŀ��ҳ�棬���������Ʒ�ظ���ӵ�����
		return "redirect:seller/s_main.jsp";
	}

	/* ����û��Զ���SUKvalue���� */
	@RequestMapping(value = "/addDefineSUKValue", method = RequestMethod.POST)
	public String addUserDefineSUKValue(HttpServletRequest request) {
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {

			String paramName = (String) paramNames.nextElement();

			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					System.out.println("������" + paramName + "=" + paramValue);
					 
					// ������ݿ���
					if (paramName == null || paramValue == null) {
						//
					} else {
						// ���
						SKU_AttributeValue attributeValue = new SKU_AttributeValue();

						attributeValue.setValue(paramValue);
						// �������ӵ����ݿ�
						sellerService.addSkUAttrValue(attributeValue,
								Integer.parseInt(paramName));
					}
				}
			} else {
				// ��������ͬʱ������������ϵ�ֵ
				List<String> valueString = new ArrayList<String>();
				for (String str : paramValues) {
					valueString.add(str);
					// ���
					SKU_AttributeValue attributeValue = new SKU_AttributeValue();

					attributeValue.setValue(str);
					// �������ӵ����ݿ�
					sellerService.addSkUAttrValue(attributeValue,
							Integer.parseInt(paramName));
					System.out.println("������" + paramName + "=" + str);
				}
			}

		}

		return "redirect:seller/s_main.jsp";
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/addDefineSUK", method = RequestMethod.POST)
	public String addUserDefineSUK(HttpServletRequest request,HttpSession session) {
		// ��ȡ������Ҫ�Ĳ���

		String[] tempExtName = request.getParameterValues("ExtName");

		String[] tempSKUAttr = request.getParameterValues("SKUAttr");
		
		//ȡ����ǰ�û�������
		 Seller seller= (Seller) session.getAttribute("USER_IDENTITY");

		// ����С��������ҳ�С���id
		ExtendClass extendClass = sellerService.findExtByName(tempExtName[0]);
		
		Set<ExtendClass> classes=new HashSet<ExtendClass>();
		classes.add(extendClass);

		for (String str : tempSKUAttr) {
			//�����µ�attr
			SKU_Attribute attr=new SKU_Attribute();
			attr.setName(str);
			//������С��Ĺ����ϵ
			attr.setExtendClasses(classes);
			//��һ��αֵ��������������һ��ֵ�����û����֮����ɾ��
			SKU_AttributeValue value=new SKU_AttributeValue();
			value.setValue("����ֵռλ��");
			value.setAttribute(attr);
			
			
			//������seller�Ĺ�����ϵ
			Seller sellertihs=sellerRepository.findOne(seller.getSellerNo());
			attr.setSeller(sellertihs);
			
			
			//����
			sellerService.saveDefineSKUAttr(attr);
			
			sellerService.addSkUAttrValue(value);
			 
			 
		}

		// SKUAttr ExtName
		return "a.jsp";
	}
	
	/* 
	 * ���ͬ����Ʒ��һ�ַ���
	 */
	@RequestMapping(value="/addSimilarClass" ,method=RequestMethod.POST)
	public String addSimilarClass(HttpServletRequest request,HttpSession  session ,
			@RequestParam("simImage") MultipartFile imag){
		String[] parms=request.getParameterValues("skuValue");
		String similarClassName=request.getParameter("simClassName");
		String lowestPrice=request.getParameter("lowestPrice");
		
		//��ȡͼƬ,���������
		String imagPath=null;
		try {
			imagPath=sellerService.uploadImg(imag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//System.out.println("simClassName :"+similarClassName);
		//���ﱾ��Ҫ���û������м�飬��������û��ʱ����
		//sku_DetailRepostitory
		 SimilarClass similarClass=new SimilarClass();
		 //����ͬ������
		 similarClass.setSimilarClassName(similarClassName);
		 //������ͼ۸�
		 similarClass.setLowestPrice(Double.parseDouble(lowestPrice));
		 
		 //�����ܵ�ͼƬ·��
		 similarClass.setImgPath(imagPath);
		 
		 Set<SKU_AttributeValueDetail> details=new HashSet<SKU_AttributeValueDetail>();
		 
		
		for(String str:parms){
			//����ͨ��
			//System.out.println(str);
			//�����ϸ
			 SKU_AttributeValueDetail detail=new SKU_AttributeValueDetail();
			 //ȡ����Ӧ��ֵ
			 SKU_AttributeValue sKUvalue= skuAttrValueRepository.findOne(Integer.parseInt(str));
			 
			 //������ϸ
			 detail.setSKUvalue(sKUvalue);
			 detail.setSimilarClass(similarClass);
			 //����ϸ���뼯��
			 details.add(detail);
		}
		
		Seller seller=(Seller) session.getAttribute("USER_IDENTITY") ;
		//ȡ�����ң������ҷ�����ϵ
		similarClass.setSeller(seller);
		
		//����ϸ����ͬ��
		similarClass.setSKU_details(details);
		//����
		similarClassRepostitory.save(similarClass);
		
		//������ϸ
		sku_DetailRepostitory.save(details);
		
		
		
		//�ض��򵽵�¼��Ϊ����ˢ�µ�session��
		String email=seller.getEmail();
		String password=seller.getPassword();

	   return "redirect:/s_login?email="+email+"&"+"password="+password;
	}
	
	
	
	
	
	
}
