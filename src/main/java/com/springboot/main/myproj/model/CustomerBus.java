package com.springboot.main.myproj.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CustomerBus {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String dateOfBooking;
	private int noOfPassengers;
	
	@ManyToOne
	private Bus bus;
	
	@ManyToOne
	private Customer customer;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDateOfBooking() {
		return dateOfBooking;
	}
	public void setDateOfBooking(String dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}
	public int getNoOfPassengers() {
		return noOfPassengers;
	}
	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Bus getBus() {
		return bus;
	}
	public void setBus(Bus bus) {
		this.bus = bus;
	}
	@Override
	public String toString() {
		return "CustomerBus [id=" + id + ", dateOfBooking=" + dateOfBooking + ", noOfPassengers=" + noOfPassengers
				+ ", customer=" + customer + ", bus=" + bus +"]";
	}
	
	

}