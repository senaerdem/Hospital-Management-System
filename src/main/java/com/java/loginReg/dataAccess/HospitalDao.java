package com.java.loginReg.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.loginReg.entities.Hospital;

public interface HospitalDao extends JpaRepository<Hospital, Long> {

}
