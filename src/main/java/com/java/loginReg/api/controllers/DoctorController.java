package com.java.loginReg.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.loginReg.business.abstracts.DoctorService;
import com.java.loginReg.dataAccess.DoctorDao;
import com.java.loginReg.dataAccess.UserDao;
import com.java.loginReg.entities.Doctor;
import com.java.loginReg.entities.DoctorDto;
import com.java.loginReg.entities.Role;
import com.java.loginReg.entities.User;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorController {
	@Autowired
    private DoctorService doctorService;
	
	@Autowired
    private DoctorDao doctorDao;

    @Autowired
    private UserDao userDao;
	
	@GetMapping("/all")
    public ResponseEntity<List<Doctor>> getAllDoctors(@RequestParam Role role) {
		if (role != Role.DOCTOR) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }
	
	@PostMapping("/add")
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
		Doctor savedDoctor = doctorService.save(doctor);
		return ResponseEntity.ok(savedDoctor);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
		boolean isUpdated = doctorService.updateDoctor(id, doctor);
		if(isUpdated) {
			return ResponseEntity.ok("User updated successfully!");
		} else {
			return ResponseEntity.status(400).body("User not found!");
		}
	}
	

	@GetMapping("/doctors/{id}")
    public ResponseEntity<Object> getDoctorById(@PathVariable Long id, @RequestParam Role role) {
        if (role != Role.DOCTOR) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)  // 403 Forbidden
                    .body("You do not have permission to view this resource.");
        }

        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)  // 404 Not Found
                    .body("Doctor not found.");
        }

        return ResponseEntity.ok(doctor);  // 200 OK ve doktor bilgisi
    }
	
	
	
	
	
	
	
	
	// Doktor bilgilerini almak için
    @GetMapping("/{id}")
    public DoctorDto getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorDao.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
        User user = doctor.getUser(); // Doktorun kullanıcı bilgilerini alıyoruz
        return new DoctorDto(
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPassword(),
            user.getRole(),
            doctor.getWorkingDays(),
            doctor.getWorkingHours(),
            doctor.getHospital(),
            doctor.getSpecialization()
        );
    }
    
    // Doktor bilgisini güncelleme
    @PutMapping("/{id}")
    public DoctorDto updateDoctor(@PathVariable Long id, @RequestBody DoctorDto doctorDto) {
        Doctor doctor = doctorDao.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
        User user = doctor.getUser();
        
        // Kullanıcı bilgilerini güncelle
        user.setFirstName(doctorDto.getFirstName());
        user.setLastName(doctorDto.getLastName());
        user.setEmail(doctorDto.getEmail());
        user.setPassword(doctorDto.getPassword());
        
        // Doktor bilgilerini güncelle
        doctor.setWorkingDays(doctorDto.getWorkingDays());
        doctor.setWorkingHours(doctorDto.getWorkingHours());
        doctor.setHospital(doctorDto.getHospital());
        doctor.setSpecialization(doctorDto.getSpecialty());
        
        // Veritabanına kaydet
        userDao.save(user);
        doctorDao.save(doctor);

        return new DoctorDto(
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPassword(),
            user.getRole(),
            doctor.getWorkingDays(),
            doctor.getWorkingHours(),
            doctor.getHospital(),
            doctor.getSpecialization()
        );
    }
    
    
    
    
    
 // Giriş yapan doktorun profilini almak için
    @GetMapping("/profile")
    public ResponseEntity<DoctorDto> getDoctorProfile() {
        // Giriş yapan kullanıcının email adresini güvenlik bağlamından alın
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userDao.findByEmail(userEmail);
        if (user == null || user.getRole() != Role.DOCTOR) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        Doctor doctor = doctorDao.findByUserEmail(userEmail);
        DoctorDto doctorDto = new DoctorDto(
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPassword(),
            user.getRole(),
            doctor.getWorkingDays(),
            doctor.getWorkingHours(),
            doctor.getHospital(),
            doctor.getSpecialization()
        );

        return ResponseEntity.ok(doctorDto);
    }

    

    @PutMapping("/updateDoctor")
    public ResponseEntity<String> updateDoctor(@RequestBody DoctorDto doctorDto) {
        // Giriş yapan doktorun e-posta adresini alıyoruz
        String loggedInEmail = doctorDto.getEmail(); // Burayı oturum doğrulama ile alabilirsiniz

        // Doktoru mevcut e-posta ile bul
        Doctor doctor = doctorDao.findByUserEmail(loggedInEmail);

        // Eğer giriş yapan doktor bulunamazsa
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }

        // Kendi bilgilerini güncellemesine izin ver
        User user = doctor.getUser();
        user.setFirstName(doctorDto.getFirstName());
        user.setLastName(doctorDto.getLastName());
        user.setEmail(doctorDto.getEmail());
        user.setPassword(doctorDto.getPassword());
        doctor.setWorkingDays(doctorDto.getWorkingDays());
        doctor.setWorkingHours(doctorDto.getWorkingHours());
        doctor.setHospital(doctorDto.getHospital());
        doctor.setSpecialization(doctorDto.getSpecialty());

        // Güncellenen bilgileri kaydet
        userDao.save(user);
        doctorDao.save(doctor);

        return ResponseEntity.ok("Doctor information updated successfully");
    }

    // Uzmanlık alanına göre doktorları getiren GET endpoint
    @GetMapping("/doctors")
    public List<Doctor> getDoctorsBySpecialization(@RequestParam String specialization) {
        return doctorService.findBySpecialization(specialization); // DoctorService'den gelen listi döndürüyoruz
    }


}
