package com.java.loginReg.dataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.loginReg.entities.Specialization;

public interface SpecializationDao extends JpaRepository<Specialization, Long> {

	Optional<Specialization> findByName(String name);
}
