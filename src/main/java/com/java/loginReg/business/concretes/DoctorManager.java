package com.java.loginReg.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.loginReg.business.abstracts.DoctorService;
import com.java.loginReg.dataAccess.DoctorDao;
import com.java.loginReg.dataAccess.HospitalDao;
import com.java.loginReg.dataAccess.SpecializationDao;
import com.java.loginReg.entities.Doctor;
import com.java.loginReg.entities.DoctorDto;
import com.java.loginReg.entities.Specialization;
import com.java.loginReg.entities.User;
import com.java.loginReg.entities.Hospital;

@Service
public class DoctorManager implements DoctorService{
	
	@Autowired
    private DoctorDao doctorDao;
	
	@Autowired
	private HospitalDao hospitalDao;
	
	@Autowired
	private SpecializationDao specializationDao;
	
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
    public boolean updateDoctor(Long id, DoctorDto doctorDto) {
        if (doctorDao.existsById(id)) {
            Doctor existingDoctor = doctorDao.findById(id).orElseThrow();

            // Hospital dönüşümü
            Hospital hospital = hospitalDao.findByName(doctorDto.getHospital())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid hospital name: " + doctorDto.getHospital()));
            existingDoctor.setHospital(hospital);

            // Specialization dönüşümü
            Specialization specialization = specializationDao.findByName(doctorDto.getSpecialization())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid specialization name: " + doctorDto.getSpecialization()));
            existingDoctor.setSpecialization(specialization);

            // Diğer alanları set et
            existingDoctor.setWorkingDays(doctorDto.getWorkingDays());
            existingDoctor.setWorkingHours(doctorDto.getWorkingHours());

            doctorDao.save(existingDoctor);
            return true;
        }
        return false;
    }
	
}
