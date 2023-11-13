package com.springboot.main.myproj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.myproj.model.BusSchedule;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Integer>{

	List<BusSchedule> findByBusOperatorId(int boid);
<<<<<<< HEAD

=======
>>>>>>> abce891822666668ef94ba156407ec566486a4eb

}
