package com.averysync.shcmsbackend.service;

import com.averysync.shcmsbackend.entity.Patient;

import java.util.List;

public interface PatientService {
    String registerPatient(Patient patient);
    String updatePatient(Patient patient);
    String removePatient(Long patientId);

    Patient getPatient(Long patientId);
    List<Patient> getAllPatient();
}
