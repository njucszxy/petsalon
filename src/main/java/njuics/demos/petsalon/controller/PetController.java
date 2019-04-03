package njuics.demos.petsalon.controller;

import java.util.List;
import java.util.ArrayList;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import njuics.demos.petsalon.model.Pet;

@RestController
public class PetController {

	private List pets = new ArrayList<Pet>();


	@GetMapping("/pets")
	List<Pet> all() {
		return this.pets;
	}

	@PostMapping("/pets")
	void newPet(@RequestBody Pet newPet) {
		this.pets.add(newPet);
	}
    
}