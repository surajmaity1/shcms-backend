package com.averysync.shcmsbackend.service;

import com.averysync.shcmsbackend.dao.DoctorRepository;
import com.averysync.shcmsbackend.dao.ReviewRepository;
import com.averysync.shcmsbackend.entity.Review;
import com.averysync.shcmsbackend.requestmodels.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Transactional
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void createReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
        Review validateReview = reviewRepository.findByUserEmailAndDoctorId(userEmail, reviewRequest.getDoctorId());

        if (validateReview != null) {
            throw new Exception("Review Already Given");
        }

        Review review = new Review();
        review.setDoctorId(reviewRequest.getDoctorId());
        review.setRating(reviewRequest.getRating());
        review.setUserEmail(userEmail);

        if (reviewRequest.getReviewDescription().isPresent()) {
            review.setReviewDescription(
                    reviewRequest.getReviewDescription()
                            .map(Object::toString)
                            .orElse(null)
            );
        }

        review.setDate(Date.valueOf(LocalDate.now()));
        reviewRepository.save(review);
    }

    public Boolean userReviewListed(String userEmail, Long doctorId) {
        Review validateReview = reviewRepository.findByUserEmailAndDoctorId(userEmail, doctorId);
        return validateReview != null;
    }
}
