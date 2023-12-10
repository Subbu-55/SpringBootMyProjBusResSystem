package com.springboot.main.myproj.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.main.myproj.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
	
	@Query("select c from Customer c where c.user.id=?1")
	Customer finduser(int uid);

	@Query("select c from Customer c where c.user.id=?1")
	Customer findByUserbyId(int id);

	//List<Customer> findByCustomerId(int customerid);

	//List<Customer> findByCustomerId(int cid);



}
