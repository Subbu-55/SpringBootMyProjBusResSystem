package com.springboot.main.myproj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.myproj.model.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer>{

	List<Bus> findByBusOperatorId(int boid);

	List<Bus> findByExecutiveId(int eid);

	List<Bus> findBusById(int bid);

}
