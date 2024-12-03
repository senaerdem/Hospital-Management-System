package com.java.loginReg.dataAccess;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.loginReg.entities.Appointment;
import com.java.loginReg.entities.Doctor;
import com.java.loginReg.entities.Patient;

public interface AppointmentDao extends JpaRepository<Appointment, Long> {

	 List<Appointment> findByDoctorId(Long doctorId);
	 
	 List<Appointment> findByPatientId(Long patientId);
	 
	 List<Appointment> findByPatient(Patient patient);
	 
	 List<Appointment> findByDoctor(Doctor doctor);
	 
	 // Doktorun o gün ve saatteki randevuları getiren metod
	 List<Appointment> findByDoctorIdAndDayAndTime(Long doctorId, String day, String time);
	 
	 List<Appointment> findByDoctorIdAndDayIn(Long doctorId, List<String> days);


}
