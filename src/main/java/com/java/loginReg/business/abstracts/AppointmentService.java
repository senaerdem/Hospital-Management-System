package com.java.loginReg.business.abstracts;

import java.util.List;

import com.java.loginReg.entities.Appointment;

public interface AppointmentService {
	
	Appointment createAppointment(Long doctorId, Long patientId, String day, String time);
	
	List<Appointment> getAllAppointments();
	
	boolean deleteAppointment(Long id);

}
