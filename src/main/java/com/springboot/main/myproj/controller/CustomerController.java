package com.springboot.main.myproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.myproj.dto.CustomerDto;
import com.springboot.main.myproj.exception.InvalidIdException;
import com.springboot.main.myproj.model.Customer;
import com.springboot.main.myproj.model.User;
import com.springboot.main.myproj.service.CustomerService;
import com.springboot.main.myproj.service.UserService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public Customer insertCustomer(@RequestBody Customer customer) {
		//save user info in db
		User user=customer.getUser();
		// i am encrypting the password
		String passwordPlain = user.getPassword();
		
		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		
		user.setRole("CUSTOMER");
		user = userService.insert(user);
		// attach the saved user(in step 1)
		customer.setUser(user);
		
		
		return customerService.insert(customer);
	}
	
	@PutMapping("/update/{cid}")
	public ResponseEntity<?> updateCustomer(@PathVariable("cid") int cid,@RequestBody CustomerDto customerDto){
		try {
			Customer customer = customerService.getById(cid);
			if(customerDto.getCity()!=null)
				customer.setCity(customerDto.getCity());
			if(customerDto.getName()!=null)
				customer.setName(customerDto.getName());
			if(customerDto.getEmail()!=null)
				customer.setEmail(customerDto.getEmail());
			customer=customerService.postCustomer(customer);
			return ResponseEntity.ok().body(customer);	
		}catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
}
