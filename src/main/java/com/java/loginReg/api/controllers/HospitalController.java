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

import com.java.loginReg.business.abstracts.HospitalService;
import com.java.loginReg.entities.Hospital;
import com.java.loginReg.entities.HospitalRequestDto;
import com.java.loginReg.entities.User;

@RestController
@RequestMapping("/hospitals")
@CrossOrigin(origins = "http://localhost:3000")
public class HospitalController {
	
	@Autowired
	private HospitalService hospitalService;

	// Hastane eklemek için endpoint
	@PostMapping("/add")
    public ResponseEntity<Hospital> addHospital(@RequestBody Hospital hospital) {
        Hospital savedHospital = hospitalService.addHospital(hospital);
        return ResponseEntity.ok(savedHospital);
    }
	
	// Tüm hastaneleri getiren endpoint
	@GetMapping("/all")
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        return ResponseEntity.ok(hospitals);
    }
	
	// Hastane silme endpointi
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteHospital(@PathVariable Long id) {
		boolean isDeleted = hospitalService.deleteHospital(id);
		if(isDeleted) {
			return ResponseEntity.ok("Hospital deleted successfully!");
		} else {
			return ResponseEntity.status(400).body("Hospital not found!");
		}
	}
	
	// Hastane güncellemek için endpoint
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateHospital(@PathVariable Long id, @RequestBody HospitalRequestDto hospitalRequestDto) {
		boolean isUpdated = hospitalService.updateHospital(id, hospitalRequestDto);
		if(isUpdated) {
			return ResponseEntity.ok("Hospital updated successfully!");
		} else {
			return ResponseEntity.status(400).body("Hospital not found!");
		}
	}
}
