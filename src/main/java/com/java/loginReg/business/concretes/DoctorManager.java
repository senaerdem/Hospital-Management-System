package com.java.loginReg.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.loginReg.business.abstracts.DoctorService;
import com.java.loginReg.dataAccess.DoctorDao;
import com.java.loginReg.entities.Doctor;
import com.java.loginReg.entities.Specialization;
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
    public List<Doctor> findBySpecialization(Specialization specialization) {
        return doctorDao.findBySpecialization(specialization); // Repository üzerinden veriyi çekiyoruz
    }
    
    // Doktoru günceller
    public boolean updateDoctor(Long id, Doctor doctor) {
		if (doctorDao.existsById(id)) { // güncellenecek doktorun ID'sinin veritabanında olup olmadığını kontrol et
	        Doctor existingDoctor = doctorDao.findById(id).orElseThrow(); // veritabanından mevcut doktor bilgilerini getir, bulunamazsa hata fırlat
	        // Güncellenmiş bilgileri set et
	        existingDoctor.setHospital(doctor.getHospital());
	        existingDoctor.setSpecialization(doctor.getSpecialization());
	        existingDoctor.setWorkingDays(doctor.getWorkingDays());
	        existingDoctor.setWorkingHours(doctor.getWorkingHours());
	        doctorDao.save(existingDoctor); // Güncellenen bilgileri veritabanına kaydet
	        return true;
	    }
		return false;
	}
	
}
