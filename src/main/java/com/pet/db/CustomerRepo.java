package com.pet.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pet.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
