package com.averysync.shcmsbackend.controller;

import com.averysync.shcmsbackend.entity.Patient;
import com.averysync.shcmsbackend.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/patient")
@AllArgsConstructor
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public String registerPatient(@RequestBody Patient patient) {
        patientService.registerPatient(patient);
        return "Patient Registered";
    }

    @PutMapping("/{patientId}")
    public String updatePatient(@PathVariable("patientId") Long patientId, @RequestBody Patient inputPatient) {
        Patient updatePatient = patientService.getPatient(patientId);

        updatePatient.setFirstName(inputPatient.getFirstName());
        updatePatient.setLastName(inputPatient.getLastName());
        updatePatient.setGender(inputPatient.getGender());
        updatePatient.setAge(inputPatient.getAge());

        patientService.updatePatient(updatePatient);
        return "Patient Updated";
    }

    @DeleteMapping("/{patientId}")
    public String deletePatient(@PathVariable("patientId") Long patientId) {
        patientService.removePatient(patientId);
        return "Patient Deleted";
    }

    @GetMapping("/{patientId}")
    public Patient getPatient(@PathVariable("patientId") Long patientId) {
        return patientService.getPatient(patientId);
    }

    @GetMapping()
    public List<Patient> getAllPatient() {
        return patientService.getAllPatient();
    }

}
