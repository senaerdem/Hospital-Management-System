package com.java.loginReg.business.concretes;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.loginReg.business.abstracts.UserService;
import com.java.loginReg.dataAccess.AppointmentDao;
import com.java.loginReg.dataAccess.DoctorDao;
import com.java.loginReg.dataAccess.PatientDao;
import com.java.loginReg.dataAccess.SpecializationDao;
import com.java.loginReg.dataAccess.UserDao;
import com.java.loginReg.entities.Appointment;
import com.java.loginReg.entities.Doctor;
import com.java.loginReg.entities.Patient;
import com.java.loginReg.entities.Role;
import com.java.loginReg.entities.Specialization;
import com.java.loginReg.entities.User;
import com.java.loginReg.entities.UserDto;

@Service
public class UserManager implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
    private DoctorDao doctorDao;
	
	@Autowired
	private PatientDao patientDao;
	
	@Autowired
	private AppointmentDao appointmentDao;
	
	@Autowired
	private SpecializationDao specializationDao;
	
	@Override
	public User save(UserDto userDto) {
	    // Eğer rol ADMIN ise, başka bir admin olup olmadığını kontrol edin
	    if (userDto.getRole() == Role.ADMIN) {
	        boolean adminExists = userDao.existsByRole(Role.ADMIN);
	        if (adminExists) {
	            throw new IllegalStateException("Sistemde zaten bir admin mevcut. Yeni bir admin kaydı yapılamaz.");
	        }
	    }

	    // Yeni kullanıcı nesnesi oluşturuluyor
	    User user = new User(userDto.getEmail(), userDto.getPassword(), userDto.getRole(), userDto.getGender(), userDto.getFirstName(), userDto.getLastName());

	    // User tablosuna kaydet
	    user = userDao.save(user); 

	    // Role göre ilgili tabloya da kaydet
	    if (userDto.getRole() == Role.PATIENT) {
	        Patient patient = new Patient();
	        patient.setUser(user);  // Patient ile User'ı ilişkilendir
	        patientDao.save(patient); // Patient tablosuna kaydet
	       
	    } else if (userDto.getRole() == Role.DOCTOR) {
	        Doctor doctor = new Doctor();
	        doctor.setUser(user);
	        
	        if(userDto.getSpecializationId() != null) { // Böyle bir uzmanlık varsa
	        	Specialization specialization = specializationDao.findById(userDto.getSpecializationId()).orElseThrow(() -> new IllegalArgumentException("Specialization not found"));
	        	doctor.setSpecialization(specialization); // Bu uzmanlık bilgisi doctor nesnesine set edilir
	        }
	        doctorDao.save(doctor); // Uzmanlık bilgisi ile birlikte doctor nesnesi veritabanına kaydedilir.
	    }

	    return user;
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
	public void deleteUser(Long userId) {
	    // Kullanıcıyı veritabanında bul
	    User user = userDao.findById(userId).orElseThrow(() -> new IllegalStateException("Kullanıcı bulunamadı"));

	    // Kullanıcının rolüne göre ilişkili randevuları kontrol et
	    if (user.getRole() == Role.PATIENT) {
	    	Patient patient = patientDao.findByUser(user).orElseThrow(() -> new IllegalStateException("Hasta bulunamadı"));

	        if (patient != null) {
	            // Hastaya ait randevuları sil
	            List<Appointment> appointments = appointmentDao.findByPatient(patient);
	            for (Appointment appointment : appointments) {
	                appointmentDao.delete(appointment);
	            }
	            patientDao.delete(patient);  // Patient'ı sil
	        }
	    } else if (user.getRole() == Role.DOCTOR) {
	    	Doctor doctor = doctorDao.findByUser(user).orElseThrow(() -> new IllegalStateException("Doktor bulunamadı"));

	        if (doctor != null) {
	            // Doktora ait randevuları sil
	            List<Appointment> appointments = appointmentDao.findByDoctor(doctor);
	            for (Appointment appointment : appointments) {
	                appointmentDao.delete(appointment);
	            }
	            doctorDao.delete(doctor);  // Doctor'u sil
	        }
	    }

	    // Son olarak User'ı sil
	    userDao.delete(user); 
	}

	@Override
	public boolean updateUser(Long id, User user) {
		if (userDao.existsById(id)) { // 
            User existingUser = userDao.findById(id).orElseThrow();
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            existingUser.setGender(user.getGender());
            userDao.save(existingUser);
            return true;
        }
		return false;
	}
	
	@Override
	public User getUserByEmail(String email) {
	    return userDao.findByEmail(email);
	}

	public Map<String, Object> getUserIdByCredentials(String email, String password, Role role) {
	    Optional<User> user = userDao.findByEmailAndPasswordAndRole(email, password, role);
	    
	    if (user.isPresent()) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("userId", user.get().getId());  // Kullanıcı ID'sini döndür

	        response.put("firstName", user.get().getFirstName());
	        response.put("lastName", user.get().getLastName());

	        if (role == Role.PATIENT) {
	            // Hasta ise, Patient ID'yi ekle
	            Optional<Patient> patient = patientDao.findByUser(user.get());
	            patient.ifPresent(p -> response.put("patientId", p.getId()));
	        } else if (role == Role.DOCTOR) {
	            // Doktor ise, Doctor ID'yi ekle
	            Optional<Doctor> doctor = doctorDao.findByUser(user.get());
	            doctor.ifPresent(d -> response.put("doctorId", d.getId()));
	        }

	        return response;
	    }

	    return null;  // Kullanıcı bulunamazsa null döner
	}
	
	

}
