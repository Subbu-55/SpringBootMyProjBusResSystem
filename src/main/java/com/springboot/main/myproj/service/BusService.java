package com.springboot.main.myproj.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.myproj.exception.InvalidIdException;
import com.springboot.main.myproj.model.Bus;
import com.springboot.main.myproj.model.BusSchedule;
import com.springboot.main.myproj.model.Seat;
import com.springboot.main.myproj.repository.BusRepository;
import com.springboot.main.myproj.repository.SeatRepository;

@Service
public class BusService {

	@Autowired 
	private  BusRepository busRepository;
	
	@Autowired
	private SeatRepository seatRepository;
	
	public Bus insert(Bus bus) {
		return busRepository.save(bus);
	}
	public List<Bus> getBusByBusOperatorId(int boid) {
		
		return busRepository.findByBusOperatorId(boid);
	}
	public List<Bus> getBusByExecutiveId(int eid) {
		
		return busRepository.findByExecutiveId(eid);
	}
	public Bus getById(int bid) throws InvalidIdException {
		Optional<Bus>optional=busRepository.findById(bid);
		if(!optional.isPresent())
			throw new InvalidIdException("bid is incorrect");
		return optional.get();
	}
	public void deleteBus(int bid) {
		busRepository.deleteById(bid);
		
	}
	public List<Bus> getBus() {
		// TODO Auto-generated method stub
		return busRepository.findAll();
	}
	public List<Bus> getBusById(int bid) {
		// TODO Auto-generated method stub
		return busRepository.findBusById(bid);
	}
	public List<Bus> findby(String source, String destination, LocalDate date) {
		// TODO Auto-generated method stub
		return busRepository.findBy(source,destination,date);
	}
	/*public void updateSeatAvailability(int bid, int sid, String newStatus) throws InvalidIdException {
		try {
	    Seat seat = seatRepository.findByBusIdAndSeatId(bid, sid);
	    if (seat == null) {
	        throw new InvalidIdException("Seat not found for busId");
	    }
	    if ("AVAILABLE".equalsIgnoreCase(seat.getAvailable()) && "UNAVAILABLE".equalsIgnoreCase(newStatus)) {
	        seat.setAvailable("UNAVAILABLE");
	    } else if (!"AVAILABLE".equalsIgnoreCase(seat.getAvailable()) && "AVAILABLE".equalsIgnoreCase(newStatus)) {
	        seat.setAvailable("AVAILABLE");
	    }

	    seatRepository.save(seat);
		}catch(Exception e) {
			e.getMessage();
		}
	}*/
	public List<Bus> getBusesWithFilters( String seatType, Boolean hasPersonalScreen,
			Boolean hasWaterBottle, Boolean hasBlanket, Boolean hasChargingPoints) {
		return busRepository.findWithFilters(seatType, hasPersonalScreen, hasWaterBottle, hasBlanket, hasChargingPoints);
	}

	
	

}
