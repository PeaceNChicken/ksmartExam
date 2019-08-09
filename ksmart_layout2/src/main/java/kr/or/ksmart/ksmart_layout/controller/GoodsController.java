package kr.or.ksmart.ksmart_layout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.ksmart.ksmart_layout.service.GoodsService;
import kr.or.ksmart.ksmart_vo.Goods;

@Controller //GoodsController 클래스가 Controller라는걸 인지시켜주는 어노테이션
public class GoodsController {

	@Autowired private GoodsService goodsService; // goodsService 객체 의존성 주입시키는 어노테이션 
	
	@PostMapping("/addGoods")
	public String addGoods(Goods goods) {
		return null;
	}
	
	@GetMapping("/addGoods")
	public String addGoods() {
		return "/goods/gInsert/addGoods";
	}
}
