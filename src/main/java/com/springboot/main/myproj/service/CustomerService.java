package com.springboot.main.myproj.service;

<<<<<<< HEAD
=======

>>>>>>> 43f2458d06bf02e1a75a3dc78239cf5c6992325b
import java.util.List;
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
<<<<<<< HEAD
	public List<Customer> getCustomer() {
		
		return customerRepository.findAll();
	}
	public Customer postCustomer(Customer customer) {

		return customerRepository.save(customer);
	}
=======
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}
>>>>>>> 43f2458d06bf02e1a75a3dc78239cf5c6992325b

}
