package kr.or.ksmart.ksmart_layout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ksmart.ksmart_layout.mapper.GoodsMapper;

@Service //GoodsService가 service 객체임을 인지시켜주는 어노테이션
@Transactional //CRUD에 오류가 발생시 try-catch문처럼 어떻게 예외처리 할 것인지에 대한 어노테이션
public class GoodsService {

	@Autowired private GoodsMapper goodsMapper; // goodsMapper 인터페이스 의존성 주입시키는 어노테이션
}
