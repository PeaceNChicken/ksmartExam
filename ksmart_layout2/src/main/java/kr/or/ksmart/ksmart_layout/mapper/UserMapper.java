package kr.or.ksmart.ksmart_layout.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ksmart.ksmart_vo.User;

@Mapper // UserMapper 인터페이스가 mapping 객체임을 인지시켜주는 어노테이션 
public interface UserMapper {
	// UserMapper.xml에서 id가 userInsert인 쿼리문을 호출하는 메서드 
	// 결과값이 행 1개가 추가되는 것이기에 정수값 1이 리턴될 테니 메서드 데이터타입을 int로 적어준다.
	public int userInsert(User user);
	
	// UserMapper.xml에서 id가 userList인 쿼리문을 호출하는 메서드
	// 결과값이 객체들의 배열형태로 나타나기에 List<User>로 메서드 데이터타입을 선언한다.
	public List<User> userList();
	
	// UserMapper.xml에서 id가 userSearch인 쿼리문을 호출하는 메서드
	// 결과값이 행 하나로 끝난다면 User 데이터타입으로 선언해도 상관없지만
	// 객체들의 배열형태로 나타날 수도 있기 때문에 List<User>로 메서드 데이터타입을 선언한다.
	public List<User> userSearch(String uk, String uv);
	
	// UserMapper.xml에서 id가 userUpdateById인 쿼리문을 호출하는 메서드
	// 결과값이 행 하나로 나오기때문에 User 데이터타입으로 메서드 선언한다.
	public User userUpdateById(String userId);
}
