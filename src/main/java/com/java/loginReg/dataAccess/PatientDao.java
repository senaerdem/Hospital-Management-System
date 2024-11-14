package com.java.loginReg.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.loginReg.entities.Patient;

public interface PatientDao extends JpaRepository<Patient, Long> {

}
