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
	
	
	// /create endpoint'inde randevu oluşturulacak ve Appointment nesnesi döndürülecek
	@PostMapping("/create")
	public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDto appointmentRequest) {
	    // Randevu oluşturulmadan önce doktorun uygunluğunu kontrol ediyoruz
	    boolean isAvailable = appointmentService.isDoctorAvailable(
	        appointmentRequest.getDoctorId(), 
	        appointmentRequest.getDay(), 
	        appointmentRequest.getTime()
	    );

	    if (!isAvailable) {
	        // Eğer doktor uygun değilse, hatalı bir mesaj dönüyoruz
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                             .body(null);  // Veya uygun bir hata mesajı dönebilirsiniz
	    }

	    // Eğer doktor uygun ise, randevuyu oluşturuyoruz
	    Appointment appointment = appointmentService.createAppointment(
	        appointmentRequest.getDoctorId(), 
	        appointmentRequest.getPatientId(), 
	        appointmentRequest.getDay(), 
	        appointmentRequest.getTime()
	    );

	    // Başarılı bir şekilde randevu oluşturulduysa, Appointment nesnesi döndürüyoruz
	    return ResponseEntity.ok(appointment);
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
    
    // Doktorun belirli bir günde ve saatte uygun olup olmadığını kontrol eden endpoint
    @GetMapping("/check-availability")
    public ResponseEntity<String> checkAvailability(
        @RequestParam Long doctorId, 
        @RequestParam String day, 
        @RequestParam String time) {

        boolean isAvailable = appointmentService.isDoctorAvailable(doctorId, day, time);

        if (isAvailable) {
            return ResponseEntity.ok("Doctor is available at this time.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Doctor is not available at this time.");
        }
    }
    
    // Randevu güncelleyen endpoint
    @PutMapping("/update/{appointmentId}")
    public ResponseEntity<Appointment> updateAppointment( @PathVariable Long appointmentId, @RequestParam String day, String time) {
    	Appointment updatedAppointment = appointmentService.updateAppointment(appointmentId, day, time);
    	if (updatedAppointment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedAppointment);
    }
    
    
}
