package com.java.loginReg.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
	@Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "blood_type")
    private String blood;
    
    @Column(name = "medical_history")
    private String medicalHistory;  
    
    @Column(name = "allergies")
    private String allergies;

	public Patient() {
		super();
	}

	public Patient(Long id, String gender, String phoneNumber,
			LocalDate dateOfBirth, String blood, String medicalHistory, String allergies) {
		super();
		this.id = id;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.blood = blood;
		this.medicalHistory = medicalHistory;
		this.allergies = allergies;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

}
