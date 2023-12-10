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

    @Query(value="SELECT * FROM bus b " +
            "WHERE (:seatType IS NULL OR b.seat_type = :seatType) " +
            "AND (:hasPersonalScreen IS NULL OR b.has_personal_screen = :hasPersonalScreen) " +
            "AND (:hasWaterBottle IS NULL OR b.has_water_bottle = :hasWaterBottle) " +
            "AND (:hasBlanket IS NULL OR b.has_blanket = :hasBlanket) " +
            "AND (:hasChargingPoints IS NULL OR b.has_charging_points = :hasChargingPoints)",nativeQuery=true)
	List<Bus> findWithFilters( String seatType, Boolean hasPersonalScreen, Boolean hasWaterBottle,
			Boolean hasBlanket, Boolean hasChargingPoints);
    

    
    

    @Query("SELECT bs FROM BusSchedule bs WHERE bs.bus.source = ?1 AND bs.bus.destination = ?2 AND bs.doj= ?3")
	List<BusSchedule> findBysdd(String source, String destination, LocalDate parsedDate);

    @Query("SELECT bs FROM BusSchedule bs WHERE bs.bus.source = ?1 AND bs.bus.destination = ?2 AND bs.doj= ?3 AND bs.bus.seatType=?4")
	List<BusSchedule> findwithseatType(String source, String destination, LocalDate parsedDate, String seatType);

    @Query("SELECT bs FROM BusSchedule bs WHERE bs.bus.source = ?1 AND bs.bus.destination = ?2 AND bs.doj= ?3 AND bs.bus.seatType=?4 AND bs.bus.busType=?5")
	List<BusSchedule> findwithbusseatType(String source, String destination, LocalDate parsedDate, String seatType,
			String busType);

  
    @Query("SELECT bs FROM BusSchedule bs WHERE bs.bus.source = ?1 AND bs.bus.destination = ?2 AND bs.doj= ?3 AND bs.bus.busType=?4")
	List<BusSchedule> findwithbusType(String source, String destination, LocalDate parsedDate, String busType);

    @Query(value = "Select * from bus where id=?1",nativeQuery = true)
	List<Bus> findBuswithbusid(int bid);

    

    
    


    
    


}
