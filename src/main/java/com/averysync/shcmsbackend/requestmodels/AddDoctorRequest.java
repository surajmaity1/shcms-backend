package com.averysync.shcmsbackend.requestmodels;

import lombok.Data;

@Data
public class AddDoctorRequest {

    private String firstName;

    private String lastName;

    private String role;

    private int appointments;

    private String dept;

    private String img;

}
