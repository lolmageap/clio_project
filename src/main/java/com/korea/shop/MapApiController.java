package com.korea.shop;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.MapApiDAO;
import util.MyCommon;
import vo.ManagerVO;

@Controller
public class MapApiController {
	MapApiDAO mapapi_dao;
	
	public void setMapapi_dao(MapApiDAO mapapi_dao) {
		this.mapapi_dao = mapapi_dao;
	}
	
	@RequestMapping("mapapi.do")
	public String list(Model model) {
		List<ManagerVO> list = mapapi_dao.selectList();
		
		model.addAttribute("list", list);
		
		return MyCommon.VIEW_PATH+"mapapi.jsp";
	}
	
	
	
}
