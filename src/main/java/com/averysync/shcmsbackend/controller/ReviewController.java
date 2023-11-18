package com.averysync.shcmsbackend.controller;

import com.averysync.shcmsbackend.requestmodels.ReviewRequest;
import com.averysync.shcmsbackend.service.ReviewService;
import com.averysync.shcmsbackend.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/shcms/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController (ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/secure/user/doctor")
    public Boolean reviewDoctorByUser(@RequestHeader(value="Authorization") String token,
                                    @RequestParam Long doctorId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        if (userEmail == null) {
            throw new Exception("User Email Not Found");
        }
        return reviewService.userReviewListed(userEmail, doctorId);
    }

    @PostMapping("/secure")
    public void createReview(@RequestHeader(value="Authorization") String token,
                           @RequestBody ReviewRequest reviewRequest) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        if (userEmail == null) {
            throw new Exception("User Email Not Found");
        }
        reviewService.createReview(userEmail, reviewRequest);
    }
}
