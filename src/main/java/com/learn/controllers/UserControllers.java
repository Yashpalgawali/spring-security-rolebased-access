package com.learn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.models.User;
import com.learn.service.UserService;

@RestController
@RequestMapping("/users")
public class UserControllers {

	@Autowired
	UserService userservice;
	
	@PostMapping("/")
	public User saveUser(@RequestBody User user) {
		return userservice.addUser(user);
	}
	
	@GetMapping("/")
	public List<User> getAllUsers() {
		
		return userservice.getAllUsers();
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{username}")
	public User getUser(@PathVariable String username)
	{
		return userservice.getUser(username);
	}
}
