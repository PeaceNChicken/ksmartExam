package kr.or.ksmart.ksmart_layout.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

	@Autowired private UserService userService; //UserService 클래스 의존성 주입하는 어노테이션
	
	/* @param	session객체
	 * @return 	index화면
	 * @detail	get방식으로 /logout url 요청받으면 session.invalidate메서드 실행시켜 세션을 삭제하고
	 			index화면으로 리다이렉트 시킨다*/
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	/* @param	user - userId, userPw
	 * @return 	로그인실패시 /login/login, 로그인성공시 redirect:/
	 * @detail	post방식으로 /login url 요청받으면 controller단에서 user객체참조변수에 
	  			id와 pw를 담고 로그인 처리를 위해 생성되어있는 session객체와 model객체를 매개변수에 담아 메서드 실행
	  			service단에서 map객체를 리턴받고자 Map클래스데이터타입으로 map객체참조변수를 선언하고
	  			userService.userLogin(user) 실행해서 리턴값을 담는다 
	  			map객체에 담긴 result 값을 String 타입으로 형변환하여 String데이터타입으로 선언한 result 변수에 담는다
	  			map객체에 담긴 userLogin 값을 User 타입으로 형변환하여 User 데이터 타입으로 선언한 userLogin 변수에 담는다
	  			조건문 result값이 "로그인성공" 이 아닐경우
	  			model객체에 유효성검사 변수 result에 리턴받아서 형변환시킨 result 변수에 담긴 값을 
	  			"result" 이름을 맞춰 담고 /login/login페이지로 돌아가게 한다
	  			조건문 result값이 "로그인 성공"이라면 userLogin 변수에 담겨있는 id, level, name값을 
	  			미리 생성해놓은 session객체 SID, SLEVEL, SNAME에 세팅해주고 index화면으로 리다이렉트 시킨다*/
	@PostMapping("/login")
	public String userLogin(User user, HttpSession session, Model model) {
		Map<String, Object> map = userService.userLogin(user);
		String result = (String) map.get("result");
		User userLogin = (User) map.get("userLogin");
		if(!result.equals("로그인성공")) {
			model.addAttribute("result", result);
			return "/login/login";
		}
		session.setAttribute("SID", userLogin.getUserId());
		session.setAttribute("SLEVEL", userLogin.getUserLevel());
		session.setAttribute("SNAME", userLogin.getUserName());
		return "redirect:/";
	}

	/* @param /login url요청
	 * @return /login/login 페이지
	 * @detail get방식으로 /login url 요청받으면 controller단에서 /login/login 페이지를 화면에 출력
	 */
	@GetMapping("/login")
	public String login() {
		return "/login/login";
	}
		
	// 회원삭제페이지에서 삭제버튼을 눌렀을 때 받아온 id와 입력한 비밀번호가 일치하면 삭제처리 되는것이 목표
	// post방식으로 userDelete url 요청받을 때 user클래스데이터 타입으로 선언된 
	// user객체참조변수에 id값과 비밀번호를 담아 userDelete 메서드 실행
	// model 객체는 비밀번호가 일치하지 않았을때 텍스트와 id값을 다시 입력해주고 userDelete.html 페이지를 보여주기위해 매개변수에 선언
	@PostMapping("/userDelete")
	public String userDelete(User user, Model model) {
		int result = userService.userDelete(user);
		// result== 0이란말은 delete 쿼리문이 실행되지 않았다는 의미
		if(result== 0) {
			model.addAttribute("result", "비밀번호가 일치하지 않습니다");
			model.addAttribute("userId", user.getUserId());
			return "/user/uDelete/userDelete";
		}
		return "redirect:/userList";
	}
	
	// 회원검색결과화면에서 삭제버튼을 누르면 해당id값을 담은채 삭제페이지로 이동하는 것이 목표
	// get방식으로 userDelete url 요청을 받을 때 각 행의 id값을 받아와 userDelete 메서드 실행
	@GetMapping("/userDelete")
	public String userDelete(@RequestParam(value="userId") String userId
							,Model model) {
		System.out.println(userId +"<--userDelete UserController"); //userId를 제대로 받아오는지 확인
		// userUpdate화면띄울때와 동일한 패턴이다
		// User 데이터 타입 리턴값을 userById라는 이름의 키에 담고 삭제화면으로 이동해서 아이디만 삭제화면에 뿌려줄것이다
		model.addAttribute("user", userService.userUpdateById(userId));
		return "/user/uDelete/userDelete";
	}
	
	//회원수정페이지에서 수정할 데이터들을 입력하고 정보수정하기버튼을 누르면 db에 수정사항이 적용되는 것이 목표
	// post방식으로 수정데이터가 userUpdate 요청을 통해 controller단에 전달되어
	// 매개변수에 user데이터타입으로 선언된 객체참조변수 user가 담겨서 userUpdate 메서드 실행
	@PostMapping("/userUpdate")
	public String userUpdate(User user) {
		System.out.println(user +"<-- UserController userUpdate");//정보수정하기버튼을 누르면 값들이 제대로 보내지는지 확인
		//service단의 userUpdate메서드 호출을 요청한다
		//수정처리가 되면 업무흐름은 끝이므로 호출하는것으로 끝내고 리스트화면으로 리다이렉트 시켜서 수정처리가 되는지 확인할 수 있도록 한다
		userService.userUpdate(user);
		return "redirect:/userList";
	}
	// 회원검색결과화면에서 수정버튼을 누르면 해당id의 정보들을 화면에 보여주는 것이 목표
	// get방식으로 userUpdate url을 요청받을때 각 행의 id값을 가져와서 userUpdateById() 실행
	@GetMapping("/userUpdate")
	public String userUpdateById(@RequestParam(value="userId") String userId
							,Model model) {
		//service단에서 요청처리된 리턴값을 model객체에 담는데 "userById"라는 명칭으로 선언한다.
		model.addAttribute("userById", userService.userUpdateById(userId));
		
		return "/user/uUpdate/userUpdate";
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
	// 회원관리 -> 회원검색을 클릭하면 검색화면이 뜨도록 하는게 목표
	// get방식으로  /userSearch url 요청을 받으면String userSearch() 실행되고 클릭하면 화면에 뿌려줘야되니까
	// /user/usearch/userSearch.html으로 요청 처리된 값을 보낸다
	@GetMapping("/userSearch")
	public String userSearch() {
		return "/user/usearch/userSearch";
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
		
	// defalut.html 에서 회원가입을 클릭해 userInsert url을 요청받으면 회원가입 화면이 떠야 하는게 목표이다
	// get방식으로 요청받아서 아래의 userInsert() 실행되고 /user/uinsert/userInsert으로 요청 처리된 값을 화면에 뿌려준다
	@GetMapping("/userInsert")
	public String userInsert() {
		return "/user/uinsert/userInsert";
	}
		
}
