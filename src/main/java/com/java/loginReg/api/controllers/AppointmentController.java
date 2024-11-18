package com.java.loginReg.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.java.loginReg.business.abstracts.AppointmentService;
import com.java.loginReg.entities.Appointment;
import com.java.loginReg.entities.AppointmentDto;
import com.java.loginReg.entities.Status;

@RestController
@RequestMapping("/appointments")
@CrossOrigin
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	
	@PostMapping("/create")
	public Appointment createAppointment(@RequestBody AppointmentDto appointmentRequest) {
	    // AppointmentService'i kullanarak randevuyu oluşturma
	    return appointmentService.createAppointment(
	        appointmentRequest.getDoctorId(), 
	        appointmentRequest.getPatientId(), 
	        appointmentRequest.getDay(), 
	        appointmentRequest.getTime()
	    );
	}
	
	// Doctor id'ye göre randevuları listeleyen endpoint
	@GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
        return ResponseEntity.ok(appointments);
    }
	
	// Randevu durumunu güncelleyen endpoint
    @PutMapping("/update-status/{appointmentId}")
    public ResponseEntity<Appointment> updateAppointmentStatus(
            @PathVariable Long appointmentId,
            @RequestParam Status status) {

        Appointment updatedAppointment = appointmentService.updateAppointmentStatus(appointmentId, status);

        if (updatedAppointment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedAppointment);
    }
    
    // Hasta id'ye göre randevuları listeleyen endpoint
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable Long patientId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patientId);
        return ResponseEntity.ok(appointments);
    }

}
