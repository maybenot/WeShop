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
		// targetPageĿ��ҳ�� ��ȡ��ȷ

		Map<String, Object> resultMap = mainService.getPage(targetPage, 6);

		// ��ȡ���ݣ��ô���session�Ĵ���sesison��Ȼ��ѷ�ҳ��Ϣ����
		Integer totalPages = (Integer) resultMap.get("totalPage");

		// �����ܵ�ҳ����
		session.setAttribute("totalPages", totalPages);
		// ���뵱ǰҳ����
		session.setAttribute("currentPage", targetPage);

		return resultMap;
	}

	// �����Ʒ����ҳ�ı༭��
	@RequestMapping(value = "/recPdtDetail")
	public String config(@RequestParam(value="shopProductNo")Integer shopProductNo,
			HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String content = request.getParameter("content");
		// System.out.println(content);

		// ��ΪĿǰ·�����Ǻ�����������ֶ����·��
		// ��ȡ��Ŀ�ĸ�·��,����֮��,���Ե�ʱ������һ�����⣬����eclipse���ļ�֮��Ĺ�ϵ��
		// Ӧ������Ŀ���е�ʱ��eclipse�й�������Ŀ�������ļ������·�����Ӷ���eclipse���ģ�
		// ���ԣ�����ĸ����ļ��൱��ͬ����
		String path = srequest.getRealPath("ueditor/img") + "/";

		// ��ȡstupid�ĸ��ı��༭���ϴ���ͼƬ
		String imgPath = "E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\WeShop_SE\\ueditor\\img";
		// ��ȡ�ı��༭�������ͼƬ
		FileGeter fileGeter = new FileGeter();
		// �����ļ�
		fileGeter.copyFolder(imgPath, path);
		// ɾ��ԭ�����ļ�
		// fileGeter.deleteAll(new File(imgPath));

		 //���渻�ı��༭��
		ProductUserDefine productUserDefine=new ProductUserDefine();
		productUserDefine.setContent(content);
		mainService.saveContent(productUserDefine);
		
		Seller seller=(Seller) session.getAttribute("USER_IDENTITY");
		String email=seller.getEmail();
		String password=seller.getPassword();

		//Ϊ�˸����û�����Ʒ״̬�������ض��򵽵�¼�Ķ���������������ױ�session��ƭ
		return "redirect:/s_login?email="+email+"&password="+password;

	}
}
