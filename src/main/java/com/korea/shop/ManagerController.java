package com.korea.shop;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import dao.ManagerDAO;
import util.MyCommon;
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
	
	//관리자 페이지로 이동
	@RequestMapping("manager_list.do")
	public String list(Model model) {
		List<ManagerVO> list = manager_dao.selectList();
		
		model.addAttribute("list", list);
		 
		return MyCommon.VIEW_PATH+"manager_list.jsp";
	}
	
	//등록 페이지로 이동
	@RequestMapping("manager_insert_form.do")
	public String insert_form() {
		return MyCommon.VIEW_PATH+"manager_insert_form.jsp";
	}
	
	//수정 페이지로 이동
	@RequestMapping("manager_update_form.do")
	public String update_form() {
		return MyCommon.VIEW_PATH+"manager_update_form.jsp";
	}
	
	//수정
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
	
	//삭제 페이지로 이동
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
	
	//사이즈 등록
	@RequestMapping("size_insert.do")
	public String size_insert(ManagerVO vo) {
		
		int res = manager_dao.size_insert(vo);
		
		return "redirect:manager_list.do";
	}
	
	//컬러 등록
	@RequestMapping("color_insert.do")
	public String color_insert(ManagerVO vo) {
		
		int res = manager_dao.color_insert(vo);
		
		return "redirect:manager_list.do";
	}
	
	// 주문 현황
	@RequestMapping("manager_basket_list.do")
	public String manager_basket_list(Model model) {
		
		OrderVO vo = new OrderVO();
		
		List<OrderVO> m_b_list = manager_dao.select_order();
		
		//리스트에서 & 값을 공백으로 전환
		//이 작업을 하지않으면 "04583 & 서울 중구 난계로 125 & 떡볶이" 이런식으로 값이 출력됨
		for(int i = 0 ; i < m_b_list.size(); i++){	
		String res = m_b_list.get(i).getUser_addr();
		String a = res.substring(res.indexOf("&")+1);
		
		String addr1 = res.substring(0,res.indexOf("&"));
		String addr2 = a.substring(0,a.indexOf("&"));
		String addr3 = a.substring(a.indexOf("&")+1);
		String addr = addr1 + " " + addr2 + " " + addr3;
		m_b_list.get(i).setUser_addr(addr);
		}
		
		try {
			
			for(int i = 0 ; i < m_b_list.size(); i++){	
			
				//조회된 주문 날짜가 최신 순으로 정렬되어있다
				//정렬 되어 있는 리스트에서 날짜가 바뀔 때 리스트에 인덱스 add
					
					if(!m_b_list.get(i).getOrder_date().equals(m_b_list.get(i+1).getOrder_date())) {
						
						//이렇게 하면 다음 날짜에 다음이라는 글이 들어간 vo를 list에 추가함 (인덱스 역할)
						//오늘 올라온 것만 이제 다음이라는 글을 넣기 (최신 날짜추가)
						vo.setUser_email("no");
						m_b_list.add(i+1,vo);
						
				}
			}
		} catch (Exception e) {
			
		}
		
			model.addAttribute("m_b_list", m_b_list);
		
		return MyCommon.VIEW_PATH+"manager_basket_list.jsp";
	}
	
}