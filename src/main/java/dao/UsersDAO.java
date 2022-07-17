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
	
	

}
