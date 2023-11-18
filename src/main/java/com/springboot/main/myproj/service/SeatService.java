package com.springboot.main.myproj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.myproj.model.Seat;
import com.springboot.main.myproj.repository.SeatRepository;
@Service
public class SeatService {

	@Autowired
	private SeatRepository seatRepository;
	public Seat insert(Seat seat){
		// TODO Auto-generated method stub
		return seatRepository.save(seat);
	}

}
