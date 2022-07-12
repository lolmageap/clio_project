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
	public ItemVO payment_all(ItemVO vo) {
		ItemVO ivo = sqlSession.selectOne("basket.payment_all", vo);
		return ivo;
	}
	public int basket_out(ItemVO vo) {
		int res = sqlSession.delete("basket.basket_out", vo);
		return res;
	}
	public int basket_plus(ItemVO vo) {
		int res = sqlSession.update("basket.basket_plus", vo);
		return res;
	}
	public int basket_minus(ItemVO vo) {
		int res = sqlSession.update("basket.basket_minus", vo);
		return res;
	}
	public int basket_delete(ItemVO vo) {
		int res = sqlSession.update("basket.basket_delete", vo);
		return res;
	}
	public UsersVO select_user(ItemVO vo) {
		UsersVO res= sqlSession.selectOne("basket.select_user", vo);
		return res;
	}
	public int order_insert(ItemVO vo) {
		int res= sqlSession.insert("basket.insert_order", vo);
		return res;
	}
	public List<ItemVO> order_page(ItemVO vo) {
		List<ItemVO> res= sqlSession.selectList("basket.order_page", vo);
		return res;
	}
	
}
