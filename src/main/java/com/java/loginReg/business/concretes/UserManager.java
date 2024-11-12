package com.java.loginReg.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.loginReg.business.abstracts.UserService;
import com.java.loginReg.dataAccess.UserDao;
import com.java.loginReg.entities.Role;
import com.java.loginReg.entities.User;
import com.java.loginReg.entities.UserDto;

@Service
public class UserManager implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getEmail(), userDto.getPassword(), userDto.getRole(), userDto.getFirstName(), userDto.getLastName());
		return userDao.save(user);
	}

	@Override
	public boolean authenticate(String email, String password, Role role) {
		User user = userDao.findByEmail(email);
        return user != null && user.getPassword().equals(password) && user.getRole() == role;
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public boolean deleteUser(Long id) {
		if (userDao.existsById(id)) {
			userDao.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUser(Long id, User user) {
		if (userDao.existsById(id)) {
            User existingUser = userDao.findById(id).orElseThrow();
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            userDao.save(existingUser);
            return true;
        }
		return false;
	}

}
