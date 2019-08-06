package kr.or.ksmart.ksmart_vo;

public class User {
	private String userId;
	private String userPw;
	private String userLevel;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String userAddr;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone= userPhone;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr= userAddr;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public String toString() {
		return "User[userId="+userId+", userPw="+userPw+", userLevel="
				+userLevel+", userName="+userName+", userEmail="
				+userEmail+", userPhone="+userPhone+", userAddr="+userAddr+ "]";
	}
}
