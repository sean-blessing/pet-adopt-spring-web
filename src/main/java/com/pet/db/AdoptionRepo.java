package com.pet.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pet.model.Adoption;

public interface AdoptionRepo extends JpaRepository<Adoption, Integer>{

}
