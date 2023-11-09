package com.springboot.main.myproj.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.myproj.exception.InvalidIdException;
import com.springboot.main.myproj.model.BusOperator;
import com.springboot.main.myproj.repository.BusOperatorRepository;
@Service
public class BusOperatorService {
	
	@Autowired
	private BusOperatorRepository busOperatorRepository;
	
	

	public BusOperator insert(BusOperator busOperator) {
		
		return busOperatorRepository.save(busOperator);
	}

	public BusOperator getById(int eid) throws InvalidIdException {
		Optional<BusOperator> optional = busOperatorRepository.findById(eid);
		if(!optional.isPresent())
			throw new InvalidIdException("boid invalid");
		return optional.get();
	}

}
