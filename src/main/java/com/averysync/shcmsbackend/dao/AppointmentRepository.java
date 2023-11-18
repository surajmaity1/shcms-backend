package com.averysync.shcmsbackend.dao;

import com.averysync.shcmsbackend.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findByUserEmailAndDoctorId(String userEmail, Long doctorId);

    List<Appointment> findDoctorsByUserEmail(String userEmail);
}
