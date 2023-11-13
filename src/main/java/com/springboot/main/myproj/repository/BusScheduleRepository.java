package com.springboot.main.myproj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.myproj.model.BusSchedule;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Integer>{

	List<BusSchedule> findByBusOperatorId(int boid);
<<<<<<< HEAD
=======

>>>>>>> 29d9290dcc5ed0bd11f944c1fb78050f2d84b6c1

}
