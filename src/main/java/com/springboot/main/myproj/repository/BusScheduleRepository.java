package com.springboot.main.myproj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.main.myproj.model.BusSchedule;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Integer>{

	List<BusSchedule> findByBusOperatorId(int boid);

	@Query("select bs from BusSchedule bs where bs.bus.id=?1")
	BusSchedule findbyBusId(int bid);

	


}
