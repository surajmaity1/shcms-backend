package com.averysync.shcmsbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "appointment_history")
@Data
public class AppointmentHistory {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="appointment_date")
    private String appointmentDate;

    @Column(name="cancel_date")
    private String cancelDate;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="role")
    private String role;

    @Column(name="img")
    private String img;

    public AppointmentHistory() {
    }

    public AppointmentHistory(String userEmail, String appointmentDate, String cancelDate, String firstName, String lastName, String role, String img) {
        this.userEmail = userEmail;
        this.appointmentDate = appointmentDate;
        this.cancelDate = cancelDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.img = img;
    }
}
