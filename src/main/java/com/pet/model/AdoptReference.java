package com.pet.model;

import jakarta.persistence.*;

@Entity
public class AdoptReference {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="AdoptionId")
	private Adoption adoption;
	@ManyToOne
	@JoinColumn(name="ReferenceId")
	private Reference reference;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Adoption getAdoption() {
		return adoption;
	}

	public void setAdoption(Adoption adoption) {
		this.adoption = adoption;
	}

	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}
	
	

}
