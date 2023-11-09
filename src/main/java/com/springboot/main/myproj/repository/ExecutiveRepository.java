package com.springboot.main.myproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.myproj.model.Executive;

public interface ExecutiveRepository extends JpaRepository<Executive, Integer>{

}
