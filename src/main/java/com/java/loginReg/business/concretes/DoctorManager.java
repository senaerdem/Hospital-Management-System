package com.java.loginReg.business.concretes;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.loginReg.business.abstracts.DoctorService;
import com.java.loginReg.dataAccess.AppointmentDao;
import com.java.loginReg.dataAccess.DoctorDao;
import com.java.loginReg.dataAccess.HospitalDao;
import com.java.loginReg.dataAccess.SpecializationDao;
import com.java.loginReg.entities.Appointment;
import com.java.loginReg.entities.Doctor;
import com.java.loginReg.entities.DoctorDto;
import com.java.loginReg.entities.Specialization;
import com.java.loginReg.entities.Status;
import com.java.loginReg.entities.User;
import com.java.loginReg.entities.Hospital;

@Service
public class DoctorManager implements DoctorService{
	
	@Autowired
    private DoctorDao doctorDao;
	
	@Autowired
	private HospitalDao hospitalDao;
	
	@Autowired
	private SpecializationDao specializationDao;
	
	@Autowired
	private AppointmentDao appointmentDao;
	
	@Override
	public List<Doctor> getAllDoctors() {
		return doctorDao.findAll();
	}
	
	@Override
	public Doctor save(Doctor doctor) {
		return doctorDao.save(doctor);
	}
	
	// Doktorun ID'sine göre doktoru bulma
    public Doctor getDoctorById(Long id) {
        return doctorDao.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    // Uzmanlık alanına göre doktorları getir
    public List<Doctor> findBySpecialization(Specialization specialization) {
        return doctorDao.findBySpecialization(specialization); // Repository üzerinden veriyi çekiyoruz
    }
    
    // Doktoru günceller
    public boolean updateDoctor(Long id, DoctorDto doctorDto) {
        if (doctorDao.existsById(id)) {
            Doctor existingDoctor = doctorDao.findById(id).orElseThrow();

            // Hastane bilgisi güncelleme
            Hospital hospital = hospitalDao.findByName(doctorDto.getHospital())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid hospital name: " + doctorDto.getHospital()));
            existingDoctor.setHospital(hospital);

            // Uzmanlık bilgisi güncelleme
            Specialization specialization = specializationDao.findByName(doctorDto.getSpecialization())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid specialization name: " + doctorDto.getSpecialization()));
            existingDoctor.setSpecialization(specialization);

            // Çalışma günlerini güncelle
            String oldWorkingDays = existingDoctor.getWorkingDays();
            String newWorkingDays = doctorDto.getWorkingDays();
            existingDoctor.setWorkingDays(newWorkingDays);

            // Çalışma günlerindeki değişikliklere göre randevuları iptal et
            List<String> removedDays = Arrays.stream(oldWorkingDays.split(","))
                    .filter(day -> !newWorkingDays.contains(day))
                    .toList();

            List<Appointment> appointmentsToCancel = appointmentDao.findByDoctorIdAndDayIn(existingDoctor.getId(), removedDays);
            appointmentsToCancel.forEach(appointment -> {
                appointment.setStatus(Status.CANCELLED);
                appointmentDao.save(appointment);
            });

            doctorDao.save(existingDoctor);
            return true;
        }
        return false;
    }

	
}
