package com.java.loginReg.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.loginReg.entities.Doctor;

public interface DoctorDao extends JpaRepository<Doctor, Long> {

}
