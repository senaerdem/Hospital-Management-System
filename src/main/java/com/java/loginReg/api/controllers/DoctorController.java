package com.java.loginReg.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.loginReg.business.abstracts.DoctorService;
import com.java.loginReg.entities.Doctor;
import com.java.loginReg.entities.Role;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorController {
	@Autowired
    private DoctorService doctorService;
	
	@GetMapping("/all")
    public ResponseEntity<List<Doctor>> getAllDoctors(@RequestParam Role role) {
		if (role != Role.DOCTOR) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }
	
	@PostMapping("/add")
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
		Doctor savedDoctor = doctorService.save(doctor);
		return ResponseEntity.ok(savedDoctor);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
		boolean isUpdated = doctorService.updateDoctor(id, doctor);
		if(isUpdated) {
			return ResponseEntity.ok("User updated successfully!");
		} else {
			return ResponseEntity.status(400).body("User not found!");
		}
	}
	

	@GetMapping("/doctors/{id}")
    public ResponseEntity<Object> getDoctorById(@PathVariable Long id, @RequestParam Role role) {
        if (role != Role.DOCTOR) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)  // 403 Forbidden
                    .body("You do not have permission to view this resource.");
        }

        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)  // 404 Not Found
                    .body("Doctor not found.");
        }

        return ResponseEntity.ok(doctor);  // 200 OK ve doktor bilgisi
    }


}
