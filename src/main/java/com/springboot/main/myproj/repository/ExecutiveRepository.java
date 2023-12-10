package com.springboot.main.myproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.main.myproj.model.Executive;

public interface ExecutiveRepository extends JpaRepository<Executive, Integer>{

	@Query("Select e from Executive e where e.user.id=?1")
	Executive getByUserId(int id);

}
