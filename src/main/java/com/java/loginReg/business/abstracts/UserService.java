package com.java.loginReg.business.abstracts;

import org.springframework.stereotype.Service;

import com.java.loginReg.entities.User;
import com.java.loginReg.entities.UserDto;

@Service
public interface UserService {

	User save(UserDto userDto);
}
