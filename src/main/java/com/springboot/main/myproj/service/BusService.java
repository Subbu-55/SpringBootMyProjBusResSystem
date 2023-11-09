package com.springboot.main.myproj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.myproj.model.Bus;
import com.springboot.main.myproj.repository.BusRepository;

@Service
public class BusService {

	@Autowired 
	private BusRepository busRepository;
	public Bus insert(Bus bus) {
		return busRepository.save(bus);
	}

}
