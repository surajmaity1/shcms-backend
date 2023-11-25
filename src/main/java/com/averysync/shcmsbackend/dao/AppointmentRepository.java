package com.averysync.shcmsbackend.dao;

import com.averysync.shcmsbackend.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findByUserEmailAndDoctorId(String userEmail, Long doctorId);

    List<Appointment> findDoctorsByUserEmail(String userEmail);

    @Modifying
//    @Query("delete from appointment where doctor_id in :doctor_id")
    void deleteByDoctorId(@Param("doctor_id") Long doctorId);
}
