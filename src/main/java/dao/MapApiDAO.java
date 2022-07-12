package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.ManagerVO;

public class MapApiDAO {
	SqlSession sqlSession;
		
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<ManagerVO> selectList(){
		List<ManagerVO> list = sqlSession.selectList("map.mapapi");
		return list;
	}
}
