package com.averysync.shcmsbackend.dao;

import com.averysync.shcmsbackend.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findByFirstNameContaining(@RequestParam("firstName") String firstName, Pageable pageable);
    Page<Doctor> findByDept(@RequestParam("dept") String dept, Pageable pageable);
}
