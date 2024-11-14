package com.java.loginReg.dataAccess;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.loginReg.entities.Doctor;
import com.java.loginReg.entities.User;

public interface DoctorDao extends JpaRepository<Doctor, Long> {

	Doctor findByUserEmail(String email);
	
	List<Doctor> findBySpecialization(String specialization);
	
	Optional<Doctor> findByUser(User user);
}
