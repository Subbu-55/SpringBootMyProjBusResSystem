package com.springboot.main.myproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.main.myproj.model.CustomerBus;

public interface CustomerBusRepository extends JpaRepository<CustomerBus, Integer>{

	@Modifying
	@Transactional
	@Query(value="update seat set available='UNAVAILABLE' where seat_number=?1",nativeQuery=true)
	 void changestatus(String seatNo);
}
