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



	public void deleteBusOperator(int boid) {
		busOperatorRepository.deleteById(boid);
<<<<<<< HEAD
		
=======
>>>>>>> 43f2458d06bf02e1a75a3dc78239cf5c6992325b
	}



<<<<<<< HEAD
	public BusOperator postBusOperator(BusOperator busOperator) {
		
		return busOperatorRepository.save(busOperator);
=======
	public List<BusOperator> getAllBusOperator() {
		return busOperatorRepository.findAll();
>>>>>>> 43f2458d06bf02e1a75a3dc78239cf5c6992325b
	}

}
