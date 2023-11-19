package com.springboot.main.myproj.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.myproj.exception.InvalidIdException;
import com.springboot.main.myproj.model.Bus;
import com.springboot.main.myproj.model.BusOperator;
import com.springboot.main.myproj.model.Executive;
import com.springboot.main.myproj.repository.BusRepository;
import com.springboot.main.myproj.service.BusOperatorService;
import com.springboot.main.myproj.service.BusService;
import com.springboot.main.myproj.service.ExecutiveService;

@RestController
@RequestMapping("/bus")
public class BusController {

	@Autowired
	private BusService busService;
	
	@Autowired
	private ExecutiveService executiveService;
	
	@Autowired
	private BusOperatorService busOperatorService;
	
	@Autowired
	private BusRepository busRepository;
	@PostMapping("/add/{boid}/{eid}")
	public ResponseEntity<?> assignBus (@PathVariable("boid")int boid,@PathVariable("eid") int eid,
			@RequestBody Bus bus){
		try {
			BusOperator busOperator=busOperatorService.getById(boid);
			Executive executive = executiveService.getById(eid);
			bus.setBusOperator(busOperator);
			bus.setExecutive(executive);
			bus=busService.insert(bus);
			return ResponseEntity.ok().body(bus);
		}
		catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
    }
	@GetMapping("/get-all")
	public List<Bus> getBus(){

		return busService.getBus(); 
	}
	
	@GetMapping("/get/{bid}")
	public List<Bus> getBusById(@PathVariable("bid") int bid){

		return busService.getBusById(bid); 
	}
	@GetMapping("/get/{source}/{destination}/{date}") 
	public List<Bus> getBussesbysdd(@PathVariable("source") String source,
			@PathVariable("destination") String destination,
			@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		List<Bus> list = busService.findby(source, destination, date);
		return list;

	}
	/*@PutMapping("/seats/{bid}/{seatNumber}")
	public ResponseEntity<?> updateSeatAvailability(@PathVariable("bid") int bid, @PathVariable("sid") int sid, @RequestBody Str
{
	    try {
	        busService.updateSeatAvailability(bid, sid, availabilityStatus);
	        return ResponseEntity.ok("Seat availability updated successfully.");
	    } catch (InvalidIdException e) {
	        // Handle SeatNotFoundException (custom exception or any other exception as needed)
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seat not found");
	    } catch (Exception e) {
	        // Handle other exceptions
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
	    }
	}*/
	@GetMapping("/get/filter")
    public List<Bus> getBusesWithFilters(
            @RequestParam(required = false) String seatType,
            @RequestParam(required = false) Boolean hasPersonalScreen,
            @RequestParam(required = false) Boolean hasWaterBottle,
            @RequestParam(required = false) Boolean hasBlanket,
            @RequestParam(required = false) Boolean hasChargingPoints,
            @RequestParam(required = false)Boolean hasAc) {

		  List<Bus> filteredBuses = busRepository.findAll().stream()
		            .filter(bus -> seatType == null || seatType.equalsIgnoreCase(bus.getSeatType()))
		            .filter(bus -> hasPersonalScreen == null || bus.getHasPersonalScreen() == hasPersonalScreen)
		            .filter(bus -> hasWaterBottle == null || bus.getHasWaterBottle() == hasWaterBottle)
		            .filter(bus -> hasBlanket == null || bus.getHasBlanket() == hasBlanket)
		            .filter(bus -> hasChargingPoints == null || bus.getHasChargingPoints() == hasChargingPoints)
		            .collect(Collectors.toList());

		    return filteredBuses;
    }

	
}
