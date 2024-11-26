package com.java.loginReg.business.abstracts;

import java.util.List;

import com.java.loginReg.entities.Appointment;
import com.java.loginReg.entities.Status;

public interface AppointmentService {
	
	Appointment createAppointment(Long doctorId, Long patientId, String day, String time);
	
	List<Appointment> getAllAppointments();
	
	boolean deleteAppointment(Long id);
	
	List<Appointment> getAppointmentsByDoctorId(Long doctorId);
	
	Appointment updateAppointmentStatus(Long appointmentId, Status status);
	
	List<Appointment> getAppointmentsByPatientId(Long patientId);

	boolean isDoctorAvailable(Long doctorId, String day, String time);
	
	Appointment updateAppointment(Long appointmentId, String day, String time);
}
