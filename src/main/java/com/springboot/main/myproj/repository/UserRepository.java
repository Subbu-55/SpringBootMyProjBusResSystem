package com.springboot.main.myproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.myproj.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

}
