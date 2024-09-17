package com.learn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.learn.models.User;

@Service("userservice")
public class UserService {

	List<User> users = new ArrayList<>();
	
	public UserService() {
		/*
		 * users.add(new User("abc","abc","ABC@GMAIL.COM")); users.add(new
		 * User("xyz","xyz","XYZ@GMAIL.COM"));
		 */
	}
	
	public List<User> getAllUsers()
	{
		return users;
	}
	
	public User getUser(String username) {
	
		return this.users.stream().filter(user -> user.getUsername().equals(username)).findAny().orElse(null) ;
	}
	public User addUser(User user)
	{
		users.add(user);
		return user;
	}
}
