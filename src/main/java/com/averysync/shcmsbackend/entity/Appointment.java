package com.averysync.shcmsbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "appointment")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "appointment_date")
    private String appointmentDate;

    @Column(name = "cancel_date")
    private String cancelDate;

    @Column(name = "doctor_id")
    private Long doctorId;

    public Appointment() {
    }

    public Appointment(String userEmail, String appointmentDate, String cancelDate, Long doctorId) {
        this.userEmail = userEmail;
        this.appointmentDate = appointmentDate;
        this.cancelDate = cancelDate;
        this.doctorId = doctorId;
    }
}
