package com.springboot.main.myproj.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.main.myproj.model.Bus;
import com.springboot.main.myproj.model.BusSchedule;

public interface BusRepository extends JpaRepository<Bus, Integer>{

	List<Bus> findByBusOperatorId(int boid);

	List<Bus> findByExecutiveId(int eid);

	List<Bus> findBusById(int bid);
	
    @Query(value="select * from bus b,bus_schedule bs where b.id=bs.bus_id",nativeQuery = true)
	List<Bus> findBy(String source, String destination, LocalDate date);
    
    


}
