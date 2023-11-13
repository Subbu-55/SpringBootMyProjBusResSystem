package com.springboot.main.myproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.springboot.main.myproj.service.BusOperatorService;
import com.springboot.main.myproj.service.BusService;
import com.springboot.main.myproj.service.ExecutiveService;

@RestController
@RequestMapping("/bus")
public class BusController {

	@Autowired
	private BusService busService;
	
	@Autowired
	private ExecutiveService executiveService;
	
	@Autowired
	private BusOperatorService busOperatorService;
	@PostMapping("/add/{boid}/{eid}")
	public ResponseEntity<?> assignBus (@PathVariable("boid")int boid,@PathVariable("eid") int eid,
			@RequestBody Bus bus){
		try {
			BusOperator busOperator=busOperatorService.getById(boid);
			Executive executive = executiveService.getById(eid);
			bus.setBusOperator(busOperator);
			bus.setExecutive(executive);
			bus=busService.insert(bus);
			return ResponseEntity.ok().body(bus);
		}
		catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
    }
	@GetMapping("/get-all")
	public List<Bus> getBus(){

		return busService.getBus(); 
	}
	
	@GetMapping("/get/{bid}")
	public List<Bus> getBusById(@PathVariable("bid") int bid){

		return busService.getBusById(bid); 
	}
	
}
