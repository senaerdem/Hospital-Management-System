package com.java.loginReg.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.loginReg.business.abstracts.AppointmentService;
import com.java.loginReg.entities.Appointment;
import com.java.loginReg.entities.AppointmentDto;

@RestController
@RequestMapping("/appointments")
@CrossOrigin
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@PostMapping("/create")
	public Appointment createAppointment(@RequestBody AppointmentDto appointmentRequest) {
	    System.out.println("Doctor ID: " + appointmentRequest.getDoctorId());  // Doktor ID kontrolü
	    System.out.println("Patient ID: " + appointmentRequest.getPatientId());  // Hasta ID kontrolü
	    System.out.println("Day: " + appointmentRequest.getDay());  // Gün kontrolü
	    System.out.println("Time: " + appointmentRequest.getTime());  // Saat kontrolü

	    // AppointmentService'i kullanarak randevuyu oluşturma
	    return appointmentService.createAppointment(
	        appointmentRequest.getDoctorId(), 
	        appointmentRequest.getPatientId(), 
	        appointmentRequest.getDay(), 
	        appointmentRequest.getTime()
	    );
	}


}
