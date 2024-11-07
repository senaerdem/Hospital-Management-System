package com.java.loginReg.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.loginReg.business.abstracts.DoctorService;
import com.java.loginReg.dataAccess.DoctorDao;
import com.java.loginReg.entities.Doctor;

@Service
public class DoctorManager implements DoctorService{
	
	@Autowired
	private DoctorDao doctorDao;

	@Override
	public Doctor save(Doctor doctor) {
		return doctorDao.save(doctor);
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return doctorDao.findAll();
	}

	@Override
	public Doctor findDoctorById(Long id) {
		return doctorDao.findById(id).orElse(null);
	}

}
