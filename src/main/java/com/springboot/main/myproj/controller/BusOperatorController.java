package com.springboot.main.myproj.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.myproj.dto.BusOperatorDto;
import com.springboot.main.myproj.enums.Role;
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
@CrossOrigin(origins = {"http://localhost:3000"})
public class BusOperatorController {

	@Autowired
	private BusOperatorService busOperatorService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Logger logger;
	
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
		
		user.setRole(Role.BUSOPERATOR);
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
	
	@PutMapping("/update/{boid}")
	public ResponseEntity<?> updateBusOperator(@PathVariable("boid") int boid,@RequestBody BusOperatorDto busOperatorDto){
		try {
			BusOperator busOperator = busOperatorService.getById(boid);
			if(busOperatorDto.getCity()!=null)
				busOperator.setCity(busOperatorDto.getCity());
			if(busOperatorDto.getName()!=null)
				busOperator.setName(busOperatorDto.getName());
			busOperator=busOperatorService.postBusOperator(busOperator);
			logger.info("updated status of busOperator"+busOperator.getCity()+"to"+busOperatorDto.getCity());
			return ResponseEntity.status(HttpStatus.OK).body(busOperator);	
		}catch(InvalidIdException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	
}
