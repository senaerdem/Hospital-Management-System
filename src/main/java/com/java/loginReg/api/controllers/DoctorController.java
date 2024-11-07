package com.java.loginReg.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.loginReg.business.abstracts.DoctorService;
import com.java.loginReg.entities.Doctor;

@RestController
@RequestMapping("/doctors")
@CrossOrigin
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/add")
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
		Doctor savedDoctor = doctorService.save(doctor);
		return ResponseEntity.ok(savedDoctor);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		List<Doctor> doctors = doctorService.getAllDoctors();
		return ResponseEntity.ok(doctors);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
		Doctor doctor = doctorService.findDoctorById(id);
		return doctor != null ? ResponseEntity.ok(doctor) : ResponseEntity.notFound().build();
	}
	
}
