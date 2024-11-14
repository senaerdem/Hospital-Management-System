package com.java.loginReg.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.loginReg.entities.Appointment;

public interface AppointmentDao extends JpaRepository<Appointment, Long> {

}
