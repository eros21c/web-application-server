package webserver.persistence;

import java.util.ArrayList;

import model.User;

public class UserStore {
	
	private static ArrayList<User> users = new ArrayList<User>();
	
	/**
	 * 사용자 정보를 저장한다.
	 * @param user - 사용자 정보
	 */
	public static void add(User user) {
		users.add(user);
	}
	
	/**
	 * 사용자 계정이 이미 존재하는지 확인한다.
	 * @param userId - 사용자 계
	 * @return 존재여부 <br> true : 존재<br> false : 미존재
	 */
	public boolean exist(String userId) {
		for(User user : users) {
			if(user.getUserId().equals(userId)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 사용자 계정과 패스워드가 일치하는 사용자 정보를 구한다.
	 * @param userId - 사용자 계
	 * @param password - 패스워
	 * @return 사용자 정보
	 * @see model.User
	 */
	public static User get(String userId, String password) {
		for(User user : users) {
			if(user.getUserId().equals(userId) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

}
