package com.may.tong.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.may.tong.enetities.basic.AttributeValue;
import com.may.tong.enetities.basic.DescribeAttribute;
import com.may.tong.enetities.basic.DescribeDetail;
import com.may.tong.enetities.basic.ExtendClass;
import com.may.tong.enetities.repository.ExtendsClassRepository;

@Controller
public class AjaxTest {

	@Autowired
	private ExtendsClassRepository extendsClassRepository;

	// 静态数据
	private static List<ExtendClass> exts;

	@RequestMapping(value = "/test_ajax", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getdata(ServletRequest request) {

		@SuppressWarnings("unchecked")
		Map<String, Object> map2 = request.getParameterMap();
		for (Object key : map2.keySet()) {
			System.out.println(key + ":" + map2.get(key));
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("age", 10);
		map.put("name", "tang");
		map.put("age", 10);
		return map;
	}

	@RequestMapping(value = "/test2")
	@ResponseBody
	public Map<String, Map<Integer,String>> test(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// 获取用户选择的小类名称
		String extName = request.getParameter("eca");
		extName = new String(extName.getBytes("ISO-8859-1"), "UTF-8");

		// 取出该小类名称对应的 描述属性：如：颜色，全新，等等
		ExtendClass target = null;
		for (ExtendClass temp : exts) {
			if (temp.getExtendClassName().equals(extName)) {
				// 得到目标的一个extendsClass
				target = temp;
			}
		}
		
		//定义返回Map的一个数据结构
		Map<String,Map<Integer,String>> returnValues=new HashMap<String, Map<Integer,String>>();
		
		
		// 得到带信息的目标小类
		if (target != null) {
			// 得到它对应的一个描述属性
			Set<DescribeDetail> describeDetails = target.getDescribeDetails();
			// 遍历其描述属性，得到它的一个描述属性
		 	for (DescribeDetail temp : describeDetails) {
		 		//得到它的描述属性号
				// 得到它的一个属性值集
				AttributeValue attrvalue = temp.getAttributeValue();
				// 得到它的描述属性
				DescribeAttribute descriAttr = attrvalue.getDescribeAttribute();
				// 得到它对应的属性名字
				String attrName = descriAttr.getDescribeAttributeName();
				
				//获取细节的描述号
				Integer describeNo = temp.getDescribeDetailNo();
				// 获取文字的一个描述属性值
				String valueName = attrvalue.getAttributeValueName();
				
				//判断，返回值集合中是否已经存在这个属性
				if(returnValues.containsKey(attrName)){
					//如果存在，取出集合的map
					Map<Integer, String> valueMap=returnValues.get(attrName); 
					valueMap.put(describeNo, valueName);
				}else{
					//如果不存在，新添加
					Map<Integer, String> values = new HashMap<Integer, String>();
					values.put(describeNo, valueName);
					//加入到对应的map中
					returnValues.put(attrName, values);
				}

			} 
		}
		
		//测试：完美的符合情况
		//System.out.println(returnValues);
		 
		 //返回这个结果集
		return returnValues;
	}

	@RequestMapping("to_test")
	public String test_ajax2(Map<String, Object> map) {
		// 准备小类号
		exts = extendsClassRepository.findAll();
		map.put("exts", exts);
		return "test.jsp";
	}

}
