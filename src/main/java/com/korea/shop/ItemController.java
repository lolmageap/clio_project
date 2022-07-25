package com.korea.shop;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.ItemDAO;
import util.MyCommon;
import vo.ItemVO;

@Controller
public class ItemController {
	ItemDAO item_dao;

	public void setItem_dao(ItemDAO item_dao) {
		this.item_dao = item_dao;
	}

	// 로그인시 가는 메인 페이지
	@RequestMapping("/item_list.do")
	public String item_list(Model model) {
		List<ItemVO> list = item_dao.selectList();
		List<ItemVO> list2 = item_dao.select_top();
		List<ItemVO> list3 = item_dao.select_outer();
		List<ItemVO> list4 = item_dao.select_pants();
		List<ItemVO> list5 = item_dao.select_jewelry();

		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("list4", list4);
		model.addAttribute("list5", list5);

		return MyCommon.VIEW_PATH + "main.jsp";
	}

	// 로그인 하기전 제약이 많은 뷰페이지
	@RequestMapping(value = { "/", "view.do" })
	public String item_list2(Model model) {
		List<ItemVO> list = item_dao.selectList();
		List<ItemVO> list2 = item_dao.select_top();
		List<ItemVO> list3 = item_dao.select_outer();
		List<ItemVO> list4 = item_dao.select_pants();
		List<ItemVO> list5 = item_dao.select_jewelry();

		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("list4", list4);
		model.addAttribute("list5", list5);

		return MyCommon.VIEW_PATH + "view.jsp";
	}

	// 검색
	@RequestMapping("item_search.do")
	public String item_search(Model model, String constraint, String item_name) {

		List<ItemVO> vo = new ArrayList<ItemVO>();

		if (item_name.contains("검정")) {
			item_name = "black";
		} else if (item_name.contains("검은")) {
			item_name = "black";
		} else if (item_name.contains("흰")) {
			item_name = "white";
		} else if (item_name.contains("하양")) {
			item_name = "white";
		} else if (item_name.contains("하늘")) {
			item_name = "skyblue";
		} else if (item_name.contains("베이지")) {
			item_name = "beige";
		} else if (item_name.contains("회색")) {
			item_name = "gray";
		} else if (item_name.contains("아이보리")) {
			item_name = "ivory";
		} else if (item_name.contains("파랑")) {
			item_name = "blue";
		} else if (item_name.contains("파란")) {
			item_name = "blue";
		} else if (item_name.contains("네이비")) {
			item_name = "navy";
		} else if (item_name.contains("차콜")) {
			item_name = "charcol";
		}

		if (constraint.equals("all")) {
			vo = item_dao.item_search1(item_name);
		} else if (constraint.equals("item_name")) {
			vo = item_dao.item_search2(item_name);
		} else if (constraint.equals("item_color")) {
			vo = item_dao.item_search3(item_name);
		}

		if (vo.isEmpty()) {
			
			String none = "검색 결과가 없습니다 다시 검색 해주세요";
			model.addAttribute("none", none);
		} else {
			model.addAttribute("vo", vo);
		}

		return MyCommon.VIEW_PATH + "search_list.jsp";
	}

	// 상품 클릭시 상품 구매 페이지 이동
	@RequestMapping("/buy_item.do")
	public String buy_item(Model model, int item_id) {

		ItemVO item_list = item_dao.buy_item(item_id);
		List<ItemVO> item_list2 = item_dao.buy_item_size(item_id);
		List<ItemVO> item_list3 = item_dao.buy_item_color(item_id);

		model.addAttribute("item_list", item_list);
		model.addAttribute("item_list2", item_list2);
		model.addAttribute("item_list3", item_list3);

		return MyCommon.VIEW_PATH + "item_show.jsp";
	}

	// 로그 아웃
	@RequestMapping("logout.do")
	public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {

		List<ItemVO> list = item_dao.selectList();
		List<ItemVO> list2 = item_dao.select_top();
		List<ItemVO> list3 = item_dao.select_outer();
		List<ItemVO> list4 = item_dao.select_pants();
		List<ItemVO> list5 = item_dao.select_jewelry();

		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		model.addAttribute("list4", list4);
		model.addAttribute("list5", list5);

		HttpSession session = request.getSession();

		session.removeAttribute("Ulist");

		return MyCommon.VIEW_PATH + "view.jsp";
	}

	// 임시 테이블에 담기
	@RequestMapping("sum_item.do")
	public String sum_item(ItemVO vo, Model model, int item_id) {

		int res = item_dao.insert_temp(vo);
		List<ItemVO> basket_list = item_dao.select_temp(vo);
		ItemVO item_list = item_dao.buy_item(item_id);
		List<ItemVO> item_list2 = item_dao.buy_item_size(item_id);
		List<ItemVO> item_list3 = item_dao.buy_item_color(item_id);

		model.addAttribute("item_list", item_list);
		model.addAttribute("item_list2", item_list2);
		model.addAttribute("item_list3", item_list3);
		model.addAttribute("basket_list", basket_list);

		return MyCommon.VIEW_PATH + "item_show.jsp";

	}

	// 수량 없이 담기 눌렀을 때 값 가져오기
	@RequestMapping("sum_item2.do")
	public String sum_item2(ItemVO vo, Model model, int item_id) {

		List<ItemVO> basket_list = item_dao.select_temp(vo);
		ItemVO item_list = item_dao.buy_item(item_id);
		List<ItemVO> item_list2 = item_dao.buy_item_size(item_id);
		List<ItemVO> item_list3 = item_dao.buy_item_color(item_id);

		model.addAttribute("item_list", item_list);
		model.addAttribute("item_list2", item_list2);
		model.addAttribute("item_list3", item_list3);
		model.addAttribute("basket_list", basket_list);

		return MyCommon.VIEW_PATH + "item_show.jsp";

	}

	// 장바구니에 넣기전 임시 테이블 지우기
	@RequestMapping("del_bas.do")
	public String del_bas(ItemVO vo, Model model, int item_id) {

		int res = item_dao.delete_temp(vo);
		List<ItemVO> basket_list = item_dao.select_temp(vo);
		ItemVO item_list = item_dao.buy_item(item_id);
		List<ItemVO> item_list2 = item_dao.buy_item_size(item_id);
		List<ItemVO> item_list3 = item_dao.buy_item_color(item_id);

		model.addAttribute("item_list", item_list);
		model.addAttribute("item_list2", item_list2);
		model.addAttribute("item_list3", item_list3);
		model.addAttribute("basket_list", basket_list);

		return MyCommon.VIEW_PATH + "item_show.jsp";

	}

	// 담기에서 장바구니에 넣기
	@RequestMapping("basket_in.do")
	public String basket_in(HttpServletRequest request, Model model, int item_id, String user_id) {

		//	파라미터 값들을 여러개 받은 후 각각 배열에 넣고 순서대로 VO에 넣으면서 DB에 추가
		String ii[] = request.getParameterValues("item_id");
		String in[] = request.getParameterValues("item_name");
		String is[] = request.getParameterValues("item_size");
		String ic[] = request.getParameterValues("item_color");
		String ip[] = request.getParameterValues("item_price");
		String icn[] = request.getParameterValues("item_count");
		String fn[] = request.getParameterValues("file_name");

		ItemVO avo = new ItemVO();
		//	유저 아이디는 고정
		avo.setUser_id(user_id);
		
		for (int i = 0; i < is.length; i++) {
			avo.setItem_id(Integer.parseInt(ii[i]));
			avo.setItem_name(in[i]);
			avo.setItem_size(is[i]);
			avo.setItem_color(ic[i]);
			avo.setItem_price(Integer.parseInt(ip[i]));
			avo.setItem_count(Integer.parseInt(icn[i]));
			avo.setFile_name(fn[i]);

			int res = item_dao.insert_basket(avo);
			int res2 = item_dao.delete_temp(avo);
		}
		
		ItemVO item_list = item_dao.buy_item(item_id);
		List<ItemVO> item_list2 = item_dao.buy_item_size(item_id);
		List<ItemVO> item_list3 = item_dao.buy_item_color(item_id);
		model.addAttribute("item_list" , item_list);
		model.addAttribute("item_list2", item_list2);
		model.addAttribute("item_list3", item_list3);

		return MyCommon.VIEW_PATH + "item_show.jsp";

	}

}
