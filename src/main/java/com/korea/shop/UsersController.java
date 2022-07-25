package com.korea.shop;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.UsersDAO;
import util.MyCommon;
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
		
		vo.setUser_addr(user_addr1 + "&" + user_addr2 + "&" + user_addr3);
		
		List<UsersVO> list = users_dao.insert_list(vo);
		
		model.addAttribute("list", list);
		
		//포워딩(도착하는 지점)
		return MyCommon.VIEW_PATH + "users_insert.jsp";
		
	}
	
	//로그인
	@RequestMapping("/log_in.do")
	@ResponseBody
	public String log_in(UsersVO vo, HttpServletRequest request, Model model) {
		
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		
		UsersVO Ulist = users_dao.log_in(vo);
		
	    String result = "no";
	      
	    if(Ulist != null) {
			result="yes";
	    }
	    
		if(Ulist.getUser_id().equals("aaaa")) {
			Ulist.setUser_data("<i style=\"position: fixed;\" onclick=\"location.href"
					+ "='manager_list.do'\" class=\"gear fa-solid fa-gear fa-2x\"></i>");			
		}
	    
	    HttpSession session = request.getSession();
		session.setAttribute("Ulist", Ulist);
		session.setMaxInactiveInterval(3600);
		
	    String finRes= String.format("[{'result':'%s'}]",result);
			
	    return finRes;

	}
	
	//OAUTH 로그인 
	@RequestMapping("/oauth2.do")
	public String oauth2(String name , String email) {
		
		//DB에 값이 있는지 확인하고 있으면 넘어가고 없으면 값을 등록
		//user_id를 이메일 값으로 등록 , email은 중복 x ,그리고 다시 메인페이지로 변환
		
		UsersVO Ulist = new UsersVO();
		
		Ulist.setUser_email(email);
		Ulist.setUser_id(email);
		Ulist.setUser_name(name);
		
		//랜덤 비밀번호 생성 후 비밀번호에 넣기
		int ax = new Random().nextInt(1000000)+ 1000000;
		Ulist.setUser_pwd(String.valueOf(ax));
		
		String res = users_dao.selectOne(email); 
		
		if(res != null && !res.isEmpty()) {
			}
		else{
			int put = users_dao.insert_kakao(Ulist); 
		}

		 HttpSession session = request.getSession();
			session.setAttribute("Ulist", Ulist);
			session.setMaxInactiveInterval(3600);
		
		return "item_list.do";
	}
	
	//인증번호 발송 및 이메일 인증
	@RequestMapping("/NaverMailSend.do")
	@ResponseBody
	private String example(String user_email) {
		
		String email = user_email;
		String result = "no";
		int ax = new Random().nextInt(100000)+ 100000;
		
		String res = users_dao.selectOne(email);
		
		if(res != null && !res.isEmpty()) {
			result = "yes";
		}
		else {
			
			final String user = "ekxk1234@naver.com"; 
			final String password = "wjd0322189!";
			
			
			System.setProperty("https.protocols", "TLSv1.2");
			
			Properties prop = new Properties();   
			prop.put("mail.smtp.host", "smtp.naver.com");
			prop.put("mail.smtp.port", "465");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.ssl.enable", "true");
			prop.put("mail.smtp.ssl.trust", "smtp.naver.com");
			
			Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});
			
			MimeMessage message = new MimeMessage(session);
			
			try {
				message.setFrom(new InternetAddress(user));
				
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
				
				
				// 메일 제목
				message.setSubject("클리오샵에서 인증번호 발송~!");
				
				// 메일 내용
				message.setText(Integer.toString(ax));
				
				// send the message
				Transport.send(message);
				
				UsersVO uservo = new UsersVO();
				uservo.setUser_number(ax);
				uservo.setUser_email(email);
				
				//조회 값 있으면 지우고 insert
				String cnum = users_dao.view_email(email);
				
				if(cnum!= null && !cnum.isEmpty()) {
					//지우고 insert
					int del = users_dao.del_email(email);
					int ins = users_dao.ins_num(uservo);
				}
				else {
					//insert
					int ins = users_dao.ins_num(uservo);
				}
				
			}catch (AddressException e) {
				e.printStackTrace();
			}  catch (MessagingException e) {
				e.printStackTrace();
			}
			
		}
		
		String finRes= String.format("[{'result':'%s'}]",result);
		
	    return finRes;
	}
	
	//인증번호 확인
	@RequestMapping("/number_check.do")
	@ResponseBody
	public String number_check( String user_email , int user_number) {
		
		UsersVO uvo = new UsersVO();
		uvo.setUser_number(user_number);
		uvo.setUser_email(user_email);
		
		String results = "no";
		
		//인증번호 확인
		String res = users_dao.select_num(uvo);
		
		// 확인 완료
		if(res != null && !res.isEmpty()) {
			results = "yes";
		}
		
		String finRes= String.format("[{'results':'%s'}]",results);
		
	    return finRes;
		
	}
	
	// 배송지 정보 입력
	@RequestMapping("update_user.do")
	@ResponseBody
	public String order_page(String user_id , String user_addr1, String user_addr2, String user_addr3 , String user_tel) {
		
		UsersVO vo = new UsersVO();
		vo.setUser_addr(user_addr1 + "&" + user_addr2 + "&" + user_addr3);
		vo.setUser_tel(user_tel);
		vo.setUser_id(user_id);
		
		int res = users_dao.update_user(vo);
		
		return "";
	}
	
	// 기존 배송지 불러오기
	@RequestMapping("select_addr.do")
	@ResponseBody
	public List<String> select_addr(String user_id , Model model) {
		
		
		String ud = users_dao.select_addr(user_id);
		
		String result = "no";
		String addr1 = "";
		String addr2 = "";
		String addr3 = "";
		UsersVO vo = new UsersVO();
		
		if(ud != null && !ud.isEmpty()) {
			String res = ud;
			String a = res.substring(res.indexOf("&")+1);
			
			addr1 = res.substring(0,res.indexOf("&")).trim();
			addr2 = a.substring(0,a.indexOf("&")).trim();
			addr3 = a.substring(a.indexOf("&")+1).trim();

			model.addAttribute("addr1", addr1);
			model.addAttribute("addr2", addr2);
			model.addAttribute("addr3", addr3);
			
			result = "yes";
		}
		
		List<String> finRes = new ArrayList<String>();
		finRes.add(result);
		finRes.add(addr1);
		finRes.add(addr2);
		finRes.add(addr3);
		
	    return finRes;
	}
	
}
