package com.springboot.main.myproj.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity 
public class BusOperator {
	@Id // 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int id;
	private String name;
	private String city;

	@ManyToOne
	private Executive executive;
	
	@OneToOne
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getCity() {
		return city;
	}

	
	public void setCity(String city) {
		this.city = city;
	}

	public Executive getExecutive() {
		return executive;
	}

	public void setExecutive(Executive executive) {
		this.executive = executive;
	}

	@Override
	public String toString() {
		return "BusOperator [id=" + id + ", name=" + name + ", city=" + city + ", executive=" + executive + ", user="
				+ user + "]";
	}

	
	
	
}