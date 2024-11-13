package com.java.loginReg.dataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.loginReg.entities.Doctor;

public interface DoctorDao extends JpaRepository<Doctor, Long> {

	Doctor findByUserEmail(String email);
}
