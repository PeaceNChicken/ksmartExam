package kr.or.ksmart.ksmart_layout.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import kr.or.ksmart.ksmart_layout.mapper.UserMapper;
import kr.or.ksmart.ksmart_vo.User;

@Service // UserService.java가 서비스객체임을 인지시켜주는 어노테이션
@Transactional //CRUD에 오류가 발생시 try-catch문처럼 어떻게 예외처리 할 것인지에 대한 어노테이션
public class UserService {

	@Autowired private UserMapper userMapper; //UserMapper 인터페이스 의존성 주입시키는 어노테이션
	
	/* @param	user - userId, userPw	
	 * @return	map객체
	 * @detail	controller단에서 userId, userPw가 담겨있는 user객체 주소값을 매개변수에 담아 메서드 실행 
	  			로그인처리에 따라 나타날 메세지를 출력할 result 변수를 ""으로 선언
	  			여러 형질의 데이터를 담기위해 Map데이터 타입으로 객체참조변수 map 선언하고 HashMap 메서드 생성하고 주소값을 할당한다
	 			userMapper에서 메서드 실행한 결과값인 user데이터타입의 하나의 행을 User클래스데이터타입으로 선언한 userCheck 변수에 담는다
	 			조건문 userCheck가 null이라면 id나 pw 입력이 이루어지지 않아 쿼리문이 실행이 안된 것이다
	 			그럼 이제 입력이 안된 부분이 id인지 pw인지 확인하기 위해 userCheck 내 userId를 가져와 userMapper.userUpdateById 메서드의 
	 			매개변수에 담아 호출해서 그 결과값을 User 클래스 데이터 타입으로 선언한 userIdCheck 변수에 담는다
	 				조건문 userIdCheck가 null이라면 id 입력이 안된거라는 의미가 되기에 유효성검사 result변수에 "존재하지 않는 아이디입니다"를 담는다 
	 				userIdCheck가 null이 아니면 pw 입력이 안된거라는 의미 => 유효성검사 result 변수에 "비밀번호가 일치하지 않습니다"를 담는다 
	 			조건문 userCheck가 null이 아니면 id와 pw가 일치했다는 의미니까 유효성 검사 result 변수에 "로그인 성공" 을 담고
	 			선언해놓은 map객체에 "userLogin"이란 이름으로 usercheck에 담긴 주소값을 값으로 담는다
	 			마지막으로 조건문 처리에 맞춰 담긴 result 변수의 값을 "result"란 이름으로 담고 map객체를 controller단으로 리턴시킨다*/
	public Map<String, Object> userLogin(User user) {
		String result = "";
		Map<String, Object> map = new HashMap<String, Object>();
		User userCheck = userMapper.userLogin(user);
		if(userCheck == null) {
			User userIdCheck = userMapper.userUpdateById(userCheck.getUserId());
			if(userIdCheck == null) {
				result= "존재하지 않는 아이디입니다";
			}else {
				result= "비밀번호가 일치하지 않습니다"; 
			}
		}else {
			result = "로그인성공";
			map.put("userLogin", userCheck);
		}
		map.put("result", result);
		return map;
	}
	
	// Controller단에서 받은 요청을 처리
	// 쿼리 실행결과는 정수값 1을 리턴하므로 int 데이터 타입으로 메서드 선언
	// controller단에서 전달된 User 데이터 타입으로 선언된 user 객체에 담긴 값을 매개변수에 담아 실행
	public int userDelete(User user) {
		return userMapper.userDelete(user);
	}
	
	// Controller단에서 받은 요청을 처리
	// 쿼리실행결과는 User 클래스 데이터 타입으로 하나의 행이 출력되므로 User데이터 타입으로 메서드 선언하고
	// controller단에서 전달된 userId값을 매개변수에 담아 실행한다.
	public User userDelete(String userId) {
		// userMapper 인터페이스안에 있는 userUpdateById 메서드 호출해서
		// 남은 결과값을 controller단에 리턴시켜준다.
		return userMapper.userUpdateById(userId);
	}
	
	// Controller단에서 받은 요청을 처리하는데
	// 쿼리 실행결과는 정수값 1을 리턴하므로 int 데이터 타입으로 메서드 선언하고
	// controller단에서 전달된 User 데이터 타입으로 선언된 user 객체에 담긴 값을 매개변수에 담아 실행
	public int userUpdate(User user) {
		// 남은 결과값을 controller단에 리턴시켜준다
		return userMapper.userUpdate(user);
	}
	
	// Controller단에서 받은 요청을 처리하는데
	// 쿼리실행결과는 User 클래스 데이터 타입으로 하나의 행이 출력되므로 User데이터 타입으로 메서드 선언하고
	// controller단에서 전달된 userId값을 매개변수에 담아 실행한다.
	public User userUpdateById(String userId) {
		
		// userMapper 인터페이스안에 있는 userUpdateById 메서드 호출해서
		// 남은 결과값을 controller단에 리턴시켜준다.
		return userMapper.userUpdateById(userId);
	}
	// Controller단에서 받은 요청이 Service단으로 가고
	// 쿼리실행결과가 한줄이 아닌 여러 행이 들어올 수도 있으니 
	// List<User>데이터타입으로 메서드를 선언하고  controller단에서 전달된 uk, uv 값을 담은채 실행한다.
	public List<User> userSearch(String uk, String uv){
		// userMapper 인터페이스 안에 있는 userSearch 메서드 호출하고
		// 쿼리문이 실행되어 리턴된 값을 controller단으로 넘겨준다.  
		return userMapper.userSearch(uk, uv);
	}
	// Controller단에서 받은 요청이 Service단으로 가고
	// 쿼리실행결과가 User클래스 데이터 형태의 list 배열 형태로 들어올 것이기에 메서드 데이터타입은 List<User>
	public List<User> userList() {
		return userMapper.userList();
	}
	// Controller단에서 받은 요청이 Service단으로 가고
	// 쿼리실행결과가 정수값으로 리턴될테니까 메서드 데이터타입은 int로 적는다
	public int userInsert(User user) {
		int result = userMapper.userInsert(user);
		// Service단에서 해당 sql을 실행시키기 위해 생성해놓은 
		// Mapper 인터페이스안에 있는 userInsert 메서드를 호출한다
		// 쿼리문이 정상적으로 실행되면 리턴값을 남길텐데 insert쿼리문은 입력된 행에 따른 정수값을 남긴다.
		// 그렇기에 쿼리문 실행 후 리턴값을 받는 result 변수는 int 데이터타입으로 선언하고 그걸  Controller단으로 넘겨준다
		// 굳이 이런과정 없이 return값으로 userMapper.userInsert(user)를 넣어도 무관하다
		return result;
	}
		
}
