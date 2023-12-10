package com.springboot.main.myproj.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.myproj.model.Bus;
import com.springboot.main.myproj.model.BusOperator;
import com.springboot.main.myproj.model.Customer;
import com.springboot.main.myproj.model.Executive;
import com.springboot.main.myproj.model.User;
import com.springboot.main.myproj.service.BusOperatorService;
import com.springboot.main.myproj.service.BusService;
import com.springboot.main.myproj.service.CustomerService;
import com.springboot.main.myproj.service.ExecutiveService;
import com.springboot.main.myproj.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BusOperatorService busOperatorService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ExecutiveService executiveService;
	
	@PostMapping("/user/login")
	public User login(Principal principal) {
		System.out.println("hi");
		String username= principal.getName();
		User user=(User) userService.loadUserByUsername(username);
		System.out.println(user);
		return user;
	}
	@PostMapping("/auth/login")
	public ResponseEntity<?> userLogin(Principal principal) {
	    String username = principal.getName();
	    User user = (User) userService.loadUserByUsername(username);
	    System.out.println(user);

	    if (user != null) {
	        switch (user.getRole()) {
	            case CUSTOMER:
	                // Handle login for CUSTOMER role
	                Customer customer = customerService.getByUserbyId(user.getId());
	                return ResponseEntity.ok().body(customer);
	            case BUSOPERATOR:
	                // Handle login for AIRLINE role
	                BusOperator busOperator = busOperatorService.getByUserId(user.getId());
	                return ResponseEntity.ok().body(busOperator);
	            case EXECUTIVE:
	                // Handle login for EXECUTIVE role
	                Executive executive = executiveService.getByUserId(user.getId());
	                return ResponseEntity.ok().body(executive);
	            default:
	                // Handle other roles or provide an error response
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unknown user role");
	        }
	    }

	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
	}
}
