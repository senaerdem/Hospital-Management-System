package com.java.loginReg.business.abstracts;

import java.util.List;

import com.java.loginReg.entities.Hospital;
import com.java.loginReg.entities.HospitalRequestDto;

public interface HospitalService {

	Hospital addHospital(Hospital hospital);
	
	List<Hospital> getAllHospitals();
	
	boolean deleteHospital(Long id);
	
	boolean updateHospital(Long id, HospitalRequestDto hospitalRequestDto);
}
