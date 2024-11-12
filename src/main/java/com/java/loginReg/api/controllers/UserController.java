package com.java.loginReg.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.java.loginReg.business.abstracts.UserService;
import com.java.loginReg.entities.Role;
import com.java.loginReg.entities.User;
import com.java.loginReg.entities.UserDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService; 
	
	@GetMapping("/registration")
	public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
		return "register";
	}
	
	@PostMapping("/registration")
	public String saveUser(@RequestBody UserDto userDto) {
	    System.out.println(userDto);
	    userService.save(userDto);
	    return "Kayıt Başarılı";
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
	    boolean isAuthenticated = userService.authenticate(userDto.getEmail(), userDto.getPassword(), userDto.getRole());
	    
	    if (isAuthenticated) {
	        switch (userDto.getRole()) {
	            case ADMIN:
	                return ResponseEntity.ok("Admin Home Page");
	            case DOCTOR:
	                return ResponseEntity.ok("Doctor Home Page");
	            case PATIENT:
	                return ResponseEntity.ok("Patient Home Page");
	            default:
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid role");
	        }
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials or role");
	    }
	}

	@GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam Role role) {
		if (role != Role.ADMIN) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		boolean isDeleted = userService.deleteUser(id);
		if(isDeleted) {
			return ResponseEntity.ok("User deleted successfully!");
		} else {
			return ResponseEntity.status(400).body("User not found!");
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
		boolean isUpdated = userService.updateUser(id, user);
		if(isUpdated) {
			return ResponseEntity.ok("User updated successfully!");
		} else {
			return ResponseEntity.status(400).body("User not found!");
		}
	}
}
