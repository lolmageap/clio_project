package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.ItemVO;
import vo.ManagerVO;
import vo.OrderVO;

public class ManagerDAO {
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	//조회
	public List<ManagerVO> selectList(){
		List<ManagerVO> list = sqlSession.selectList("m.manager_list");
		return list;
	}
	//제품 등록
	public int insert(ManagerVO vo) {
		int res = sqlSession.insert("m.manager_insert",vo);
		return res;
	}
	//사이즈 등록
	public int size_insert(ManagerVO vo) {
		int res = sqlSession.insert("m.size_insert",vo);
		return res;
	}
	//색상 등록
	public int color_insert(ManagerVO vo) {
		int res = sqlSession.insert("m.color_insert",vo);
		return res;
	}
	//삭제
	public int delete(int item_name) {
		int res = sqlSession.delete("m.manager_delete",item_name);
		return res;
	}
	public int size_delete(int item_name) {
		int res = sqlSession.delete("m.size_delete",item_name);
		return res;
	}
	public int color_delete(int item_name) {
		int res = sqlSession.delete("m.color_delete",item_name);
		return res;
	}
	//수정
	public int update(ManagerVO vo) {
		int res = sqlSession.delete("m.manager_update",vo);
		return res;
	}
	
	public List<OrderVO> select_order() {
		List<OrderVO> vo = sqlSession.selectList("m.select_order");
		return vo;
	}
	
}
