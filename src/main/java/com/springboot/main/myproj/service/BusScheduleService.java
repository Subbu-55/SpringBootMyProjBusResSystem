package com.springboot.main.myproj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.myproj.exception.InvalidIdException;
import com.springboot.main.myproj.model.Bus;
import com.springboot.main.myproj.model.BusSchedule;
import com.springboot.main.myproj.repository.BusScheduleRepository;
@Service
public class BusScheduleService {

	@Autowired
	private BusScheduleRepository busScheduleRepository ;
	public BusSchedule insert(BusSchedule busSchedule) {
		return busScheduleRepository.save(busSchedule);
	}
	public List<BusSchedule> getBusByBusOperatorId(int boid) {

		return busScheduleRepository.findByBusOperatorId(boid);
	}
	public void deleteBusSchedule(int bid) {
        busScheduleRepository.deleteById(bid); 		
	}
	public BusSchedule getById(int bid) throws InvalidIdException {
		Optional<BusSchedule>optional=busScheduleRepository.findById(bid);
		if(!optional.isPresent())
			throw new InvalidIdException("bid is incorrect");
		return optional.get();
	}

}
