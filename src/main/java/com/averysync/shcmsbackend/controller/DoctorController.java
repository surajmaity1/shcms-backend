package com.averysync.shcmsbackend.controller;

import com.averysync.shcmsbackend.entity.Doctor;
import com.averysync.shcmsbackend.responsemodels.AppointmentResponse;
import com.averysync.shcmsbackend.service.DoctorService;
import com.averysync.shcmsbackend.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("https://main.d5vpw8xlywhxl.amplifyapp.com")
@RestController
@RequestMapping("/shcms/doctors")
public class DoctorController {
    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/secure/currentappointments/count")
    public int currentAppointmentsCount(@RequestHeader(value = "Authorization") String token) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return doctorService.currentAppointmentsCount(userEmail);
    }

    @GetMapping("/secure/isappointment/byuser")
    public Boolean appointmentDoctorByUser(@RequestHeader(value = "Authorization") String token,
                                           @RequestParam Long doctorId) {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return doctorService.appointmentDoctorByUser(userEmail, doctorId);
    }

    @PutMapping("/secure/appointment")
    public Doctor appointmentDoctor(@RequestHeader(value = "Authorization") String token,
                                    @RequestParam Long doctorId) throws Exception{
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return doctorService.appointmentDoctor(userEmail, doctorId);
    }

    @GetMapping("/secure/currentappointments")
    public List<AppointmentResponse> currentAppointments(@RequestHeader(value = "Authorization") String token)  throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        return doctorService.currentAppointments(userEmail);
    }

    @PutMapping("/secure/cancel")
    public void cancelDoctor(@RequestHeader(value = "Authorization") String token,
                             @RequestParam Long doctorId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        doctorService.cancelDoctor(userEmail, doctorId);
    }

    @PutMapping("/secure/reschedule/appointment")
    public void rescheduleAppointment(@RequestHeader(value = "Authorization") String token,
                             @RequestParam Long doctorId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        doctorService.rescheduleAppointment(userEmail, doctorId);
    }
}
