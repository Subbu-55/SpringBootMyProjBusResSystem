package com.springboot.main.myproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.myproj.dto.ExecutiveDto;
import com.springboot.main.myproj.enums.Role;
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
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	@Autowired
	private BusService busService;
	
	@Autowired
	private BusOperatorService busOperatorService;
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/add")
	public Executive insertExecutive(@RequestBody Executive executive) {
		User user=executive.getUser();
		String passwordPlain = user.getPassword();	
		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		user.setRole(Role.Executive);
		user = userService.insert(user);
		executive.setUser(user);
		return executiveService.insert(executive);
	}
	
	@GetMapping("/get/bus/{eid}")
	public List<Bus> getBusByExecutiveId(@PathVariable("eid") int eid){

		return busService.getBusByExecutiveId(eid); 
	}
	
	@GetMapping("/get/busOperator/{eid}")
	public List<BusOperator> getBusOperatorByExecitiveId(@PathVariable("eid") int eid){
		
		return busOperatorService.getBusOperatorByExecitiveId(eid);
	}
	@GetMapping("/get/customer")
	public List<Customer> getCustomer(){

		return customerService.getCustomer(); 
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
	
	@DeleteMapping("/delete/{eid}")
	public ResponseEntity<?> deleteExecutive(@PathVariable("eid") int eid) {
		try {
			Executive executive = executiveService.getById(eid);
			executiveService.deleteExecutive(executive.getId());
			return ResponseEntity.ok().body("Executive Record deleted");
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	
	@PutMapping("/update/{eid}")
	public ResponseEntity<?> updateExecutive(@PathVariable("eid") int eid,@RequestBody ExecutiveDto executiveDto){
		try {
			Executive executive = executiveService.getById(eid);
			if(executiveDto.getPhoneNo()!=null)
				executive.setPhoneNo(executiveDto.getPhoneNo());
			if(executiveDto.getName()!=null)
				executive.setName(executiveDto.getName());
			if(executiveDto.getEmail()!=null)
				executive.setEmail(executiveDto.getEmail());
			executive=executiveService.postExecutive(executive);
			return ResponseEntity.ok().body(executive);	
		}catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
