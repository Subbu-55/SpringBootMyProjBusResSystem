package com.springboot.main.myproj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.main.myproj.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer>{

	@Query("Select s.seatNumber from Seat s where s.bus.id=?1")
	List<?> findseats(int bid);

	@Query("Select s.seatNumber from Seat s where s.bus.id=?1 AND s.available='AVAILABLE'")
	List<?> findavailableseats(int bid);

	@Query("Select s from Seat s where s.seatNumber=?1 AND s.bus.id=?2")
	Seat getSeat(String seatNo, int bid);

	/*@Query(value="select * from bus b , seat s where b.id=s.bus_id",nativeQuery = true)
	Seat findByBusIdAndSeatId(int bid, int sid);*/

}
