package com.java.loginReg.business.abstracts;

import java.util.List;

import com.java.loginReg.entities.Doctor;

public interface DoctorService {

	List<Doctor> getAllDoctors();
	
	Doctor save(Doctor doctor);
	
	Doctor getDoctorById(Long id);
	
	List<Doctor> findBySpecialization(String specialization);
	
	boolean updateDoctor(Long id, Doctor doctor);
	

}
