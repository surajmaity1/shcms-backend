package com.averysync.shcmsbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    private Long doctorId;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    //private List<Department> deptList;
    //private List<Patient> patientList;
}
