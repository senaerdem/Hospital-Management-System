package com.java.loginReg.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.loginReg.entities.User;

public interface UserDao extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
