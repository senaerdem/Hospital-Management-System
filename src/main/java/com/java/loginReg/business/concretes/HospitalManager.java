package com.java.loginReg.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.loginReg.business.abstracts.HospitalService;
import com.java.loginReg.dataAccess.HospitalDao;
import com.java.loginReg.entities.Hospital;
import com.java.loginReg.entities.User;

@Service
public class HospitalManager implements HospitalService {
	
	@Autowired
    private HospitalDao hospitalDao;

	// Hastane kaydı için method
	@Override
	public Hospital addHospital(Hospital hospital) {
		return hospitalDao.save(hospital);
	}

	// Tüm hastanelerin listesini döndüren method
	@Override
    public List<Hospital> getAllHospitals() {
        return hospitalDao.findAll();
    }
	
	@Override
	public boolean deleteHospital(Long id) {
		if (hospitalDao.existsById(id)) { // Veritabanında randevu bulunduysa
			hospitalDao.deleteById(id); // Randevu silinir
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateHospital(Long id, Hospital hospital) {
		if (hospitalDao.existsById(id)) { // 
            Hospital existingHospital = hospitalDao.findById(id).orElseThrow();
            existingHospital.setName(hospital.getName());
            existingHospital.setCity(hospital.getCity());
            hospitalDao.save(existingHospital);
            return true;
        }
		return false;
	}
}
