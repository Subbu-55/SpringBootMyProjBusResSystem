package com.springboot.main.myproj.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.myproj.dto.BusDto;
import com.springboot.main.myproj.exception.InvalidIdException;
import com.springboot.main.myproj.model.Bus;
import com.springboot.main.myproj.model.BusOperator;
import com.springboot.main.myproj.model.BusSchedule;
import com.springboot.main.myproj.model.Executive;
import com.springboot.main.myproj.repository.BusRepository;
import com.springboot.main.myproj.service.BusOperatorService;
import com.springboot.main.myproj.service.BusService;
import com.springboot.main.myproj.service.ExecutiveService;

@RestController
@RequestMapping("/bus")
@CrossOrigin(origins = {"http://localhost:3000"})
public class BusController {

	@Autowired
	private BusService busService;
	
	@Autowired
	private ExecutiveService executiveService;
	
	@Autowired
	private BusOperatorService busOperatorService;
	
	@Autowired
	private Logger logger;
	
	@Autowired
	private BusRepository busRepository;
	@PostMapping("/add/{boid}")
	public ResponseEntity<?> assignBus (@PathVariable("boid")int boid,
			@RequestBody Bus bus){
		try {
			BusOperator busOperator=busOperatorService.getById(boid);
			
			bus.setBusOperator(busOperator);
			
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
	
	@GetMapping("/get-all/{bid}")
	public List<Bus> getBuswithbusid(@PathVariable("bid") int bid){

		return busService.getBuswithbusid(bid); 
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
//	@GetMapping("/getbysdd")
//	public List<Bus> getBusses(
//            @RequestParam(required = false) String source,
//            @RequestParam(required = false) String destination,
//            @RequestParam(required = false) String date)
//	{
//        return busService.findbysdd(source, destination, date);
//    }
	@GetMapping("/getbysdd/hi")
	public List<BusSchedule> getBusses(@RequestParam String source,@RequestParam String destination,@RequestParam String doj){
		return busService.findbysdd(source, destination, doj);
	}
	
	
//	@GetMapping("/get/filter")
//    public List<Bus> getBusesWithFilters(
//            @RequestParam(required = false) String seatType,
//            @RequestParam(required = false) Boolean hasPersonalScreen,
//            @RequestParam(required = false) Boolean hasWaterBottle,
//            @RequestParam(required = false) Boolean hasBlanket,
//            @RequestParam(required = false) Boolean hasChargingPoints,
//            @RequestParam(required = false)Boolean hasAc) {
//
//		  List<Bus> filteredBuses = busRepository.findAll().stream()
//		            .filter(bus -> seatType == null || seatType.equalsIgnoreCase(bus.getSeatType()))
//		            .filter(bus -> hasPersonalScreen == null || bus.getHasPersonalScreen() == hasPersonalScreen)
//		            .filter(bus -> hasWaterBottle == null || bus.getHasWaterBottle() == hasWaterBottle)
//		            .filter(bus -> hasBlanket == null || bus.getHasBlanket() == hasBlanket)
//		            .filter(bus -> hasChargingPoints == null || bus.getHasChargingPoints() == hasChargingPoints)
//		            .collect(Collectors.toList());
//
//		    return filteredBuses;
//    }
	
	@GetMapping("/getwithseatType")
	public List<BusSchedule> findwithseatType(@RequestParam String source,@RequestParam String destination,@RequestParam String doj,@RequestParam String seatType){
		return busService.findwithseatType(source, destination, doj, seatType);
	}
	@GetMapping("/getwithbusseatType")
	public List<BusSchedule> findwithbusseatType(@RequestParam String source,@RequestParam String destination,@RequestParam String doj,@RequestParam String seatType,@RequestParam String busType){
		return busService.findwithbusseatType(source, destination, doj, seatType,busType);
	}
	@GetMapping("/getwithbusType")
	public List<BusSchedule> findwithbusType(@RequestParam String source,@RequestParam String destination,@RequestParam String doj,@RequestParam String busType){
		return busService.findwithbusType(source, destination, doj,busType);
	}
	@PutMapping("/update/{bid}")
	public ResponseEntity<?> updateBus(@PathVariable("bid")int bid,@RequestBody BusDto busDto) {
		try {
			Bus bus=busService.getById(bid);
			if(busDto.getBusNo()!=null)
				bus.setBusNo(busDto.getBusNo());
			if(busDto.getBusType()!=null)
				bus.setBusType(busDto.getBusType());
			if(busDto.getSource()!=null)
				bus.setSource(busDto.getSource());
			if(busDto.getDestination()!=null)
				bus.setDestination(busDto.getDestination());
			if(busDto.getSeatType()!=null)
				bus.setSeatType(busDto.getSeatType());			
			if(busDto.getSeatsAvailable()!=0)
				bus.setSeatsAvailable(busDto.getSeatsAvailable());
	        bus=busService.insert(bus);
			
			logger.info("updated status of busOperator"+bus.getSource()+"to"+busDto.getSource());
			return ResponseEntity.status(HttpStatus.OK).body(bus);	
			
		}catch(InvalidIdException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	
}
