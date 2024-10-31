package com.java.loginReg.business.concretes;

import com.java.loginReg.business.abstracts.UserService;
import com.java.loginReg.dataAccess.UserDao;
import com.java.loginReg.entities.User;
import com.java.loginReg.entities.UserDto;

public class UserManager implements UserService {

	private UserDao userDao;
	
	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getEmail(), userDto.getPassword(), userDto.getRole(), userDto.getFirstName(), userDto.getLastName());
		return null;
	}

}
