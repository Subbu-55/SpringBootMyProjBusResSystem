package com.springboot.main.myproj.service;

<<<<<<< HEAD
import java.util.List;
=======
import java.util.Optional;
>>>>>>> 29d9290dcc5ed0bd11f944c1fb78050f2d84b6c1

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
<<<<<<< HEAD
	public List<Customer> getCustomer(int cid) {
		return customerRepository.findByCustomerId(cid);
=======
	public Customer getById(int cid)throws InvalidIdException {
		Optional<Customer> optional=customerRepository.findById(cid);
		if(!optional.isPresent())
			throw new InvalidIdException("cid invalid");
				return optional.get();
>>>>>>> 29d9290dcc5ed0bd11f944c1fb78050f2d84b6c1
	}

}
