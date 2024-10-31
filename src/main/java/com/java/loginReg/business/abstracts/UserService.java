package com.java.loginReg.business.abstracts;

import com.java.loginReg.entities.User;
import com.java.loginReg.entities.UserDto;

public interface UserService {

	User save(UserDto userDto);
}
