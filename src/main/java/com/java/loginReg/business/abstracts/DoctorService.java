package com.java.loginReg.business.abstracts;

import java.util.List;

import com.java.loginReg.entities.Doctor;
import com.java.loginReg.entities.DoctorDto;
import com.java.loginReg.entities.Specialization;

public interface DoctorService {

	List<Doctor> getAllDoctors();
	
	Doctor save(Doctor doctor);
	
	Doctor getDoctorById(Long id);
	
	List<Doctor> findBySpecialization(Specialization specialization);
	
	boolean updateDoctor(Long id, DoctorDto doctorDto);
	

}
