package com.springboot.main.myproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.myproj.model.BusOperator;

public interface BusOperatorRepository extends JpaRepository<BusOperator, Integer>{

}
