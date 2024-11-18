package com.java.loginReg.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.loginReg.entities.Appointment;

public interface AppointmentDao extends JpaRepository<Appointment, Long> {

	 List<Appointment> findByDoctorId(Long doctorId);
}
