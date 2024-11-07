package com.java.loginReg.business.abstracts;

import java.util.List;

import com.java.loginReg.entities.Doctor;

public interface DoctorService {

	Doctor save(Doctor doctor);
	List<Doctor> getAllDoctors();
	Doctor findDoctorById(Long id);
}
