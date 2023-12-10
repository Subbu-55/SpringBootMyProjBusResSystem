package com.springboot.main.myproj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.main.myproj.model.BusOperator;

public interface BusOperatorRepository extends JpaRepository<BusOperator, Integer>{

	List<BusOperator> findByExecutiveId(int eid);

	@Query("Select bo from BusOperator bo where bo.user.id=?1")
	BusOperator getByUserId(int id);

	

}
