package com.java.loginReg.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.loginReg.business.abstracts.SpecializationService;
import com.java.loginReg.entities.Specialization;
import com.java.loginReg.entities.SpecializationRequestDto;

@RestController
@RequestMapping("/specializations")
@CrossOrigin(origins = "http://localhost:3000")
public class SpecializationController {
	
	@Autowired
	private SpecializationService specializationService;

	// Uzmanlık eklemek için endpoint
	@PostMapping("/add")
    public ResponseEntity<Specialization> addHospital(@RequestBody Specialization specialization) {
        Specialization savedSpecialization = specializationService.addSpecialization(specialization);
        return ResponseEntity.ok(savedSpecialization);
    }
	
	// Tüm uzmanlıkları getiren endpoint
	@GetMapping("/all")
    public ResponseEntity<List<Specialization>> getAllSpecializations() {
        List<Specialization> specializations = specializationService.getAllSpecializations();
        return ResponseEntity.ok(specializations);
    }
	
	// Uzmanlık silme endpointi
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteSpecialization(@PathVariable Long id) {
		boolean isDeleted = specializationService.deleteSpecialization(id);
		if(isDeleted) {
			return ResponseEntity.ok("Specilization deleted successfully!");
		} else {
			return ResponseEntity.status(400).body("Specilization not found!");
		}
	}
	
	// Uzmanlık güncellemek için endpoint
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateSpecialization(@PathVariable Long id, @RequestBody SpecializationRequestDto specializationRequestDto) {
		boolean isUpdated = specializationService.updateSpecialization(id, specializationRequestDto);
		if(isUpdated) {
			return ResponseEntity.ok("Specialization updated successfully!");
		} else {
			return ResponseEntity.status(400).body("Specialization not found!");
		}
	}

}
