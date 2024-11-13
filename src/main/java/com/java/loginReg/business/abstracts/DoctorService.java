package com.java.loginReg.business.abstracts;

import java.util.List;

import com.java.loginReg.entities.Doctor;

public interface DoctorService {

	List<Doctor> getAllDoctors();
	
	boolean updateDoctor(Long id, Doctor doctor);

}
