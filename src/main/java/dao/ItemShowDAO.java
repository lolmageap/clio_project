package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.ItemVO;

public class ItemShowDAO {
	
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<ItemVO> selectList_top() {
		List<ItemVO> list = sqlSession.selectList("is.item_show_top");
		return list;
	}
	public List<ItemVO> selectList_pants() {
		List<ItemVO> list = sqlSession.selectList("is.item_show_pants");
		return list;
	}
	public List<ItemVO> selectList_outer() {
		List<ItemVO> list = sqlSession.selectList("is.item_show_outer");
		return list;
	}
	
	public List<ItemVO> selectList_jewelry() {
		List<ItemVO> list = sqlSession.selectList("is.item_show_jewelry");
		return list;
	}
	
}
