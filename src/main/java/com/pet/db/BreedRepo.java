package com.pet.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pet.model.Breed;

public interface BreedRepo extends JpaRepository<Breed, Integer>{

}
