package com.averysync.shcmsbackend.dao;

import com.averysync.shcmsbackend.entity.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface QueryRepository extends JpaRepository<Query, Long> {
    Page<Query> findByUserEmail(@RequestParam("user_email") String userEmail, Pageable pageable);
    Page<Query> findByClosed(@RequestParam("closed") boolean closed, Pageable pageable);
}
