package com.springboot.main.myproj.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.myproj.exception.InvalidIdException;
import com.springboot.main.myproj.model.Customer;
import com.springboot.main.myproj.repository.CustomerRepository;
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer insert(Customer customer) {
		return customerRepository.save(customer);
	}
	public Customer getById(int cid)throws InvalidIdException {
		Optional<Customer> optional=customerRepository.findById(cid);
		if(!optional.isPresent())
			throw new InvalidIdException("cid invalid");
				return optional.get();
	}

}
