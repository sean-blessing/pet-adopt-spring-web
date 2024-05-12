package com.pet.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pet.model.Pet;

public interface PetRepo extends JpaRepository<Pet, Integer>{

}
