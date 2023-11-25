package com.averysync.shcmsbackend.service;

import com.averysync.shcmsbackend.dao.AppointmentRepository;
import com.averysync.shcmsbackend.dao.DoctorRepository;
import com.averysync.shcmsbackend.dao.ReviewRepository;
import com.averysync.shcmsbackend.entity.Doctor;
import com.averysync.shcmsbackend.requestmodels.AddDoctorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AdminService {

    private DoctorRepository doctorRepository;
    private ReviewRepository reviewRepository;
    private AppointmentRepository appointmentRepository;

    @Autowired
    public AdminService (DoctorRepository doctorRepository,
                         ReviewRepository reviewRepository,
                         AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.reviewRepository = reviewRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public void addMoreDoctorAppointmentAvailability(Long doctorId) throws Exception {

        Optional<Doctor> doctor = doctorRepository.findById(doctorId);

        if (!doctor.isPresent()) {
            throw new Exception("DOCTOR NOT EXIST");
        }

        doctor.get().setAppointmentsAvailable(doctor.get().getAppointmentsAvailable() + 1);
        doctor.get().setAppointments(doctor.get().getAppointments() + 1);

        doctorRepository.save(doctor.get());
    }

    public void reduceDoctorAppointmentAvailability(Long doctorId) throws Exception {

        Optional<Doctor> doctor = doctorRepository.findById(doctorId);

        if (!doctor.isPresent() || doctor.get().getAppointmentsAvailable() <= 0 || doctor.get().getAppointments() <= 0) {
            throw new Exception("DOCTOR NOT EXIST or Appointment Availability Locked");
        }

        doctor.get().setAppointmentsAvailable(doctor.get().getAppointmentsAvailable() - 1);
        doctor.get().setAppointments(doctor.get().getAppointments() - 1);

        doctorRepository.save(doctor.get());
    }

    public void addMoreDoctor(AddDoctorRequest addDoctorRequest) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(addDoctorRequest.getFirstName());
        doctor.setLastName(addDoctorRequest.getLastName());
        doctor.setRole(addDoctorRequest.getRole());
        doctor.setAppointments(addDoctorRequest.getAppointments());
        doctor.setAppointmentsAvailable(addDoctorRequest.getAppointments());
        doctor.setDept(addDoctorRequest.getDept());
        doctor.setImg(addDoctorRequest.getImg());
        doctorRepository.save(doctor);
    }

    public void removeDoctor(Long doctorId) throws Exception {

        Optional<Doctor> doctor = doctorRepository.findById(doctorId);

        if (!doctor.isPresent()) {
            throw new Exception("DOCTOR NOT EXIST");
        }

        doctorRepository.delete(doctor.get());
        appointmentRepository.deleteByDoctorId(doctorId);
        reviewRepository.deleteByDoctorId(doctorId);
    }
}
