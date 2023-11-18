package com.averysync.shcmsbackend.requestmodels;

import lombok.Data;

import java.util.Optional;

@Data
public class ReviewRequest {

    private long rating;

    private Long doctorId;

    private Optional<String> reviewDescription;
}
