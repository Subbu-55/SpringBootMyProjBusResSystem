package com.springboot.main.myproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.myproj.model.BusSchedule;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Integer>{

}
