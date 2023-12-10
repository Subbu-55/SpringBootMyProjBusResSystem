package com.springboot.main.myproj.service;

import java.util.List;

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
	public List<?> getseats(int bid) {
		// TODO Auto-generated method stub
		return seatRepository.findseats(bid);
	}
	public List<?> getavailableseats(int bid) {
		// TODO Auto-generated method stub
		return seatRepository.findavailableseats(bid);
	}
	public Seat getSeat(String seatNo, int bid) {
		// TODO Auto-generated method stub
		return seatRepository.getSeat(seatNo,bid);
		
	}

}
