package com.averysync.shcmsbackend.repository;

import com.averysync.shcmsbackend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
