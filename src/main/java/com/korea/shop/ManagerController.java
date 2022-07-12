package com.korea.shop;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import dao.ItemDAO;
import dao.ManagerDAO;
import util.MyCommon;
import vo.ItemVO;
import vo.ManagerVO;
import vo.OrderVO;

@Controller
public class ManagerController {
	
	@Autowired
	ServletContext application;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	ManagerDAO manager_dao;
	public void setManager_dao(ManagerDAO manager_dao) {
		this.manager_dao = manager_dao;
	}
	
	
	@RequestMapping("manager_list.do")
	public String list(Model model) {
		List<ManagerVO> list = manager_dao.selectList();
		
		model.addAttribute("list", list);
		 
		return MyCommon.VIEW_PATH+"manager_list.jsp";
	}
	
	//등록
	@RequestMapping("manager_insert_form.do")
	public String insert_form() {
		return MyCommon.VIEW_PATH+"manager_insert_form.jsp";
	}
	
	//수정
	@RequestMapping("manager_update_form.do")
	public String update_form() {
		return MyCommon.VIEW_PATH+"manager_update_form.jsp";
	}
	@RequestMapping("manager_update.do")
	public String update(ManagerVO vo) {
		
		String webPath = "/resources/upload/";
		String savePath = application.getRealPath(webPath);
		System.out.println(savePath);
		
		MultipartFile photo = vo.getPhoto();
		String file_name = "no_file";
		
		if(!photo.isEmpty()) {
			file_name = photo.getOriginalFilename();
			
			File saveFile = new File(savePath, file_name);
		
			if (!saveFile.exists()) {
				saveFile.mkdirs();
			}else {
				// 동명파일 방지
				long time = System.currentTimeMillis();
				file_name = String.format("%d_%s", time, file_name);
				saveFile = new File(savePath, file_name);
			}
			try {
				photo.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		vo.setFile_name(file_name);
		
		int res = manager_dao.update(vo);
		
		return "redirect:manager_list.do";
	}
	
	//삭제
	@RequestMapping("manager_delete_form.do")
	public String delete_form() {
		return MyCommon.VIEW_PATH+"manager_delete_form.jsp";
	}
	//전체 삭제
	@RequestMapping("manager_delete.do")
	public String delete(int item_id) {
		
		int res = manager_dao.delete(item_id);
		
		return "redirect:manager_list.do";
	}
	//사이즈 삭제
	@RequestMapping("size_delete.do")
	public String size_delete(int item_id) {
		
		int res = manager_dao.size_delete(item_id);
		
		return "redirect:manager_list.do";
	}
	//컬러 삭제
	@RequestMapping("color_delete.do")
	public String color_delete(int item_id) {
		
		int res = manager_dao.color_delete(item_id);
		
		return "redirect:manager_list.do";
	}
	
	
	//등록
	@RequestMapping("manager_insert.do")
	
	public String insert(ManagerVO vo) {
		int item_like = 0;
		vo.setItem_like(item_like);
		//파일업로드
		String webPath = "/resources/upload/";
		String savePath = application.getRealPath(webPath);
		System.out.println(savePath);
		
		MultipartFile photo = vo.getPhoto();
		String file_name = "no_file";
		
		if(!photo.isEmpty()) {
			file_name = photo.getOriginalFilename();
			
			File saveFile = new File(savePath, file_name);
		
			if (!saveFile.exists()) {
				saveFile.mkdirs();
			}else {
				// 동명파일 방지
				long time = System.currentTimeMillis();
				file_name = String.format("%d_%s", time, file_name);
				saveFile = new File(savePath, file_name);
			}
			try {
				photo.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		vo.setFile_name(file_name);
		//여기까지
		int res = manager_dao.insert(vo);
		
		return "redirect:manager_list.do";
	}
	
	@RequestMapping("size_insert.do")
	public String size_insert(ManagerVO vo) {
		
		int res = manager_dao.size_insert(vo);
		
		return "redirect:manager_list.do";
	}
	
	@RequestMapping("color_insert.do")
	public String color_insert(ManagerVO vo) {
		
		int res = manager_dao.color_insert(vo);
		
		return "redirect:manager_list.do";
	}
	
	@RequestMapping("manager_basket_list.do")
	public String manager_basket_list(Model model) {
		
		List<OrderVO> m_b_list = manager_dao.select_order();
		
		model.addAttribute("m_b_list", m_b_list);
		
		return MyCommon.VIEW_PATH+"manager_basket_list.jsp";
	}
	
	


}
