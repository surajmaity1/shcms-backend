package com.averysync.shcmsbackend.dao;

import com.averysync.shcmsbackend.entity.AppointmentHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface AppointmentHistoryRepository extends JpaRepository<AppointmentHistory, Long> {
    Page<AppointmentHistory> findDoctorsByUserEmail(
            @RequestParam("email") String userEmail,
            Pageable pageable
    );
}
