package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.ItemVO;
import vo.UsersVO;

public class BasketLineDAO {
	
	SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//장바구니 페이지 이동
	public List<ItemVO> payment(ItemVO vo) {
		List<ItemVO> list = sqlSession.selectList("basket.payment", vo);
		return list;
	}
	
	//장바구니 조회
	public ItemVO payment_all(ItemVO vo) {
		ItemVO ivo = sqlSession.selectOne("basket.payment_all", vo);
		return ivo;
	}
	
	//장바구니 비우기
	public int basket_out(ItemVO vo) {
		int res = sqlSession.delete("basket.basket_out", vo);
		return res;
	}
	
	//장바구니에 수량 추가
	public int basket_plus(ItemVO vo) {
		int res = sqlSession.update("basket.basket_plus", vo);
		return res;
	}
	
	//장바구니 수량 감소
	public int basket_minus(ItemVO vo) {
		int res = sqlSession.update("basket.basket_minus", vo);
		return res;
	}
	
	//장바구니 단일 제품 삭제
	public int basket_delete(ItemVO vo) {
		int res = sqlSession.update("basket.basket_delete", vo);
		return res;
	}

	//유저 id 정보 가져오기
	public UsersVO select_user(ItemVO vo) {
		UsersVO res= sqlSession.selectOne("basket.select_user", vo);
		return res;
	}
	
	//장바구니 테이블에서 주문테이블로 insert
	public int order_insert(ItemVO vo) {
		int res= sqlSession.insert("basket.insert_order", vo);
		return res;
	}
	
	//주문리스트 가져오기
	public List<ItemVO> order_page(ItemVO vo) {
		List<ItemVO> res= sqlSession.selectList("basket.order_page", vo);
		return res;
	}
	
}
