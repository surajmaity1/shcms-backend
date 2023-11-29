package com.averysync.shcmsbackend.controller;

import com.averysync.shcmsbackend.requestmodels.AddDoctorRequest;
import com.averysync.shcmsbackend.service.AdminService;
import com.averysync.shcmsbackend.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/shcms/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PutMapping("/secure/addmore/doctor/availability")
    public void addMoreDoctorAppointmentAvailability(@RequestHeader(value="Authorization") String token,
                                                     @RequestParam Long doctorId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("ONLY ADMINISTRATION GRANTED");
        }
        adminService.addMoreDoctorAppointmentAvailability(doctorId);
    }

    @PutMapping("/secure/reduce/doctor/availability")
    public void reduceDoctorAppointmentAvailability(@RequestHeader(value="Authorization") String token,
                                                    @RequestParam Long doctorId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("ONLY ADMINISTRATION GRANTED");
        }
        adminService.reduceDoctorAppointmentAvailability(doctorId);
    }

    @PostMapping("/secure/register/doctor")
    public void registerDoctor(@RequestHeader(value="Authorization") String token,
                               @RequestBody AddDoctorRequest addDoctorRequest) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("ONLY ADMINISTRATION GRANTED");
        }
        adminService.addMoreDoctor(addDoctorRequest);
    }

    @DeleteMapping("/secure/remove/doctor")
    public void removeDoctor(@RequestHeader(value="Authorization") String token,
                             @RequestParam Long doctorId) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("ONLY ADMINISTRATION GRANTED");
        }
        adminService.removeDoctor(doctorId);
    }

}
