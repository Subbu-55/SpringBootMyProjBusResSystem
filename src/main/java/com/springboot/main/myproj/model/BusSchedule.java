package com.springboot.main.myproj.model;



import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class BusSchedule {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private LocalDate doj;
	private int noOfHours;
	private String timeOfJourney;
	private double fare;
		
	@ManyToOne
	private Bus bus;
	
	@ManyToOne
	private BusOperator busOperator;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDoj() {
		return doj;
	}

	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}

	public int getNoOfHours() {
		return noOfHours;
	}

	public void setNoOfHours(int noOfHours) {
		this.noOfHours = noOfHours;
	}

	public String getTimeOfJourney() {
		return timeOfJourney;
	}

	public void setTimeOfJourney(String timeOfJourney) {
		this.timeOfJourney = timeOfJourney;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}
	

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public BusOperator getBusOperator() {
		return busOperator;
	}

	public void setBusOperator(BusOperator busOperator) {
		this.busOperator = busOperator;
	}

	@Override
	public String toString() {
		return "BusSchedule [id=" + id + ", doj=" + doj + ", noOfHours=" + noOfHours + ", timeOfJourney="
				+ timeOfJourney + ", fare=" + fare + ", bus=" + bus +"]";
	}

	

	
}