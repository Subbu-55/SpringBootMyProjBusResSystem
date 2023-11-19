package com.springboot.main.myproj.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class Bus {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) // 
	private int id;
	private String busNo;
	private String source;
	private String destination;
    

    private String seatType;
    
    private int seatsAvailable;

    private Boolean hasPersonalScreen;

    private Boolean hasWaterBottle;

    private Boolean hasBlanket;

    private Boolean hasChargingPoints;
    
    private Boolean hasAc;
	
	public Boolean getHasAc() {
		return hasAc;
	}

	public void setHasAc(Boolean hasAc) {
		this.hasAc = hasAc;
	}

	@ManyToOne
	private BusOperator busOperator;
	
	@ManyToOne
	private Executive executive;
	
	@OneToOne
	private Seat seat;

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

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

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public Boolean getHasPersonalScreen() {
		return hasPersonalScreen;
	}

	public void setHasPersonalScreen(Boolean hasPersonalScreen) {
		this.hasPersonalScreen = hasPersonalScreen;
	}

	public Boolean getHasWaterBottle() {
		return hasWaterBottle;
	}

	public void setHasWaterBottle(Boolean hasWaterBottle) {
		this.hasWaterBottle = hasWaterBottle;
	}

	public Boolean getHasBlanket() {
		return hasBlanket;
	}

	public void setHasBlanket(Boolean hasBlanket) {
		this.hasBlanket = hasBlanket;
	}

	public Boolean getHasChargingPoints() {
		return hasChargingPoints;
	}

	public void setHasChargingPoints(Boolean hasChargingPoints) {
		this.hasChargingPoints = hasChargingPoints;
	}
	

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	@Override
	public String toString() {
		return "Bus [id=" + id + ", busNo=" + busNo + ", source=" + source + ", destination=" + destination
				+ ", busOperator=" + busOperator + ", executive=" + executive + "]";
	}

	

	

	
	

}