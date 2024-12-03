package com.java.loginReg.business.abstracts;

import java.util.List;

import com.java.loginReg.entities.Specialization;
import com.java.loginReg.entities.SpecializationRequestDto;

public interface SpecializationService {

	Specialization addSpecialization(Specialization specialization);
	
	List<Specialization> getAllSpecializations();
	
	Specialization findByName(String name);
	
	boolean deleteSpecialization(Long id);
	
	boolean updateSpecialization(Long id, SpecializationRequestDto specializationRequestDto);
}
