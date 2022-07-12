package com.korea.shop;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.ItemShowDAO;
import util.MyCommon;
import vo.ItemVO;
import vo.ManagerVO;

@Controller
public class ItemShowController {

	ItemShowDAO itemshow_dao;
	public void setItemshow_dao(ItemShowDAO itemshow_dao) {
		this.itemshow_dao = itemshow_dao;
	}
	
	@RequestMapping("item_show_top.do")
	public String list_top(Model model) {
		List<ItemVO> list = itemshow_dao.selectList_top();
		
		model.addAttribute("list", list);
		 
		return MyCommon.VIEW_PATH+"item_show_top.jsp";
	}
	
	@RequestMapping("item_show_pants.do")
	public String list_pants(Model model) {
		List<ItemVO> list = itemshow_dao.selectList_pants();
		
		model.addAttribute("list", list);
		 
		return MyCommon.VIEW_PATH+"item_show_pants.jsp";
	}
	
	@RequestMapping("item_show_outer.do")
	public String list_outer(Model model) {
		List<ItemVO> list = itemshow_dao.selectList_outer();
		
		model.addAttribute("list", list);
		 
		return MyCommon.VIEW_PATH+"item_show_outer.jsp";
	}
	@RequestMapping("item_show_jewelry.do")
	public String list_jewelry(Model model) {
		List<ItemVO> list = itemshow_dao.selectList_jewelry();
		
		model.addAttribute("list", list);
		 
		return MyCommon.VIEW_PATH+"item_show_jewelry.jsp";
	}
	@RequestMapping("item_show_top2.do")
	public String list_top2(Model model) {
		List<ItemVO> list = itemshow_dao.selectList_top();
		
		model.addAttribute("list", list);
		
		return MyCommon.VIEW_PATH+"item_show_top2.jsp";
	}
	
	@RequestMapping("item_show_pants2.do")
	public String list_pants2(Model model) {
		List<ItemVO> list = itemshow_dao.selectList_pants();
		
		model.addAttribute("list", list);
		
		return MyCommon.VIEW_PATH+"item_show_pants2.jsp";
	}
	
	@RequestMapping("item_show_outer2.do")
	public String list_outer2(Model model) {
		List<ItemVO> list = itemshow_dao.selectList_outer();
		
		model.addAttribute("list", list);
		
		return MyCommon.VIEW_PATH+"item_show_outer2.jsp";
	}
	@RequestMapping("item_show_jewelry2.do")
	public String list_jewelry2(Model model) {
		List<ItemVO> list = itemshow_dao.selectList_jewelry();
		
		model.addAttribute("list", list);
		
		return MyCommon.VIEW_PATH+"item_show_jewelry2.jsp";
	}
	
	
}
