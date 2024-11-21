package com.java.loginReg.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.loginReg.business.abstracts.HospitalService;
import com.java.loginReg.entities.Hospital;

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
}
