package com.springboot.main.myproj.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Bus {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) // 
	private int id;
	private String busNo;
	private String source;
	private String destination;
	
	@ManyToOne
	private BusOperator busOperator;
	
	@ManyToOne
	private Executive executive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusNo() {
		return busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public BusOperator getBusOperator() {
		return busOperator;
	}

	public void setBusOperator(BusOperator busOperator) {
		this.busOperator = busOperator;
	}

	public Executive getExecutive() {
		return executive;
	}

	public void setExecutive(Executive executive) {
		this.executive = executive;
	}

	@Override
	public String toString() {
		return "Bus [id=" + id + ", busNo=" + busNo + ", source=" + source + ", destination=" + destination
				+ ", busOperator=" + busOperator + ", executive=" + executive + "]";
	}

	

	
	

}