package com.java.loginReg.entities;

public class UserDto {
	
	private String email;
	private String password;
	private Role role;
	private Gender gender;
	private String firstName;
	private String lastName;
	
	public UserDto(String email, String password, Role role, Gender gender, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
