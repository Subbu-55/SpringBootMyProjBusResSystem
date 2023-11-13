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
import com.springboot.main.myproj.model.BusSchedule;
import com.springboot.main.myproj.model.Executive;
import com.springboot.main.myproj.model.User;
import com.springboot.main.myproj.service.BusOperatorService;
import com.springboot.main.myproj.service.BusScheduleService;
import com.springboot.main.myproj.service.BusService;
import com.springboot.main.myproj.service.ExecutiveService;
import com.springboot.main.myproj.service.UserService;

@RestController
@RequestMapping("/busOperator")
public class BusOperatorController {

	@Autowired
	private BusOperatorService busOperatorService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExecutiveService executiveService;
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private BusScheduleService busScheduleService;
	
	@PostMapping("/add/{eid}")
	public ResponseEntity<?> assignBusOperator(@PathVariable("eid") int eid,@RequestBody BusOperator busOperator) {
	
		try {
		Executive executive=executiveService.getById(eid);
		
		busOperator.setExecutive(executive);
		
		//save user info in db
		User user=busOperator.getUser();
		// i am encrypting the password
		String passwordPlain = user.getPassword();
		
		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		
		user.setRole("BUSOPERATOR");
		user = userService.insert(user);
		// attach the saved user(in step 1)
		busOperator.setUser(user);
		
		busOperator=busOperatorService.insert(busOperator);
		return ResponseEntity.ok().body(busOperator);
		}catch(InvalidIdException e){
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}
	
	@GetMapping("/get/bus/{boid}")
	public List<Bus> getBusByBusOperatorId(@PathVariable("boid") int boid){

		return busService.getBusByBusOperatorId(boid); 
	}
	
	@GetMapping("/get/busSchedule/{boid}")
	public List<BusSchedule> getBusScheduleByBusOperatorId(@PathVariable("boid") int boid){


		return busScheduleService.getBusByBusOperatorId(boid);
	}
	
	@DeleteMapping("/bus/delete/{bid}")
	public ResponseEntity<?> deleteBus(@PathVariable("bid") int bid) {
		try {
			Bus bus = busService.getById(bid);
			busService.deleteBus(bus.getId());
			return ResponseEntity.ok().body("Bus Record deleted");
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	
	@DeleteMapping("/busSchedule/delete/{bid}")
	public ResponseEntity<?> deleteBusSchedule(@PathVariable("bid") int bid) {
		try {
			BusSchedule busSchedule = busScheduleService.getById(bid);
			busScheduleService.deleteBusSchedule(busSchedule.getId());
			return ResponseEntity.ok().body("BusSchedule Record deleted");
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	
}


