package kr.or.ksmart.ksmart_layout.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ksmart.ksmart_layout.mapper.GoodsMapper;
import kr.or.ksmart.ksmart_vo.Goods;

@Service //GoodsService가 service 객체임을 인지시켜주는 어노테이션
@Transactional //CRUD에 오류가 발생시 try-catch문처럼 어떻게 예외처리 할 것인지에 대한 어노테이션
public class GoodsService {
	@Autowired private GoodsMapper goodsMapper; // goodsMapper 인터페이스 의존성 주입시키는 어노테이션
	
	public List<Goods> goodsList() 
	{
		//List<Goods> goods = goodsMapper.goodsList();
		//System.out.println(goods + "<--goodsList() GoodsService");
		return goodsMapper.goodsList();
	}
	
	/* @param	goods, session
	 * @return 	쿼리문 실행된 결과값 int
	 * @detail	post방식으로 /addGoods url 요청받을때 controller단에서 goos데이터 타입으로 addGoods에 입력된
				데이터값과 등록아이디를 대신할 session객체에 담겨있는 SID를 받아서 메서드 실행
				int 데이터 타입으로 max 변수 선언하고 goodsMapper.getCodeMax()를 통해 얻은 db행들 중
				가장 큰 값을 가져오고 +1 시킨 값을 담아 상품 등록 할때 g_code 컬럼 행이 1씩 증가되도록 할 것이기에
				goods 객체 내 setter메서드로 goods_와 max변수를 연결연산자 +로 연결시켜 goodsCode 세팅
				setter메서드로 등록아이디를 session 객체 내 "SID" 이름에 담겨있는 id값으로 goodsUserId 세팅해주고 
				goodsMapper 객체 내 addGoods 메서드 매개변수에 goods 데이터를 담아 호출하고 리턴값 정수를 받는다*/
	public int addGoods(Goods goods, HttpSession session) 
	{
		int max = goodsMapper.getCodeMax() +1;
		goods.setGoodsCode("goods_" + max);
		goods.setGoodsUserId((String)session.getAttribute("SID"));
		return goodsMapper.addGoods(goods);
	}
}
