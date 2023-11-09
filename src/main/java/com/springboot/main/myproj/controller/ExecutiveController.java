package com.springboot.main.myproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.myproj.model.BusOperator;
import com.springboot.main.myproj.model.Executive;
import com.springboot.main.myproj.model.User;
import com.springboot.main.myproj.service.ExecutiveService;
import com.springboot.main.myproj.service.UserService;

@RestController
@RequestMapping("/executive")
public class ExecutiveController {
	
	@Autowired
	private ExecutiveService executiveService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public Executive insertExecutive(@RequestBody Executive executive) {
		//save user info in db
		User user=executive.getUser();
		// i am encrypting the password
		String passwordPlain = user.getPassword();
		
		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		
		user.setRole("EXECUTIVE");
		user = userService.insert(user);
		// attach the saved user(in step 1)
		executive.setUser(user);
		
		
		return executiveService.insert(executive);
	}

}
