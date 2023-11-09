package com.springboot.main.myproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.myproj.model.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer>{

}
