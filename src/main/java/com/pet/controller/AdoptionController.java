package com.pet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.pet.db.AdoptionRepo;
import com.pet.model.Adoption;

@CrossOrigin
@RestController
@RequestMapping("/api/adoptions")
public class AdoptionController {
	
	@Autowired
	private AdoptionRepo actorRepo;
	
	@GetMapping("/")
	public List<Adoption> getAllAdoptions() {
		return actorRepo.findAll();
	}

	@GetMapping("/{id}")
	public Adoption getAdoptionById(@PathVariable int id) {
		Optional<Adoption> a = actorRepo.findById(id);
		if (a.isPresent()) {
			return a.get();
		}
		else {
			System.err.println("Get Adoption error: id ["+id+"] does not exist.");
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Adoption Not Found: id ["+id+"]");
		}
	}
	
	@PostMapping("")
	public Adoption addAdoption(@RequestBody Adoption actor) {
		return actorRepo.save(actor);
	}
	
	@PutMapping("/{id}")
	public Adoption updateAdoption(@PathVariable int id, @RequestBody Adoption actor) {
		Adoption a = null;
		if (id != actor.getId()) {
			System.err.println("Adoption id [" + actor.getId()+"] does not match path id["+id+"].");
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Adoption Not Found");
		}
		else if (!actorRepo.existsById(id)) {
			System.err.println("Adoption does not exist for id: "+id);
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Adoption Not Found");
		}
		else {
			try {
				a = actorRepo.save(actor);
			}
			catch (Exception e) {
				System.err.println(e);
				throw new ResponseStatusException(
						HttpStatus.INTERNAL_SERVER_ERROR, "Error Saving Adoption");
			}
		}
		return a;
	}

	@DeleteMapping("/{id}")
	public boolean deleteAdoption(@PathVariable int id) {
		boolean success = false;
		if (actorRepo.existsById(id)) {
			try {
				actorRepo.deleteById(id);
			}
			catch (Exception e) {
				throw new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Error deleting Adoption for id: "+id);
			}
			success = true;
		}
		else {
			System.err.println("Delete Error: No actor exists for id: "+id);
			success = false;
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Adoption Not Found");
		}
		return success;
	}

}
