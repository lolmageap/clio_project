package dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import vo.UsersVO;

public class UsersDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//회원가입 페이지
	public List<UsersVO> selectList(){ 
		List<UsersVO> list = sqlSession.selectList("u.users_list");
		return list;
	}
	
	//가입완료 페이지
	public List<UsersVO> insert_list(UsersVO vo) {
		List<UsersVO> list = sqlSession.selectList("u.insert_list",vo);
		return list;
	}
	
	
	//아이디 중복확인
	public UsersVO id_check(UsersVO vo){
		UsersVO res = sqlSession.selectOne("u.id_check",vo);
		return res;
	}
	
	//비밀번호 확인
	public UsersVO pwd_check(UsersVO vo){
		UsersVO res = sqlSession.selectOne("u.pwd_check",vo);
		return res;
	}
	
	//로그인
	public UsersVO log_in(UsersVO vo){
		UsersVO list = sqlSession.selectOne("u.login",vo);
		return list;
	}
	
	// users 테이블의 이메일 조회
	  public String selectOne(String email){
	  String res = sqlSession.selectOne("u.selectOne",email); 
	  return res; 
	  }
	  
	 // users 테이블에 oauth2 로그인 정보 주입
	 public int insert_kakao(UsersVO vo){
		  int res = sqlSession.insert("u.insert_kakao",vo); 
		  return res; 
	 }
	 
	 // user_number 테이블에 이메일 조회
	 public String view_email(String email){
		  String res = sqlSession.selectOne("u.view_email",email); 
		  return res; 
	 }
	
	 public int del_email(String email){
		 int res = sqlSession.delete("u.del_email",email); 
		 return res; 
	 }
	 
	 public int ins_num(UsersVO vo){
		 int res = sqlSession.delete("u.ins_num", vo); 
		 return res; 
	 }
	 
	 public String select_num(UsersVO vo){
		 String res = sqlSession.selectOne("u.select_num", vo); 
		 return res; 
	 }
	 
	 public int update_user(UsersVO vo){
		 int res = sqlSession.insert("u.update_user", vo); 
		 return res; 
	 }
	 

}
