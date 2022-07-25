package com.korea.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.BasketLineDAO;
import util.MyCommon;
import vo.ItemVO;
import vo.UsersVO;
@Controller
public class BasketLineController {
	
	
	BasketLineDAO basketline_dao;
	
	public void setBasketline_dao(BasketLineDAO basketline_dao) {
		this.basketline_dao = basketline_dao;
	}
	
	//장바구니 페이지 이동
	@RequestMapping("payment.do")
	public String basket_list(Model model, HttpServletRequest request ,ItemVO vo) {
		
		String user_id = request.getParameter("user_id");
		
		vo.setUser_id(user_id);
		
		List<ItemVO> list = basketline_dao.payment(vo);
		ItemVO ivo = basketline_dao.payment_all(vo);
		UsersVO uvo = basketline_dao.select_user(vo); 
					
		model.addAttribute("list",list);
		model.addAttribute("ivo",ivo);
		model.addAttribute("uvo",uvo); 
		
		return MyCommon.VIEW_PATH+"basket_line.jsp";
	}
	
	//장바구니 값 삭제
	@RequestMapping("basket_out.do")
	public String basket_out(Model model, HttpServletRequest request ,ItemVO vo) {
		
		String user_id = request.getParameter("user_id");
		vo.setUser_id(user_id);
		
		int res = basketline_dao.basket_out(vo);
		List<ItemVO> list = basketline_dao.payment(vo);
		ItemVO ivo = basketline_dao.payment_all(vo);
		
		model.addAttribute("list",list);
		model.addAttribute("ivo",ivo);
		
		return MyCommon.VIEW_PATH+"basket_line.jsp";
	}
	
	//장바구니 수량 증가
	@RequestMapping("basket_plus.do")
	public String basket_plus(Model model, HttpServletRequest request ,ItemVO vo) {
		
		String user_id = request.getParameter("user_id");
		vo.setUser_id(user_id);
		
		int res = basketline_dao.basket_plus(vo);
		List<ItemVO> list = basketline_dao.payment(vo);
		ItemVO ivo = basketline_dao.payment_all(vo);
		
		model.addAttribute("list",list);
		model.addAttribute("ivo",ivo);
		
		return MyCommon.VIEW_PATH+"basket_line.jsp";
	}
	
	//장바구니 수량 감소
	@RequestMapping("basket_minus.do")
	public String basket_minus(Model model, HttpServletRequest request ,ItemVO vo) {
		
		String user_id = request.getParameter("user_id");
		vo.setUser_id(user_id);
		
		int res = basketline_dao.basket_minus(vo);
		List<ItemVO> list = basketline_dao.payment(vo);
		ItemVO ivo = basketline_dao.payment_all(vo);
		
		model.addAttribute("list",list);
		model.addAttribute("ivo",ivo);
		
		return MyCommon.VIEW_PATH+"basket_line.jsp";
	}
	
	//장바구니 비우기
	@RequestMapping("del_all.do")
	public String del_all(Model model, HttpServletRequest request ,ItemVO vo) {
		
		String user_id = request.getParameter("user_id");
		vo.setUser_id(user_id);
		
		int res = basketline_dao.basket_delete(vo);
		List<ItemVO> list = basketline_dao.payment(vo);
		ItemVO ivo = basketline_dao.payment_all(vo);
		
		model.addAttribute("list",list);
		model.addAttribute("ivo",ivo);
		
		return MyCommon.VIEW_PATH+"basket_line.jsp";
	}
	
	//결제 완료 시
	@RequestMapping("order_items.do")
	public String order_items(Model model, HttpServletRequest request ,ItemVO vo) {
		
		String user_id = request.getParameter("user_id");
		vo.setUser_id(user_id);
		
		int result = basketline_dao.order_insert(vo);
		int res = basketline_dao.basket_delete(vo);
		List<ItemVO> list = basketline_dao.payment(vo);
		ItemVO ivo = basketline_dao.payment_all(vo);
		
		model.addAttribute("list",list);
		model.addAttribute("ivo",ivo);
		
		return MyCommon.VIEW_PATH+"basket_line.jsp";
	}
	
	//사용자 주문 확인 페이지
	@RequestMapping("order_page.do")
	public String order_page(Model model, HttpServletRequest request ,ItemVO vo) {
		
		String user_id = request.getParameter("user_id");
		
		vo.setUser_id(user_id);
		
		List<ItemVO> list = basketline_dao.order_page(vo);
		
		model.addAttribute("list",list);

		return MyCommon.VIEW_PATH+"basket_status.jsp";
	}
	
}
