package com.averysync.shcmsbackend.dao;

import com.averysync.shcmsbackend.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByDoctorId(@RequestParam("doctor_id") Long doctorId, Pageable pageable);
    Review findByUserEmailAndDoctorId(String userEmail, Long doctorId);
    @Modifying
//    @Query("delete from review where doctor_id in :doctor_id")
    void deleteByDoctorId(@Param("doctor_id") Long doctorId);
}
