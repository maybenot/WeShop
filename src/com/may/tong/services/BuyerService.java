package com.may.tong.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.may.tong.enetities.basic.BigClass;
import com.may.tong.enetities.repository.BigClassRepository;
import com.may.tong.enetities.repository.BuyerRepository;
import com.may.tong.enetities.services.Buyer;

//�������ҵ���ҵ���
@Service
public class BuyerService {

	@Autowired
	private BuyerRepository repository;

	@Autowired
	private BigClassRepository bigClassRepository;

	/**
	 * ��ѯ������ҵķ��� �������ڲ�ѯ����Ƿ�����Լ���ȡ��ҵĻ�����Ϣ
	 * */
	@Transactional(readOnly = true)
	public Buyer getBuyer(String userName) {
		return repository.getUserByUserName(userName);
	}

	/**
	 * ������ҵķ��� ����������ҵ�ע�� ������ﱣ֤ԭ���� �������û�������Ϣ֮ǰ���û�ʵ�����������ֻ��email��passwd
	 * ������һЩ�����Ķ���������Ϊ�丽��ֵ
	 * */

	@Transactional
	public void addBuyer(Buyer buyer) {
		repository.save(buyer);
	}

	/**
	 * ������ҵķ��� ��ɾ���ɵ���ҵ���Ϣ��Ȼ������µ���ҵ���Ϣ ����Ч����ߵģ����ǿ��ǵ�����Ĺ����ԣ����Բ�����ɾ������Ϊ�������Ϣ
	 * ���Ի��ǲ��ø��µķ��� ps���������Ҳ��û�в���
	 * */
	@Transactional
	public void updateBuyer(Buyer newBuyer) {
		if (newBuyer != null) {
			repository.saveAndFlush(newBuyer);
		}
	}

	/**
	 * �ж��û��Ƿ���ڵķ���
	 * */
	@Transactional(readOnly = true)
	public boolean nameIsExist(String userName) {

		Buyer buyer = null;
		if (userName != null) {
			buyer = repository.getUserByUserName(userName);
		}
		if (buyer != null) {
			return true;
		}
		return false;

	}

	// װ�������һ������
	public List<BigClass> prepredBigClass() {
		// ׼�������
		List<BigClass> bgc = bigClassRepository.findAll();

		return bgc;
	}

}
