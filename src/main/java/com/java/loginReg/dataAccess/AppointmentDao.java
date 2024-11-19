package com.java.loginReg.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.loginReg.entities.Appointment;
import com.java.loginReg.entities.Doctor;
import com.java.loginReg.entities.Patient;

public interface AppointmentDao extends JpaRepository<Appointment, Long> {

	 List<Appointment> findByDoctorId(Long doctorId);
	 
	 List<Appointment> findByPatientId(Long patientId);
	 
	 List<Appointment> findByPatient(Patient patient);
	 
	 List<Appointment> findByDoctor(Doctor doctor);
}
