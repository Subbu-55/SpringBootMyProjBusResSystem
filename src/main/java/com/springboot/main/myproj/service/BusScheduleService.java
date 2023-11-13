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
		
>>>>>>> 29d9290dcc5ed0bd11f944c1fb78050f2d84b6c1
		return busScheduleRepository.findByBusOperatorId(boid);
	}

}
