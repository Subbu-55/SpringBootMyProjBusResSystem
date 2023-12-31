package com.springboot.main.myproj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.myproj.exception.InvalidIdException;
import com.springboot.main.myproj.model.BusOperator;
import com.springboot.main.myproj.model.Customer;
import com.springboot.main.myproj.model.User;
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
	public List<Customer> getCustomer() {
		
		return customerRepository.findAll();
	}
	public Customer postCustomer(Customer customer) {

		return customerRepository.save(customer);
	}
	public void deleteBus(int cid) {
		
		customerRepository.deleteById(cid);
	}
	public  List<Customer> getAll(Pageable pageable) {
		return customerRepository.findAll(pageable).getContent();
	}
	public Customer getByUserId(int uid) {
		
		return customerRepository.finduser(uid);
	}
	public Customer getByUserbyId(int id) {
		
		return customerRepository.findByUserbyId(id);
	}
	
	

}
