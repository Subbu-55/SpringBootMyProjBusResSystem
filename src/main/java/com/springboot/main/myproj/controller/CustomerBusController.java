package com.springboot.main.myproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.myproj.exception.InvalidIdException;
import com.springboot.main.myproj.model.Bus;
import com.springboot.main.myproj.model.Customer;
import com.springboot.main.myproj.model.CustomerBus;
import com.springboot.main.myproj.service.BusService;
import com.springboot.main.myproj.service.CustomerBusService;
import com.springboot.main.myproj.service.CustomerService;

@RestController
@RequestMapping("/customerBus")
public class CustomerBusController {

	@Autowired
	private CustomerBusService customerBusService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BusService busService;
	
	
	@PostMapping("/add/{cid}/{bid}")
	public ResponseEntity<?> insertCustomerBus(@PathVariable("cid") int cid,@PathVariable("bid") int bid,
			@RequestBody CustomerBus customerBus) {
		try {
		Customer customer =customerService.getById(cid);
		Bus bus = busService.getById(bid);
		customerBus.setBus(bus);
		customerBus.setCustomer(customer);
		customerBus=customerBusService.insert(customerBus);
		return ResponseEntity.ok().body(customerBus);
		}catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
}
