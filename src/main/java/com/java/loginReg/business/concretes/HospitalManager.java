package com.java.loginReg.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.loginReg.business.abstracts.HospitalService;
import com.java.loginReg.dataAccess.HospitalDao;
import com.java.loginReg.entities.Hospital;
import com.java.loginReg.entities.HospitalRequestDto;
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
	
	// Hastane silmek için method
	@Override
	public boolean deleteHospital(Long id) {
		if (hospitalDao.existsById(id)) { // Veritabanında hastane bulunduysa
			hospitalDao.deleteById(id); // Hastane silinir
			return true;
		}
		return false;
	}
	
	//Hastane güncellemek için method
	@Override
	public boolean updateHospital(Long id, HospitalRequestDto hospitalRequestDto) {
		Hospital existingHospital = hospitalDao.findById(id).orElseThrow(); // Veritabanındaki mevcut hastane bilgilerini al
		if (existingHospital != null) { // Eğer hastane veritabanında mevcutsa
            existingHospital.setName(hospitalRequestDto.getName()); // Yeni isimle güncelle
            existingHospital.setCity(hospitalRequestDto.getCity()); // Yeni şehirle güncelle
            hospitalDao.save(existingHospital); // Güncellenmiş hastane kaydını kaydet
            return true;
        }
		return false;
	}
}
