package com.springboot.main.myproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.myproj.model.BusSchedule;
import com.springboot.main.myproj.repository.BusScheduleRepository;
@Service
public class BusScheduleService {

	@Autowired
	private BusScheduleRepository busScheduleRepository;
	public BusSchedule insert(BusSchedule busSchedule) {
		return busScheduleRepository.save(busSchedule);
	}
	public List<BusSchedule> getBusByBusOperatorId(int boid) {
<<<<<<< HEAD
		
=======
>>>>>>> abce891822666668ef94ba156407ec566486a4eb
		return busScheduleRepository.findByBusOperatorId(boid);
	}

}
