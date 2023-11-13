package com.springboot.main.myproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.myproj.model.Customer;
import com.springboot.main.myproj.repository.CustomerRepository;
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	public Customer insert(Customer customer) {
		return customerRepository.save(customer);
	}
	public List<Customer> getCustomer(int cid) {
		return customerRepository.findByCustomerId(cid);
	}

}
