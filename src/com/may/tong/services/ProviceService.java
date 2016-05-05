package com.may.tong.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.may.tong.enetities.basic.Province;
import com.may.tong.enetities.repository.ProvinceRepository;

//����ʡ�е�service��
@Service
public class ProviceService {

	@Autowired
	private ProvinceRepository repository;

	/**
	 * ��ȡ���е�ʡ
	 * ���ȣ���Ϊʹ�����������ӣ������ڲ�ѯʡ��ʱ��������У�����
	 * ��Ϣȫ���õ���
	 * ���ң��������ʹ���˶������棬�����һͬһ�����󣬳��򲻻��ٷ������ݿ�
	 * ��ʹ�����ݿ�ĸ�������
	 * ps:�����������������Ч
	 * */
	public List<Province> getProvinces() {
		return repository.findAll();
	}
	
	/**
	 * ��ȡָ����ʡ���������ԣ������б��
	 */
	public Province getProvince(Integer id){
		return repository.findOne(id);
	}

}
