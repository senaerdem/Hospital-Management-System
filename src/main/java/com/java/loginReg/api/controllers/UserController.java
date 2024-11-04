package com.java.loginReg.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.java.loginReg.business.abstracts.UserService;
import com.java.loginReg.entities.User;
import com.java.loginReg.entities.UserDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	    System.out.println(userDto); // Hata ayıklama için loglama
	    userService.save(userDto);
	    return "Kayıt Başarılı"; // Sadece bir başarı mesajı döndür
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
	    // Kullanıcıyı doğrulamak için bir servis çağır
	    boolean isAuthenticated = userService.authenticate(userDto.getEmail(), userDto.getPassword());
	    
	    if (isAuthenticated) {
	        return ResponseEntity.ok("Login Successful");
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }
	}

	@GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
