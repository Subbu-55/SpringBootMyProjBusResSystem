package com.springboot.main.myproj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.myproj.model.CustomerBus;

public interface CustomerBusRepository extends JpaRepository<CustomerBus, Integer>{

	List<CustomerBus> findByCustomerId(int cid);

}
