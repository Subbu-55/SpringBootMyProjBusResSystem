package com.springboot.main.myproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.myproj.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}