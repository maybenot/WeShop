package com.may.tong.handlers;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.may.tong.enetities.basic.ProductUserDefine;
import com.may.tong.enetities.services.Seller;
import com.may.tong.services.MainService;
import com.may.tong.utils.FileGeter;

@Controller
public class MainHandler {

	@Autowired
	private MainService mainService;

	@Autowired
	private ServletRequest srequest;
	
	
	
	

	@RequestMapping(value = "/receivePage")
	public @ResponseBody
	Map<String, Object> getSpyPage(
			@RequestParam(value = "targetPage", required = true) Integer targetPage,
			HttpSession session) {
		// targetPage目标页面 获取正确

		Map<String, Object> resultMap = mainService.getPage(targetPage, 6);

		// 获取数据，该存入session的存入sesison，然后把分页信息返回
		Integer totalPages = (Integer) resultMap.get("totalPage");

		// 存入总的页面数
		session.setAttribute("totalPages", totalPages);
		// 存入当前页面数
		session.setAttribute("currentPage", targetPage);

		return resultMap;
	}

	// 添加商品详情页的编辑器
	@RequestMapping(value = "/recPdtDetail")
	public String config(@RequestParam(value="shopProductNo")Integer shopProductNo,
			HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String content = request.getParameter("content");
		// System.out.println(content);

		// 因为目前路径不是很清楚，所以手动添加路径
		// 获取项目的根路径,无奈之举,调试的时候发现了一点问题，就是eclipse跟文件之间的关系，
		// 应该是项目运行的时候eclipse托管了了项目，所以文件的相对路径对视对于eclipse来的，
		// 所以，这里的复制文件相当于同步？
		String path = srequest.getRealPath("ueditor/img") + "/";

		// 获取stupid的富文本编辑器上传的图片
		String imgPath = "E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\WeShop_SE\\ueditor\\img";
		// 获取文本编辑器传入的图片
		FileGeter fileGeter = new FileGeter();
		// 复制文件
		fileGeter.copyFolder(imgPath, path);
		// 删除原来的文件
		// fileGeter.deleteAll(new File(imgPath));

		 //保存富文本编辑器
		ProductUserDefine productUserDefine=new ProductUserDefine();
		productUserDefine.setContent(content);
		mainService.saveContent(productUserDefine);
		
		Seller seller=(Seller) session.getAttribute("USER_IDENTITY");
		String email=seller.getEmail();
		String password=seller.getPassword();

		//为了更新用户的商品状态，所以重定向到登录的东西，这个东西容易被session欺骗
		return "redirect:/s_login?email="+email+"&password="+password;

	}
}
