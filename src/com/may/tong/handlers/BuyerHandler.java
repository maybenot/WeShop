package com.may.tong.handlers;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.may.tong.enetities.basic.Account;
import com.may.tong.enetities.basic.BigClass;
import com.may.tong.enetities.basic.Cart;
import com.may.tong.enetities.basic.Intersting;
import com.may.tong.enetities.basic.StarLevel;
import com.may.tong.enetities.repository.AccountRepository;
import com.may.tong.enetities.repository.CartRepository;
import com.may.tong.enetities.repository.InterstingRepository;
import com.may.tong.enetities.repository.StarLevelRepository;
import com.may.tong.enetities.services.Buyer;
import com.may.tong.enetities.services.ShopProduct;
import com.may.tong.message.ResultMessage;
import com.may.tong.services.BuyerService;
import com.may.tong.services.MainService;

@Controller
public class BuyerHandler {

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private MainService mainService;

	@Autowired
	private InterstingRepository interstingRepository;

	@Autowired
	private StarLevelRepository starLevelRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private AccountRepository accountRepository;
	private static final String useridentity = "USER_IDENTITY_INFO";

	/**
	 * ȥ���û�ע��ҳ�������
	 * */
	@RequestMapping(value = "/to_register", method = RequestMethod.GET)
	public String to_register(Map<String, Object> map) {
		// ����һ���µ�buyer���������������
		Buyer buyer = new Buyer();
		map.put("buyer", buyer);

		// ������ݱ������е���Ȥ�����������Ȥ�ǿ��Ի����
		List<Intersting> ints = interstingRepository.findAll();
		if (ints != null) {
			map.put("ints", ints);
		}
		return "register/register.jsp";
	}

	// �û���¼�Ĳ���
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "password", required = true) String password,
			HttpSession session, Map<String, Object> map) {
		Buyer buyer = null;
		// ��ȡ�û�
		buyer = buyerService.getBuyer(userName);
		if (buyer == null) {
			ResultMessage resultMessage = new ResultMessage(true, "�û����д���!");
			map.put("resultMessage", resultMessage);
			return "login/login.jsp";
		} else {
			String pass = buyer.getPassword();
			if (!pass.equals(password)) {
				ResultMessage resultMessage = new ResultMessage(true, "�������");
				map.put("resultMessage", resultMessage);
				return "login/login.jsp";
			}

		}
		// ��¼�����ض�����ҳ�����Ұ���ص���Ϣ����session��
		if (buyer.getNickName() != null) {
			session.setAttribute(useridentity, buyer.getNickName());
		} else {
			session.setAttribute(useridentity, buyer.getUserName());
		}

		// �Ѵ���Ŵ��뵽session��
		List<BigClass> bigClasses = buyerService.prepredBigClass();
		session.setAttribute("bigClasses", bigClasses);
		// ������ҳ�ķ�ҳ����,ÿҳ�̶�������Ʒ
		@SuppressWarnings("unchecked")
		List<ShopProduct> shopProducts =  (List<ShopProduct>) mainService.getPage(1, 6).get("list");
		session.setAttribute("listProducts", shopProducts);
		
		//�����ܵ�ҳ����
		 session.setAttribute("totalPages", (Integer) mainService.getPage(1, 6).get("totalPage"));
		//���뵱ǰҳ����
		session.setAttribute("currentPage", 1);
		return "redirect:/main/main.jsp";
	}

	// ����û��Ĳ���
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addBuyer(
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "nickName", required = true) String nickName,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "gender", required = false) Integer gender,
			@RequestParam(value = "inters", required = false) List<Integer> intes,
			HttpSession session) {

		Buyer buyer = initBuyer(userName, password, nickName, phone, intes,
				gender);
		// ������ʽ��ʼдע�ᱣ��Ĳ���
		buyerService.addBuyer(buyer);

		// ע��ɹ����ض�����ҳ�����Ұ���ص���Ϣ����session��
		if (buyer.getNickName() != null) {
			session.setAttribute(useridentity, buyer.getNickName());
		} else {
			session.setAttribute(useridentity, buyer.getUserName());
		}

		// �Ѵ���Ŵ��뵽session��
		List<BigClass> bigClasses = buyerService.prepredBigClass();
		session.setAttribute("bigClasses", bigClasses);
		
		// ������ҳ�ķ�ҳ����,ÿҳ�̶�������Ʒ
		@SuppressWarnings("unchecked")
		List<ShopProduct> shopProducts =  (List<ShopProduct>) mainService.getPage(1, 6).get("list");
		session.setAttribute("listProducts", shopProducts);
		
		//�����ܵ�ҳ����
		 session.setAttribute("totalPages", (Integer) mainService.getPage(1, 6).get("totalPage"));
		//���뵱ǰҳ����
		session.setAttribute("currentPage", 1);
		return "redirect:/main/main.jsp";

	}

	/**
	 * ��֤�û��Ƿ���ڣ����ؽ����������ResultMessage �ýṹ��������Ա������һ����״̬����һ������Ϣ ǰ����json�������ͽ���
	 * ps:�������������ajax�������Ҫ����responseBody.
	 * */
	@RequestMapping(value = "/isExist", method = RequestMethod.POST)
	@ResponseBody
	public ResultMessage userIsExist(
			@RequestParam(value = "userName", required = true) String userName) {
		boolean existUserName = buyerService.nameIsExist(userName);
		ResultMessage message = new ResultMessage();
		if (existUserName) {
			message.setFlag(true);
			message.setMessage("�û����Ѿ�����");
		} else {
			message.setFlag(false);
			message.setMessage("��ϲ�û�������ʹ��");
		}
		// System.out.println(message);
		return message;
	}

	/*
	 * ��û��д��������֮ǰ�����������ȥ��ҳ
	 */
	// ����һ��ȥ��ҳ�ķ���
	@RequestMapping(value = "/main")
	public String to_main(HttpSession session) {
		// �Ѵ���Ŵ��뵽session��
		List<BigClass> bigClasses = buyerService.prepredBigClass();
		session.setAttribute("bigClasses", bigClasses);

		// ������ҳ�ķ�ҳ����,ÿҳ�̶�������Ʒ
		@SuppressWarnings("unchecked")
		List<ShopProduct> shopProducts =  (List<ShopProduct>) mainService.getPage(1, 6).get("list");
		session.setAttribute("listProducts", shopProducts);
		
		//�����ܵ�ҳ����
		 session.setAttribute("totalPages", (Integer) mainService.getPage(1, 6).get("totalPage"));
		//���뵱ǰҳ����
		session.setAttribute("currentPage", 1);
		return "redirect:/main/main.jsp";
	}

	/**
	 * ȥ���û����µ�ҳ�棬�ڴ�֮ǰ����Ҫ�����ݿ��в��ҳ���صļ�¼���뵽requestScope��
	 * */
	@RequestMapping(value = "/completeUserInfo", method = RequestMethod.GET)
	public String to_completeUserInfo(
			@RequestParam(value = "userName", required = true) String userName,
			Map<String, Object> map) {
		Buyer buyer = buyerService.getBuyer(userName);
		ResultMessage message = null;
		if (buyer != null) {
			map.put("buyer", buyer);
			message = new ResultMessage(false, "");
		} else {
			// �쳣�����Ҳ�����Ӧ���û�
			message = new ResultMessage(true, "�û���Ϣ����");
		}
		map.put("message", message);
		return "completeUserInfo";
	}

	/**
	 * ��ʼ���û���method
	 * 
	 * @param gender
	 * */

	public Buyer initBuyer(String userName, String password, String nickName,
			String phone, List<Integer> ins, Integer gender) {
		Buyer buyer = new Buyer();
		buyer.setGender(gender);
		buyer.setUserName(userName);
		buyer.setPassword(password);
		buyer.setNickName(nickName);
		buyer.setPhone(phone);

		Set<Intersting> inters = new HashSet<>();
		for (int i = 0; i < ins.size(); i++) {
			Intersting inte = interstingRepository.findOne(ins.get(i));
			inters.add(inte);
		}

		// Ϊ�û�������Ȥ
		buyer.setInters(inters);

		// Ϊ�û������Ǽ�
		StarLevel starLevel = starLevelRepository.findOne(1);

		buyer.setLevel(starLevel);

		// Ϊ�û�����һ�����ﳵ
		Cart cart = new Cart();
		cartRepository.save(cart);
		buyer.setCart(cart);

		// Ϊ�û�����һ���˻�

		Account account = new Account();
		account.setBalance(100);
		accountRepository.save(account);
		account.setBuyer(buyer);
		buyer.setAccount(account);

		return buyer;
	}

}
