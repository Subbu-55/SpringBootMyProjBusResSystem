package com.springboot.main.myproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.main.myproj.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer>{

	/*@Query(value="select * from bus b , seat s where b.id=s.bus_id",nativeQuery = true)
	Seat findByBusIdAndSeatId(int bid, int sid);*/

}
