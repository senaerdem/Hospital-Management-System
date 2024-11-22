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
import com.java.loginReg.business.abstracts.SpecializationService;
import com.java.loginReg.entities.Hospital;
import com.java.loginReg.entities.Specialization;

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

}
