package com.java.loginReg.entities;

public class DoctorDto {

	private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    
    private String workingDays;
    private String workingHours;
    private String hospital;
    private String specialization;
    
	public DoctorDto(String firstName, String lastName, String email, String password, Role role, String workingDays,
			String workingHours, String hospital, String specialization) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.workingDays = workingDays;
		this.workingHours = workingHours;
		this.hospital = hospital;
		this.specialization = specialization;
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
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
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	@Override
	public String toString() {
	    return "DoctorDto{" +
	           "firstName='" + firstName + '\'' +
	           ", lastName='" + lastName + '\'' +
	           ", email='" + email + '\'' +
	           ", role='" + role + '\'' +
	           ", workingDays='" + workingDays + '\'' +
	           ", workingHours='" + workingHours + '\'' +
	           ", hospital='" + hospital + '\'' +
	           ", specialization='" + specialization + '\'' +
	           '}';
	}

}
