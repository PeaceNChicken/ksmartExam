package kr.or.ksmart.ksmart_layout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ksmart.ksmart_layout.service.UserService;
import kr.or.ksmart.ksmart_vo.User;

@Controller //UserController 클래스가 컨트롤러임을 인지시켜주는 어노테이션
public class UserController {

	@Autowired UserService userService; //UserService 클래스 의존성 주입하는 어노테이션
	
	// defalut.html 에서 회원가입을 클릭해 userInsert url을 요청받으면 회원가입 화면이 떠야 하는게 목표이다
	// get방식으로 요청받아서 아래의 userInsert() 실행되고 /user/uinsert/userInsert으로 요청 처리된 값을 화면에 뿌려준다
	@GetMapping("/userInsert")
	public String userInsert() {
		return "/user/uinsert/userInsert";
	}
	
	// 회원가입form에서 정보를 입력하고 회원가입버튼을 눌러 /user/uinsert/userInsert에서 userInsert url을 요청하면 
	// form에서 post방식으로 /userInsert url요청받으면 매개변수에 User클래스데이터 타입으로 입력한 회원가입 정보를 
	// 매개변수 user에 담아 userInsert(User user) 메서드를 실행시키면서 service단으로 요청을 넘긴다
	@PostMapping("/userInsert")  
	public String userInsert(User user) {
		userService.userInsert(user);
		// userService.userInsert(user) 실행으로 정수값을 리턴받았지만 리턴값을 재사용할 필요가 없으니
		// 처리과정을 끝마치면 되고 데이터 흐름이 막다른길에 도달했으므로  redirect를 사용해 localhost로 돌아가게끔 한다  
		return "redirect:/";
	}
	
	// 회원관리 -> 회원전체목록을 클릭하면 회원목록을 화면에 보이도록 하는게 목표
	// get방식으로  /userList url 요청을 받으면 String userList() 실행되고 클릭하면 화면에 뿌려줘야되니까
	// /user/ulist에 있는 userList.html로 요청 처리된 값을 보낸다 
	@GetMapping("/userList")
	public String userList(Model model) {
		List<User> uList = userService.userList();
		model.addAttribute("uList", uList);
		//service단에서 실행된 메서드 리턴값을 List<User> 클래스 데이터 타입으로 선언된 uList 변수에 담는다
		//uList 변수에 담긴 값을 "uList"라고 정한 키의 값에 할당한다
		return "/user/ulist/userList";
	}
	
	// 회원관리 -> 회원검색을 클릭하면 검색화면이 뜨도록 하는게 목표
	// get방식으로  /userSearch url 요청을 받으면String userSearch() 실행되고 클릭하면 화면에 뿌려줘야되니까
	// /user/usearch/userSearch.html으로 요청 처리된 값을 보낸다
	@GetMapping("/userSearch")
	public String userSearch() {
		return "/user/usearch/userSearch";
	}
	
	// 회원검색화면에서 검색버튼을 클릭하면 조건에 맞는 목록이 뜨게 하는 것이 목표
	// post방식으로 /userSearch url요청받으면 userSearch()에 매개변수로 uk, uv에 입력된 값이 담긴채 실행
	@PostMapping("/userSearch")
	public String userSearch(@RequestParam(value="uk") String uk
							,@RequestParam(value="uv") String uv
							,Model model) {
		//System.out.println(uk +"<--uk"); 검색창에서 입력한 uk와 
		//System.out.println(uv +"<--uv"); uv값이 제대로 들어오는지 확인
		List<User> uList = userService.userSearch(uk, uv);
		model.addAttribute("uList", uList);
		//service단에서 요청처리 된 리턴값을 List<User> 클래스 데이터 타입으로 선언된 uList 변수에 담는다
		//uList 변수에 담긴 값을 "uList"라고 정한 키의 값에 할당한다
		return "/user/usearch/userSearch";
	}
	@GetMapping("/userUpdate")
	public String userUpdateById(@RequestParam(value="userId") String userId
							,Model model) {
		model.addAttribute("userById", userService.userUpdateById(userId));
		
		return "/user/uUpdate/userUpdate";
	}
}
