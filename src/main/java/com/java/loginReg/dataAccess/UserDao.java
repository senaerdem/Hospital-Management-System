package com.java.loginReg.dataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.loginReg.entities.Role;
import com.java.loginReg.entities.User;

public interface UserDao extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	Optional<User> findByEmailAndPasswordAndRole(String email, String password, Role role);
	
	boolean existsByRole(Role role);
}
