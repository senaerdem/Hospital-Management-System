package com.java.loginReg.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.loginReg.business.abstracts.AppointmentService;
import com.java.loginReg.entities.Appointment;

@RestController
@RequestMapping("/appointments")
@CrossOrigin
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@PostMapping("/create")
    public Appointment createAppointment(@RequestParam Long doctorId, @RequestParam Long patientId, 
                                        @RequestParam String day, @RequestParam String time) {
        return appointmentService.createAppointment(doctorId, patientId, day, time);
    }

}
