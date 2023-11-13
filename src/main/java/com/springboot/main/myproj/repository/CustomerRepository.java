package com.springboot.main.myproj.repository;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 29d9290dcc5ed0bd11f944c1fb78050f2d84b6c1

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.myproj.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

<<<<<<< HEAD
	List<Customer> findByCustomerId(int cid);
=======

>>>>>>> 29d9290dcc5ed0bd11f944c1fb78050f2d84b6c1

}
