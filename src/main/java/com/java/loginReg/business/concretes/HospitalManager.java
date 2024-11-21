package com.java.loginReg.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.loginReg.business.abstracts.HospitalService;
import com.java.loginReg.dataAccess.HospitalDao;
import com.java.loginReg.entities.Hospital;

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
}
