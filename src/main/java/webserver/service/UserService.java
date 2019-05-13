package webserver.service;

import model.User;
import webserver.persistence.UserStore;

public class UserService {
	
	public void create(User user) {
		UserStore.add(user);
	}
}
