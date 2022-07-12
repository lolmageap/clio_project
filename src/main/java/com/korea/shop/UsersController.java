package com.korea.shop;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.UsersDAO;
import util.MyCommon;
import vo.ItemVO;
import vo.UsersVO;

@Controller
public class UsersController {
	
	@Autowired
	ServletContext application;
	
	@Autowired 
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;

	UsersDAO users_dao;
	
	public UsersController() {
		System.out.println("--controller 생성자--");
	}
	
	public void setUsers_dao(UsersDAO users_dao) {
		this.users_dao = users_dao;
	}
	
	
	//회원가입 페이지 이동
	@RequestMapping("/users_list.do") 
	public String users_list(Model model) {
		
		List<UsersVO> list = users_dao.selectList();
		model.addAttribute("list", list);
		
		return MyCommon.VIEW_PATH + "users_list.jsp";
	}
	
	//메인 페이지로 전환
	@RequestMapping("/main.do")
	public String modify_form(Model model) {
		List<UsersVO> vo = users_dao.selectList(); 
					
		model.addAttribute("vo",vo);
					
		return MyCommon.VIEW_PATH + "main.jsp";
	}
	
	 //아이디 중복 확인
	   @RequestMapping("/id_check.do") //user_id=aaaa
	   @ResponseBody
	   public String id_check(UsersVO vo) {
		  
		  UsersVO par = users_dao.id_check(vo);
		  
	      String result = "no";
	      
	      if(par != null) {
				result="yes";
	      }
			
	      String finRes= String.format("[{'result':'%s'}]",result);
			
	      return finRes;
	   }

	//가입하기 (DB연동) 
	@RequestMapping("/users_insert.do") //id=aaaa&pwd=1111&name=홍길동....
	public String insert1(Model model, UsersVO vo, String user_addr1, String user_addr2, String user_addr3) {
		
		vo.setUser_addr(user_addr1 + user_addr2 + user_addr3);
		
		List<UsersVO> list = users_dao.insert_list(vo);
		
		model.addAttribute("list", list);
		
		//포워딩(도착하는 지점)
		return MyCommon.VIEW_PATH + "users_insert.jsp";
		
	}

	@RequestMapping("/log_in.do")
	@ResponseBody
	public String log_in(UsersVO vo, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		
		UsersVO Ulist = users_dao.log_in(vo);
		
	    String result = "no";
	      
	    if(Ulist != null) {
			result="yes";
	    }
	    
		if(Ulist.getUser_id().equals("aaaa")) {
			Ulist.setUser_data("<i style=\"position: fixed;\" onclick=\"location.href='manager_list.do'\" class=\"gear fa-solid fa-gear fa-2x\"></i>");			
		}
	    
	    HttpSession session = request.getSession();
		session.setAttribute("Ulist", Ulist);
		session.setMaxInactiveInterval(3600);
		
	    String finRes= String.format("[{'result':'%s'}]",result);
			
	    return finRes;

	}
	
}
