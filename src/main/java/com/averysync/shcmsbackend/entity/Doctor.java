package com.averysync.shcmsbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "doctor")
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role")
    private String role;

    @Column(name = "appointments")
    private int appointments;

    @Column(name = "appointments_avl")
    private int appointmentsAvailable;

    @Column(name = "dept")
    private String dept;

    @Column(name = "img")
    private String img;
}
