package com.springboot.main.myproj.service;

import java.util.List;
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

	

	public List<BusOperator> getBusOperatorByExecitiveId(int eid) {
		return busOperatorRepository.findByExecutiveId(eid);
	}



	public BusOperator getById(int boid) throws InvalidIdException {
		Optional<BusOperator> optional = busOperatorRepository.findById(boid);
		if(!optional.isPresent())
			throw new InvalidIdException("boid invalid");
		return optional.get();
	}

}
