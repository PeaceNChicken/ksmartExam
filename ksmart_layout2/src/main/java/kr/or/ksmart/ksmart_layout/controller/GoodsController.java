package kr.or.ksmart.ksmart_layout.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.ksmart.ksmart_layout.service.GoodsService;
import kr.or.ksmart.ksmart_vo.Goods;

@Controller //GoodsController 클래스가 Controller라는걸 인지시켜주는 어노테이션
public class GoodsController {
	@Autowired private GoodsService goodsService; // goodsService 객체 의존성 주입시키는 어노테이션 
	
	@GetMapping("/goodsList")
	public String goodsList(Model model) 
	{
		model.addAttribute("gList", goodsService.goodsList());
		return "/goods/gList/goodsList";
	}
	
	/* @param	goods 객체, session 객체
	 * @return 	service단에서 응답받은 정수값
	 * @detail	post방식으로 /addGoods url 요청받으면  상품등록화면에서 입력한 User 클래스 데이터 타입으로 선언된 
	 			user객체 형태로 입력된 데이터와 등록id를 대신할 session객체 내 id 값을 매개변수에 담아 service객체내
	 			addGoods 메서드 호출한다. 리턴값을 다른곳에 사용할 필요가 없으니 호출하는 것으로 처리과정은 끝내고
	 			흐름이 끝났으므로 index화면으로 리다이렉트 시켜준다.
	 			*/
	@PostMapping("/addGoods")
	public String addGoods(Goods goods, HttpSession session) 
	{
		//System.out.println(goods +"<-- goods addGoods(Goods goods, HttpSession session)");
		//System.out.println(session.getAttribute("SID") +"<-- addGoods(Goods goods, HttpSession session)");
		goodsService.addGoods(goods, session);
		return "redirect:/";
	}
	
	/* @param	/addGoods url 요청
	 * @return 	/goods/gInsert/addGoods
	 * @detail	get방식으로 /addGoods url 요청받으면 controller단에서 
				/goods/gInsert/addGoods 화면을 forward시켜 보여준다 */
	@GetMapping("/addGoods")
	public String addGoods() 
	{
		return "goods/gInsert/addGoods";
	}
}
