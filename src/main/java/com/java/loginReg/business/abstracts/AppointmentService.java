package com.java.loginReg.business.abstracts;

import com.java.loginReg.entities.Appointment;

public interface AppointmentService {
	
	Appointment createAppointment(Long doctorId, Long patientId, String day, String time);

}
