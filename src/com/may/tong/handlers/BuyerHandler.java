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
	 * 去往用户注册页面的请求
	 * */
	@RequestMapping(value = "/to_register", method = RequestMethod.GET)
	public String to_register(Map<String, Object> map) {
		// 创建一个新的buyer，解决表单回显问题
		Buyer buyer = new Buyer();
		map.put("buyer", buyer);

		// 查出数据表中所有的兴趣，并且这个兴趣是可以缓存的
		List<Intersting> ints = interstingRepository.findAll();
		if (ints != null) {
			map.put("ints", ints);
		}
		return "register/register.jsp";
	}

	// 用户登录的操作
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "password", required = true) String password,
			HttpSession session, Map<String, Object> map) {
		Buyer buyer = null;
		// 获取用户
		buyer = buyerService.getBuyer(userName);
		if (buyer == null) {
			ResultMessage resultMessage = new ResultMessage(true, "用户名有错误!");
			map.put("resultMessage", resultMessage);
			return "login/login.jsp";
		} else {
			String pass = buyer.getPassword();
			if (!pass.equals(password)) {
				ResultMessage resultMessage = new ResultMessage(true, "密码错误");
				map.put("resultMessage", resultMessage);
				return "login/login.jsp";
			}

		}
		// 登录功，重定向到主页，并且把相关的信息存入session中
		if (buyer.getNickName() != null) {
			session.setAttribute(useridentity, buyer.getNickName());
		} else {
			session.setAttribute(useridentity, buyer.getUserName());
		}

		// 把大类号存入到session中
		List<BigClass> bigClasses = buyerService.prepredBigClass();
		session.setAttribute("bigClasses", bigClasses);
		// 处理主页的分页内容,每页固定六个商品
		@SuppressWarnings("unchecked")
		List<ShopProduct> shopProducts =  (List<ShopProduct>) mainService.getPage(1, 6).get("list");
		session.setAttribute("listProducts", shopProducts);
		
		//存入总的页面数
		 session.setAttribute("totalPages", (Integer) mainService.getPage(1, 6).get("totalPage"));
		//存入当前页面数
		session.setAttribute("currentPage", 1);
		return "redirect:/main/main.jsp";
	}

	// 添加用户的操作
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
		// 现在正式开始写注册保存的操作
		buyerService.addBuyer(buyer);

		// 注册成功，重定向到主页，并且把相关的信息存入session中
		if (buyer.getNickName() != null) {
			session.setAttribute(useridentity, buyer.getNickName());
		} else {
			session.setAttribute(useridentity, buyer.getUserName());
		}

		// 把大类号存入到session中
		List<BigClass> bigClasses = buyerService.prepredBigClass();
		session.setAttribute("bigClasses", bigClasses);
		
		// 处理主页的分页内容,每页固定六个商品
		@SuppressWarnings("unchecked")
		List<ShopProduct> shopProducts =  (List<ShopProduct>) mainService.getPage(1, 6).get("list");
		session.setAttribute("listProducts", shopProducts);
		
		//存入总的页面数
		 session.setAttribute("totalPages", (Integer) mainService.getPage(1, 6).get("totalPage"));
		//存入当前页面数
		session.setAttribute("currentPage", 1);
		return "redirect:/main/main.jsp";

	}

	/**
	 * 验证用户是否存在，返回结果的类型是ResultMessage 该结构有两个成员变量，一个市状态嫲，一个市信息 前端用json数据类型接受
	 * ps:这个是用来处理ajax请求，因此要加入responseBody.
	 * */
	@RequestMapping(value = "/isExist", method = RequestMethod.POST)
	@ResponseBody
	public ResultMessage userIsExist(
			@RequestParam(value = "userName", required = true) String userName) {
		boolean existUserName = buyerService.nameIsExist(userName);
		ResultMessage message = new ResultMessage();
		if (existUserName) {
			message.setFlag(true);
			message.setMessage("用户名已经存在");
		} else {
			message.setFlag(false);
			message.setMessage("恭喜用户名可以使用");
		}
		// System.out.println(message);
		return message;
	}

	/*
	 * 在没有写出拦截器之前，用这个方法去主页
	 */
	// 定义一个去主页的方法
	@RequestMapping(value = "/main")
	public String to_main(HttpSession session) {
		// 把大类号存入到session中
		List<BigClass> bigClasses = buyerService.prepredBigClass();
		session.setAttribute("bigClasses", bigClasses);

		// 处理主页的分页内容,每页固定六个商品
		@SuppressWarnings("unchecked")
		List<ShopProduct> shopProducts =  (List<ShopProduct>) mainService.getPage(1, 6).get("list");
		session.setAttribute("listProducts", shopProducts);
		
		//存入总的页面数
		 session.setAttribute("totalPages", (Integer) mainService.getPage(1, 6).get("totalPage"));
		//存入当前页面数
		session.setAttribute("currentPage", 1);
		return "redirect:/main/main.jsp";
	}

	/**
	 * 去往用户更新的页面，在此之前，先要从数据库中查找出相关的记录加入到requestScope中
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
			// 异常处理，找不到相应的用户
			message = new ResultMessage(true, "用户信息出错");
		}
		map.put("message", message);
		return "completeUserInfo";
	}

	/**
	 * 初始化用户的method
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

		// 为用户设置兴趣
		buyer.setInters(inters);

		// 为用户设置星级
		StarLevel starLevel = starLevelRepository.findOne(1);

		buyer.setLevel(starLevel);

		// 为用户生成一个购物车
		Cart cart = new Cart();
		cartRepository.save(cart);
		buyer.setCart(cart);

		// 为用户设置一个账户

		Account account = new Account();
		account.setBalance(100);
		accountRepository.save(account);
		account.setBuyer(buyer);
		buyer.setAccount(account);

		return buyer;
	}

}
