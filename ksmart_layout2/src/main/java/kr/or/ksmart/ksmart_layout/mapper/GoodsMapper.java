package kr.or.ksmart.ksmart_layout.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ksmart.ksmart_vo.Goods;

@Mapper // GoodsMapper 인터페이스가 mapping 객체임을 인지시켜주는 어노테이션 
public interface GoodsMapper {

	public List<Goods> goodsList();
	
	public int getCodeMax();
	
	public int addGoods(Goods goods);
}
