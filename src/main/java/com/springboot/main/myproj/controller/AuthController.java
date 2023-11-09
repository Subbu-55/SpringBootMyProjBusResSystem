package com.springboot.main.myproj.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.myproj.model.User;
import com.springboot.main.myproj.service.UserService;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user/login")
	public User login(Principal principal) {
		String username= principal.getName();
		User user=(User) userService.loadUserByUsername(username);
		return user;
	}
}
