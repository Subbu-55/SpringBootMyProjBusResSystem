package com.springboot.main.myproj.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.myproj.model.CustomerBus;
import com.springboot.main.myproj.repository.CustomerBusRepository;

@Service
public class CustomerBusService {

	@Autowired
	private CustomerBusRepository customerBusRepository;
	
	public CustomerBus insert(CustomerBus customerBus) {
		return  customerBusRepository.save(customerBus);
	}
	public void changestatus(String seatNo){
	  customerBusRepository.changestatus(seatNo);
	  }
	public List<CustomerBus> findall(int cid) {

		
		List<CustomerBus>list = customerBusRepository.findall(cid);
		
	    return list;
		
	}

}
