package com.java.loginReg.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.loginReg.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	
}
