package com.java.loginReg.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.loginReg.business.abstracts.SpecializationService;
import com.java.loginReg.dataAccess.SpecializationDao;
import com.java.loginReg.entities.Specialization;

@Service
public class SpecializationManager implements SpecializationService{

	@Autowired
    private SpecializationDao specializationDao;

	// Uzmanlık kaydı için method
	@Override
	public Specialization addSpecialization(Specialization specialization) {
		return specializationDao.save(specialization);
	}

	// Tüm uzmanlıkların listesini döndüren method
	@Override
	public List<Specialization> getAllSpecializations() {
		return specializationDao.findAll();
	}
	
	public Specialization findByName(String name) {
        return specializationDao.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Specialization not found with name: " + name));
    }
	
	@Override
	public boolean deleteSpecialization(Long id) {
		if (specializationDao.existsById(id)) { // Veritabanında uzmanlık bulunduysa
			specializationDao.deleteById(id); // Uzmanlık silinir
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateSpecialization(Long id, Specialization specialization) {
		if (specializationDao.existsById(id)) { // 
            Specialization existingSpecialization = specializationDao.findById(id).orElseThrow();
            existingSpecialization.setName(specialization.getName());
            return true;
        }
		return false;
	}
}
