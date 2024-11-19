package com.java.loginReg.business.abstracts;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.java.loginReg.entities.Role;
import com.java.loginReg.entities.User;
import com.java.loginReg.entities.UserDto;

@Service
public interface UserService {
	
	List<User> getAllUsers();
	
	User save(UserDto userDto);
	
	boolean authenticate(String email, String password, Role role);
	
	void deleteUser(Long userId);
	
	boolean updateUser(Long id, User user);
	
	User getUserByEmail(String email);

	Map<String, Object> getUserIdByCredentials(String email, String password, Role role);
}
