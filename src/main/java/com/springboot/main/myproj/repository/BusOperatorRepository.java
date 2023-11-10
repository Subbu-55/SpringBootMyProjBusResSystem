package com.springboot.main.myproj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.myproj.model.BusOperator;

public interface BusOperatorRepository extends JpaRepository<BusOperator, Integer>{

	List<BusOperator> findByExecutiveId(int eid);

}
