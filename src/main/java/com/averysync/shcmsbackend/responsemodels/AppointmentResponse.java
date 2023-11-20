package com.averysync.shcmsbackend.responsemodels;

import com.averysync.shcmsbackend.entity.Doctor;
import lombok.Data;

@Data
public class AppointmentResponse {
    private Doctor doctor;

    private int daysLeft;

    public AppointmentResponse() {
    }

    public AppointmentResponse(Doctor doctor, int daysLeft) {
        this.doctor = doctor;
        this.daysLeft = daysLeft;
    }
}
