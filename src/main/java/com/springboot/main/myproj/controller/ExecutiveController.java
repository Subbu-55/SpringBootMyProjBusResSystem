package com.springboot.main.myproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.myproj.exception.InvalidIdException;
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
@RequestMapping("/executive")
public class ExecutiveController {
	
	@Autowired
	private ExecutiveService executiveService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	@Autowired
	private BusService busService;
	
	@Autowired
	private BusOperatorService busOperatorService;
	
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
	
	@GetMapping("/get/bus/{eid}")
	public List<Bus> getBusByExecutiveId(@PathVariable("eid") int eid){

		return busService.getBusByExecutiveId(eid); 
	}
	
	@GetMapping("/get/bus/all")
	public List<Bus> getAllBus(){

		return busService.getAllBus();
	}
	
	@GetMapping("/get/customer/all")
	public List<Customer> getAllCustomer(){

		return customerService.getAllCustomer(); 
	}
	
	@GetMapping("/get/busOperator/{eid}")
	public List<BusOperator> getBusOperatorByExecitiveId(@PathVariable("eid") int eid){
		
		return busOperatorService.getBusOperatorByExecitiveId(eid);
	}
	
	@GetMapping("/get/busOperator/all")
	public List<BusOperator> getAllBusOperator(){
		
		return busOperatorService.getAllBusOperator();
	}
	
	@DeleteMapping("/delete/executive/{eid}")
	public ResponseEntity<?> deleteExecutive(@PathVariable("eid") int eid) {
		try {
			Executive executive = executiveService.getById(eid);
			executiveService.deleteExecutive(executive.getId());
			return ResponseEntity.ok().body("Executive Record deleted");
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	@DeleteMapping("/delete/busOperator/{boid}")
	public ResponseEntity<?> deleteBusOperator(@PathVariable("boid") int boid) {
		try {
			BusOperator busOperator = busOperatorService.getById(boid);
			busOperatorService.deleteBusOperator(busOperator.getId());
			return ResponseEntity.ok().body("BusOperator Record deleted");
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	

}
