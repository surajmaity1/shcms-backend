package com.averysync.shcmsbackend.service.impl;

import com.averysync.shcmsbackend.entity.Patient;
import com.averysync.shcmsbackend.repository.PatientRepository;
import com.averysync.shcmsbackend.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public String registerPatient(Patient patient) {
        patientRepository.save(patient);
        return "REGISTERED";
    }

    @Override
    public String updatePatient(Patient patient) {
        patientRepository.save(patient);
        return "UPDATED";
    }

    @Override
    public String removePatient(Long patientId) {
        patientRepository.deleteById(patientId);
        return "DELETED";
    }

    @Override
    public Patient getPatient(Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);

        if (patient.isEmpty()) {
            // TODO: Implement Exception Handling
        }
        return patient.get();
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }
}
