package com.java.loginReg.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.loginReg.business.abstracts.AppointmentService;
import com.java.loginReg.dataAccess.AppointmentDao;
import com.java.loginReg.dataAccess.DoctorDao;
import com.java.loginReg.dataAccess.PatientDao;
import com.java.loginReg.entities.Appointment;
import com.java.loginReg.entities.Doctor;
import com.java.loginReg.entities.Patient;
import com.java.loginReg.entities.Status;

@Service
public class AppointmentManager implements AppointmentService {

	@Autowired
    private AppointmentDao appointmentDao ;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private PatientDao patientDao;
    
    @Override
    public Appointment createAppointment(Long doctorId, Long patientId, String day, String time) {
        // İlgili doktor ve hasta verilerini al
        Doctor doctor = doctorDao.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));
        Patient patient = patientDao.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));

        // Yeni randevuyu oluştur
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDay(day);
        appointment.setTime(time);
        appointment.setStatus(Status.PENDING);

        // Randevuyu veritabanına kaydet
        return appointmentDao.save(appointment);
    }
    
    @Override
	public List<Appointment> getAllAppointments() {
		return appointmentDao.findAll();
	}

    @Override
	public boolean deleteAppointment(Long id) {
		if (appointmentDao.existsById(id)) {
			appointmentDao.deleteById(id);
			return true;
		}
		return false;
	}
    
    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentDao.findByDoctorId(doctorId);
    }
    
    // Randevu durumunu güncelleyen metod
    public Appointment updateAppointmentStatus(Long appointmentId, Status status) {
        Appointment appointment = appointmentDao.findById(appointmentId).orElse(null);

        if (appointment != null) {
            appointment.setStatus(status); // Durumu güncelliyoruz
            return appointmentDao.save(appointment); // Güncellenmiş randevuyu kaydediyoruz
        }

        return null;
    }
    
    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        return appointmentDao.findByPatientId(patientId);
    }
}
