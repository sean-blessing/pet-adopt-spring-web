package com.pet.model;

import jakarta.persistence.*;

@Entity
public class Species {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private double adoptionFee;
	
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
	
	public double getAdoptionFee() {
		return adoptionFee;
	}
	
	public void setAdoptionFee(double adoptionFee) {
		this.adoptionFee = adoptionFee;
	}

}
