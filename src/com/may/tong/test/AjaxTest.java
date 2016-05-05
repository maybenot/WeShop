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

	// ��̬����
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
		// ��ȡ�û�ѡ���С������
		String extName = request.getParameter("eca");
		extName = new String(extName.getBytes("ISO-8859-1"), "UTF-8");

		// ȡ����С�����ƶ�Ӧ�� �������ԣ��磺��ɫ��ȫ�£��ȵ�
		ExtendClass target = null;
		for (ExtendClass temp : exts) {
			if (temp.getExtendClassName().equals(extName)) {
				// �õ�Ŀ���һ��extendsClass
				target = temp;
			}
		}
		
		//���巵��Map��һ�����ݽṹ
		Map<String,Map<Integer,String>> returnValues=new HashMap<String, Map<Integer,String>>();
		
		
		// �õ�����Ϣ��Ŀ��С��
		if (target != null) {
			// �õ�����Ӧ��һ����������
			Set<DescribeDetail> describeDetails = target.getDescribeDetails();
			// �������������ԣ��õ�����һ����������
		 	for (DescribeDetail temp : describeDetails) {
		 		//�õ������������Ժ�
				// �õ�����һ������ֵ��
				AttributeValue attrvalue = temp.getAttributeValue();
				// �õ�������������
				DescribeAttribute descriAttr = attrvalue.getDescribeAttribute();
				// �õ�����Ӧ����������
				String attrName = descriAttr.getDescribeAttributeName();
				
				//��ȡϸ�ڵ�������
				Integer describeNo = temp.getDescribeDetailNo();
				// ��ȡ���ֵ�һ����������ֵ
				String valueName = attrvalue.getAttributeValueName();
				
				//�жϣ�����ֵ�������Ƿ��Ѿ������������
				if(returnValues.containsKey(attrName)){
					//������ڣ�ȡ�����ϵ�map
					Map<Integer, String> valueMap=returnValues.get(attrName); 
					valueMap.put(describeNo, valueName);
				}else{
					//��������ڣ������
					Map<Integer, String> values = new HashMap<Integer, String>();
					values.put(describeNo, valueName);
					//���뵽��Ӧ��map��
					returnValues.put(attrName, values);
				}

			} 
		}
		
		//���ԣ������ķ������
		//System.out.println(returnValues);
		 
		 //������������
		return returnValues;
	}

	@RequestMapping("to_test")
	public String test_ajax2(Map<String, Object> map) {
		// ׼��С���
		exts = extendsClassRepository.findAll();
		map.put("exts", exts);
		return "test.jsp";
	}

}
