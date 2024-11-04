package com.java.loginReg.business.abstracts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.loginReg.dataAccess.UserDao;
import com.java.loginReg.entities.User;
import com.java.loginReg.entities.UserDto;

@Service
public interface UserService {
	
	List<User> getAllUsers();
	
	User save(UserDto userDto);
	
	boolean authenticate(String email, String password);
	
}
