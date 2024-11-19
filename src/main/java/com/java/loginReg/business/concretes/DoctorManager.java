package com.java.loginReg.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.loginReg.business.abstracts.DoctorService;
import com.java.loginReg.dataAccess.DoctorDao;
import com.java.loginReg.entities.Doctor;
import com.java.loginReg.entities.User;

@Service
public class DoctorManager implements DoctorService{
	
	@Autowired
    private DoctorDao doctorDao;
	
	@Override
	public List<Doctor> getAllDoctors() {
		return doctorDao.findAll();
	}
	
	@Override
	public Doctor save(Doctor doctor) {
		return doctorDao.save(doctor);
	}
	
	// Doktorun ID'sine göre doktoru bulma
    public Doctor getDoctorById(Long id) {
        return doctorDao.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    // Uzmanlık alanına göre doktorları getir
    public List<Doctor> findBySpecialization(String specialization) {
        return doctorDao.findBySpecialization(specialization); // Repository üzerinden veriyi çekiyoruz
    }
    
    public boolean updateDoctor(Long id, Doctor doctor) {
		if (doctorDao.existsById(id)) {
	        Doctor existingDoctor = doctorDao.findById(id).orElseThrow();
	        existingDoctor.setHospital(doctor.getHospital());
	        existingDoctor.setSpecialization(doctor.getSpecialization());
	        existingDoctor.setWorkingDays(doctor.getWorkingDays());
	        existingDoctor.setWorkingHours(doctor.getWorkingHours());
	        doctorDao.save(existingDoctor);
	        return true;
	    }
		return false;
	}
	
}
