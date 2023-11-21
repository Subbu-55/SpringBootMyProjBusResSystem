package com.springboot.main.myproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.myproj.exception.InvalidIdException;
import com.springboot.main.myproj.model.Bus;
import com.springboot.main.myproj.model.Seat;
import com.springboot.main.myproj.service.BusService;
import com.springboot.main.myproj.service.SeatService;

@RestController
@RequestMapping("/seat")
public class SeatController {

	/*@Autowired
	private SeatService seatService;
	@Autowired
	private BusService busService;
	
	@PostMapping("/add/{bid}")
	public ResponseEntity<?> assignSeats(@PathVariable("bid")int bid,@RequestBody List<Seat> seats) {
		try {
			Bus bus=busService.getById(bid);
			for (Seat seat : seats) {
	            seatService.insert(seat);
	        }
			return ResponseEntity.ok().body(seats);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
	}*/
	
}
