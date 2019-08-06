package kr.or.ksmart.ksmart_layout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import kr.or.ksmart.ksmart_layout.mapper.UserMapper;
import kr.or.ksmart.ksmart_vo.User;

@Service // UserService.java가 서비스객체임을 인지시켜주는 어노테이션
@Transactional //CRUD에 오류가 발생시 try-catch문처럼 어떻게 예외처리 할 것인지에 대한 어노테이션
public class UserService {

	@Autowired UserMapper userMapper; //UserMapper 인터페이스 의존성 주입시키는 어노테이션
	
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
	
	// Controller단에서 받은 요청이 Service단으로 가고
	// 쿼리실행결과가 User클래스 데이터 형태의 list 배열 형태로 들어올 것이기에 메서드 데이터타입은 List<User>
	public List<User> userList() {
		return userMapper.userList();
	}
	
	// Controller단에서 받은 요청이 Service단으로 가고
	// 쿼리실행결과가 한줄이 아닌 여러 행이 들어올 수도 있으니 
	// List<User>데이터타입으로 메서드를 선언하고  controller단에서 전달된 uk, uv 값을 담은채 실행한다.
	public List<User> userSearch(String uk, String uv){
	// userMapper 인터페이스 안에 있는 userSearch 메서드 호출하고
	// 쿼리문이 실행되어 리턴된 값을 controller단으로 넘겨준다.  
		return userMapper.userSearch(uk, uv);
	}
	
	public User userUpdateById(String userId) {
		return userMapper.userUpdateById(userId);
	}
	
}
