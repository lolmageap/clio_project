package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.ItemVO;

public class ItemDAO {
	SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// 전체 조회
	public List<ItemVO> selectList() {
		List<ItemVO> list = sqlSession.selectList("i.item_list");
		return list;
	}

	// 검색
	public List<ItemVO> item_search1(String item_name) {
		List<ItemVO> list = sqlSession.selectList("i.item_search1", item_name);
		return list;
	}

	public List<ItemVO> item_search2(String item_name) {
		List<ItemVO> list = sqlSession.selectList("i.item_search2", item_name);
		return list;
	}

	public List<ItemVO> item_search3(String item_name) {
		List<ItemVO> list = sqlSession.selectList("i.item_search3", item_name);
		return list;
	}

	// 상의 조회
	public List<ItemVO> select_top() {
		List<ItemVO> list = sqlSession.selectList("i.item_list_top");
		return list;
	}
	// 아우터 조회
	public List<ItemVO> select_outer() {
		List<ItemVO> list = sqlSession.selectList("i.item_list_outer");
		return list;
	}
	// 하의 조회
	public List<ItemVO> select_pants() {
		List<ItemVO> list = sqlSession.selectList("i.item_list_pants");
		return list;
	}
	// 악세사리 조회
	public List<ItemVO> select_jewelry() {
		List<ItemVO> list = sqlSession.selectList("i.item_list_jewelry");
		return list;
	}
	
	public ItemVO buy_item(int item_id) {
		ItemVO list = sqlSession.selectOne("i.buy_item" , item_id);
		return list;
	}
	public List<ItemVO> buy_item_size(int item_id) {
		List<ItemVO> list = sqlSession.selectList("i.buy_item_size" , item_id);
		return list;
	}
	public List<ItemVO> buy_item_color(int item_id) {
		List<ItemVO> list = sqlSession.selectList("i.buy_item_color" , item_id);
		return list;
	}
	
	//확인 버튼시 ajax로 item_show.jsp에 상품 라인 생성
	public int insert_temp(ItemVO vo) {
		int res = sqlSession.insert("i.insert_temp" , vo);
		return res;
	}
	public List<ItemVO> select_temp(ItemVO vo) {
		List<ItemVO> list = sqlSession.selectList("i.select_temp" , vo);
		return list;
	}
	//임시 테이블 삭제
	public int delete_temp(ItemVO vo) {
		int res = sqlSession.delete("i.delete_temp" , vo);
		return res;
	}
	
	//바스켓 테이블에 넣기
	public int insert_basket(ItemVO vo) {
		int res = sqlSession.insert("i.insert_basket" , vo);
		return res;
	}
	
	
	

}
