package com.averysync.shcmsbackend.service;

import com.averysync.shcmsbackend.dao.AppointmentRepository;
import com.averysync.shcmsbackend.dao.DoctorRepository;
import com.averysync.shcmsbackend.entity.Appointment;
import com.averysync.shcmsbackend.entity.Doctor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class DoctorService {
    private DoctorRepository doctorRepository;
    private AppointmentRepository appointmentRepository;

    public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public Doctor appointmentDoctor(String userEmail, Long doctorId) throws Exception {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        Appointment validateAppointment = appointmentRepository.findByUserEmailAndDoctorId(userEmail, doctorId);

        if (!doctor.isPresent() || validateAppointment != null || doctor.get().getAppointmentsAvailable() <= 0) {
            throw new Exception("Doctor not present OR Appointment Full. Book later");
        }

        doctor.get().setAppointmentsAvailable(doctor.get().getAppointmentsAvailable() - 1);
        doctorRepository.save(doctor.get());

        Appointment appointment = new Appointment(
                userEmail, LocalDate.now().toString(), LocalDate.now().plusDays(1).toString(), doctor.get().getId()
        );

        appointmentRepository.save(appointment);
        return doctor.get();
    }

    public Boolean appointmentDoctorByUser(String userEmail, Long doctorId) {
        Appointment validateAppointment = appointmentRepository.findByUserEmailAndDoctorId(userEmail, doctorId);
        return validateAppointment != null;
    }

    public int currentAppointmentsCount(String userEmail) {
        return appointmentRepository.findDoctorsByUserEmail(userEmail).size();
    }

}
