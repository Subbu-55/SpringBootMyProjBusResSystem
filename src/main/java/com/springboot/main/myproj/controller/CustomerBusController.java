package com.springboot.main.myproj.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.myproj.dto.PassengerDto;
import com.springboot.main.myproj.exception.InvalidIdException;
import com.springboot.main.myproj.model.Bus;
import com.springboot.main.myproj.model.BusSchedule;
import com.springboot.main.myproj.model.Customer;
import com.springboot.main.myproj.model.CustomerBus;
import com.springboot.main.myproj.model.Seat;
import com.springboot.main.myproj.service.BusScheduleService;
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
	@Autowired
	private BusScheduleService busScheduleService;
	
	@Autowired
	private Seat seat;
	
	
	
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
	@PostMapping("/booking/multiple/{cid}/{bid}")
	public ResponseEntity<?> bookTickets(@PathVariable("cid") int cid, @PathVariable("bid") int bid,
			@RequestBody List<PassengerDto> passengerDtoList) {
		try {
			Customer customer = customerService.getById(cid);
			Bus bus = busService.getById(bid);
			List<CustomerBus> bookedTickets = new ArrayList<>();
            double totalPrice=0;
			for (PassengerDto passengerDto : passengerDtoList) {
				CustomerBus customerBus = new CustomerBus();
				BusSchedule busSchedule =busScheduleService.getByBusId(bid);
				customerBus.setCustomer(customer);
				customerBus.setBus(bus);
				customerBus.setDateOfBooking(LocalDate.now());
				// Set passenger-specific information from the DTO
				customerBus.setPassengerName(passengerDto.getPassengerName());
				customerBus.setAge(passengerDto.getAge());
				customerBus.setGender(passengerDto.getGender());
				customerBus.setPrice(busSchedule.getFare());
				seat.setSeatNumber(passengerDto.getSeatNo());
				String seatNo=passengerDto.getSeatNo();
				  customerBusService.changestatus(seatNo);
                totalPrice = totalPrice+(customerBus.getPrice());
				// Add the processed ticket to the list
				bookedTickets.add(customerBusService.insert(customerBus));
			}

			Map<String, Object> response = new HashMap<>();
	        response.put("bookedTickets", bookedTickets);
	        response.put("totalPrice", totalPrice);

	        return ResponseEntity.ok().body(response);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
	
	
}
