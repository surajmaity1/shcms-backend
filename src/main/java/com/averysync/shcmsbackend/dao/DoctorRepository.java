package com.averysync.shcmsbackend.dao;

import com.averysync.shcmsbackend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
