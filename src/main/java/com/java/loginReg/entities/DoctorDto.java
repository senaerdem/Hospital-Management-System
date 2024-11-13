package com.java.loginReg.entities;

public class DoctorDto {

	private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;  // Role (doctor)
    
    // Diğer doktor bilgileri (isteğe bağlı)
    private String workingDays;
    private String workingHours;
    private String hospital;
    private String specialty;
    
	public DoctorDto(String firstName, String lastName, String email, String password, String role, String workingDays,
			String workingHours, String hospital, String specialty) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.workingDays = workingDays;
		this.workingHours = workingHours;
		this.hospital = hospital;
		this.specialty = specialty;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}
	public String getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
}
