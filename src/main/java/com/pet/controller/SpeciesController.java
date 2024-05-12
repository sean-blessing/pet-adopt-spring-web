package com.pet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.pet.db.SpeciesRepo;
import com.pet.model.Species;

@CrossOrigin
@RestController
@RequestMapping("/api/species")
public class SpeciesController {
	
	@Autowired
	private SpeciesRepo speciesRepo;
	
	@GetMapping("/")
	public List<Species> getAllSpeciess() {
		return speciesRepo.findAll();
	}

	@GetMapping("/{id}")
	public Species getSpeciesById(@PathVariable int id) {
		Optional<Species> a = speciesRepo.findById(id);
		if (a.isPresent()) {
			return a.get();
		}
		else {
			System.err.println("Get Species error: id ["+id+"] does not exist.");
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Species Not Found: id ["+id+"]");
		}
	}
	
	@PostMapping("")
	public Species addSpecies(@RequestBody Species species) {
		return speciesRepo.save(species);
	}
	
	@PutMapping("/{id}")
	public Species updateSpecies(@PathVariable int id, @RequestBody Species species) {
		Species a = null;
		if (id != species.getId()) {
			System.err.println("Species id [" + species.getId()+"] does not match path id["+id+"].");
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Species Not Found");
		}
		else if (!speciesRepo.existsById(id)) {
			System.err.println("Species does not exist for id: "+id);
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Species Not Found");
		}
		else {
			try {
				a = speciesRepo.save(species);
			}
			catch (Exception e) {
				System.err.println(e);
				throw new ResponseStatusException(
						HttpStatus.INTERNAL_SERVER_ERROR, "Error Saving Species");
			}
		}
		return a;
	}

	@DeleteMapping("/{id}")
	public boolean deleteSpecies(@PathVariable int id) {
		boolean success = false;
		if (speciesRepo.existsById(id)) {
			try {
				speciesRepo.deleteById(id);
			}
			catch (Exception e) {
				throw new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Error deleting Species for id: "+id);
			}
			success = true;
		}
		else {
			System.err.println("Delete Error: No species exists for id: "+id);
			success = false;
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Species Not Found");
		}
		return success;
	}

}
