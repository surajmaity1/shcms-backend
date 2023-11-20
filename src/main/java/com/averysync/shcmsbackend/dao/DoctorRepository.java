package com.averysync.shcmsbackend.dao;

import com.averysync.shcmsbackend.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findByFirstNameContaining(@RequestParam("firstName") String firstName, Pageable pageable);
    Page<Doctor> findByDept(@RequestParam("dept") String dept, Pageable pageable);

    @Query("select o from Doctor o where id in :doctor_ids")
    List<Doctor> findDoctorsByDoctorIds(@Param("doctor_ids")List<Long> doctorId);
}
